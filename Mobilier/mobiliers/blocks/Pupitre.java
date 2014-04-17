package mobiliers.blocks;

import java.util.List;

import mobiliers.mobilier;
import mobiliers.data.PupitreD;
import mobiliers.tileEntity.TileEntityBook;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import carpentersblocks.block.BlockCoverable;
import carpentersblocks.tileentity.TEBase;
import carpentersblocks.util.BlockProperties;

public class Pupitre extends BlockCoverable
{

	public Pupitre(Material material)
	{
		super(material);
	}

	@Override
	/**
	 * Toggles post.
	 */
	protected boolean onHammerLeftClick(TEBase TE, EntityPlayer entityPlayer)
	{
//		int data = BlockProperties.getMetadata(TE);
//		int Rotation = CreuxD.getRotation(data);
//		if (++Rotation > CreuxD.CREUX_Z_POS)
//		{
//			Rotation = CreuxD.CREUX_X_NEG;
//		}
//		BancD.setRotation(TE, Rotation);
		return false;
	}

	@Override
	/**
	 * Alters barrier type or sub-type.
	 */
	protected boolean onHammerRightClick(TEBase TE, EntityPlayer entityPlayer)
	{
//			int data = BlockProperties.getMetadata(TE);
//			int type = BancD.getType(data);
//			if (++type > BancD.BANC)
//			{
//				type = BancD.BORD;
//			}
//			BancD.setType(TE, type);
//			
		return false;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l)
	{
		return true;
	}

//	@Override
//	public boolean getBlocksMovement(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
//	{
//		return true;
//	}

	@Override
	public int getRenderType()
	{
		return mobilier.PupitreID;
	}

	@Override
	public void addCollisionBoxesToList(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity)
	{
		setBlockBounds(0.3F, 0.0F, 0.3F, 0.7F, 1.0F, 0.7F);
		super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase EntityLivingBase, ItemStack par6ItemStack)
	{
		int l = MathHelper.floor_double((EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		TEBase TE = (TEBase) par1World.getTileEntity(par2, par3, par4);
		switch (l)
		{
			case 0:
				l = PupitreD.PUPITRE_Z_POS;
				break;
			case 1:
				l = PupitreD.PUPITRE_X_POS;
				break;
			case 2:
				l = PupitreD.PUPITRE_X_NEG;
				break;
			case 3:
				l = PupitreD.PUPITRE_Z_NEG;
				break;
		}
		BlockProperties.setMetadata(TE, l);
	}

	/**
	 * Called upon block activation (right click on the block.)
	 */
	@Override
	public void postOnBlockActivated(TEBase TE, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ, List<Boolean> altered, List<Boolean> decInv)
	{
		ItemStack itemstack = entityPlayer.inventory.getCurrentItem();
		World world = TE.getWorldObj();
		int Meta = TE.getBlockMetadata();
		if (world.isRemote)
		{
			
		}
		else if (itemstack == null)
		{
			
		}
		else
		{
			if (getMeta(itemstack))
			{
				world.playSoundEffect(TE.xCoord + 0.5D, TE.yCoord + 0.5D, TE.zCoord + 0.5D, "random.click", 0.3F, 0.6F);
				PupitreD.setType(TE, PupitreD.PLEIN);
				
				if (!entityPlayer.capabilities.isCreativeMode && --itemstack.stackSize <= 0)
				{
					entityPlayer.inventory.setInventorySlotContents(entityPlayer.inventory.currentItem, (ItemStack)null);
				}
				world.notifyBlocksOfNeighborChange(TE.xCoord, TE.yCoord, TE.zCoord, this);
				TE.updateEntity();
			}
		}
	}

	private boolean getMeta(ItemStack itemstack)
	{
		if (itemstack.getItem() == Items.book)
		{
			return true;
		}
		return false;
	}

	/**
	 * Returns a new instance of a block's tile entity class. Called on placing the block.
	 */
	@Override
	public TileEntity createNewTileEntity(World par1World, int var2)
	{
		return new TileEntityBook();
	}

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    @Override
	public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6)
    {
    	TEBase TE = getTileEntity(par1World, par2, par3, par4);
    	int Meta = TE.getBlockMetadata();
    	if (PupitreD.getType(Meta) ==  PupitreD.PLEIN)
    	{
    		BlockProperties.ejectEntity(TE, new ItemStack(Items.book, 1, 0));
    	}
    }
}
