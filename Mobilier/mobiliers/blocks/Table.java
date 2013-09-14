package mobiliers.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mobiliers.mobilier;
import mobiliers.data.PlateauD;
import mobiliers.data.TableD;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
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
		this.setUnlocalizedName("plateau");
		this.setCreativeTab(CarpentersBlocks.tabCarpentersBlocks);
		this.setTextureName("carpentersblocks:stairs/stairs");
	}
	
	/**
	 * Alter type.
	 */
	public int onHammerLeftClick(TECarpentersBlock TE, EntityPlayer entityPlayer, int data)
	{
//		if (++data > PlateauD.PLATEAU_Z_POS)
//			data = PlateauD.PLATEAU_X_NEG;

		return data;
	}

	@Override
	/**
	 * Alternate between full 1m cube and slab.
	 */
	public int onHammerRightClick(TECarpentersBlock TE, EntityPlayer entityPlayer, int data, int side)
	{
		int type = TableD.getType(data);
		if (++type > TableD.TYPE_GLASS_1)
		{
			type = TableD.TYPE_NORMAL;
		}
		else
		{
			type = TableD.TYPE_GLASS_1;
		}

		TableD.setType(TE, type);
		
		return BlockProperties.getData(TE);
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

		int data = BlockProperties.getData(TE);

		if (side == ForgeDirection.UP)
		{
			return true;
		}
		return false;
	}

//	@Override
//	@SideOnly(Side.CLIENT)
//	/**
//	 * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
//	 * coordinates.  Args: blockAccess, x, y, z, side
//	 */
//	public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int side)
//	{
//		if (isThis(blockAccess, x, y, z))
//		{
//			ForgeDirection side_adj = ForgeDirection.getOrientation(ForgeDirection.OPPOSITES[side]);
//
//			TECarpentersBlock TE_adj = (TECarpentersBlock) blockAccess.getBlockTileEntity(x, y, z);
//			TECarpentersBlock TE_src = (TECarpentersBlock) blockAccess.getBlockTileEntity(x + side_adj.offsetX, y + side_adj.offsetY, z + side_adj.offsetZ);
//
//			if (haveSharedFaces(TE_adj, TE_src, side))
//				return BlockProperties.shouldRenderSharedFaceBasedOnCovers(TE_adj, TE_src);
//			else
//				return true;
//		}
//
//		return super.shouldSideBeRendered(blockAccess, x, y, z, side);
//	}


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
