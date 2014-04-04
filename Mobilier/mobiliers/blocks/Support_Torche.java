package mobiliers.blocks;

import static net.minecraftforge.common.util.ForgeDirection.EAST;
import static net.minecraftforge.common.util.ForgeDirection.NORTH;
import static net.minecraftforge.common.util.ForgeDirection.SOUTH;
import static net.minecraftforge.common.util.ForgeDirection.WEST;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mobiliers.mobilier;
import mobiliers.data.StorcheD;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import carpentersblocks.block.BlockCoverable;
import carpentersblocks.tileentity.TEBase;
import carpentersblocks.util.BlockProperties;

public class Support_Torche extends BlockCoverable
{

	public Support_Torche(Material material)
	{
		super(material);
	}

	@Override
	/**
	 * Toggles post.
	 */
	protected boolean onHammerLeftClick(TEBase TE, EntityPlayer entityPlayer)
	{
		int data = BlockProperties.getMetadata(TE);
		int Rotation = StorcheD.getRotation(data);
		if (++Rotation > StorcheD.STORCHE_Z_POS)
		{
			Rotation = StorcheD.STORCHE_X_NEG;
		}
		StorcheD.setRotation(TE, Rotation);
		return true;
	}

	@Override
	/**
	 * Alters barrier type or sub-type.
	 */
	protected boolean onHammerRightClick(TEBase TE, EntityPlayer entityPlayer)
	{
//		int data = BlockProperties.getMetadata(TE);
//		int type = BancD.getType(data);
//		if (++type > BancD.BANC)
//		{
//			type = BancD.BORD;
//		}
//		BancD.setType(TE, type);
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
//	public int quantityDropped(Random random)
//	{
//		return 1;
//	}

	@Override
	public int getRenderType()
	{
		return mobilier.Support_TorcheID;
	}

	/**
	 * checks to see if you can place this block can be placed on that side of a block: BlockLever overrides
	 */
	@Override
	public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side)
	{
		ForgeDirection dir = ForgeDirection.getOrientation(side);
		return world.getBlock(x - dir.offsetX, y, z - dir.offsetZ).isSideSolid(world, x - dir.offsetX, y, z - dir.offsetZ, dir);
	}

	/**
	 * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
	 */
	@Override
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
	{
		return (par1World.isSideSolid(par2 - 1, par3, par4, EAST)) ||
				(par1World.isSideSolid(par2 + 1, par3, par4, WEST)) ||
				(par1World.isSideSolid(par2, par3, par4 - 1, SOUTH)) ||
				(par1World.isSideSolid(par2, par3, par4 + 1, NORTH));
	}

	/**
	 * Returns true if the block is emitting indirect/weak redstone power on the specified side. If isBlockNormalCube
	 * returns true, standard redstone propagation rules will apply instead and this will not be called. Args: World, X,
	 * Y, Z, side. Note that the side is reversed - eg it is 1 (up) when checking the bottom of the block.
	 */
	@Override
	public int isProvidingWeakPower(IBlockAccess world, int par2, int par3, int par4, int par5)
	{
		TEBase TE = (TEBase) world.getTileEntity(par2, par3, par4);
		int Meta = TE.getBlockMetadata();
		if (StorcheD.getType(Meta) == StorcheD.ALLUME)
		{
			return 15;
		}
		return 0;
	}

	/**
	 * Returns true if the block is emitting direct/strong redstone power on the specified side. Args: World, X, Y, Z,
	 * side. Note that the side is reversed - eg it is 1 (up) when checking the bottom of the block.
	 */
	@Override
	public int isProvidingStrongPower(IBlockAccess world, int par2, int par3, int par4, int par5)
	{
		TEBase TE = (TEBase) world.getTileEntity(par2, par3, par4);
		int Meta = TE.getBlockMetadata();
		if (StorcheD.getType(Meta) == StorcheD.ALLUME)
		{
			return 15;
		}
		return 0;
	}

	/**
	 * Can this block provide power. Only wire currently seems to have this change based on its state.
	 */
	@Override
	public boolean canProvidePower()
	{
		return true;
	}

	/**
	 * Returns the bounding box of the wired rectangular prism to render.
	 */
	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
		this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
		return super.getSelectedBoundingBoxFromPool(par1World, par2, par3, par4);
	}

//	/**
//	 * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
//	 * cleared to be reused)
//	 */
//	@Override
//	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
//	{
//		this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
//		return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
//	}

	public void setBlockBoundsBasedOnState(World world, int i, int j, int k)
	{
		TEBase TE = (TEBase) world.getTileEntity(i, j, k);
		int data = BlockProperties.getMetadata(TE);
		int Direction = StorcheD.getRotation(data);
		if(Direction == StorcheD.STORCHE_X_NEG)
		{ //nord
			setBlockBounds(0.6F, 0.2F, 0.4F, 1.0F, 0.4F, 0.6F);
		} else if(Direction == StorcheD.STORCHE_X_POS)
		{ //sud
			setBlockBounds(0.0F, 0.2F, 0.4F, 0.4F, 0.4F, 0.6F);
		} else if(Direction == StorcheD.STORCHE_Z_NEG)
		{ //est
			setBlockBounds(0.4F, 0.2F, 0.6F, 0.6F, 0.4F, 1.0F);
		} else if(Direction == StorcheD.STORCHE_Z_POS)
		{ //ouest
			setBlockBounds(0.4F, 0.2F, 0.0F, 0.6F, 0.4F, 0.4F);
		}
	}

	/**
	 * Adds all intersecting collision boxes to a list. (Be sure to only add boxes to the list if they intersect the
	 * mask.) Parameters: World, X, Y, Z, mask, list, colliding entity
	 */
	@Override
	public void addCollisionBoxesToList(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity)
	{
		TEBase TE = (TEBase) par1World.getTileEntity(par2, par3, par4);
        int data = BlockProperties.getMetadata(TE);
        int Direction = StorcheD.getRotation(data);
		if(Direction == StorcheD.STORCHE_X_NEG)
		{ //nord
			setBlockBounds(0.6F, 0.2F, 0.4F, 1.0F, 0.4F, 0.6F);
			super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
		} 
		else if(Direction == StorcheD.STORCHE_X_POS)
		{ //sud
			setBlockBounds(0.0F, 0.2F, 0.4F, 0.4F, 0.4F, 0.6F);
			super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
		} 
		else if(Direction == StorcheD.STORCHE_Z_NEG)
		{ //est
			setBlockBounds(0.4F, 0.2F, 0.6F, 0.6F, 0.4F, 1.0F);
			super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
		} 
		else if(Direction == StorcheD.STORCHE_Z_POS)
		{ //ouest
			setBlockBounds(0.4F, 0.2F, 0.0F, 0.6F, 0.4F, 0.4F);
			super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
		}
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
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
		int Direction = StorcheD.getRotation(Meta);
		if (StorcheD.getType(Meta) == StorcheD.ALLUME)
		{

		}
		else if (itemstack == null)
		{

		}
		else
		{
			int i = getMeta(itemstack);
			if (i > 0)
			{
				world.playSoundEffect(TE.xCoord + 0.5D, TE.yCoord + 0.5D, TE.zCoord + 0.5D, "random.click", 0.3F, 0.6F);
				this.setLightLevel(1.0F);
				StorcheD.setType(TE, StorcheD.ALLUME);

				if (!entityPlayer.capabilities.isCreativeMode && --itemstack.stackSize <= 0)
				{
					entityPlayer.inventory.setInventorySlotContents(entityPlayer.inventory.currentItem, (ItemStack)null);
				}
				world.notifyBlocksOfNeighborChange(TE.xCoord, TE.yCoord, TE.zCoord, this);
				if (Direction == StorcheD.STORCHE_X_NEG)
				{
					world.notifyBlocksOfNeighborChange(TE.xCoord + 1, TE.yCoord, TE.zCoord, this);
				}
				else if (Direction == StorcheD.STORCHE_X_POS)
				{
					world.notifyBlocksOfNeighborChange(TE.xCoord - 1, TE.yCoord, TE.zCoord, this);
				}
				else if (Direction == StorcheD.STORCHE_Z_NEG)
				{
					world.notifyBlocksOfNeighborChange(TE.xCoord, TE.yCoord, TE.zCoord + 1, this);
				}
				else if (Direction == StorcheD.STORCHE_Z_POS)
				{
					world.notifyBlocksOfNeighborChange(TE.xCoord, TE.yCoord, TE.zCoord - 1, this);
				}
				TE.updateEntity();
			}
		}
	}

	/**
	 * ejects contained items into the world, and notifies neighbours of an update, as appropriate
	 */
	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6)
	{
		TEBase TE = getTileEntity(par1World, par2, par3, par4);
		int Meta = TE.getBlockMetadata();
		par1World.notifyBlocksOfNeighborChange(par2, par3, par4, this);
		int j1 = par6;

		if (j1 == 1)
		{
			par1World.notifyBlocksOfNeighborChange(par2 + 1, par3, par4, this);
		}
		else if (j1 == 5)
		{
			par1World.notifyBlocksOfNeighborChange(par2 - 1, par3, par4, this);
		}
		else if (j1 == 9)
		{
			par1World.notifyBlocksOfNeighborChange(par2, par3, par4 + 1, this);
		}
		else if (j1 == 13)
		{
			par1World.notifyBlocksOfNeighborChange(par2, par3, par4 - 1, this);
		}
		if (StorcheD.getType(Meta) == StorcheD.ALLUME)
		{
			BlockProperties.ejectEntity(TE, new ItemStack(Block.getBlockFromName("torch"), 1, 0));
		}
		super.breakBlock(par1World, par2, par3, par4, par5, par6);
	}

	private int getMeta(ItemStack itemstack)
	{
		Item i = itemstack.getItem();
		Block j = (Block) Block.blockRegistry.getObject("torch");
		String s1 = i.getUnlocalizedName();
		String s2 = j.getUnlocalizedName();
		
		if ( s1.equalsIgnoreCase(s2))
		{
			return 1;
		}
		return 0;
	}

	@Override
	@SideOnly(Side.CLIENT)

	/**
	 * A randomly called display update to be able to add particles or other items for display
	 */
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		TEBase TE = getTileEntity(par1World, par2, par3, par4);
		int Meta = BlockProperties.getMetadata(TE);
		int Direction = StorcheD.getRotation(Meta);
		double d0 = par2 + 0.5F;
		double d1 = par3 + 0.7F;
		double d2 = par4 + 0.5F;
		double d3 = 0.2199999988079071D;
		double d4 = 0.27000001072883606D;

		if (StorcheD.getType(Meta) == StorcheD.ALLUME)
		{
			if (Direction == StorcheD.STORCHE_X_NEG)
			{
				par1World.spawnParticle("smoke", d0 - d4+0.45D, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
				par1World.spawnParticle("flame", d0 - d4+0.45D, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
			}
			if (Direction == StorcheD.STORCHE_X_POS)
			{
				par1World.spawnParticle("smoke", d0 - d4+0.1D, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
				par1World.spawnParticle("flame", d0 - d4+0.1D, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
			}
			if (Direction == StorcheD.STORCHE_Z_NEG)
			{
				par1World.spawnParticle("smoke", d0 - d4+0.255D, d1 + d3, d2+0.2D, 0.0D, 0.0D, 0.0D);
				par1World.spawnParticle("flame", d0 - d4+0.255D, d1 + d3, d2+0.2D, 0.0D, 0.0D, 0.0D);
			}
			if (Direction == StorcheD.STORCHE_Z_POS)
			{
				par1World.spawnParticle("smoke", d0 - d4+0.255D, d1 + d3, d2-0.2D, 0.0D, 0.0D, 0.0D);
				par1World.spawnParticle("flame", d0 - d4+0.255D, d1 + d3, d2-0.2D, 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@Override
	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase EntityLivingBase, ItemStack par6ItemStack)
	{
		int facing = MathHelper.floor_double((EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		TEBase TE = (TEBase) world.getTileEntity(i, j, k);
		switch (facing)
		{
			case 0:
				facing = StorcheD.STORCHE_Z_NEG;
				break;
			case 1:
				facing = StorcheD.STORCHE_X_POS;
				break;
			case 2:
				facing = StorcheD.STORCHE_Z_POS;
				break;
			case 3:
				facing = StorcheD.STORCHE_X_NEG;
				break;
		}
		BlockProperties.setMetadata(TE, facing);
	}
}
