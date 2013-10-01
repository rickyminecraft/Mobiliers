package mobiliers.blocks;

import java.util.List;

import mobiliers.mobilier;
import mobiliers.data.QuartD;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import carpentersblocks.CarpentersBlocks;
import carpentersblocks.block.BlockBase;
import carpentersblocks.tileentity.TECarpentersBlock;
import carpentersblocks.util.BlockProperties;

public class Quart extends BlockBase
{
	public Quart(int blockID)
	{
		super(blockID, Material.wood);
		this.setHardness(0.2F);
		this.setUnlocalizedName("Quart");
		this.setCreativeTab(CarpentersBlocks.tabCarpentersBlocks);
		this.setTextureName("carpentersblocks:stairs/stairs");
	}
	
	@Override
	/**
	 * Alter type.
	 */
	protected boolean onHammerLeftClick(TECarpentersBlock TE, EntityPlayer entityPlayer)
	{
		int data = BlockProperties.getData(TE);
		int Rotation = QuartD.getRotation(data);
		if (++Rotation > QuartD.CENTRE)
			Rotation = QuartD.XPOS;
		QuartD.setRotation(TE, Rotation);
		return true;
	}
	
	@Override
	/**
	 * Alternate between full 1m cube and slab.
	 */
	protected boolean onHammerRightClick(TECarpentersBlock TE, EntityPlayer entityPlayer, int side)
	{
		int data = BlockProperties.getData(TE);
		int type = QuartD.getType(data);
		if (++type > QuartD.HAUT)
		{
			type = QuartD.PLEIN;
		}
		QuartD.setType(TE, type);
		
		return true;
	}

	@Override
	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y, z
	 */
	public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int x, int y, int z)
	{
		TECarpentersBlock TE = (TECarpentersBlock) blockAccess.getBlockTileEntity(x, y, z);

		int data = BlockProperties.getData(TE);
		int type = QuartD.getType(data);
		float bas = 0.0F, haut = 1.0F;
		data = QuartD.getRotation(data);
		switch (type)
		{
			case QuartD.PLEIN:
				break;
			case QuartD.BAS:
				haut = 0.5F;
				break;
			case QuartD.MILIEU:
				bas = 0.25F;
				haut = 0.75F;
				break;
			case QuartD.HAUT:
				bas = 0.5F;
		}
		float[] bounds = { 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F };

		switch (data)
		{
			case QuartD.XPOS:
				bounds = new float[] { 0.0F, bas, 0.0F, 0.5F, haut, 0.5F };
				break;
			case QuartD.XNEG:
				bounds = new float[] { 0.5F, bas, 0.0F, 1.0F, haut, 0.5F };
				break;
			case QuartD.ZPOS:
				bounds = new float[] { 0.0F, bas, 0.5F, 0.5F, haut, 1.0F };
				break;
			case QuartD.ZNEG:
				bounds = new float[] { 0.5F, bas, 0.5F, 1.0F, haut, 1.0F };
				break;
			case QuartD.XPOSCENTRE:
				bounds = new float[] { 0.25F, bas, 0.0F, 0.75F, haut, 0.5F };
				break;
			case QuartD.XNEGCENTRE:
				bounds = new float[] { 0.25F, bas, 0.5F, 0.75F, haut, 1.0F };
				break;
			case QuartD.ZPOSCENTRE:
				bounds = new float[] { 0.0F, bas, 0.25F, 0.5F, haut, 0.75F };
				break;
			case QuartD.ZNEGCENTRE:
				bounds = new float[] { 0.5F, bas, 0.25F, 1.0F, haut, 0.75F };
				break;
			case QuartD.XPOSLARGE:
				bounds = new float[] { 0.0F, bas, 0.0F, 1.0F, haut, 0.5F };
				break;
			case QuartD.XNEGLARGE:
				bounds = new float[] { 0.0F, bas, 0.5F, 1.0F, haut, 1.0F };
				break;
			case QuartD.ZPOSLARGE:
				bounds = new float[] { 0.0F, bas, 0.0F, 0.5F, haut, 1.0F };
				break;
			case QuartD.ZNEGLARGE:
				bounds = new float[] { 0.5F, bas, 0.0F, 1.0F, haut, 1.0F };
				break;
			case QuartD.CENTRE:
				bounds = new float[] { 0.25F, bas, 0.25F, 0.75F, haut, 0.75F };
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
		this.setBlockBoundsBasedOnState(world, x, y, z);
		super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);
	}
    
    @Override
	/**
	 * Called when the block is placed in the world.
	 */
	public void auxiliaryOnBlockPlacedBy(TECarpentersBlock TE, World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack)
	{
    	int facing = MathHelper.floor_double(entityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
    	int data = TE.blockMetadata;
		// If shift key is down, skip auto-orientation
		if (!entityLiving.isSneaking())
		{
			/*
			 * Match block type with adjacent type if possible
			 */
			TECarpentersBlock TE_YN = world.getBlockId(x, y - 1, z) == blockID ? (TECarpentersBlock)world.getBlockTileEntity(x, y - 1, z) : null;
			TECarpentersBlock TE_YP = world.getBlockId(x, y + 1, z) == blockID ? (TECarpentersBlock)world.getBlockTileEntity(x, y + 1, z) : null;
			TECarpentersBlock TE_XN = world.getBlockId(x - 1, y, z) == blockID ? (TECarpentersBlock)world.getBlockTileEntity(x - 1, y, z) : null;
			TECarpentersBlock TE_XP = world.getBlockId(x + 1, y, z) == blockID ? (TECarpentersBlock)world.getBlockTileEntity(x + 1, y, z) : null;
			TECarpentersBlock TE_ZN = world.getBlockId(x, y, z - 1) == blockID ? (TECarpentersBlock)world.getBlockTileEntity(x, y, z - 1) : null;
			TECarpentersBlock TE_ZP = world.getBlockId(x, y, z + 1) == blockID ? (TECarpentersBlock)world.getBlockTileEntity(x, y, z + 1) : null;

			if (TE_YN != null)
			{
				data = BlockProperties.getData(TE_YN);
			}
			else if (TE_YP != null)
			{
				data = BlockProperties.getData(TE_YP);
			}
			else if (TE_XN != null)
			{
				data = BlockProperties.getData(TE_XN);
			}
			else if (TE_XP != null)
			{
				data = BlockProperties.getData(TE_XP);
			}
			else if (TE_ZN != null)
			{
				data = BlockProperties.getData(TE_ZN);
			}
			else if (TE_ZP != null)
			{
				data = BlockProperties.getData(TE_ZP);
			}
			else
			{
				switch (facing)
				{
					case 0:
						data = QuartD.XNEGCENTRE;
						break;
					case 1:
						data = QuartD.ZPOSCENTRE;
						break;
					case 2:
						data = QuartD.XPOSCENTRE;
						break;
					case 3:
						data = QuartD.ZNEGCENTRE;
				}
			}
		}

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
		return mobilier.QuartRenderID;
	}
}
