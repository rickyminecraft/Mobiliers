package mobiliers.blocks;

import java.util.List;

import mobiliers.mobilier;
import mobiliers.data.EscaliersD;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import carpentersblocks.CarpentersBlocks;
import carpentersblocks.block.BlockBase;
import carpentersblocks.tileentity.TECarpentersBlock;
import carpentersblocks.util.BlockProperties;

public class Escaliers extends BlockBase
{
	public Escaliers(int blockID)
	{
		super(blockID, Material.wood);
		this.setHardness(0.2F);
		this.setUnlocalizedName("Escaliers");
		this.setCreativeTab(CarpentersBlocks.tabCarpentersBlocks);
		this.setTextureName("carpentersblocks:general/generic");
	}
	
	@Override
	/**
	 * Alter type.
	 */
	protected boolean onHammerLeftClick(TECarpentersBlock TE, EntityPlayer entityPlayer)
	{
		int data = BlockProperties.getData(TE);
		if (++data > EscaliersD.ESCALIER_Z_POS)
			data = EscaliersD.ESCALIER_X_NEG;
		BlockProperties.setData(TE, data);
		return true;
	}

	@Override
	/**
	 * Alternate between full 1m cube and slab.
	 */
	protected boolean onHammerRightClick(TECarpentersBlock TE, EntityPlayer entityPlayer, int side)
	{
		return false;
	}
	
	/**
	 * Will return stairs boundaries for data.
	 * @param flag 
	 */
	public float[] genBounds(int box, int data)
	{
		++box;
		switch (data)
		{
			case EscaliersD.ESCALIER_X_NEG:
				switch (box)
				{
					case 1:
						return new float[] { 0.0F, 0.4F, 0.0F, 0.5F, 0.5F, 1.0F };
					case 2:
						return new float[] { 0.5F, 0.9F, 0.0F, 1.0F, 1.0F, 1.0F };
				}
			case EscaliersD.ESCALIER_X_POS:
				switch (box)
				{
					case 1:
						return new float[] { 0.5F, 0.4F, 0.0F, 1.0F, 0.5F, 1.0F };
					case 2:
						return new float[] { 0.0F, 0.9F, 0.0F, 0.5F, 1.0F, 1.0F };
				}
			case EscaliersD.ESCALIER_Z_NEG:
				switch (box)
				{
					case 1:
						return new float[] { 0.0F, 0.4F, 0.0F, 1.0F, 0.5F, 0.5F };
					case 2:
						return new float[] { 0.0F, 0.9F, 0.5F, 1.0F, 1.0F, 1.0F };
				}
			case EscaliersD.ESCALIER_Z_POS:
				switch (box)
				{
					case 1:
						return new float[] { 0.0F, 0.4F, 0.5F, 1.0F, 0.5F, 1.0F };
					case 2:
						return new float[] { 0.0F, 0.9F, 0.0F, 1.0F, 1.0F, 0.5F };
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
		TECarpentersBlock TE = (TECarpentersBlock)world.getBlockTileEntity(x, y, z);

		int data = BlockProperties.getData(TE);
		data &= 7;
		for (int box = 0; box < 2; ++box) 
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
	 * Ray traces through the blocks collision from start vector to end vector returning a ray trace hit. Args: world,
	 * x, y, z, startVec, endVec
	 */
	public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 startVec, Vec3 endVec)
	{
		TECarpentersBlock TE = (TECarpentersBlock)world.getBlockTileEntity(x, y, z);

		MovingObjectPosition finalTrace = null;

		int data = BlockProperties.getData(TE);

		double currDist = 0.0D;
		double maxDist = 0.0D;

		// Determine if ray trace is a hit on stairs
		for (int box = 0; box < 4; ++box)
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
	 * Called when the block is placed in the world.
	 * Uses cardinal direction to adjust metadata if player clicks top or bottom face of block.
	 */
	public void auxiliaryOnBlockPlacedBy(TECarpentersBlock TE, World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack)
	{
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
		BlockProperties.setData(TE, facing);
	}
	
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
		return mobilier.EscalierRenderID;
	}
}
