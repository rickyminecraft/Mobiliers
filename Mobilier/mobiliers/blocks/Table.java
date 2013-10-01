package mobiliers.blocks;

import java.util.List;

import mobiliers.mobilier;
import mobiliers.data.TableD;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import carpentersblocks.CarpentersBlocks;
import carpentersblocks.block.BlockBase;
import carpentersblocks.tileentity.TECarpentersBlock;
import carpentersblocks.util.BlockProperties;

public class Table extends BlockBase
{
	public Table(int blockID)
	{
		super(blockID, Material.wood);
		this.setHardness(0.2F);
		this.setUnlocalizedName("Table");
		this.setCreativeTab(CarpentersBlocks.tabCarpentersBlocks);
		this.setTextureName("carpentersblocks:stairs/stairs");
	}
	
	/**
	 * Alter type.
	 */
	@Override
	protected boolean onHammerLeftClick(TECarpentersBlock TE, EntityPlayer entityPlayer)
	{
		int data = BlockProperties.getData(TE);
		int tmp = TableD.getRotation(data);
		if (++tmp > TableD.ROTATE_1)
			tmp = TableD.ROTATE_0;
		TableD.setRotation(TE, tmp);
		
		return true;
	}

	@Override
	/**
	 * Alternate between table style.
	 */
	protected boolean onHammerRightClick(TECarpentersBlock TE, EntityPlayer entityPlayer, int side)
	{
		int data = BlockProperties.getData(TE);
		int type = TableD.getType(data);
		if (++type > TableD.TYPE_BAS_GLASS)
		{
			type = TableD.TYPE_NORMAL;
		}
		TableD.setType(TE, type);
		
		return true;
	}
	
	@Override
	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y, z
	 */
	public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int x, int y, int z)
	{
		TECarpentersBlock TE = (TECarpentersBlock) blockAccess.getBlockTileEntity(x, y, z);

		int data = BlockProperties.getData(TE);
		int type = TableD.getType(data);
		int Rotation = TableD.getRotation(data);
		float[] bounds = { 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F };
		switch (type)
		{
			case TableD.TYPE_NORMAL:
			case TableD.TYPE_GLASS:
				bounds = new float[] { 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F };
				break;
			case TableD.TYPE_BAS:
			case TableD.TYPE_BAS_GLASS:
				switch (Rotation)
				{
					case TableD.ROTATE_0:
						bounds = new float[] { 0.2F, 0.0F, 0.0F, 0.8F, 0.5F, 1.0F };
						break;
					case TableD.ROTATE_1:
						bounds = new float[] { 0.0F, 0.0F, 0.2F, 1.0F, 0.5F, 0.8F };
				}
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
	
//	@Override
//	/**
//	 * Ray traces through the blocks collision from start vector to end vector returning a ray trace hit. Args: world,
//	 * x, y, z, startVec, endVec
//	 */
//	public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 startVec, Vec3 endVec)
//	{
//		TECarpentersBlock TE = (TECarpentersBlock)world.getBlockTileEntity(x, y, z);
//
//		MovingObjectPosition finalTrace = null;
//
//		int data = BlockProperties.getData(TE);
//
//		double currDist = 0.0D;
//		double maxDist = 0.0D;
//
//		// Determine if ray trace is a hit on stairs
//		for (int box = 0; box < 4; ++box)
//		{
//			float[] bounds = genBounds(box, data);
//
//			if (bounds != null)
//			{
//				setBlockBounds(bounds[0], bounds[1], bounds[2], bounds[3], bounds[4], bounds[5]);
//				MovingObjectPosition traceResult = super.collisionRayTrace(world, x, y, z, startVec, endVec);
//
//				if (traceResult != null)
//				{
//					currDist = traceResult.hitVec.squareDistanceTo(endVec);
//					if (currDist > maxDist) {
//						finalTrace = traceResult;
//						maxDist = currDist;
//					}
//				}
//			}
//		}
//
//		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
//		return finalTrace;
//	}
	
	@Override
	/**
	 * Return true if the block is a normal, solid cube.  This
	 * determines indirect power state, entity ejection from blocks, and a few
	 * others.
	 */
	public boolean isBlockNormalCube(World world, int x, int y, int z)
	{
		return false;
	}

	@Override
	/**
	 * Checks if the block is a solid face on the given side, used by placement logic.
	 */
	public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side)
	{
		TECarpentersBlock TE = (TECarpentersBlock) world.getBlockTileEntity(x, y, z);

		int data = BlockProperties.getData(TE) & 0xC >>2;

		if (data < TableD.TYPE_BAS && side == ForgeDirection.UP)
		{
			return true;
		}
		return false;
	}

	@Override
	/**
	 * Returns whether block can support cover on side.
	 */
	public boolean canCoverSide(TECarpentersBlock TE, World world, int x, int y, int z, int side)
	{
		return true;
	}

	@Override
	/**
	 * The type of render function that is called for this block
	 */
	public int getRenderType()
	{
		return mobilier.TableRenderID;
	}
}
