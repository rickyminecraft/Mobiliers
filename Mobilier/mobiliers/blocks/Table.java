package mobiliers.blocks;

import java.util.List;

import mobiliers.mobilier;
import mobiliers.data.TableD;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import carpentersblocks.block.BlockCoverable;
import carpentersblocks.tileentity.TEBase;
import carpentersblocks.util.BlockProperties;

public class Table extends BlockCoverable
{
	public Table(Material material)
	{
		super(material);
	}
	
	/**
	 * Alter type.
	 */
	@Override
	protected boolean onHammerLeftClick(TEBase TE, EntityPlayer entityPlayer)
	{
		int data = BlockProperties.getMetadata(TE);
		int rotation = TableD.getRotation(data);
		if (++rotation > TableD.ROTATE_1)
			rotation = TableD.ROTATE_0;
		TableD.setRotation(TE, rotation);
		
		return true;
	}

	@Override
	/**
	 * Alternate between table style.
	 */
	protected boolean onHammerRightClick(TEBase TE, EntityPlayer entityPlayer)
	{
		int data = BlockProperties.getMetadata(TE);
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
		TEBase TE = getTileEntityStrict(blockAccess, x, y, z);

		int data = BlockProperties.getMetadata(TE);
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
	
//	/**
//	 * Will return stairs boundaries for data.
//	 * @param flag 
//	 */
//	public float[] genBounds(int box, int data)
//	{
//		++box;
//		switch (data)
//		{
//
//		case TableD.TYPE_NORMAL:
//			switch (box)
//			{
//			case 1:
//				return new float[] { 0.0F, 0.0F, 0.2F, 0.2F, 1.0F, 1.0F };
//			case 2:
//				return new float[] { 0.8F, 0.0F, 0.2F, 1.0F, 1.0F, 1.0F };
//			case 3:
//				return new float[] { 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.2F };
//			case 4:
//				return new float[] { 0.2F, 0.0F, 0.8F, 0.8F, 1.0F, 1.0F };
//			default:	
//			}
//			break;
//		case TableD.TYPE_GLASS:
//			switch (box)
//			{
//			case 1:
//				return new float[] { 0.0F, 0.0F, 0.0F, 1.0F, 0.2F, 1.0F };
//			case 2:
//				return new float[] { 0.0F, 0.8F, 0.0F, 1.0F, 1.0F, 1.0F };
//			case 3:
//				return new float[] { 0.0F, 0.2F, 0.0F, 1.0F, 0.8F, 0.2F };
//			case 4:
//				return new float[] { 0.0F, 0.2F, 0.8F, 1.0F, 0.8F, 1.0F };
//			default:	
//			}
//			break;
//		}
//
//		return null;
//	}
//	
//	@Override
//	/**
//	 * Ray traces through the blocks collision from start vector to end vector returning a ray trace hit. Args: world,
//	 * x, y, z, startVec, endVec
//	 */
//	public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 startVec, Vec3 endVec)
//	{
//		TEBase TE = getTileEntityStrict(world, x, y, z);
//		int data = BlockProperties.getMetadata(TE);
//		MovingObjectPosition finalTrace = null;
//
//		double currDist = 0.0D;
//		double maxDist = 0.0D;
//
//		// Determine if ray trace is a hit on stairs
//		for (int box = 0; box < 5; ++box)
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

		int data = BlockProperties.getMetadata(TE) & 0xC >>2;

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
		return mobilier.TableRenderID;
	}
}
