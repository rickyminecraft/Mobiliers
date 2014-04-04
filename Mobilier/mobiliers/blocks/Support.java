package mobiliers.blocks;

import java.util.List;

import mobiliers.mobilier;
import mobiliers.data.SupportD;
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
import carpentersblocks.block.BlockCoverable;
import carpentersblocks.tileentity.TEBase;
import carpentersblocks.util.BlockProperties;
import carpentersblocks.util.handler.EventHandler;

public class Support extends BlockCoverable
{

	public Support(Material material)
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
		int tmp = data & 8;
		if (SupportD.getPosition(data) == SupportD.SUPPORT_HAUT)
		{
			data &= 7;
			if (++data > SupportD.SUPPORT_Z_POS)
			{
				data = SupportD.SUPPORT_X_NEG;
				tmp = 8;
			}
		}
		if (SupportD.getPosition(data) == SupportD.SUPPORT_BAS)
		{
			data &= 7;
			if (++data > SupportD.SUPPORT_Z_POS)
			{
				data = SupportD.SUPPORT_X_NEG;
				tmp = 0;
			}
		}
		data += tmp;
		BlockProperties.setMetadata(TE, data);
		return true;
	}

	@Override
	/**
	 * Alternate between full 1m cube and slab.
	 */
	protected boolean onHammerRightClick(TEBase TE, EntityPlayer entityPlayer)
	{
		int data = BlockProperties.getMetadata(TE);
		int tmp = data & 8;

		if (SupportD.getPosition(data) == SupportD.SUPPORT_HAUT)
		{
			SupportD.setPosition(TE, SupportD.SUPPORT_BAS);
			data &= 7;
			switch (EventHandler.eventFace)
			{
				case 2:
					data = SupportD.SUPPORT_Z_NEG;
					break;
				case 3:
					data = SupportD.SUPPORT_Z_POS;
					break;
				case 4:
					data = SupportD.SUPPORT_X_NEG;
					break;
				case 5:
					data = SupportD.SUPPORT_X_POS;
					break;
			}
		}
		if (SupportD.getPosition(data) == SupportD.SUPPORT_BAS)
		{
			SupportD.setPosition(TE, SupportD.SUPPORT_HAUT);
			data &= 7;
			switch (EventHandler.eventFace)
			{
				case 2:
					data = SupportD.SUPPORT_Z_NEG;
					break;
				case 3:
					data = SupportD.SUPPORT_Z_POS;
					break;
				case 4:
					data = SupportD.SUPPORT_X_NEG;
					break;
				case 5:
					data = SupportD.SUPPORT_X_POS;
					break;
			}
		}
		data += tmp;
		BlockProperties.setMetadata(TE, data);
		return true;
	}
	
	/**
	 * Will return stairs boundaries for data.
	 * @param flag 
	 */
	public float[] genBounds(int box, int data, int flag)
	{
		++box;
		switch (flag)
		{
			case 0:
				switch (data)
				{
					case SupportD.SUPPORT_X_NEG:
						switch (box)
						{
							case 1:
								return new float[] { 0.0F, 0.9F, 0.45F, 1.0F, 1.0F, 0.55F };
							case 2:
								return new float[] { 0.9F, 0.0F, 0.45F, 1.0F, 0.9F, 0.55F };
						}
					case SupportD.SUPPORT_X_POS:
						switch (box)
						{
							case 1:
								return new float[] { 0.0F, 0.9F, 0.45F, 1.0F, 1.0F, 0.55F };
							case 2:
								return new float[] { 0.0F, 0.0F, 0.45F, 0.1F, 0.9F, 0.55F };
						}
					case SupportD.SUPPORT_Z_NEG:
						switch (box)
						{
							case 1:
								return new float[] { 0.45F, 0.9F, 0.0F, 0.55F, 1.0F, 1.0F };
							case 2:
								return new float[] { 0.45F, 0.0F, 0.9F, 0.55F, 0.9F, 1.0F };
						}
					case SupportD.SUPPORT_Z_POS:
						switch (box)
						{
							case 1:
								return new float[] { 0.45F, 0.9F, 0.0F, 0.55F, 1.0F, 1.0F };
							case 2:
								return new float[] { 0.45F, 0.0F, 0.0F, 0.55F, 0.9F, 0.1F };
						}
				}
			case 8:
				switch (data)
				{
					case SupportD.SUPPORT_X_NEG:
						switch (box)
						{
							case 1:
								return new float[] { 0.0F, 0.0F, 0.45F, 1.0F, 0.1F, 0.55F };
							case 2:
								return new float[] { 0.9F, 0.1F, 0.45F, 1.0F, 0.9F, 0.55F };
						}
					case SupportD.SUPPORT_X_POS:
						switch (box)
						{
							case 1:
								return new float[] { 0.0F, 0.0F, 0.45F, 1.0F, 0.1F, 0.55F };
							case 2:
								return new float[] { 0.0F, 0.1F, 0.45F, 0.1F, 0.9F, 0.55F };
						}
					case SupportD.SUPPORT_Z_NEG:
						switch (box)
						{
							case 1:
								return new float[] { 0.45F, 0.0F, 0.0F, 0.55F, 0.1F, 1.0F };
							case 2:
								return new float[] { 0.45F, 0.1F, 0.9F, 0.55F, 0.9F, 1.0F };
						}
					case SupportD.SUPPORT_Z_POS:
						switch (box)
						{
							case 1:
								return new float[] { 0.45F, 0.0F, 0.0F, 0.55F, 0.1F, 1.0F };
							case 2:
								return new float[] { 0.45F, 0.1F, 0.0F, 0.55F, 0.9F, 0.1F };
						}
				}
		}

		return null;
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
		int flag = data & 8;
		data &= 7;
		for (int box = 0; box < 2; ++box) 
		{
			float[] bounds = genBounds(box, data, flag);
			if (bounds != null)
			{
				this.setBlockBounds(bounds[0], bounds[1], bounds[2], bounds[3], bounds[4], bounds[5]);
				super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);
			}
		}
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
		int flag = data & 8;
		data &= 7;

		double currDist = 0.0D;
		double maxDist = 0.0D;

		// Determine if ray trace is a hit on stairs
		for (int box = 0; box < 4; ++box)
		{
			float[] bounds = genBounds(box, data, flag);

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
	 * Called when the block is placed in the world.
	 * Uses cardinal direction to adjust metadata if player clicks top or bottom face of block.
	 */
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack)
	{
    	TEBase TE = getTileEntity(world, x, y, z);
		int facing = MathHelper.floor_double(entityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		switch (facing)
		{
			case 0:
				facing = 2;
				break;
			case 1:
				facing = 1;
				break;
			case 2:
				facing = 3;
				break;
			case 3:
				facing = 0;
		}
		BlockProperties.setMetadata(TE, facing);
	}
    
	@Override
	/**
	 * Checks if the block is a solid face on the given side, used by placement logic.
	 */
	public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side)
	{
		return false;
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
		return mobilier.SupportRenderID;
	}
}
