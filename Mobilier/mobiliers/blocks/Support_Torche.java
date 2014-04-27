package mobiliers.blocks;

import static net.minecraftforge.common.util.ForgeDirection.EAST;
import static net.minecraftforge.common.util.ForgeDirection.NORTH;
import static net.minecraftforge.common.util.ForgeDirection.SOUTH;
import static net.minecraftforge.common.util.ForgeDirection.WEST;

import java.util.List;
import mobiliers.mobilier;
import mobiliers.data.StorcheD;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
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
		return 0;
	}

	/**
	 * Returns true if the block is emitting direct/strong redstone power on the specified side. Args: World, X, Y, Z,
	 * side. Note that the side is reversed - eg it is 1 (up) when checking the bottom of the block.
	 */
	@Override
	public int isProvidingStrongPower(IBlockAccess world, int par2, int par3, int par4, int par5)
	{
		return 0;
	}

	/**
	 * Can this block provide power. Only wire currently seems to have this change based on its state.
	 */
	@Override
	public boolean canProvidePower()
	{
		return false;
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
		TEBase TE = getTileEntityStrict(world, i, j, k);
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
	public void addCollisionBoxesToList(World world, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity)
	{
		TEBase TE = getTileEntityStrict(world, par2, par3, par4);
        int data = BlockProperties.getMetadata(TE);
        int Direction = StorcheD.getRotation(data);
		if(Direction == StorcheD.STORCHE_X_NEG)
		{ //nord
			setBlockBounds(0.6F, 0.2F, 0.4F, 1.0F, 0.4F, 0.6F);
			super.addCollisionBoxesToList(world, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
		} 
		else if(Direction == StorcheD.STORCHE_X_POS)
		{ //sud
			setBlockBounds(0.0F, 0.2F, 0.4F, 0.4F, 0.4F, 0.6F);
			super.addCollisionBoxesToList(world, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
		} 
		else if(Direction == StorcheD.STORCHE_Z_NEG)
		{ //est
			setBlockBounds(0.4F, 0.2F, 0.6F, 0.6F, 0.4F, 1.0F);
			super.addCollisionBoxesToList(world, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
		} 
		else if(Direction == StorcheD.STORCHE_Z_POS)
		{ //ouest
			setBlockBounds(0.4F, 0.2F, 0.0F, 0.6F, 0.4F, 0.4F);
			super.addCollisionBoxesToList(world, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
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
		if (itemstack == null)
		{

		}
		else
		{
			if (getMeta(itemstack))
			{
				world.playSoundEffect(TE.xCoord + 0.5D, TE.yCoord + 0.5D, TE.zCoord + 0.5D, "random.click", 0.3F, 0.6F);
				world.setBlock(TE.xCoord, TE.yCoord, TE.zCoord, mobilier.Storche2, TE.getBlockMetadata(), 2);
				Block bl = world.getBlock(TE.xCoord, TE.yCoord, TE.zCoord);
				bl.onBlockPlaced(world, TE.xCoord, TE.yCoord, TE.zCoord, 0, 0.0f, 0.0f, 0.0f, TE.getBlockMetadata());

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
			}
		}
	}

	/**
	 * ejects contained items into the world, and notifies neighbours of an update, as appropriate
	 */
	@Override
	public void breakBlock(World world, int par2, int par3, int par4, Block par5, int par6)
	{
		TEBase TE = getTileEntityStrict(world, par2, par3, par4);
		int Meta = TE.getBlockMetadata();
		world.notifyBlocksOfNeighborChange(par2, par3, par4, this);
		int j1 = par6;

		if (j1 == 1)
		{
			world.notifyBlocksOfNeighborChange(par2 + 1, par3, par4, this);
		}
		else if (j1 == 5)
		{
			world.notifyBlocksOfNeighborChange(par2 - 1, par3, par4, this);
		}
		else if (j1 == 9)
		{
			world.notifyBlocksOfNeighborChange(par2, par3, par4 + 1, this);
		}
		else if (j1 == 13)
		{
			world.notifyBlocksOfNeighborChange(par2, par3, par4 - 1, this);
		}
		super.breakBlock(world, par2, par3, par4, par5, par6);
	}

	private boolean getMeta(ItemStack itemstack)
	{
		Item i = itemstack.getItem();
		Block j = Blocks.torch;
		String s1 = i.getUnlocalizedName();
		String s2 = j.getUnlocalizedName();
		
		if ( s1.equalsIgnoreCase(s2))
		{
			return true;
		}
		return false;
	}
	
	@Override
	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase EntityLivingBase, ItemStack par6ItemStack)
	{
		int facing = MathHelper.floor_double((EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		TEBase TE = getTileEntityStrict(world, i, j, k);
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
		world.setBlockMetadataWithNotify(i, j, k, facing, 1);
		BlockProperties.setMetadata(TE, facing);
	}
}
