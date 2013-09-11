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
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import carpentersblocks.CarpentersBlocks;
import carpentersblocks.block.BlockBase;
import carpentersblocks.tileentity.TECarpentersBlock;
import carpentersblocks.util.BlockProperties;

public class Support extends BlockBase
{

	public Support(int blockID)
	{
		super(blockID, Material.wood);
		this.setHardness(0.2F);
		this.setUnlocalizedName("support");
		this.setCreativeTab(CarpentersBlocks.tabCarpentersBlocks);
		this.setTextureName("carpentersblocks:general/generic");
	}

	@Override
	/**
	 * Alter type.
	 */
	public int onHammerLeftClick(TECarpentersBlock TE, EntityPlayer entityPlayer, int data)
	{
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
		return data;
	}

	@Override
	/**
	 * Alternate between full 1m cube and slab.
	 */
	public int onHammerRightClick(TECarpentersBlock TE, EntityPlayer entityPlayer, int data, int side)
	{
		int tmp = data & 8;

		if (SupportD.getPosition(data) == SupportD.SUPPORT_HAUT)
		{
			SupportD.setPosition(TE, SupportD.SUPPORT_BAS);
			data &= 7;
			switch (side)
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
			switch (side)
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

		return data;
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
	 * Updates the blocks bounds based on its current state. Args: world, x, y, z
	 */
	public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int x, int y, int z)
	{
		float[] bounds = { 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F };
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
		return mobilier.SupportRenderID;
	}
}
