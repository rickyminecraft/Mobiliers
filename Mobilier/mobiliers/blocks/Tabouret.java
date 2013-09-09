package mobiliers.blocks;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mobiliers.mobilier;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
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
		this.setTextureName("carpentersblocks:stairs/stairs");
	}

	@Override
	/**
	 * Alter type.
	 */
	public int onHammerLeftClick(TECarpentersBlock TE, EntityPlayer entityPlayer, int data)
	{
		return 0;
	}

	@Override
	/**
	 * Alternate between full 1m cube and slab.
	 */
	public int onHammerRightClick(TECarpentersBlock TE, EntityPlayer entityPlayer, int data, int side)
	{
		return 0;
	}

	@Override
	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y, z
	 */
	public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int x, int y, int z)
	{
		float[] bounds = { 0.2F, 0.0F, 0.2F, 0.8F, 0.5F, 0.8F };
		this.setBlockBounds(bounds[0], bounds[1], bounds[2], bounds[3], bounds[4], bounds[5]);
	}

	@Override
	/**
	 * Adds all intersecting collision boxes to a list. (Be sure to only add boxes to the list if they intersect the
	 * mask.) Parameters: World, X, Y, Z, mask, list, colliding entity
	 */
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB axisAlignedBB, List list, Entity entity)
	{
		setBlockBounds(0.2F, 0.0F, 0.2F, 0.3F, 0.4F, 0.3F);
		super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);
		setBlockBounds(0.7F, 0.0F, 0.7F, 0.8F, 0.4F, 0.8F);
		super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);
		setBlockBounds(0.7F, 0.0F, 0.2F, 0.8F, 0.4F, 0.3F);
		super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);
		setBlockBounds(0.2F, 0.0F, 0.7F, 0.3F, 0.4F, 0.8F);
		super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);
		setBlockBounds(0.2F, 0.4F, 0.2F, 0.8F, 0.5F, 0.8F);
		super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);
	}

	@Override
	/**
	 * Called when the block is placed in the world.
	 */
	public void auxiliaryOnBlockPlacedBy(TECarpentersBlock TE, World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack)
	{
		int data = 0;
		BlockProperties.setData(TE, data);
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
	@SideOnly(Side.CLIENT)
	/**
	 * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
	 * coordinates.  Args: blockAccess, x, y, z, side
	 */
	public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int side)
	{
		if (isThis(blockAccess, x, y, z))
		{
			ForgeDirection side_adj = ForgeDirection.getOrientation(ForgeDirection.OPPOSITES[side]);

			TECarpentersBlock TE_adj = (TECarpentersBlock) blockAccess.getBlockTileEntity(x, y, z);
			TECarpentersBlock TE_src = (TECarpentersBlock) blockAccess.getBlockTileEntity(x + side_adj.offsetX, y + side_adj.offsetY, z + side_adj.offsetZ);

			if (haveSharedFaces(TE_adj, TE_src, side))
				return BlockProperties.shouldRenderSharedFaceBasedOnCovers(TE_adj, TE_src);
			else
				return true;
		}

		return super.shouldSideBeRendered(blockAccess, x, y, z, side);
	}

	/**
	 * Compares dimensions and coordinates of two opposite sides to determine
	 * whether they share faces.
	 */
	private boolean haveSharedFaces(TECarpentersBlock TE_adj, TECarpentersBlock TE_src, int side)
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
