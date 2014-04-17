package mobiliers.renderer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mobiliers.data.StorcheD;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import carpentersblocks.renderer.BlockHandlerBase;
import carpentersblocks.util.BlockProperties;

@SideOnly(Side.CLIENT)
public class Support_torche extends BlockHandlerBase
{
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderBlocks)
	{
        for(int l = 0; l < 6; l++)
        {
            if(l == 0)
            {
            	renderBlocks.setRenderBounds(0.9D, 0.2D, 0.4D, 1.0D, 0.4D, 0.6D);
            }
            if(l == 1)
            {
            	renderBlocks.setRenderBounds(0.8D, 0.25D, 0.45D, 0.9D, 0.35D, 0.55D);
            }
            if(l == 2)
            {
            	renderBlocks.setRenderBounds(0.75D, 0.2D, 0.4D, 0.8D, 0.4D, 0.6D);
            }
            if(l == 3)
            {
            	renderBlocks.setRenderBounds(0.6D, 0.2D, 0.4D, 0.65D, 0.4D, 0.6D);
            }
            if(l == 4)
            {
            	renderBlocks.setRenderBounds(0.65D, 0.2D, 0.4D, 0.75D, 0.4D, 0.45D);
            }
            if(l == 5)
            {
            	renderBlocks.setRenderBounds(0.65D, 0.2D, 0.55D, 0.75D, 0.4D, 0.6D);
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
		renderSupport(coverBlock, x, y, z);
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
	
	private void renderSupport(ItemStack coverBlock, int x, int y, int z)
	{
		int data = BlockProperties.getMetadata(TE);
		int Direction = StorcheD.getRotation(data);
		Block torch = Blocks.torch;
		Tessellator tessellator = Tessellator.instance;
		IIcon icon = renderBlocks.getBlockIconFromSideAndMetadata(torch, 0, 0);
    	double d5 = icon.getMinU();
    	double d6 = icon.getMinV();
    	double d7 = icon.getMaxU();
    	double d8 = icon.getMaxV();
    	double d9 = icon.getInterpolatedU(7.0D);
    	double d10 = icon.getInterpolatedV(6.0D);
    	double d11 = icon.getInterpolatedU(9.0D);
    	double d12 = icon.getInterpolatedV(8.0D);
    	double d13 = icon.getInterpolatedU(7.0D);
    	double d14 = icon.getInterpolatedV(13.0D);
    	double d15 = icon.getInterpolatedU(9.0D);
    	double d16 = icon.getInterpolatedV(15.0D);
    	
        boolean i = StorcheD.getType(data) == StorcheD.ALLUME? true : false;
        if(Direction == StorcheD.STORCHE_X_NEG)
        {
			renderBlocks.setRenderBounds(0.9D, 0.2D, 0.4D, 1.0D, 0.4D, 0.6D);
			renderBlock(coverBlock, x, y, z);
			renderBlocks.setRenderBounds(0.8D, 0.25D, 0.45D, 0.9D, 0.35D, 0.55D);
			renderBlock(coverBlock, x, y, z);
			renderBlocks.setRenderBounds(0.75D, 0.2D, 0.4D, 0.8D, 0.4D, 0.6D);
			renderBlock(coverBlock, x, y, z);
			renderBlocks.setRenderBounds(0.6D, 0.2D, 0.4D, 0.65D, 0.4D, 0.6D);
			renderBlock(coverBlock, x, y, z);
			renderBlocks.setRenderBounds(0.65D, 0.2D, 0.4D, 0.75D, 0.4D, 0.45D);
			renderBlock(coverBlock, x, y, z);
			renderBlocks.setRenderBounds(0.65D, 0.2D, 0.55D, 0.75D, 0.4D, 0.6D);
			renderBlock(coverBlock, x, y, z);
            if (i == true)//est
            {
                tessellator.setBrightness(srcBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, TE.xCoord, TE.yCoord, TE.zCoord));
                tessellator.setColorOpaque_F(1.0F, 1.0F, 1.0F);
            	x += 0.0D;
            	z += 0.0D;
            	double d17 = x - 0.5D;
            	double d18 = x + 0.5D;
            	double d19 = z - 0.5D;
            	double d20 = z + 0.5D;
            	double d21 = 0.0625D;
            	double d22 = 0.625D;

                tessellator.addVertexWithUV(x + 0.0D * (1.0D - d22) - d21 + 0.7D, y + d22 + 0.15D, z + 0.0D * (1.0D - d22) - d21 + 0.5D, d9, d10);
                tessellator.addVertexWithUV(x + 0.0D * (1.0D - d22) - d21 + 0.7D, y + d22 + 0.15D, z + 0.0D * (1.0D - d22) + d21 + 0.5D, d9, d12);
                tessellator.addVertexWithUV(x + 0.0D * (1.0D - d22) + d21 + 0.7D, y + d22 + 0.15D, z + 0.0D * (1.0D - d22) + d21 + 0.5D, d11, d12);
                tessellator.addVertexWithUV(x + 0.0D * (1.0D - d22) + d21 + 0.7D, y + d22 + 0.15D, z + 0.0D * (1.0D - d22) - d21 + 0.5D, d11, d10);
            	
            	tessellator.addVertexWithUV(x + d21 + 0.7D, y + 0.15D, z - d21 + 0.5D, d15, d14);
            	tessellator.addVertexWithUV(x + d21 + 0.7D, y + 0.15D, z + d21 + 0.5D, d15, d16);
            	tessellator.addVertexWithUV(x - d21 + 0.7D, y + 0.15D, z + d21 + 0.5D, d13, d16);
            	tessellator.addVertexWithUV(x - d21 + 0.7D, y + 0.15D, z - d21 + 0.5D, d13, d14);
            	
            	tessellator.addVertexWithUV(x - d21 + 0.7D, y + 1.15D, d19 + 0.5D, d5, d6);
            	tessellator.addVertexWithUV(x - d21 + 0.7D, y + 0.15D, d19 + 0.5D, d5, d8);
            	tessellator.addVertexWithUV(x - d21 + 0.7D, y + 0.15D, d20 + 0.5D, d7, d8);
            	tessellator.addVertexWithUV(x - d21 + 0.7D, y + 1.15D, d20 + 0.5D, d7, d6);
            	
            	tessellator.addVertexWithUV(x + d21 + 0.7D, y + 1.15D, d20 + 0.5D, d5, d6);
            	tessellator.addVertexWithUV(x + d21 + 0.7D, y + 0.15D, d20 + 0.5D, d5, d8);
            	tessellator.addVertexWithUV(x + d21 + 0.7D, y + 0.15D, d19 + 0.5D, d7, d8);
            	tessellator.addVertexWithUV(x + d21 + 0.7D, y + 1.15D, d19 + 0.5D, d7, d6);
            	
            	tessellator.addVertexWithUV(d17 + 0.7D, y + 1.15D, z + d21 + 0.5D, d5, d6);
            	tessellator.addVertexWithUV(d17 + 0.7D, y + 0.15D, z + d21 + 0.5D, d5, d8);
            	tessellator.addVertexWithUV(d18 + 0.7D, y + 0.15D, z + d21 + 0.5D, d7, d8);
            	tessellator.addVertexWithUV(d18 + 0.7D, y + 1.15D, z + d21 + 0.5D, d7, d6);
            	
            	tessellator.addVertexWithUV(d18 + 0.7D, y + 1.15D, z - d21 + 0.5D, d5, d6);
            	tessellator.addVertexWithUV(d18 + 0.7D, y + 0.15D, z - d21 + 0.5D, d5, d8);
            	tessellator.addVertexWithUV(d17 + 0.7D, y + 0.15D, z - d21 + 0.5D, d7, d8);
            	tessellator.addVertexWithUV(d17 + 0.7D, y + 1.15D, z - d21 + 0.5D, d7, d6);
            }
        } 
        else if(Direction == StorcheD.STORCHE_X_POS)
        {

			renderBlocks.setRenderBounds(0.0D, 0.2D, 0.4D, 0.1D, 0.4D, 0.6D);
			renderBlock(coverBlock, x, y, z);
			renderBlocks.setRenderBounds(0.1D, 0.25D, 0.45D, 0.2D, 0.35D, 0.55D);
			renderBlock(coverBlock, x, y, z);
			renderBlocks.setRenderBounds(0.2D, 0.2D, 0.4D, 0.25D, 0.4D, 0.6D);
			renderBlock(coverBlock, x, y, z);
			renderBlocks.setRenderBounds(0.35D, 0.2D, 0.4D, 0.4D, 0.4D, 0.6D);
			renderBlock(coverBlock, x, y, z);
			renderBlocks.setRenderBounds(0.25D, 0.2D, 0.4D, 0.35D, 0.4D, 0.45D);
			renderBlock(coverBlock, x, y, z);
			renderBlocks.setRenderBounds(0.25D, 0.2D, 0.55D, 0.35D, 0.4D, 0.6D);
			renderBlock(coverBlock, x, y, z);
            if (i == true)
            {
                tessellator.setBrightness(srcBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, TE.xCoord, TE.yCoord, TE.zCoord));
                tessellator.setColorOpaque_F(1.0F, 1.0F, 1.0F);
            	x += 1.0D;
            	z += 1.0D;
            	double d17 = x - 0.5D;
            	double d18 = x + 0.5D;
            	double d19 = z - 0.5D;
            	double d20 = z + 0.5D;
            	double d21 = 0.0625D;
            	double d22 = 0.625D;
                tessellator.addVertexWithUV(x + 0.0D * (1.0D - d22) - d21 - 0.7D, y + d22 + 0.15D, z + 0.0D * (1.0D - d22) - d21 - 0.5D, d9, d10);
                tessellator.addVertexWithUV(x + 0.0D * (1.0D - d22) - d21 - 0.7D, y + d22 + 0.15D, z + 0.0D * (1.0D - d22) + d21 - 0.5D, d9, d12);
                tessellator.addVertexWithUV(x + 0.0D * (1.0D - d22) + d21 - 0.7D, y + d22 + 0.15D, z + 0.0D * (1.0D - d22) + d21 - 0.5D, d11, d12);
                tessellator.addVertexWithUV(x + 0.0D * (1.0D - d22) + d21 - 0.7D, y + d22 + 0.15D, z + 0.0D * (1.0D - d22) - d21 - 0.5D, d11, d10);
            	tessellator.addVertexWithUV(x + d21 - 0.7D, y + 0.15D, z - d21 - 0.5D, d15, d14);
            	tessellator.addVertexWithUV(x + d21 - 0.7D, y + 0.15D, z + d21 - 0.5D, d15, d16);
            	tessellator.addVertexWithUV(x - d21 - 0.7D, y + 0.15D, z + d21 - 0.5D, d13, d16);
            	tessellator.addVertexWithUV(x - d21 - 0.7D, y + 0.15D, z - d21 - 0.5D, d13, d14);
            	tessellator.addVertexWithUV(x - d21 - 0.7D, y + 1.15D, d19 - 0.5D, d5, d6);
            	tessellator.addVertexWithUV(x - d21 - 0.7D, y + 0.15D, d19 - 0.5D, d5, d8);
            	tessellator.addVertexWithUV(x - d21 - 0.7D, y + 0.15D, d20 - 0.5D, d7, d8);
            	tessellator.addVertexWithUV(x - d21 - 0.7D, y + 1.15D, d20 - 0.5D, d7, d6);
            	tessellator.addVertexWithUV(x + d21 - 0.7D, y + 1.15D, d20 - 0.5D, d5, d6);
            	tessellator.addVertexWithUV(x + d21 - 0.7D, y + 0.15D, d20 - 0.5D, d5, d8);
            	tessellator.addVertexWithUV(x + d21 - 0.7D, y + 0.15D, d19 - 0.5D, d7, d8);
            	tessellator.addVertexWithUV(x + d21 - 0.7D, y + 1.15D, d19 - 0.5D, d7, d6);
            	tessellator.addVertexWithUV(d17 - 0.7D, y + 1.15D, z + d21 - 0.5D, d5, d6);
            	tessellator.addVertexWithUV(d17 - 0.7D, y + 0.15D, z + d21 - 0.5D, d5, d8);
            	tessellator.addVertexWithUV(d18 - 0.7D, y + 0.15D, z + d21 - 0.5D, d7, d8);
            	tessellator.addVertexWithUV(d18 - 0.7D, y + 1.15D, z + d21 - 0.5D, d7, d6);
            	tessellator.addVertexWithUV(d18 - 0.7D, y + 1.15D, z - d21 - 0.5D, d5, d6);
            	tessellator.addVertexWithUV(d18 - 0.7D, y + 0.15D, z - d21 - 0.5D, d5, d8);
            	tessellator.addVertexWithUV(d17 - 0.7D, y + 0.15D, z - d21 - 0.5D, d7, d8);
            	tessellator.addVertexWithUV(d17 - 0.7D, y + 1.15D, z - d21 - 0.5D, d7, d6);
            }
        } 
        else if(Direction == StorcheD.STORCHE_Z_NEG)
        {

			renderBlocks.setRenderBounds(0.4D, 0.2D, 0.9D, 0.6D, 0.4D, 1.0D);
			renderBlock(coverBlock, x, y, z);
			renderBlocks.setRenderBounds(0.45D, 0.25D, 0.8D, 0.55D, 0.35D, 0.9D);
			renderBlock(coverBlock, x, y, z);
			renderBlocks.setRenderBounds(0.4D, 0.2D, 0.75D, 0.6D, 0.4D, 0.8D);
			renderBlock(coverBlock, x, y, z);
			renderBlocks.setRenderBounds(0.4D, 0.2D, 0.6D, 0.6D, 0.4D, 0.65D);
			renderBlock(coverBlock, x, y, z);
			renderBlocks.setRenderBounds(0.4D, 0.2D, 0.65D, 0.45D, 0.4D, 0.75D);
			renderBlock(coverBlock, x, y, z);
			renderBlocks.setRenderBounds(0.55D, 0.2D, 0.65D, 0.6D, 0.4D, 0.75D);
			renderBlock(coverBlock, x, y, z);
            if (i == true)
            {
                tessellator.setBrightness(srcBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, TE.xCoord, TE.yCoord, TE.zCoord));
                tessellator.setColorOpaque_F(1.0F, 1.0F, 1.0F);
            	x += 1.0D;
            	z += 1.0D;
            	double d17 = x - 0.5D;
            	double d18 = x + 0.5D;
            	double d19 = z - 0.5D;
            	double d20 = z + 0.5D;
            	double d21 = 0.0625D;
            	double d22 = 0.625D;
                tessellator.addVertexWithUV(x + 0.0D * (1.0D - d22) - d21 - 0.5D, y + d22 + 0.15D, z + 0.0D * (1.0D - d22) - d21 - 0.3D, d9, d10);
                tessellator.addVertexWithUV(x + 0.0D * (1.0D - d22) - d21 - 0.5D, y + d22 + 0.15D, z + 0.0D * (1.0D - d22) + d21 - 0.3D, d9, d12);
                tessellator.addVertexWithUV(x + 0.0D * (1.0D - d22) + d21 - 0.5D, y + d22 + 0.15D, z + 0.0D * (1.0D - d22) + d21 - 0.3D, d11, d12);
                tessellator.addVertexWithUV(x + 0.0D * (1.0D - d22) + d21 - 0.5D, y + d22 + 0.15D, z + 0.0D * (1.0D - d22) - d21 - 0.3D, d11, d10);
            	tessellator.addVertexWithUV(x + d21 - 0.5D, y + 0.15D, z - d21 - 0.3D, d15, d14);
            	tessellator.addVertexWithUV(x + d21 - 0.5D, y + 0.15D, z + d21 - 0.3D, d15, d16);
            	tessellator.addVertexWithUV(x - d21 - 0.5D, y + 0.15D, z + d21 - 0.3D, d13, d16);
            	tessellator.addVertexWithUV(x - d21 - 0.5D, y + 0.15D, z - d21 - 0.3D, d13, d14);
            	tessellator.addVertexWithUV(x - d21 - 0.5D, y + 1.15D, d19 - 0.3D, d5, d6);
            	tessellator.addVertexWithUV(x - d21 - 0.5D, y + 0.15D, d19 - 0.3D, d5, d8);
            	tessellator.addVertexWithUV(x - d21 - 0.5D, y + 0.15D, d20 - 0.3D, d7, d8);
            	tessellator.addVertexWithUV(x - d21 - 0.5D, y + 1.15D, d20 - 0.3D, d7, d6);
            	tessellator.addVertexWithUV(x + d21 - 0.5D, y + 1.15D, d20 - 0.3D, d5, d6);
            	tessellator.addVertexWithUV(x + d21 - 0.5D, y + 0.15D, d20 - 0.3D, d5, d8);
            	tessellator.addVertexWithUV(x + d21 - 0.5D, y + 0.15D, d19 - 0.3D, d7, d8);
            	tessellator.addVertexWithUV(x + d21 - 0.5D, y + 1.15D, d19 - 0.3D, d7, d6);
            	tessellator.addVertexWithUV(d17 - 0.5D, y + 1.15D, z + d21 - 0.3D, d5, d6);
            	tessellator.addVertexWithUV(d17 - 0.5D, y + 0.15D, z + d21 - 0.3D, d5, d8);
            	tessellator.addVertexWithUV(d18 - 0.5D, y + 0.15D, z + d21 - 0.3D, d7, d8);
            	tessellator.addVertexWithUV(d18 - 0.5D, y + 1.15D, z + d21 - 0.3D, d7, d6);
            	tessellator.addVertexWithUV(d18 - 0.5D, y + 1.15D, z - d21 - 0.3D, d5, d6);
            	tessellator.addVertexWithUV(d18 - 0.5D, y + 0.15D, z - d21 - 0.3D, d5, d8);
            	tessellator.addVertexWithUV(d17 - 0.5D, y + 0.15D, z - d21 - 0.3D, d7, d8);
            	tessellator.addVertexWithUV(d17 - 0.5D, y + 1.15D, z - d21 - 0.3D, d7, d6);
            }
        } 
        else if(Direction == StorcheD.STORCHE_Z_POS)
        {

			renderBlocks.setRenderBounds(0.4D, 0.2D, 0.0D, 0.6D, 0.4D, 0.1D);
			renderBlock(coverBlock, x, y, z);
			renderBlocks.setRenderBounds(0.45D, 0.25D, 0.1D, 0.55D, 0.35D, 0.2D);
			renderBlock(coverBlock, x, y, z);
			renderBlocks.setRenderBounds(0.4D, 0.2D, 0.2D, 0.6D, 0.4D, 0.25D);
			renderBlock(coverBlock, x, y, z);
			renderBlocks.setRenderBounds(0.4D, 0.2D, 0.35D, 0.6D, 0.4D, 0.4D);
			renderBlock(coverBlock, x, y, z);
			renderBlocks.setRenderBounds(0.4D, 0.2D, 0.25D, 0.45D, 0.4D, 0.35D);
			renderBlock(coverBlock, x, y, z);
			renderBlocks.setRenderBounds(0.55D, 0.2D, 0.25D, 0.6D, 0.4D, 0.35D);
			renderBlock(coverBlock, x, y, z);
            if (i == true)//nord
            {
                tessellator.setBrightness(srcBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, TE.xCoord, TE.yCoord, TE.zCoord));
                tessellator.setColorOpaque_F(1.0F, 1.0F, 1.0F);
            	x += 0.5D;
            	z += 0.0D;
            	double d17 = x - 0.5D;
            	double d18 = x + 0.5D;
            	double d19 = z - 0.5D;
            	double d20 = z + 0.5D;
            	double d21 = 0.0625D;
            	double d22 = 0.625D;
                tessellator.addVertexWithUV(x + 0.0D * (1.0D - d22) - d21 - 0.5D, y + d22 + 0.15D, z + 0.0D * (1.0D - d22) - d21 + 0.3D, d9, d10);
                tessellator.addVertexWithUV(x + 0.0D * (1.0D - d22) - d21 - 0.5D, y + d22 + 0.15D, z + 0.0D * (1.0D - d22) + d21 + 0.3D, d9, d12);
                tessellator.addVertexWithUV(x + 0.0D * (1.0D - d22) + d21 - 0.5D, y + d22 + 0.15D, z + 0.0D * (1.0D - d22) + d21 + 0.3D, d11, d12);
                tessellator.addVertexWithUV(x + 0.0D * (1.0D - d22) + d21 - 0.5D, y + d22 + 0.15D, z + 0.0D * (1.0D - d22) - d21 + 0.3D, d11, d10);
            	tessellator.addVertexWithUV(x + d21 - 0.5D, y + 0.15D, z - d21 + 0.3D, d15, d14);
            	tessellator.addVertexWithUV(x + d21 - 0.5D, y + 0.15D, z + d21 + 0.3D, d15, d16);
            	tessellator.addVertexWithUV(x - d21 - 0.5D, y + 0.15D, z + d21 + 0.3D, d13, d16);
            	tessellator.addVertexWithUV(x - d21 - 0.5D, y + 0.15D, z - d21 + 0.3D, d13, d14);
            	tessellator.addVertexWithUV(x - d21 - 0.5D, y + 1.15D, d19 + 0.3D, d5, d6);
            	tessellator.addVertexWithUV(x - d21 - 0.5D, y + 0.15D, d19 + 0.3D, d5, d8);
            	tessellator.addVertexWithUV(x - d21 - 0.5D, y + 0.15D, d20 + 0.3D, d7, d8);
            	tessellator.addVertexWithUV(x - d21 - 0.5D, y + 1.15D, d20 + 0.3D, d7, d6);
            	tessellator.addVertexWithUV(x + d21 - 0.5D, y + 1.15D, d20 + 0.3D, d5, d6);
            	tessellator.addVertexWithUV(x + d21 - 0.5D, y + 0.15D, d20 + 0.3D, d5, d8);
            	tessellator.addVertexWithUV(x + d21 - 0.5D, y + 0.15D, d19 + 0.3D, d7, d8);
            	tessellator.addVertexWithUV(x + d21 - 0.5D, y + 1.15D, d19 + 0.3D, d7, d6);
            	tessellator.addVertexWithUV(d17 - 0.5D, y + 1.15D, z + d21 + 0.3D, d5, d6);
            	tessellator.addVertexWithUV(d17 - 0.5D, y + 0.15D, z + d21 + 0.3D, d5, d8);
            	tessellator.addVertexWithUV(d18 - 0.5D, y + 0.15D, z + d21 + 0.3D, d7, d8);
            	tessellator.addVertexWithUV(d18 - 0.5D, y + 1.15D, z + d21 + 0.3D, d7, d6);
            	tessellator.addVertexWithUV(d18 - 0.5D, y + 1.15D, z - d21 + 0.3D, d5, d6);
            	tessellator.addVertexWithUV(d18 - 0.5D, y + 0.15D, z - d21 + 0.3D, d5, d8);
            	tessellator.addVertexWithUV(d17 - 0.5D, y + 0.15D, z - d21 + 0.3D, d7, d8);
            	tessellator.addVertexWithUV(d17 - 0.5D, y + 1.15D, z - d21 + 0.3D, d7, d6);
            }
        }
	}
}
