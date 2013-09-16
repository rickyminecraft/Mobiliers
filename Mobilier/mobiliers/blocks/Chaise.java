package mobiliers.blocks;

import java.util.List;

import mobiliers.mobilier;
import mobiliers.data.ChaiseD;
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

public class Chaise extends BlockBase
{
	public Chaise(int blockID)
	{
		super(blockID, Material.wood);
		this.setHardness(0.2F);
		this.setUnlocalizedName("Chaise");
		this.setCreativeTab(CarpentersBlocks.tabCarpentersBlocks);
		this.setTextureName("carpentersblocks:general/generic");
	}
	
	@Override
	/**
	 * Alter type.
	 */
	public int onHammerLeftClick(TECarpentersBlock TE, EntityPlayer entityPlayer, int data)
	{
		int tmp = ChaiseD.getRotation(data);
		if (++tmp > ChaiseD.CHAISE_Z_POS)
		{
			tmp = ChaiseD.CHAISE_X_NEG;
		}
		ChaiseD.setRotation(TE, tmp);
		
		return BlockProperties.getData(TE);
	}

	@Override
	/**
	 * Alternate between full 1m cube and slab.
	 */
	public int onHammerRightClick(TECarpentersBlock TE, EntityPlayer entityPlayer, int data, int side)
	{
		int type = ChaiseD.getType(data);
		if (++type > ChaiseD.TYPE_4)
		{
			type = ChaiseD.TYPE_1;
		}
		ChaiseD.setType(TE, type);
		
		return BlockProperties.getData(TE);
	}
	
	@Override
	/**
	 * Let people sit on right click.
	 */
	public boolean auxiliaryOnBlockActivated(TECarpentersBlock TE, World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ)
	{
		return BlockMountable.onBlockActivated(world, x, y, z, 	entityPlayer, 0.5F);
	}
	
	/**
	 * Will return stairs boundaries for data.
	 * @param flag 
	 */
	public float[] genBounds(int box, int data)
	{
		++box;
		int tmp = ChaiseD.getRotation(data);
		switch (tmp)
		{
			case ChaiseD.CHAISE_X_NEG:
				switch (box)
				{
					case 1:
						return new float[] { 0.7F, 0.5F, 0.3F, 0.8F, 1.0F, 0.7F };
					case 2:
						return new float[] { 0.2F, 0.4F, 0.2F, 0.8F, 0.5F, 0.8F };
				}
			case ChaiseD.CHAISE_X_POS:
				switch (box)
				{
					case 1:
						return new float[] { 0.2F, 0.5F, 0.3F, 0.3F, 1.0F, 0.7F };
					case 2:
						return new float[] { 0.2F, 0.4F, 0.2F, 0.8F, 0.5F, 0.8F };
				}
			case ChaiseD.CHAISE_Z_NEG:
				switch (box)
				{
					case 1:
						return new float[] { 0.3F, 0.5F, 0.7F, 0.7F, 1.0F, 0.8F };
					case 2:
						return new float[] { 0.2F, 0.4F, 0.2F, 0.8F, 0.5F, 0.8F };
				}
			case ChaiseD.CHAISE_Z_POS:
				switch (box)
				{
					case 1:
						return new float[] { 0.3F, 0.5F, 0.2F, 0.7F, 1.0F, 0.3F };
					case 2:
						return new float[] { 0.2F, 0.4F, 0.2F, 0.8F, 0.5F, 0.8F };
				}
		}

		return null;
	}
	
	@Override
	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y, z
	 */
	public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int x, int y, int z)
	{
		float[] bounds = { 0.2F, 0.0F, 0.2F, 0.8F, 1.0F, 0.8F };
		this.setBlockBounds(bounds[0], bounds[1], bounds[2], bounds[3], bounds[4], bounds[5]);
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
	 * Checks if the block is a solid face on the given side, used by placement logic.
	 */
	public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side)
	{
		return false;
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
		return mobilier.ChaiseRenderID;
	}
}
