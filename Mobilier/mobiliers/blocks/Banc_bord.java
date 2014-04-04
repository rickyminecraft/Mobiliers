package mobiliers.blocks;

import java.util.List;

import mobiliers.mobilier;
import mobiliers.data.BancD;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import carpentersblocks.block.BlockCoverable;
import carpentersblocks.tileentity.TEBase;
import carpentersblocks.util.BlockProperties;

public class Banc_bord extends BlockCoverable
{

    public Banc_bord(Material material)
	{
		super(material);
	}

	@Override
    /**
     * Toggles post.
     */
    protected boolean onHammerLeftClick(TEBase TE, EntityPlayer entityPlayer)
    {
		int data = BlockProperties.getMetadata(TE);
		int Rotation = BancD.getRotation(data);
		if (++Rotation > BancD.BANC_Z_POS)
		{
			Rotation = BancD.BANC_X_NEG;
		}
		BancD.setRotation(TE, Rotation);
        return true;
    }
    
    @Override
    /**
     * Alters barrier type or sub-type.
     */
    protected boolean onHammerRightClick(TEBase TE, EntityPlayer entityPlayer)
    {
//		int data = BlockProperties.getMetadata(TE);
//		int type = BancD.getType(data);
//		if (++type > BancD.BANC)
//		{
//			type = BancD.BORD;
//		}
//		BancD.setType(TE, type);
//		
        return false;
    }
    
	@Override
	/**
	 * Let people sit on right click.
	 */
	public void postOnBlockActivated(TEBase TE, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ, List<Boolean> altered, List<Boolean> decInv)
	{
		World world = TE.getWorldObj();
		BlockMountable.onBlockActivated(world, TE.xCoord, TE.yCoord, TE.zCoord, entityPlayer, 0.5F);
	}
	
	@Override
	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y, z
	 */
	public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int x, int y, int z)
	{
		TEBase TE = (TEBase)blockAccess.getTileEntity(x, y, z);

		int data = BlockProperties.getMetadata(TE);
		int Rotation = BancD.getRotation(data);
		float[] bounds = { 0.15F, 0.4F, 0.15F, 1.0F, 0.5F, 0.85F };
		switch (Rotation)
		{
			case BancD.BANC_X_NEG:
				bounds = new float[] { 0.15F, 0.4F, 0.15F, 1.0F, 0.5F, 0.85F };
				break;
			case BancD.BANC_X_POS:
				bounds = new float[] { 0.0F, 0.4F, 0.15F, 0.85F, 0.5F, 0.85F };
				break;
			case BancD.BANC_Z_NEG:
				bounds = new float[] { 0.15F, 0.4F, 0.15F, 0.85F, 0.5F, 1.0F };
				break;
			case BancD.BANC_Z_POS:
				bounds = new float[] { 0.15F, 0.4F, 0.0F, 0.85F, 0.5F, 0.85F };
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
		TEBase TE = (TEBase)world.getTileEntity(x, y, z);

		int data = BlockProperties.getMetadata(TE);
		int Rotation = BancD.getRotation(data);
		switch (Rotation)
		{
			case BancD.BANC_X_NEG:
				setBlockBounds(0.2F, 0.0F, 0.2F, 0.3F, 0.4F, 0.8F);
				super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);
				setBlockBounds(0.15F, 0.4F, 0.15F, 1.0F, 0.5F, 0.85F);
				super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);
				break;
			case BancD.BANC_X_POS:
				setBlockBounds(0.7F, 0.0F, 0.2F, 0.8F, 0.4F, 0.8F);
				super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);
				setBlockBounds(0.0F, 0.4F, 0.15F, 0.85F, 0.5F, 0.85F);
				super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);
				break;
			case BancD.BANC_Z_NEG:
				setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 0.4F, 0.3F);
				super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);
				setBlockBounds(0.15F, 0.4F, 0.15F, 0.85F, 0.5F, 1.0F);
				super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);
				break;
			case BancD.BANC_Z_POS:
				setBlockBounds(0.2F, 0.0F, 0.7F, 0.8F, 0.4F, 0.8F);
				super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);
				setBlockBounds(0.15F, 0.4F, 0.0F, 0.85F, 0.5F, 0.85F);
				super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);
		}
		
	}
	
    @Override
	/**
	 * Called when the block is placed in the world.
	 * Uses cardinal direction to adjust metadata if player clicks top or bottom face of block.
	 */
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack)
	{
    	TEBase TE = getTileEntity(world, x, y, z);
		int facing = MathHelper.floor_double(entityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		switch (facing)
		{
			case 0:
				facing = BancD.BANC_Z_NEG;
				break;
			case 1:
				facing = BancD.BANC_X_POS;
				break;
			case 2:
				facing = BancD.BANC_Z_POS;
				break;
			case 3:
				facing = BancD.BANC_X_NEG;
		}
		BlockProperties.setMetadata(TE, facing);
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
		return mobilier.BancBordID;
	}
	
}
