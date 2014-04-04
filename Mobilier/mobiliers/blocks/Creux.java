package mobiliers.blocks;

import java.util.List;

import mobiliers.mobilier;
import mobiliers.data.BancD;
import mobiliers.data.CreuxD;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import carpentersblocks.block.BlockCoverable;
import carpentersblocks.tileentity.TEBase;
import carpentersblocks.util.BlockProperties;
import carpentersblocks.util.handler.EventHandler;

public class Creux extends BlockCoverable
{

	public Creux(Material material)
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
		int Rotation = CreuxD.getRotation(data);
		if (++Rotation > CreuxD.CREUX_Z_POS)
		{
			Rotation = CreuxD.CREUX_X_NEG;
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
	
    /**
     * Adds all intersecting collision boxes to a list. (Be sure to only add boxes to the list if they intersect the
     * mask.) Parameters: World, X, Y, Z, mask, list, colliding entity
     */
    @Override
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB axisAlignedBB, List list, Entity entity)
    {
    	int data = world.getBlockMetadata(x, y, z);
    	//data = CreuxD.getRotation(data);
    	for (int box = 0; box < 5; ++box)
		{
			float[] bounds = genBounds(box, data);

			if (bounds != null)
			{
				this.setBlockBounds(bounds[0], bounds[1], bounds[2], bounds[3], bounds[4], bounds[5]);
				super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);
			}
		}
    }
    
    /**
	 * Will return stairs boundaries for data.
	 * @param flag 
	 */
	public float[] genBounds(int box, int data)
	{
		++box;
		switch (data)
		{

			case CreuxD.CREUX_X_NEG:
			case CreuxD.CREUX_X_POS:
				switch (box)
				{
					case 1:
						return new float[] { 0.0F, 0.0F, 0.2F, 0.2F, 1.0F, 1.0F };
					case 2:
						return new float[] { 0.8F, 0.0F, 0.2F, 1.0F, 1.0F, 1.0F };
					case 3:
						return new float[] { 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.2F };
					case 4:
						return new float[] { 0.2F, 0.0F, 0.8F, 0.8F, 1.0F, 1.0F };
					default:	
				}
				break;
			case CreuxD.CREUX_Y_NEG:
			case CreuxD.CREUX_Y_POS:
				switch (box)
				{
					case 1:
						return new float[] { 0.0F, 0.0F, 0.0F, 1.0F, 0.2F, 1.0F };
					case 2:
						return new float[] { 0.0F, 0.8F, 0.0F, 1.0F, 1.0F, 1.0F };
					case 3:
						return new float[] { 0.0F, 0.2F, 0.0F, 1.0F, 0.8F, 0.2F };
					case 4:
						return new float[] { 0.0F, 0.2F, 0.8F, 1.0F, 0.8F, 1.0F };
					default:	
				}
				break;
			case CreuxD.CREUX_Z_NEG:
			case CreuxD.CREUX_Z_POS:
				switch (box)
				{
					case 1:
						return new float[] { 0.0F, 0.0F, 0.0F, 1.0F, 0.2F, 1.0F };
					case 2:
						return new float[] { 0.0F, 0.8F, 0.0F, 1.0F, 1.0F, 1.0F };
					case 3:
						return new float[] { 0.0F, 0.2F, 0.0F, 0.2F, 0.8F, 1.0F };
					case 4:
						return new float[] { 0.8F, 0.2F, 0.0F, 1.0F, 0.8F, 1.0F };
					default:	
				}
				break;
		}

		return null;
	}

	@Override
	/**
	 * Ray traces through the blocks collision from start vector to end vector returning a ray trace hit. Args: world,
	 * x, y, z, startVec, endVec
	 */
	public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 startVec, Vec3 endVec)
	{
		TEBase TE = getTileEntity(world, x, y, z);
		int data = BlockProperties.getMetadata(TE);
		MovingObjectPosition finalTrace = null;

		double currDist = 0.0D;
		double maxDist = 0.0D;

		// Determine if ray trace is a hit on stairs
		for (int box = 0; box < 5; ++box)
		{
			float[] bounds = genBounds(box, data);

			if (bounds != null)
			{
				setBlockBounds(bounds[0], bounds[1], bounds[2], bounds[3], bounds[4], bounds[5]);
				MovingObjectPosition traceResult = super.collisionRayTrace(world, x, y, z, startVec, endVec);

				if (traceResult != null)
				{
					currDist = traceResult.hitVec.squareDistanceTo(endVec);
					if (currDist > maxDist) {
						finalTrace = traceResult;
						maxDist = currDist;
					}
				}
			}
		}

		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		return finalTrace;
	}

	@Override
    /**
     * Called when block is placed in world.
     * Sets stairs angle depending on click coordinates on block face.
     *
     *    Metadata values:
     *      0 - 11    -    Identifies stairs angle in x, y, z space.
     *     12 - 13    -    Top or bottom side of block clicked.  onBlockPlacedBy() determines <- solution pour savoir si l'on clique dessus ou dessous un bloc
     *                 direction and sets interpolated value from 0 - 11.          
     **/
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
	{
        // Normalize face coordinates
        switch (side) 
        {
            case 2:
                hitX = 1.0F - hitX;
                break;
            case 4:
                hitX = hitZ;
                break;
            case 5:
                hitX = 1.0F - hitZ;
                break;
        }
        
        if (side > 1) 
        {
            if (hitY > 0.5F && hitX > 1.0F - hitY && hitX < hitY) 
            {
            	return side + 2;
            } 
            else if (hitY < 0.5F && hitX < 1.0F - hitY && hitX > hitY) 
            {
            	return side + 6;
            } 
            else if (hitX < 0.2F) 
            {
            	return side == 2 ? 1 : side == 3 ? 0 : side == 4 ? 3 : 2;
            } 
            else if (hitX > 0.8F)
            {
            	return side == 2 ? 2 : side == 3 ? 3 : side == 4 ? 1 : 0;
            } 
            else if (hitY > 0.5F) 
            {
            	return side + 2;
            } 
            else 
            { // hitY < 0.5F
            	return side + 6;
            }
        } 
        else 
        {
        	return side + 12;
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
		//solution pour savoir si l'on clique dessus ou dessous un bloc
		int HautBas = world.getBlockMetadata(x, y, z);
		
		if (HautBas < 12)
		{
			switch (facing)
			{
				case 0:
					facing = CreuxD.CREUX_Z_NEG;
					break;
				case 1:
					facing = CreuxD.CREUX_Y_NEG;
					break;
				case 2:
					facing = CreuxD.CREUX_Z_POS;
					break;
				case 3:
					facing = CreuxD.CREUX_Y_POS;
					break;
			}
		}
		else
		{
			facing = CreuxD.CREUX_X_NEG;
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
		return mobilier.CreuxID;
	}
}
