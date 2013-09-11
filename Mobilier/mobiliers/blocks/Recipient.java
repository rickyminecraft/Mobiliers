package mobiliers.blocks;

import java.util.List;

import mobiliers.mobilier;
import mobiliers.data.RecipientD;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import carpentersblocks.CarpentersBlocks;
import carpentersblocks.block.BlockBase;
import carpentersblocks.tileentity.TECarpentersBlock;
import carpentersblocks.util.BlockProperties;

public class Recipient extends BlockBase
{
	public Recipient(int blockID)
	{
		super(blockID, Material.wood);
		this.setHardness(0.2F);
		this.setUnlocalizedName("recipient");
		this.setCreativeTab(CarpentersBlocks.tabCarpentersBlocks);
		this.setTextureName("carpentersblocks:general/generic");
	}

	@Override
	/**
	 * Alter type.
	 */
	public int onHammerLeftClick(TECarpentersBlock TE, EntityPlayer entityPlayer, int data)
	{
		int tmp = data & 8;
		data &= 7;
		if (++data > RecipientD.RECIPIENT_Z_POS)
			data = RecipientD.CENTRER;
		
		data += tmp;

		return data;
	}

	@Override
	/**
	 * Alternate between full 1m cube and slab.
	 */
	public int onHammerRightClick(TECarpentersBlock TE, EntityPlayer entityPlayer, int data, int side)
	{
		int tmp = data & 8;
		data &= 7;
		if (data == RecipientD.CENTRER)
		{
			switch (side)
			{
				case 2:
					data = RecipientD.RECIPIENT_Z_NEG;
					break;
				case 3:
					data = RecipientD.RECIPIENT_X_NEG;
					break;
				case 4:
					data = RecipientD.RECIPIENT_X_POS;
					break;
				case 5:
					data = RecipientD.RECIPIENT_Z_POS;
					break;
			}
		}
		else
		{
			data = RecipientD.CENTRER;
		}
		data += tmp;

		return data;
	}

	/**
	 * Will return stairs boundaries for data.
	 */
	public float[] genBounds(int data)
	{
		switch (data)
		{
			case RecipientD.RECIPIENT_X_NEG:
				return new float[] { 0.4F, 0.0F, 0.65F, 0.6F, 0.3F, 0.85F };
			case RecipientD.RECIPIENT_X_POS:
				return new float[] { 0.15F, 0.0F, 0.4F, 0.35F, 0.3F, 0.6F };
			case RecipientD.RECIPIENT_Z_NEG:
				return new float[] { 0.4F, 0.0F, 0.15F, 0.6F, 0.3F, 0.35F };
			case RecipientD.RECIPIENT_Z_POS:
				return new float[] { 0.65F, 0.0F, 0.4F, 0.85F, 0.3F, 0.6F };
			default: // RecipientD.CENTRER
				return new float[] { 0.4F, 0.0F, 0.4F, 0.6F, 0.3F, 0.6F };
		}
	}

	@Override
	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y, z
	 */
	public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int x, int y, int z)
	{
		TECarpentersBlock TE = (TECarpentersBlock) blockAccess.getBlockTileEntity(x, y, z);

		int data = BlockProperties.getData(TE);
		data &= 7;

		float[] bounds = genBounds(data);
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
	 * Checks if the block is a solid face on the given side, used by placement logic.
	 */
	public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side)
	{
		return false;
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
	 * Called upon block activation (right click on the block.)
	 */
	public boolean auxiliaryOnBlockActivated(TECarpentersBlock TE, World world, int x, int y, int z, EntityPlayer entityPlayer, int side, float hitX,
			float hitY, float hitZ)
	{
		if (world.isRemote)
        {
            return true;
        }
        else
        {
            ItemStack itemstack = entityPlayer.inventory.getCurrentItem();
            int data = BlockProperties.getData(TE);
            int State = RecipientD.getState(data);

            if (itemstack == null)
            {
            	if (State == RecipientD.STATE_FULL)
            	{
            		entityPlayer.getFoodStats().addStats(1, 1.0f);
            		RecipientD.setState(TE, RecipientD.STATE_EMPTY);
            	}
                return true;
            }
            else
            {
                if (itemstack.itemID == Item.potion.itemID && itemstack.getItemDamage() == 0)
                {
                    if (State == RecipientD.STATE_EMPTY)
                    {
                        if (!entityPlayer.capabilities.isCreativeMode)
                        {
                            entityPlayer.inventory.setInventorySlotContents(entityPlayer.inventory.currentItem, new ItemStack(Item.glassBottle));
                        }

                        RecipientD.setState(TE, RecipientD.STATE_FULL);
                    }

                    return true;
                }
                else
                {
                	if (State == RecipientD.STATE_FULL)
                	{
                		entityPlayer.getFoodStats().addStats(1, 1.0f);
                		RecipientD.setState(TE, RecipientD.STATE_EMPTY);
                	}
                    return true;
                }
            }
        }
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
		return mobilier.RecipientRenderID;
	}
}
