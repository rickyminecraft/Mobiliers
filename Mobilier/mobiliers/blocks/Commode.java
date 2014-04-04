package mobiliers.blocks;

import java.util.List;

import mobiliers.mobilier;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import carpentersblocks.block.BlockCoverable;
import carpentersblocks.data.Safe;
import carpentersblocks.tileentity.TEBase;
import carpentersblocks.tileentity.TECarpentersSafe;
import carpentersblocks.util.BlockProperties;
import carpentersblocks.util.handler.ChatHandler;
import carpentersblocks.util.handler.EventHandler;

public class Commode extends BlockCoverable
{

	public Commode(Material material)
	{
		super(material);
	}

//	   @SideOnly(Side.CLIENT)
//	    @Override
//	    /**
//	     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
//	     * is the only chance you get to register icons.
//	     */
//	    public void registerBlockIcons(IIconRegister iconRegister)
//	    {
//	        IconRegistry.icon_safe_light = iconRegister.registerIcon(CarpentersBlocks.MODID + ":" + "safe/safe_light");
//	        
//	        super.registerBlockIcons(iconRegister);
//	    }
	    
	/**
	 * Returns whether player is allowed to make alterations to this block.
	 * This does not include block activation.  For that, use canPlayerActivate().
	 */
	@Override
	protected boolean canPlayerEdit(TEBase TE, EntityPlayer entityPlayer)
	{
		if (isOp(entityPlayer)) 
		{
			return true;
		} 
		else 
		{
			return entityPlayer.canPlayerEdit(TE.xCoord, TE.yCoord, TE.zCoord, EventHandler.eventFace, entityPlayer.getHeldItem()) &&
					TE.isOwner(entityPlayer);
		}
	}

	/**
	 * Returns whether player is allowed to activate this block.
	 */
	@Override
	protected boolean canPlayerActivate(TEBase TE, EntityPlayer entityPlayer)
	{
		return isOp(entityPlayer) || TE.isOwner(entityPlayer) || !Safe.isLocked(TE);
	}

	@Override
	/**
	 * Rotates safe so that it faces player.
	 */
	protected boolean onHammerLeftClick(TEBase TE, EntityPlayer entityPlayer)
	{
		int facing = BlockProperties.getOppositeFacing(entityPlayer);
		ForgeDirection dir = BlockProperties.getDirectionFromFacing(facing);

		if (dir != Safe.getFacing(TE)) 
		{
			Safe.setFacing(TE, facing);
			return true;
		}
		else 
		{
			return false;
		}
	}

	@Override
	/**
	 * Cycles locked state.
	 */
	protected boolean onHammerRightClick(TEBase TE, EntityPlayer entityPlayer)
	{
		if (entityPlayer.isSneaking()) 
		{

			boolean locked = !Safe.isLocked(TE);

			Safe.setLocked(TE, locked);

			if (locked) 
			{
				Safe.setAutoPerm(TE, Safe.AUTOMATION_DISABLED);
			} 
			else 
			{
				Safe.setAutoPerm(TE, Safe.AUTOMATION_ALL);
			}

			if (locked) 
			{
				ChatHandler.sendMessageToPlayer("message.safe_lock.name", entityPlayer);
			} 
			else 
			{
				ChatHandler.sendMessageToPlayer("message.safe_unlock.name", entityPlayer);
			}

			return true;
		} 
		else 
		{
			int autoPerm = Safe.getAutoPerm(TE);

			if (++autoPerm > 3) 
			{
				autoPerm = 0;
			}

			Safe.setAutoPerm(TE, autoPerm);

			switch (autoPerm) 
			{
				case Safe.AUTOMATION_ALL:
					ChatHandler.sendMessageToPlayer("message.automation_all.name", entityPlayer);
					break;
				case Safe.AUTOMATION_DISABLED:
					ChatHandler.sendMessageToPlayer("message.automation_disabled.name", entityPlayer);
					break;
				case Safe.AUTOMATION_RECEIVE:
					ChatHandler.sendMessageToPlayer("message.automation_insert.name", entityPlayer);
					break;
				case Safe.AUTOMATION_SEND:
					ChatHandler.sendMessageToPlayer("message.automation_extract.name", entityPlayer);
					break;
			}

		}

		return true;
	}

	@Override
	/**
	 * Called when the block is placed in the world.
	 */
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack)
	{
		TEBase TE = (TEBase) world.getTileEntity(x, y, z);

		Safe.setFacing(TE, BlockProperties.getOppositeFacing(entityLiving));

		super.onBlockPlacedBy(world, x, y, z, entityLiving, itemStack);
	}

	@Override
	/**
	 * Called upon block activation (right click on the block.)
	 */
	protected void postOnBlockActivated(TEBase TE, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ, List<Boolean> altered, List<Boolean> decInv)
	{
		if (!Safe.isOpen(TE) && canPlayerActivate(TE, entityPlayer)) 
		{

			TECarpentersSafe TE_safe = (TECarpentersSafe) TE;
			ItemStack itemStack = entityPlayer.getHeldItem();

			if (itemStack != null && itemStack.getItem().equals(Items.gold_ingot)) 
			{
				if (!TE_safe.hasUpgrade()) 
				{
					if (TE_safe.incSizeInventory()) 
					{
						TE.getWorldObj().markBlockForUpdate(TE.xCoord, TE.yCoord, TE.zCoord);
						decInv.add(true);
						return;
					}
				}
			}

			if (!decInv.contains(true)) 
			{
				entityPlayer.displayGUIChest((TECarpentersSafe)TE);
			}
		} 
		else 
		{
			ChatHandler.sendMessageToPlayer("message.block_lock.name", entityPlayer);
		}

		/*
		 * Safe should always return true because it either warns the player
		 * that it is locked, or it returns the GUI.
		 */
		 altered.add(true);
	}

	/**
	 * Location sensitive version of getExplosionRestance
	 *
	 * @param par1Entity The entity that caused the explosion
	 * @param world The current world
	 * @param x X Position
	 * @param y Y Position
	 * @param z Z Position
	 * @param explosionX Explosion source X Position
	 * @param explosionY Explosion source X Position
	 * @param explosionZ Explosion source X Position
	 * @return The amount of the explosion absorbed.
	 */
	@Override
	public float getExplosionResistance(Entity entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
	{
		return Blocks.bedrock.getExplosionResistance(entity);
	}

	@Override
	/**
	 * Checks if the block is a solid face on the given side, used by placement logic.
	 *
	 * @param world The current world
	 * @param x X Position
	 * @param y Y position
	 * @param z Z position
	 * @param side The side to check
	 * @return True if the block is solid on the specified side.
	 */
	public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side)
	{
		TEBase TE = getTileEntity(world, x, y, z);

		if (TE != null) 
		{
			if (isBlockSolid(world, x, y, z)) 
			{
				return side != Safe.getFacing(TE);
			}
		}

		return false;
	}

	@Override
	/**
	 * ejects contained items into the world, and notifies neighbours of an update, as appropriate
	 */
	public void breakBlock(World world, int x, int y, int z, Block block, int metadata)
	{
		TEBase TE = getTileEntity(world, x, y, z);

		if (TE != null) 
		{

			TECarpentersSafe TE_safe = (TECarpentersSafe) TE;

			if (TE_safe.hasUpgrade()) {
				BlockProperties.ejectEntity(TE, new ItemStack(Items.gold_ingot));
			}

			for (int slot = 0; slot < TE_safe.getSizeInventory(); ++slot)
			{
				ItemStack itemStack = TE_safe.getStackInSlot(slot);

				if (itemStack != null) 
				{
					BlockProperties.ejectEntity(TE, itemStack);
				}
			}
		}
		super.breakBlock(world, x, y, z, block, metadata);
	}

	@Override
	/**
	 * Gets the hardness of block at the given coordinates in the given world, relative to the ability of the given
	 * EntityPlayer.
	 */
	public float getPlayerRelativeBlockHardness(EntityPlayer entityPlayer, World world, int x, int y, int z)
	{
		TEBase TE = (TEBase) world.getTileEntity(x, y, z);

		if (Safe.isOpen(TE) || !canPlayerEdit(TE, entityPlayer)) 
		{
			return -1; // Unbreakable
		} 
		else 
		{
			return super.getPlayerRelativeBlockHardness(entityPlayer, world, x, y, z);
		}
	}

	@Override
	/**
	 * Returns a new instance of a block's tile entity class. Called on placing the block.
	 */
	public TileEntity createNewTileEntity(World world, int metadata)
	{
		return new TECarpentersSafe();
	}

	@Override
	/**
	 * The type of render function that is called for this block
	 */
	public int getRenderType()
	{
		return mobilier.CommodeID;
	}
}
