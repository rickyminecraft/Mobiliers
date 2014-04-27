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
import net.minecraftforge.common.util.ForgeDirection;
import carpentersblocks.block.BlockCoverable;
import carpentersblocks.tileentity.TEBase;
import carpentersblocks.util.BlockProperties;

public class Tabouret extends BlockCoverable
{

	public Tabouret(Material material)
	{
		super(material);
	}

	@Override
	/**
	 * Alter type.
	 */
	protected boolean onHammerLeftClick(TEBase TE, EntityPlayer entityPlayer)
	{
		return false;
	}

	@Override
	/**
	 * Alternate between full 1m cube and slab.
	 */
	protected boolean onHammerRightClick(TEBase TE, EntityPlayer entityPlayer)
	{
		int data = BlockProperties.getMetadata(TE);
		int type = TabouretD.getType(data);
		if (++type > TabouretD.TYPE_2)
		{
			type = TabouretD.TYPE_1;
		}
		TabouretD.setType(TE, type);
		
		return true;
	}

	@Override
	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y, z
	 */
	public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int x, int y, int z)
	{
		TEBase TE = getTileEntityStrict(blockAccess, x, y, z);

		int data = BlockProperties.getMetadata(TE);
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
		TEBase TE = getTileEntityStrict(world, x, y, z);

		int data = BlockProperties.getMetadata(TE);
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
	public void postOnBlockActivated(TEBase TE, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ, List<Boolean> altered, List<Boolean> decInv)
	{
		World world = TE.getWorldObj();
		int data = BlockProperties.getMetadata(TE);
		int type = TabouretD.getType(data);
		
		switch (type)
		{
			case TabouretD.TYPE_1:
				BlockMountable.onBlockActivated(world, TE.xCoord, TE.yCoord, TE.zCoord, 	entityPlayer, 0.5F);
				break;
			case TabouretD.TYPE_2:
				BlockMountable.onBlockActivated(world, TE.xCoord, TE.yCoord, TE.zCoord, 	entityPlayer, 0.8F);
		}
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
	 * Checks if the block is a solid face on the given side, used by placement logic.
	 */
	public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side)
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
		return mobilier.TabouretRenderID;
	}
}
