package mobiliers.blocks;

import java.util.List;

import mobiliers.mobilier;
import mobiliers.data.EscaliersD;
import mobiliers.data.SupportD;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
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
	public int onHammerLeftClick(TECarpentersBlock TE, EntityPlayer entityPlayer, int data)
	{
		if (++data > EscaliersD.ESCALIER_Z_POS)
			data = EscaliersD.ESCALIER_X_NEG;

		return data;
	}

	@Override
	/**
	 * Alternate between full 1m cube and slab.
	 */
	public int onHammerRightClick(TECarpentersBlock TE, EntityPlayer entityPlayer, int data, int side)
	{
//		if (data == PlateauD.PLATEAU_X_NEG)
//		{
//			switch (side)
//			{
//				case 0:
//					data = PlateauD.PLATEAU_Y_POS;
//					break;
//				case 1:
//					data = PlateauD.PLATEAU_Y_NEG;
//					break;
//				case 2:
//					data = PlateauD.PLATEAU_Z_POS;
//					break;
//				case 3:
//					data = PlateauD.PLATEAU_Z_NEG;
//					break;
//				case 4:
//					data = PlateauD.PLATEAU_X_POS;
//					break;
//			}
//		}
//		else
//		{
//			data = PlateauD.PLATEAU_X_NEG;
//		}
//
//		return data;
		return 0;
	}
	
	@Override
	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y, z
	 */
	public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int x, int y, int z)
	{
		TECarpentersBlock TE = (TECarpentersBlock) blockAccess.getBlockTileEntity(x, y, z);

		int data = BlockProperties.getData(TE);

		float[] bounds = { 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F };
		this.setBlockBounds(bounds[0], bounds[1], bounds[2], bounds[3], bounds[4], bounds[5]);
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
