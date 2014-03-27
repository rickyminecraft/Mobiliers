package mobiliers.blocks;

import java.util.List;

import mobiliers.mobilier;
import mobiliers.data.FenetreD;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import carpentersblocks.CarpentersBlocks;
import carpentersblocks.block.BlockCoverable;
import carpentersblocks.tileentity.TEBase;
import carpentersblocks.util.BlockProperties;

public class Fenetre extends BlockCoverable
{
	public Fenetre(Material material)
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
		int tmp = FenetreD.getRotation(data);
		if (++tmp > FenetreD.FENETRE_Z)
			tmp = FenetreD.FENETRE_X;
		FenetreD.setRotation(TE, tmp);
		return true;
	}

	@Override
	/**
	 * Alternate between full 1m cube and slab.
	 */
	protected boolean onHammerRightClick(TEBase TE, EntityPlayer entityPlayer)
	{
		int data = BlockProperties.getMetadata(TE);
		int type = FenetreD.getType(data);
		if (++type > FenetreD.TYPE_4)
		{
			type = FenetreD.TYPE_1;
		}
		FenetreD.setType(TE, type);
		return true;
	}

	/**
	 * Will return stairs boundaries for data.
	 * @param flag 
	 */
	public float[] genBounds(int box, int data)
	{
		++box;
		int type = FenetreD.getType(data);
		int rotation = FenetreD.getRotation(data);
		switch (type)
		{
			case FenetreD.TYPE_1:
				switch (rotation)
				{
					case FenetreD.FENETRE_X:
						switch (box)
						{
							case 1:
								return new float[] { 0.0F, 0.0F, 0.0F, 0.3F, 0.7F, 1.0F };
							case 2:
								return new float[] { 0.7F, 0.0F, 0.0F, 1.0F, 0.7F, 1.0F };
							case 3:
								return new float[] { 0.0F, 0.7F, 0.0F, 1.0F, 1.0F, 1.0F };
							default:	
						}
						break;
					case FenetreD.FENETRE_Z:
						switch (box)
						{
							case 1:
								return new float[] { 0.0F, 0.0F, 0.0F, 1.0F, 0.7F, 0.3F };
							case 2:
								return new float[] { 0.0F, 0.0F, 0.7F, 1.0F, 0.7F, 1.0F };
							case 3:
								return new float[] { 0.0F, 0.7F, 0.0F, 1.0F, 1.0F, 1.0F };
							default:	
						}
						break;
				}
			case FenetreD.TYPE_2:
				switch (rotation)
				{
					case FenetreD.FENETRE_X:
						switch (box)
						{
							case 1:
								return new float[] { 0.0F, 0.0F, 0.0F, 0.3F, 0.7F, 1.0F };
							case 2:
								return new float[] { 0.7F, 0.0F, 0.0F, 1.0F, 0.7F, 1.0F };
							default:	
						}
						break;
					case FenetreD.FENETRE_Z:
						switch (box)
						{
							case 1:
								return new float[] { 0.0F, 0.0F, 0.0F, 1.0F, 0.7F, 0.3F };
							case 2:
								return new float[] { 0.0F, 0.0F, 0.7F, 1.0F, 0.7F, 1.0F };
							default:	
						}
						break;
				}
			case FenetreD.TYPE_3:
				switch (rotation)
				{
					case FenetreD.FENETRE_X:
						switch (box)
						{
							case 1:
								return new float[] { 0.0F, 0.0F, 0.0F, 0.35F, 1.0F, 1.0F };
							case 2:
								return new float[] { 0.65F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F };
							default:
						}
						break;
					case FenetreD.FENETRE_Z:
						switch (box)
						{
							case 1:
								return new float[] { 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.35F };
							case 2:
								return new float[] { 0.0F, 0.0F, 0.65F, 1.0F, 1.0F, 1.0F };
							default:
						}
						break;
				}
			case FenetreD.TYPE_4:
				switch (rotation)
				{
					case FenetreD.FENETRE_X:
						switch (box)
						{
							case 1:
								return new float[] { 0.0F, 0.0F, 0.0F, 1.0F, 0.1F, 1.0F };
							case 2:
								return new float[] { 0.0F, 0.9F, 0.0F, 1.0F, 1.0F, 1.0F };
							case 3:
								return new float[] { 0.0F, 0.1F, 0.0F, 0.1F, 0.9F, 1.0F };
							case 4:
								return new float[] { 0.9F, 0.1F, 0.0F, 1.0F, 0.9F, 1.0F };
							default:	
						}
						break;
					case FenetreD.FENETRE_Z:
						switch (box)
						{
							case 1:
								return new float[] { 0.0F, 0.0F, 0.0F, 1.0F, 0.1F, 1.0F };
							case 2:
								return new float[] { 0.0F, 0.9F, 0.0F, 1.0F, 1.0F, 1.0F };
							case 3:
								return new float[] { 0.0F, 0.1F, 0.0F, 1.0F, 0.9F, 0.1F };
							case 4:
								return new float[] { 0.0F, 0.1F, 0.9F, 1.0F, 0.9F, 1.0F };
							default:	
						}
						break;
				}
		}

		return null;
	}
	
	@Override
	/**
	 * Ray traces through the blocks collision from start vector to end vector returning a ray trace hit. Args: world,
	 * x, y, z, startVec, endVec
	 */
	public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 startVec, Vec3 endVec)
	{
		TEBase TE = (TEBase)world.getTileEntity(x, y, z);

		MovingObjectPosition finalTrace = null;

		int data = BlockProperties.getMetadata(TE);

		double currDist = 0.0D;
		double maxDist = 0.0D;

		// Determine if ray trace is a hit on stairs
		for (int box = 0; box < 5; ++box)
		{
			float[] bounds = genBounds(box, data);

			if (bounds != null)
			{
				setBlockBounds(bounds[0], bounds[1], bounds[2], bounds[3], bounds[4], bounds[5]);
				MovingObjectPosition traceResult = super.collisionRayTrace(world, x, y, z, startVec, endVec);

				if (traceResult != null)
				{
					currDist = traceResult.hitVec.squareDistanceTo(endVec);
					if (currDist > maxDist) {
						finalTrace = traceResult;
						maxDist = currDist;
					}
				}
			}
		}

		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		return finalTrace;
	}
	
	@Override
	/**
	 * Adds all intersecting collision boxes to a list. (Be sure to only add boxes to the list if they intersect the
	 * mask.) Parameters: World, X, Y, Z, mask, list, colliding entity
	 */
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB axisAlignedBB, List list, Entity entity)
	{
		TEBase TE = (TEBase)world.getTileEntity(x, y, z);

		int data = BlockProperties.getMetadata(TE);

		for (int box = 0; box < 5; ++box) 
		{
			float[] bounds = genBounds(box, data);
			if (bounds != null)
			{
				this.setBlockBounds(bounds[0], bounds[1], bounds[2], bounds[3], bounds[4], bounds[5]);
				super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);
			}
		}
	}
	
	 @Override
		/**
		 * Called when the block is placed in the world.
		 */
		public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack)
		{
		 	TEBase TE = getTileEntity(world, x, y, z);
	    	int facing = MathHelper.floor_double(entityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
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
				else
				{
					switch (facing)
					{
						case 0:
							facing = FenetreD.FENETRE_X;
							break;
						case 1:
							facing = FenetreD.FENETRE_Z;
							break;
						case 2:
							facing = FenetreD.FENETRE_X;
							break;
						case 3:
							facing = FenetreD.FENETRE_Z;
					}
					data = facing;
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
		TEBase TE = (TEBase) world.getTileEntity(x, y, z);
		
		int data = BlockProperties.getMetadata(TE);
		int type = FenetreD.getType(data);
		int rotation = FenetreD.getRotation(data);

		switch (type)
		{
			case FenetreD.TYPE_1:
				switch (rotation)
				{
					case FenetreD.FENETRE_X:
						if (side == ForgeDirection.UP || side == ForgeDirection.EAST || side == ForgeDirection.WEST)
						{
							return true;
						}
						break;
					case FenetreD.FENETRE_Z:
						if (side == ForgeDirection.UP || side == ForgeDirection.NORTH || side == ForgeDirection.SOUTH)
						{
							return true;
						}
						break;
				}
			case FenetreD.TYPE_2:
				switch (rotation)
				{
					case FenetreD.FENETRE_X:
						if (side == ForgeDirection.EAST || side == ForgeDirection.WEST)
						{
							return true;
						}
						break;
					case FenetreD.FENETRE_Z:
						if (side == ForgeDirection.NORTH || side == ForgeDirection.SOUTH)
						{
							return true;
						}
						break;
				}
			case FenetreD.TYPE_3:
				switch (rotation)
				{
					case FenetreD.FENETRE_X:
						if (side == ForgeDirection.EAST || side == ForgeDirection.WEST)
						{
							return true;
						}
						break;
					case FenetreD.FENETRE_Z:
						if (side == ForgeDirection.NORTH || side == ForgeDirection.SOUTH)
						{
							return true;
						}
						break;
				}
			case FenetreD.TYPE_4:
				switch (rotation)
				{
					case FenetreD.FENETRE_X:
						if (side == ForgeDirection.UP || side == ForgeDirection.EAST || side == ForgeDirection.WEST)
						{
							return true;
						}
						break;
					case FenetreD.FENETRE_Z:
						if (side == ForgeDirection.UP || side == ForgeDirection.NORTH || side == ForgeDirection.SOUTH)
						{
							return true;
						}
						break;
				}
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
		return mobilier.FenetreRenderID;
	}
}
