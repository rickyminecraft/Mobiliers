package mobiliers.blocks;

import java.util.List;
import mobiliers.mobilier;
import mobiliers.data.TabouretD;
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

public class Tabouret extends BlockBase
{

	public Tabouret(int blockID)
	{
		super(blockID, Material.wood);
		this.setHardness(0.2F);
		this.setUnlocalizedName("tabouret");
		this.setCreativeTab(CarpentersBlocks.tabCarpentersBlocks);
		this.setTextureName("carpentersblocks:general/generic");
	}

	@Override
	/**
	 * Alter type.
	 */
	public int onHammerLeftClick(TECarpentersBlock TE, EntityPlayer entityPlayer, int data)
	{
		return data;
	}

	@Override
	/**
	 * Alternate between full 1m cube and slab.
	 */
	public int onHammerRightClick(TECarpentersBlock TE, EntityPlayer entityPlayer, int data, int side)
	{
		int type = TabouretD.getType(data);
		if (++type > TabouretD.TYPE_2)
		{
			type = TabouretD.TYPE_1;
		}
		TabouretD.setType(TE, type);
		
		return BlockProperties.getData(TE);
	}

	@Override
	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y, z
	 */
	public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int x, int y, int z)
	{
		TECarpentersBlock TE = (TECarpentersBlock)blockAccess.getBlockTileEntity(x, y, z);

		int data = BlockProperties.getData(TE);
		int type = TabouretD.getType(data);
		float[] bounds = { 0.2F, 0.0F, 0.2F, 0.8F, 0.5F, 0.8F };
		switch (type)
		{
			case TabouretD.TYPE_1:
				bounds = new float[] { 0.2F, 0.0F, 0.2F, 0.8F, 0.5F, 0.8F };
				break;
			case TabouretD.TYPE_2:
				bounds = new float[] { 0.2F, 0.0F, 0.2F, 0.8F, 0.8F, 0.8F };
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
		TECarpentersBlock TE = (TECarpentersBlock)world.getBlockTileEntity(x, y, z);

		int data = BlockProperties.getData(TE);
		int type = TabouretD.getType(data);
		setBlockBounds(0.2F, 0.0F, 0.2F, 0.3F, 0.4F, 0.3F);
		super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);
		setBlockBounds(0.7F, 0.0F, 0.7F, 0.8F, 0.4F, 0.8F);
		super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);
		setBlockBounds(0.7F, 0.0F, 0.2F, 0.8F, 0.4F, 0.3F);
		super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);
		setBlockBounds(0.2F, 0.0F, 0.7F, 0.3F, 0.4F, 0.8F);
		super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);
		switch (type)
		{
			case TabouretD.TYPE_1:
				setBlockBounds(0.2F, 0.4F, 0.2F, 0.8F, 0.5F, 0.8F);
				break;
			case TabouretD.TYPE_2:
				setBlockBounds(0.2F, 0.7F, 0.2F, 0.8F, 0.8F, 0.8F);
		}
		super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);
	}
	
	@Override
	/**
	 * Let people sit on right click.
	 */
	public boolean auxiliaryOnBlockActivated(TECarpentersBlock TE, World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ)
	{
		int data = BlockProperties.getData(TE);
		int type = TabouretD.getType(data);
		
		switch (type)
		{
			case TabouretD.TYPE_1:
				return BlockMountable.onBlockActivated(world, x, y, z, 	entityPlayer, 0.5F);
			case TabouretD.TYPE_2:
				return BlockMountable.onBlockActivated(world, x, y, z, 	entityPlayer, 0.8F);
		}
		
		return BlockMountable.onBlockActivated(world, x, y, z, 	entityPlayer, 0.5F);
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
		return mobilier.TabouretRenderID;
	}
}
