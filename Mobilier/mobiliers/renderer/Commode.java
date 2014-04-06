package mobiliers.renderer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;

import carpentersblocks.renderer.BlockHandlerBase;
import carpentersblocks.util.BlockProperties;

@SideOnly(Side.CLIENT)
public class Commode extends BlockHandlerBase
{
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderBlocks)
	{
		for(int l = 0; l < 5; l++)
		{
			switch (l)
			{
				case 0:
					renderBlocks.setRenderBounds(0.0D, 0.0D, 0.0D, 0.1D, 1.0D, 1.0D);
					break;
				case 1:
					renderBlocks.setRenderBounds(0.9D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
					break;
				case 2:
					renderBlocks.setRenderBounds(0.1D, 0.9D, 0.0D, 0.9D, 1.0D, 1.0D);
					break;
				case 3:
					renderBlocks.setRenderBounds(0.1D, 0.5D, 0.0D, 0.9D, 0.9D, 0.7D);
					break;
				case 4:
					renderBlocks.setRenderBounds(0.4D, 0.6D, 0.7D, 0.6D, 0.8D, 0.8D);
			}
			super.renderInventoryBlock(block, metadata, modelID, renderBlocks);
		}
	}

	@Override
	/**
	 * Renders stairs at the given coordinates
	 */
	public void renderCarpentersBlock(int x, int y, int z)
	{
		ItemStack coverBlock = BlockProperties.hasCover(TE, 6) == true ? BlockProperties.getCover(TE, coverRendering) : BlockProperties.getCover(TE, 6);
		renderCommode(coverBlock, x, y, z);
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
	
	public void renderCommode(ItemStack coverBlock, int x, int y, int z)
	{
		int data = BlockProperties.getMetadata(TE) & 0x3;
		
		ForgeDirection meta = BlockProperties.getDirectionFromFacing(data);
        //IIcon icon = renderBlocks.getBlockIconFromSideAndMetadata(Block.getBlockFromName("planks"), 0, meta);
        if(meta == ForgeDirection.WEST)
        {
        	renderBlocks.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.1D);
        	renderBlock(coverBlock, x, y, z);
            renderBlocks.setRenderBounds(0.0D, 0.0D, 0.9D, 1.0D, 1.0D, 1.0D);
            renderBlock(coverBlock, x, y, z);
            renderBlocks.setRenderBounds(0.0D, 0.9D, 0.1D, 1.0D, 1.0D, 0.9D);
            renderBlock(coverBlock, x, y, z);
            renderBlocks.setRenderBounds(0.2D, 0.4D, 0.1D, 1.0D, 0.9D, 0.9D);
            renderBlock(coverBlock, x, y, z);
            //renderBlocks.setOverrideBlockTexture(icon);
            renderBlocks.setRenderBounds(0.1D, 0.5D, 0.1D, 0.2D, 0.9D, 0.9D);
            renderBlock(coverBlock, x, y, z);
            renderBlocks.setRenderBounds(0.0D, 0.6D, 0.4D, 0.1D, 0.8D, 0.6D);
            renderBlock(coverBlock, x, y, z);
            //renderBlocks.clearOverrideBlockTexture();
        } else
        if(meta == ForgeDirection.EAST)//4
        {
        	renderBlocks.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.1D);
        	renderBlock(coverBlock, x, y, z);
            renderBlocks.setRenderBounds(0.0D, 0.0D, 0.9D, 1.0D, 1.0D, 1.0D);
            renderBlock(coverBlock, x, y, z);
            renderBlocks.setRenderBounds(0.0D, 0.9D, 0.1D, 1.0D, 1.0D, 0.9D);
            renderBlock(coverBlock, x, y, z);
            renderBlocks.setRenderBounds(0.0D, 0.4D, 0.1D, 0.8D, 0.9D, 0.9D);
            renderBlock(coverBlock, x, y, z);
            //renderBlocks.setOverrideBlockTexture(icon);
            renderBlocks.setRenderBounds(0.8D, 0.5D, 0.1D, 0.9D, 0.9D, 0.9D);
            renderBlock(coverBlock, x, y, z);
            renderBlocks.setRenderBounds(0.9D, 0.6D, 0.4D, 1.0D, 0.8D, 0.6D);
            renderBlock(coverBlock, x, y, z);
            //renderBlocks.clearOverrideBlockTexture();
        } else
        if(meta == ForgeDirection.NORTH)//8
        {
        	renderBlocks.setRenderBounds(0.0D, 0.0D, 0.0D, 0.1D, 1.0D, 1.0D);
        	renderBlock(coverBlock, x, y, z);
            renderBlocks.setRenderBounds(0.9D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
            renderBlock(coverBlock, x, y, z);
            renderBlocks.setRenderBounds(0.1D, 0.9D, 0.0D, 0.9D, 1.0D, 1.0D);
            renderBlock(coverBlock, x, y, z);
            renderBlocks.setRenderBounds(0.1D, 0.4D, 0.2D, 0.9D, 0.9D, 1.0D);
            renderBlock(coverBlock, x, y, z);
            //renderBlocks.setOverrideBlockTexture(icon);
            renderBlocks.setRenderBounds(0.1D, 0.5D, 0.1D, 0.9D, 0.9D, 0.2D);
            renderBlock(coverBlock, x, y, z);
            renderBlocks.setRenderBounds(0.4D, 0.6D, 0.0D, 0.6D, 0.8D, 0.1D);
            renderBlock(coverBlock, x, y, z);
            //renderBlocks.clearOverrideBlockTexture();
        } else
        if(meta == ForgeDirection.SOUTH)//12
        {
        	renderBlocks.setRenderBounds(0.0D, 0.0D, 0.0D, 0.1D, 1.0D, 1.0D);
        	renderBlock(coverBlock, x, y, z);
            renderBlocks.setRenderBounds(0.9D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
            renderBlock(coverBlock, x, y, z);
            renderBlocks.setRenderBounds(0.1D, 0.9D, 0.0D, 0.9D, 1.0D, 1.0D);
            renderBlock(coverBlock, x, y, z);
            renderBlocks.setRenderBounds(0.1D, 0.4D, 0.0D, 0.9D, 0.9D, 0.8D);
            renderBlock(coverBlock, x, y, z);
            //renderBlocks.setOverrideBlockTexture(icon);
            renderBlocks.setRenderBounds(0.1D, 0.5D, 0.8D, 0.9D, 0.9D, 0.9D);
            renderBlock(coverBlock, x, y, z);
            renderBlocks.setRenderBounds(0.4D, 0.6D, 0.9D, 0.6D, 0.8D, 1.0D);
            renderBlock(coverBlock, x, y, z);
            //renderBlocks.clearOverrideBlockTexture();
        }
	}
}
