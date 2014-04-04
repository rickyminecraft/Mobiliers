package mobiliers.renderer;

import carpentersblocks.renderer.BlockHandlerBase;
import carpentersblocks.util.BlockProperties;
import mobiliers.mobilier;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class Chaines implements ISimpleBlockRenderingHandler// extends BlockHandlerBase
{	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderer)
	{
		// TODO Auto-generated method stub
		
	}

//	@Override
	/**
	 * Renders stairs at the given coordinates
	 */
//	public void renderCarpentersBlock(int x, int y, int z)
//	{
//		ItemStack coverBlock = BlockProperties.hasCover(TE, 6) == true ? BlockProperties.getCover(TE, coverRendering) : BlockProperties.getCover(TE, 6);
//		renderChaines(coverBlock, x, y, z);
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
//	}
	
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer)
	{
		Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
        float f = 1.0F;
        int l = block.colorMultiplier(world, x, y, z);
        float f1 = (l >> 16 & 255) / 255.0F;
        float f2 = (l >> 8 & 255) / 255.0F;
        float f3 = (l & 255) / 255.0F;

        if (EntityRenderer.anaglyphEnable)
        {
            float f4 = (f1 * 30.0F + f2 * 59.0F + f3 * 11.0F) / 100.0F;
            float f5 = (f1 * 30.0F + f2 * 70.0F) / 100.0F;
            float f6 = (f1 * 30.0F + f3 * 70.0F) / 100.0F;
            f1 = f4;
            f2 = f5;
            f3 = f6;
        }

        tessellator.setColorOpaque_F(f * f1, f * f2, f * f3);
        double d0 = x;
        double d1 = y;
        double d2 = z;

        this.drawCrossedSquares(block, world.getBlockMetadata(x, y, z), d0, d1, d2, 1.0F, renderer);
        return true;
	}
	
	public void drawCrossedSquares(Block par1Block, int par2, double par3, double par5, double par7, float par9, RenderBlocks renderer)
    {
        Tessellator tessellator = Tessellator.instance;
        IIcon icon = renderer.getBlockIconFromSideAndMetadata(par1Block, 0, par2);
      IIcon icon2 = renderer.getBlockIconFromSideAndMetadata(par1Block, 1, par2);
        if (renderer.hasOverrideBlockTexture())
        {
            icon = renderer.overrideBlockTexture;
        }

        double d3 = icon.getMinU();
        double d4 = icon.getMinV();
        double d5 = icon.getMaxU();
        double d6 = icon.getMaxV();
        double d7 = 0.45D * par9;
        double d8 = par3 + 0.5D - d7;
        double d9 = par3 + 0.5D + d7;
        double d10 = par7 + 0.5D - d7;
        double d11 = par7 + 0.5D + d7;
        tessellator.addVertexWithUV(d8, par5 + par9, d10, d3, d4);
        tessellator.addVertexWithUV(d8, par5 + 0.0D, d10, d3, d6);
        tessellator.addVertexWithUV(d9, par5 + 0.0D, d11, d5, d6);
        tessellator.addVertexWithUV(d9, par5 + par9, d11, d5, d4);
        tessellator.addVertexWithUV(d9, par5 + par9, d11, d3, d4);
        tessellator.addVertexWithUV(d9, par5 + 0.0D, d11, d3, d6);
        tessellator.addVertexWithUV(d8, par5 + 0.0D, d10, d5, d6);
        tessellator.addVertexWithUV(d8, par5 + par9, d10, d5, d4);
        icon = icon2;
        d3 = icon.getMinU();
        d4 = icon.getMinV();
        d5 = icon.getMaxU();
        d6 = icon.getMaxV();
        tessellator.addVertexWithUV(d8, par5 + par9, d11, d3, d4);
        tessellator.addVertexWithUV(d8, par5 + 0.0D, d11, d3, d6);
        tessellator.addVertexWithUV(d9, par5 + 0.0D, d10, d5, d6);
        tessellator.addVertexWithUV(d9, par5 + par9, d10, d5, d4);
        tessellator.addVertexWithUV(d9, par5 + par9, d10, d3, d4);
        tessellator.addVertexWithUV(d9, par5 + 0.0D, d10, d3, d6);
        tessellator.addVertexWithUV(d8, par5 + 0.0D, d11, d5, d6);
        tessellator.addVertexWithUV(d8, par5 + par9, d11, d5, d4);
    }

	@Override
	public boolean shouldRender3DInInventory(int paramInt)
	{
		return false;
	}

	@Override
	public int getRenderId()
	{
		return mobilier.ChainesID;
	}
}
