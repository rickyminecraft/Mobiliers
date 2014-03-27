package mobiliers.blocks;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mobiliers.mobilier;
import mobiliers.data.Poteau_baseD;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import carpentersblocks.CarpentersBlocks;
import carpentersblocks.block.BlockCoverable;
import carpentersblocks.tileentity.TEBase;
import carpentersblocks.util.BlockProperties;

public class Poteau_base extends BlockCoverable
{
	public Poteau_base(Material material)
	{
		super(material);
	}

	@Override
	/**
	 * Alter type.
	 */
	protected boolean onHammerLeftClick(TEBase TE, EntityPlayer entityPlayer)
	{
		int data = BlockProperties.getMetadata(TE);
		if (data == Poteau_baseD.DOWN)
		{
			data = Poteau_baseD.UP;
		}
		else
		{
			data = Poteau_baseD.DOWN;
		}
		BlockProperties.setMetadata(TE, data);
		return true;
	}

	@Override
	/**
	 * Alternate between full 1m cube and slab.
	 */
	protected boolean onHammerRightClick(TEBase TE, EntityPlayer entityPlayer)
	{
		return false;
	}

    @Override
    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
    {
    	metadata &= 7;

        ForgeDirection dir = ForgeDirection.getOrientation(side);

        if (dir == ForgeDirection.UP) //forge as mixed up -> down and down -> up
        {
            metadata = Poteau_baseD.DOWN;
        }
        else if (dir == ForgeDirection.DOWN)
        {
        	metadata = Poteau_baseD.UP;
        }
        else
        {
        	metadata = Poteau_baseD.DOWN;
        }

        return metadata;
    }
	
	@Override
	/**
	 * Called when the block is placed in the world.
	 */
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack)
	{
		TEBase TE = getTileEntity(world, x, y, z);
		int data = TE.blockMetadata;
		BlockProperties.setMetadata(TE, data);
	}
    
	@Override
	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y, z
	 */
	public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int x, int y, int z)
	{
		float[] bounds = { 0.375F, 0.0F, 0.375F, 0.625F, 1.0F, 0.625F };
		this.setBlockBounds(bounds[0], bounds[1], bounds[2], bounds[3], bounds[4], bounds[5]);
	}

	@Override
	/**
	 * Adds all intersecting collision boxes to a list. (Be sure to only add boxes to the list if they intersect the
	 * mask.) Parameters: World, X, Y, Z, mask, list, colliding entity
	 */
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB axisAlignedBB, List list, Entity entity)
	{
		TEBase TE = (TEBase) world.getTileEntity(x, y, z);

		int data = BlockProperties.getMetadata(TE);
		switch (data)
		{
			case Poteau_baseD.DOWN:
				this.setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 0.1F, 0.8F);
				super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);
				this.setBlockBounds(0.3F, 0.1F, 0.3F, 0.7F, 0.2F, 0.7F);
				super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);
				this.setBlockBounds(0.375F, 0.2F, 0.375F, 0.625F, 1.0F, 0.625F);
				super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);
				break;
			case Poteau_baseD.UP:
				this.setBlockBounds(0.2F, 0.9F, 0.2F, 0.8F, 1.0F, 0.8F);
				super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);
				this.setBlockBounds(0.3F, 0.8F, 0.3F, 0.7F, 0.9F, 0.7F);
				super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);
				this.setBlockBounds(0.375F, 0.0F, 0.375F, 0.625F, 0.8F, 0.625F);
				super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);
				
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
	@SideOnly(Side.CLIENT)
	/**
	 * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
	 * coordinates.  Args: blockAccess, x, y, z, side
	 */
	public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int side)
	{
		return true;
	}

	/**
	 * Compares dimensions and coordinates of two opposite sides to determine
	 * whether they share faces.
	 */
	private boolean haveSharedFaces(TEBase TE_adj, TEBase TE_src, int side)
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
		return mobilier.Poteau_baseRenderID;
	}
}
