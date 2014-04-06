package mobiliers.renderer;

import mobiliers.mobilier;
import mobiliers.data.BuffetD;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import carpentersblocks.renderer.BlockHandlerBase;
import carpentersblocks.util.BlockProperties;

@SideOnly(Side.CLIENT)
public class Buffet extends BlockHandlerBase
{
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderer)
	{
		
	}
	
    @Override
    /**
     * Override to provide custom icons.
     */
    protected IIcon getUniqueIcon(ItemStack itemStack, int side, IIcon icon)
    {
        Block block = BlockProperties.toBlock(itemStack);
        
        if (BlockProperties.hasCover(TE, 6)) {
            return block.getIcon(2, renderBlocks.blockAccess.getBlockMetadata(TE.xCoord, TE.yCoord, TE.zCoord));
        } else {
            return mobilier.IdefautArmoire;
        }
    }
    
	@Override
	/**
	 * Renders stairs at the given coordinates
	 */
	public void renderCarpentersBlock(int x, int y, int z)
	{
		ItemStack coverBlock = BlockProperties.hasCover(TE, 6) == true ? BlockProperties.getCover(TE, coverRendering) : BlockProperties.getCover(TE, 6);
		renderBuffet(coverBlock, x, y, z);
//		int data = BlockProperties.getMetadata(TE);
//		int type = ChaiseD.getType(data);
//		switch (type)
//		{
//			case ChaiseD.TYPE_1:
//				renderChaise(coverBlock, x, y, z);
//				break;
//			case ChaiseD.TYPE_2:
//				renderChaise_2(coverBlock, x, y, z);
//				break;
//			case ChaiseD.TYPE_3:
//				renderChaise_3(coverBlock, x, y, z);
//				break;
//			case ChaiseD.TYPE_4:
//				renderChaise_4(coverBlock, x, y, z);
//		}
	}

	private void renderBuffet(ItemStack coverBlock, int x, int y, int z) 
	{
		int data = BlockProperties.getMetadata(TE) & 0x3;
		int Droite = BuffetD.getType(BlockProperties.getMetadata(TE));
		ForgeDirection meta = BlockProperties.getDirectionFromFacing(data);
		if (Droite == BuffetD.DROITE)
		{
			if(meta == ForgeDirection.WEST)
			{
				renderBlocks.setRenderBounds(0.0D, 0.9D, 0.0D, 1.0D, 1.0D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.1D, 0.0D, 1.0D, 0.9D, 0.9D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.8D, 0.3D, 0.1D, 0.9D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.9D, 0.0D, 0.8D, 1.0D, 0.1D, 0.9D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.1D, 0.2D, 0.0D, 0.2D, 0.8D, 0.25D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.1D, 0.2D, 0.3D, 0.2D, 0.8D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.05D, 0.4D, 0.6D, 0.1D, 0.5D, 0.8D);
				renderBlock(coverBlock, x, y, z);
			} 
			else if(meta == ForgeDirection.EAST)
			{
				renderBlocks.setRenderBounds(0.0D, 0.9D, 0.0D, 1.0D, 1.0D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.1D, 0.1D, 0.8D, 0.9D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.1D, 0.8D, 0.1D, 0.2D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.0D, 0.1D, 0.1D, 0.1D, 0.2D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.8D, 0.2D, 0.75D, 0.9D, 0.8D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.8D, 0.2D, 0.2D, 0.9D, 0.8D, 0.7D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.9D, 0.4D, 0.2D, 0.95D, 0.5D, 0.4D);
				renderBlock(coverBlock, x, y, z);
			} 
			else if(meta == ForgeDirection.NORTH)
			{
				renderBlocks.setRenderBounds(0.0D, 0.9D, 0.0D, 1.0D, 1.0D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.1D, 0.1D, 0.2D, 1.0D, 0.9D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.1D, 0.0D, 0.2D, 0.2D, 0.1D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.1D, 0.0D, 0.9D, 0.2D, 0.1D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.75D, 0.2D, 0.1D, 1.0D, 0.8D, 0.2D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.2D, 0.1D, 0.7D, 0.8D, 0.2D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.4D, 0.05D, 0.4D, 0.5D, 0.1D);
				renderBlock(coverBlock, x, y, z);
			} 
			else if(meta == ForgeDirection.SOUTH)
			{
				renderBlocks.setRenderBounds(0.0D, 0.9D, 0.0D, 1.0D, 1.0D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.1D, 0.0D, 0.9D, 0.9D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.8D, 0.0D, 0.0D, 0.9D, 0.1D, 0.1D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.8D, 0.0D, 0.7D, 0.9D, 0.1D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.2D, 0.8D, 0.25D, 0.8D, 0.9D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.2D, 0.8D, 0.8D, 0.8D, 0.9D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.6D, 0.4D, 0.9D, 0.8D, 0.5D, 0.95D);
				renderBlock(coverBlock, x, y, z);
			}
		}
		else
		{
			if(meta == ForgeDirection.WEST)
			{
				renderBlocks.setRenderBounds(0.0D, 0.9D, 0.0D, 1.0D, 1.0D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.1D, 0.1D, 1.0D, 0.9D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.1D, 0.3D, 0.1D, 0.2D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.9D, 0.0D, 0.1D, 1.0D, 0.1D, 0.2D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.1D, 0.2D, 0.75D, 0.2D, 0.8D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.1D, 0.2D, 0.2D, 0.2D, 0.8D, 0.7D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.05D, 0.4D, 0.5D, 0.1D, 0.5D, 0.7D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.05D, 0.4D, 0.75D, 0.1D, 0.5D, 0.95D);
				renderBlock(coverBlock, x, y, z);
			} 
			else if(meta == ForgeDirection.EAST)
			{
				renderBlocks.setRenderBounds(0.0D, 0.9D, 0.0D, 1.0D, 1.0D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.1D, 0.0D, 0.8D, 0.9D, 0.9D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.8D, 0.8D, 0.1D, 0.9D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.0D, 0.8D, 0.1D, 0.1D, 0.9D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.8D, 0.2D, 0.0D, 0.9D, 0.8D, 0.25D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.8D, 0.2D, 0.3D, 0.9D, 0.8D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.9D, 0.4D, 0.3D, 0.95D, 0.5D, 0.5D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.9D, 0.4D, 0.05D, 0.95D, 0.5D, 0.25D);
				renderBlock(coverBlock, x, y, z);
			}
			else if(meta == ForgeDirection.NORTH)
			{
				renderBlocks.setRenderBounds(0.0D, 0.9D, 0.0D, 1.0D, 1.0D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.1D, 0.2D, 0.9D, 0.9D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.8D, 0.0D, 0.2D, 0.9D, 0.1D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.8D, 0.0D, 0.9D, 0.9D, 0.1D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.2D, 0.1D, 0.25D, 0.8D, 0.2D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.2D, 0.1D, 0.8D, 0.8D, 0.2D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.05D, 0.5D, 0.5D, 0.1D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.05D, 0.4D, 0.05D, 0.25D, 0.5D, 0.1D);
				renderBlock(coverBlock, x, y, z);
			} 
			else if(meta == ForgeDirection.SOUTH)
			{
				renderBlocks.setRenderBounds(0.0D, 0.9D, 0.0D, 1.0D, 1.0D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.1D, 0.1D, 0.0D, 1.0D, 0.9D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.1D, 0.0D, 0.0D, 0.2D, 0.1D, 0.1D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.1D, 0.0D, 0.7D, 0.2D, 0.1D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.75D, 0.2D, 0.8D, 1.0D, 0.8D, 0.9D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.2D, 0.8D, 0.7D, 0.8D, 0.9D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.5D, 0.4D, 0.9D, 0.7D, 0.5D, 0.95D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.75D, 0.4D, 0.9D, 0.95D, 0.5D, 0.95D);
				renderBlock(coverBlock, x, y, z);
			}
		}
		
	}
	
	@Override
	public boolean shouldRender3DInInventory(int paramInt)
	{
		return false;
	}
}
