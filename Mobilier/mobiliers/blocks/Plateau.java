package mobiliers.blocks;

import static net.minecraftforge.common.util.ForgeDirection.EAST;
import static net.minecraftforge.common.util.ForgeDirection.NORTH;
import static net.minecraftforge.common.util.ForgeDirection.SOUTH;
import static net.minecraftforge.common.util.ForgeDirection.WEST;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mobiliers.mobilier;
import mobiliers.data.PlateauD;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import carpentersblocks.block.BlockCoverable;
import carpentersblocks.tileentity.TEBase;
import carpentersblocks.util.BlockProperties;

public class Plateau extends BlockCoverable
{

	public Plateau(Material material)
	{
		super(material);
	}

	@Override
	/**
	 * Alter type.
	 */
	protected boolean onHammerLeftClick(TEBase TE, EntityPlayer entityPlayer)
	{
		int data = BlockProperties.getMetadata(TE);
		int Rotation = PlateauD.getRotation(data);
		if (++Rotation > PlateauD.PLATEAU_Z_POS)
			Rotation = PlateauD.PLATEAU_X_NEG;
		PlateauD.setRotation(TE, Rotation);
		return true;
	}

	@Override
	/**
	 * Alternate between full 1m cube and slab.
	 */
	protected boolean onHammerRightClick(TEBase TE, EntityPlayer entityPlayer)
	{
		int data = BlockProperties.getMetadata(TE);
		int type = PlateauD.getType(data);
		if (++type > PlateauD.PETIT)
		{
			type = PlateauD.GRAND;
		}
		PlateauD.setType(TE, type);
		
		return true;
	}

	@Override
	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y, z
	 */
	public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int x, int y, int z)
	{
		TEBase TE = getTileEntityStrict(blockAccess, x, y, z);

		int data = BlockProperties.getMetadata(TE);
		float Decalage = 0.0F;
		int type = PlateauD.getType(data);
		if (type == PlateauD.PETIT)
		{
			Decalage = 0.35F;
		}
 		int rotation = PlateauD.getRotation(data);

		float[] bounds = { 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F };

		switch (rotation)
		{
			case PlateauD.PLATEAU_X_NEG:
				bounds = new float[] { 0.0F, 0.0F + Decalage, 0.0F + Decalage, 0.1F, 1.0F - Decalage, 1.0F - Decalage};
				break;
			case PlateauD.PLATEAU_X_POS:
				bounds = new float[] { 0.9F, 0.0F + Decalage, 0.0F + Decalage, 1.0F, 1.0F - Decalage, 1.0F - Decalage };
				break;
			case PlateauD.PLATEAU_Y_NEG:
				bounds = new float[] { 0.0F + Decalage, 0.0F, 0.0F + Decalage, 1.0F - Decalage, 0.1F, 1.0F - Decalage };
				break;
			case PlateauD.PLATEAU_Y_POS:
				bounds = new float[] { 0.0F + Decalage, 0.9F, 0.0F + Decalage, 1.0F - Decalage, 1.0F, 1.0F - Decalage };
				break;
			case PlateauD.PLATEAU_Z_NEG:
				bounds = new float[] { 0.0F + Decalage, 0.0F + Decalage, 0.0F, 1.0F - Decalage, 1.0F - Decalage, 0.1F };
				break;
			case PlateauD.PLATEAU_Z_POS:
				bounds = new float[] { 0.0F + Decalage, 0.0F + Decalage, 0.9F, 1.0F - Decalage, 1.0F - Decalage, 1.0F };
				break;
		}
		this.setBlockBounds(bounds[0], bounds[1], bounds[2], bounds[3], bounds[4], bounds[5]);
	}

	@Override
	/**
	 * Adds all intersecting collision boxes to a list. (Be sure to only add boxes to the list if they intersect the
	 * mask.) Parameters: World, X, Y, Z, mask, list, colliding entity
	 */
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB axisAlignedBB, List list, Entity entity)
	{
		this.setBlockBoundsBasedOnState(world, x, y, z);
		super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);
	}

    @Override
    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
    {
        ForgeDirection dir = ForgeDirection.getOrientation(side);

        if (dir == NORTH) 
        {
            metadata = PlateauD.PLATEAU_Z_POS;
        }
        else if (dir == SOUTH) 
        {
            metadata = PlateauD.PLATEAU_Z_NEG;
        }
        else if (dir == WEST) 
        {
            metadata = PlateauD.PLATEAU_X_POS;
        }
        else if (dir == EAST) 
        {
            metadata = PlateauD.PLATEAU_X_NEG;
        }
        else if (dir == ForgeDirection.UP) //forge as mixed up -> down and down -> up
        {
            metadata = PlateauD.PLATEAU_Y_NEG;
        }
        else if (dir == ForgeDirection.DOWN)
        {
        	metadata = PlateauD.PLATEAU_Y_POS;
        }

        return metadata;
    }
	
	@Override
	/**
	 * Called when the block is placed in the world.
	 */
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack)
	{
		TEBase TE = getTileEntityStrict(world, x, y, z);
		int data = TE.blockMetadata;

		// If shift key is down, skip auto-orientation
		if (!entityLiving.isSneaking())
		{
			/*
			 * Match block type with adjacent type if possible
			 */
			TEBase TE_YN = world.getBlock(x, y - 1, z) == this ? (TEBase)world.getTileEntity(x, y - 1, z) : null;
			TEBase TE_YP = world.getBlock(x, y + 1, z) == this ? (TEBase)world.getTileEntity(x, y + 1, z) : null;
			TEBase TE_XN = world.getBlock(x - 1, y, z) == this ? (TEBase)world.getTileEntity(x - 1, y, z) : null;
			TEBase TE_XP = world.getBlock(x + 1, y, z) == this ? (TEBase)world.getTileEntity(x + 1, y, z) : null;
			TEBase TE_ZN = world.getBlock(x, y, z - 1) == this ? (TEBase)world.getTileEntity(x, y, z - 1) : null;
			TEBase TE_ZP = world.getBlock(x, y, z + 1) == this ? (TEBase)world.getTileEntity(x, y, z + 1) : null;
			
			if (TE_YN != null)
			{
				data = BlockProperties.getMetadata(TE_YN);
			}
			else if (TE_YP != null)
			{
				data = BlockProperties.getMetadata(TE_YP);
			}
			else if (TE_XN != null)
			{
				data = BlockProperties.getMetadata(TE_XN);
			}
			else if (TE_XP != null)
			{
				data = BlockProperties.getMetadata(TE_XP);
			}
			else if (TE_ZN != null)
			{
				data = BlockProperties.getMetadata(TE_ZN);
			}
			else if (TE_ZP != null)
			{
				data = BlockProperties.getMetadata(TE_ZP);
			}
		}
		BlockProperties.setMetadata(TE, data);
	}

	@Override
	/**
	 * Return true if the block is a normal, solid cube.  This
	 * determines indirect power state, entity ejection from blocks, and a few
	 * others.
	 */
	public boolean isBlockNormalCube()
	{
		return false;
	}

	@Override
	/**
	 * Checks if the block is a solid face on the given side, used by placement logic.
	 */
	public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side)
	{
		TEBase TE = getTileEntityStrict(world, x, y, z);

		int data = BlockProperties.getMetadata(TE);
		int type = PlateauD.getType(data);
		if (type == PlateauD.PETIT)
		{
			return false;
		}
		int rotation = PlateauD.getRotation(data);

		if (rotation == PlateauD.PLATEAU_Y_NEG && side == ForgeDirection.DOWN)
		{
			return true;
		}
		else if (rotation == PlateauD.PLATEAU_Y_POS && side == ForgeDirection.UP)
		{
			return true;
		}
		else if (rotation == PlateauD.PLATEAU_Z_NEG && side == ForgeDirection.NORTH)
		{
			return true;
		}
		else if (rotation == PlateauD.PLATEAU_Z_POS && side == ForgeDirection.SOUTH)
		{
			return true;
		}
		else if (rotation == PlateauD.PLATEAU_X_NEG && side == ForgeDirection.WEST)
		{
			return true;
		}
		else if (rotation == PlateauD.PLATEAU_X_POS && side == ForgeDirection.EAST)
		{
			return true;
		}
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	/**
	 * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
	 * coordinates.  Args: blockAccess, x, y, z, side
	 */
	public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int side)
	{
		return true;
	}

	/**
	 * Compares dimensions and coordinates of two opposite sides to determine
	 * whether they share faces.
	 */
	private boolean haveSharedFaces(TEBase TE_adj, TEBase TE_src, int side)
	{
		Block block_src = TE_src.getBlockType();

		block_src.setBlockBoundsBasedOnState(TE_src.getWorldObj(), TE_src.xCoord, TE_src.yCoord, TE_src.zCoord);

		double[] bounds_src = new double[] { block_src.getBlockBoundsMinX(), block_src.getBlockBoundsMinY(), block_src.getBlockBoundsMinZ(),
				block_src.getBlockBoundsMaxX(), block_src.getBlockBoundsMaxY(), block_src.getBlockBoundsMaxZ() };

		setBlockBoundsBasedOnState(TE_adj.getWorldObj(), TE_adj.xCoord, TE_adj.yCoord, TE_adj.zCoord);

		/*
		 * Check whether faces meet and their dimensions match.
		 */
		switch (side)
		{
			case 0:
				/** -Y */
				return this.maxY == 1.0D && bounds_src[1] == 0.0D && this.minX == bounds_src[0] && this.maxX == bounds_src[3] && this.minZ == bounds_src[2]
						&& this.maxZ == bounds_src[5];
			case 1:
				/** +Y */
				return this.minY == 0.0D && bounds_src[4] == 1.0D && this.minX == bounds_src[0] && this.maxX == bounds_src[3] && this.minZ == bounds_src[2]
						&& this.maxZ == bounds_src[5];
			case 2:
				/** -Z */
				return this.maxZ == 1.0D && bounds_src[2] == 0.0D && this.minX == bounds_src[0] && this.maxX == bounds_src[3] && this.minY == bounds_src[1]
						&& this.maxY == bounds_src[4];
			case 3:
				/** +Z */
				return this.minZ == 0.0D && bounds_src[5] == 1.0D && this.minX == bounds_src[0] && this.maxX == bounds_src[3] && this.minY == bounds_src[1]
						&& this.maxY == bounds_src[4];
			case 4:
				/** -X */
				return this.maxX == 1.0D && bounds_src[0] == 0.0D && this.minY == bounds_src[1] && this.maxY == bounds_src[4] && this.minZ == bounds_src[2]
						&& this.maxZ == bounds_src[5];
			case 5:
				/** +X */
				return this.minX == 0.0D && bounds_src[3] == 1.0D && this.minY == bounds_src[1] && this.maxY == bounds_src[4] && this.minZ == bounds_src[2]
						&& this.maxZ == bounds_src[5];
			default:
				return false;
		}
	}

	@Override
	/**
	 * Returns whether block can support cover on side.
	 */
	public boolean canCoverSide(TEBase TE, World world, int x, int y, int z, int side)
	{
		return true;
	}

	@Override
	/**
	 * The type of render function that is called for this block
	 */
	public int getRenderType()
	{
		return mobilier.PlateauRenderID;
	}
}
