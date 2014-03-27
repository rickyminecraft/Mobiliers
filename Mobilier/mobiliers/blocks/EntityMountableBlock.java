package mobiliers.blocks;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;



public class EntityMountableBlock extends Entity
{
	//These variables keep track of the block that created the entity.
	protected int orgBlockPosX;
	protected int orgBlockPosY;
	protected int orgBlockPosZ;
	protected int orgBlockID;
	
	public EntityMountableBlock (World world)
	{
		super(world);
        noClip = true;
        preventEntitySpawning = true;
        width = 0F;
        height = 0F;
	}
	
	public EntityMountableBlock (World world, double d, double d1, double d2)
	{
        super(world);
        noClip = true;
        preventEntitySpawning = true;
        width = 0F;
        height = 0F;
        setPosition(d, d1, d2);
	}

	//This constructor is called by the mountable block.
	public EntityMountableBlock (World world, EntityPlayer entityplayer, int i, int j, int k, float mountingX, float mountingY, float mountingZ)
	{
		super(world);
        noClip = true;
        preventEntitySpawning = true;
        width = 0.0F;
        height = 0.0F;
        
    	orgBlockPosX = i;
    	orgBlockPosY = j;
    	orgBlockPosZ = k;
    	Block bl = world.getBlock(i, j, k);
    	orgBlockID = bl.getIdFromBlock(bl);
    	
        setPosition(mountingX, mountingY, mountingZ);
	}
	
	//This method handles mounting and dismounting.
	@Override
    public boolean interactFirst(EntityPlayer entityplayer)
    {
        if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer && this.riddenByEntity != entityplayer)
        {
        	return true;
        }
        else
        {
        	if (!this.worldObj.isRemote)
        	{
        		entityplayer.mountEntity(this);
        	}
        	return true;
        }
    }
    
	//This method is mostly a simplified version of the one in Entity but it also deletes unused EMBs.
    @Override
    public void onEntityUpdate()
    {
    	this.worldObj.theProfiler.startSection("entityBaseTick");
    	Block bl = worldObj.getBlock(orgBlockPosX, orgBlockPosY, orgBlockPosZ);
        if(riddenByEntity == null || riddenByEntity.isDead)
        {
			this.setDead();
        }
        else if(bl.getIdFromBlock(bl) != orgBlockID)
		{
        	this.interactFirst((EntityPlayer) riddenByEntity);//interact
		}
        ticksExisted++;
        this.worldObj.theProfiler.endSection();
    }
    
    //The following methods are required by the Entity class but I don't know what they are for.
    @Override
    public void entityInit() {}
    @Override
    public void readEntityFromNBT(NBTTagCompound nbttagcompound) {}
    @Override
    public void writeEntityToNBT(NBTTagCompound nbttagcompound) {}
}
