package mobiliers.blocks;

import java.util.List;

import mobiliers.mobilier;
import mobiliers.data.BancD;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import carpentersblocks.block.BlockCoverable;
import carpentersblocks.tileentity.TEBase;
import carpentersblocks.util.BlockProperties;

public class Poteau_indicateur extends BlockCoverable
{

	public Poteau_indicateur(Material material)
	{
		super(material);
	}
	
	@Override
	/**
	 * Toggles post.
	 */
	protected boolean onHammerLeftClick(TEBase TE, EntityPlayer entityPlayer)
	{
//		int data = BlockProperties.getMetadata(TE);
//		int Rotation = CreuxD.getRotation(data);
//		if (++Rotation > CreuxD.CREUX_Z_POS)
//		{
//			Rotation = CreuxD.CREUX_X_NEG;
//		}
//		BancD.setRotation(TE, Rotation);
		return false;
	}

	@Override
	/**
	 * Alters barrier type or sub-type.
	 */
	protected boolean onHammerRightClick(TEBase TE, EntityPlayer entityPlayer)
	{
//			int data = BlockProperties.getMetadata(TE);
//			int type = BancD.getType(data);
//			if (++type > BancD.BANC)
//			{
//				type = BancD.BORD;
//			}
//			BancD.setType(TE, type);
//			
		return false;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l)
	{
		return true;
	}

	@Override
	public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side)
	{
		return true;
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
	public int getRenderType()
	{
		return mobilier.PoteauID;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int i, int j, int k)
	{
		Block block = world.getBlock(i, j+1, k);

		if (block != Block.blockRegistry.getObjectById(0))
		{
			setBlockBounds(0.375F, 0.0F, 0.375F, 0.625F, 1.0F, 0.625F);
		}
		else
		{
			setBlockBounds(0.375F, 0.0F, 0.375F, 0.625F, 0.7F, 0.625F);
		}
	}

	@Override
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB axisAlignedBB, List list, Entity entity)
	{
		Block i = world.getBlock(x, y+1, z);
		if (i != Block.blockRegistry.getObjectById(0))
		{
			setBlockBounds(0.375F, 0.0F, 0.375F, 0.625F, 1.0F, 0.625F);
			super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);
		}
		else
		{
			setBlockBounds(0.375F, 0.0F, 0.375F, 0.625F, 0.7F, 0.625F);
			super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);    		
		}
		if (world.getBlock(x, y, z-1) == Block.getBlockFromName("wall_sign"))
		{
			setBlockBounds(0.4F, 0.4F, 0.0F, 0.6F, 0.6F, 0.4F);
			super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);
		}
		if (world.getBlock(x-1, y, z) == Block.getBlockFromName("wall_sign"))
		{
			setBlockBounds(0.0F, 0.4F, 0.4F, 0.4F, 0.6F, 0.6F);
			super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);
		}
		if (world.getBlock(x+1, y, z) == Block.getBlockFromName("wall_sign"))
		{
			setBlockBounds(0.6F, 0.4F, 0.4F, 1.0F, 0.6F, 0.6F);
			super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);
		}

		if (world.getBlock(x, y, z+1) == Block.getBlockFromName("wall_sign"))
		{
			setBlockBounds(0.4F, 0.4F, 0.6F, 0.6F, 0.6F, 1.0F);
			super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);
		}
	}
}
