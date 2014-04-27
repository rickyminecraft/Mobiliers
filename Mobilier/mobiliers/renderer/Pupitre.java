package mobiliers.renderer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mobiliers.data.PupitreD;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import carpentersblocks.renderer.BlockHandlerBase;
import carpentersblocks.util.BlockProperties;

@SideOnly(Side.CLIENT)
public class Pupitre extends BlockHandlerBase
{
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderBlocks)
	{
		Tessellator tessellator = Tessellator.instance;
		for(int box = 0; box < 3; box++)
		{
			switch (box)
			{
			case 0:
				renderBlocks.setRenderBounds(0.3D, 0.0D, 0.3D, 0.7D, 0.1D, 0.7D);
				break;
			case 1:
				renderBlocks.setRenderBounds(0.4D, 0.1D, 0.4D, 0.6D, 0.7D, 0.6D);
				break;
			case 2:
				renderBlocks.setRenderBounds(0.2D, 0.7D, 0.2D, 0.8D, 0.8D, 0.8D);
			}
			super.renderInventoryBlock(block, metadata, modelID, renderBlocks);
		}
	}
	
	@Override
	/**
	 * Renders pupitre at the given coordinates
	 */
	public void renderCarpentersBlock(int x, int y, int z)
	{
		ItemStack coverBlock = BlockProperties.hasCover(TE, 6) == true ? BlockProperties.getCover(TE, coverRendering) : BlockProperties.getCover(TE, 6);
		renderPupitre(coverBlock, x, y, z);
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
	
	private void renderPupitre(ItemStack coverBlock, int x, int y, int z)
	{
		int data = BlockProperties.getMetadata(TE);
		int rotation = PupitreD.getRotation(data);
		Tessellator tessellator = Tessellator.instance;
		tessellator.setBrightness(srcBlock.getMixedBrightnessForBlock(renderBlocks.blockAccess, TE.xCoord, TE.yCoord, TE.zCoord));
		tessellator.setColorOpaque_F(1.0F, 1.0F, 1.0F);
		IIcon icon = TE.getBlockType().getBlockTextureFromSide(5);
		if (icon.getIconName() == "CarpentersBlocks:general/blank")
		{
			icon = srcBlock.getIcon(TE.getWorldObj(), x, y, z, 1);
		}
		double d5 = icon.getMinU();
		double d6 = icon.getMinV();
		double d7 = icon.getMaxU();
		double d8 = icon.getMaxV();
		//derriere
		double d9 = icon.getInterpolatedU(0.0D);
		double d10 = icon.getInterpolatedV(5.0D);
		double d11 = icon.getInterpolatedU(16.0D);
		double d12 = icon.getInterpolatedV(9.0D);
		//cote
		double d13 = icon.getInterpolatedU(0.0D);
		double d14 = icon.getInterpolatedV(0.0D);
		double d15 = icon.getInterpolatedU(16.0D);
		double d16 = icon.getInterpolatedV(4.0D);
		double d34 = icon.getInterpolatedV(2.5D);
		//devant
		double d30 = icon.getInterpolatedU(0.0D);
		double d31 = icon.getInterpolatedV(2.5D);
		double d32 = icon.getInterpolatedU(16.0D);
		double d33 = icon.getInterpolatedV(4.0D);
		
		double d17 = 0.0D;
		double d18 = 0.0D;
		double d19 = 0.0D;
		double d20 = 0.0D;
		double d21 = 0.0D;
		double d22 = 0.0D;
		double d23 = 0.0D;
		double d24 = 0.0D;
		double d25 = 0.0D;
		double d26 = 0.0D;
		switch (rotation)
		{
		case PupitreD.PUPITRE_Z_NEG:
			renderBlocks.setRenderBounds(0.3D, 0.0D, 0.3D, 0.7D, 0.1D, 0.7D);
			renderBlock(coverBlock, x, y, z);
			renderBlocks.setRenderBounds(0.4D, 0.1D, 0.4D, 0.6D, 0.7D, 0.6D);
			renderBlock(coverBlock, x, y, z);
			d17 = x - 0.5D;
			d18 = x + 0.5D;
			d19 = z - 0.5D;
			d20 = z + 0.5D;
			d21 = 0.2D;
			d22 = 0.75D; //haut 1
			d23 = 0.7D; //bas
			d24 = 0.3D; //pour la largeur
			d25 = 0.5D; //pour centrer
			d26 = 0.85D; //haut 2
			//haut
			tessellator.addVertexWithUV(x - d24 + d25, y + d22, z - d24 + d25, d7, d6);
			tessellator.addVertexWithUV(x - d24 + d25, y + d22, z + d24 + d25, d7, d8);
			tessellator.addVertexWithUV(x + d24 + d25, y + d26, z + d24 + d25, d5, d8);
			tessellator.addVertexWithUV(x + d24 + d25, y + d26, z - d24 + d25, d5, d6);
			//bas
			tessellator.addVertexWithUV(x + d24 + d25, y + d23, z - d24 + d25, d7, d6);
			tessellator.addVertexWithUV(x + d24 + d25, y + d23, z + d24 + d25, d7, d8);
			tessellator.addVertexWithUV(x - d24 + d25, y + d23, z + d24 + d25, d5, d8);
			tessellator.addVertexWithUV(x - d24 + d25, y + d23, z - d24 + d25, d5, d6);
			//cote devant
			tessellator.addVertexWithUV(x - d24 + d25, y + d22, d19 + d25 + d21, d30, d31);
			tessellator.addVertexWithUV(x - d24 + d25, y + d23, d19 + d25 + d21, d30, d33);
			tessellator.addVertexWithUV(x - d24 + d25, y + d23, d20 + d25 - d21, d32, d33);
			tessellator.addVertexWithUV(x - d24 + d25, y + d22, d20 + d25 - d21, d32, d31);
			//cote derriere
			tessellator.addVertexWithUV(x + d24 + d25, y + d26, d20 + d25 - d21, d9, d10);
			tessellator.addVertexWithUV(x + d24 + d25, y + d23, d20 + d25 - d21, d9, d12);
			tessellator.addVertexWithUV(x + d24 + d25, y + d23, d19 + d25 + d21, d11, d12);
			tessellator.addVertexWithUV(x + d24 + d25, y + d26, d19 + d25 + d21, d11, d10);
			//cote droit
			tessellator.addVertexWithUV(d17 + d25 + d21, y + d22, z + d24 + d25, d13, d34);
			tessellator.addVertexWithUV(d17 + d25 + d21, y + d23, z + d24 + d25, d13, d16);
			tessellator.addVertexWithUV(d18 + d25 - d21, y + d23, z + d24 + d25, d15, d16);
			tessellator.addVertexWithUV(d18 + d25 - d21, y + d26, z + d24 + d25, d15, d14);
			//cote gauche
			tessellator.addVertexWithUV(d18 + d25 - d21, y + d26, z - d24 + d25, d13, d14);
			tessellator.addVertexWithUV(d18 + d25 - d21, y + d23, z - d24 + d25, d13, d16);
			tessellator.addVertexWithUV(d17 + d25 + d21, y + d23, z - d24 + d25, d15, d16);
			tessellator.addVertexWithUV(d17 + d25 + d21, y + d22, z - d24 + d25, d15, d34);
			break;
		case PupitreD.PUPITRE_X_POS:
			renderBlocks.setRenderBounds(0.3D, 0.0D, 0.3D, 0.7D, 0.1D, 0.7D);
			renderBlock(coverBlock, x, y, z);
			renderBlocks.setRenderBounds(0.4D, 0.1D, 0.4D, 0.6D, 0.7D, 0.6D);
			renderBlock(coverBlock, x, y, z);
			d17 = x - 0.5D;
			d18 = x + 0.5D;
			d19 = z - 0.5D;
			d20 = z + 0.5D;
			d21 = 0.2D;
			d22 = 0.75D; //haut 1
			d23 = 0.7D; //bas
			d24 = 0.3D; //pour la largeur
			d25 = 0.5D; //pour centrer
			d26 = 0.85D; //haut 2
			//haut
			tessellator.addVertexWithUV(x - d24 + d25, y + d26, z - d24 + d25, d7, d6);
			tessellator.addVertexWithUV(x - d24 + d25, y + d26, z + d24 + d25, d7, d8);
			tessellator.addVertexWithUV(x + d24 + d25, y + d22, z + d24 + d25, d5, d8);
			tessellator.addVertexWithUV(x + d24 + d25, y + d22, z - d24 + d25, d5, d6);
			//bas
			tessellator.addVertexWithUV(x + d24 + d25, y + d23, z - d24 + d25, d7, d6);
			tessellator.addVertexWithUV(x + d24 + d25, y + d23, z + d24 + d25, d7, d8);
			tessellator.addVertexWithUV(x - d24 + d25, y + d23, z + d24 + d25, d5, d8);
			tessellator.addVertexWithUV(x - d24 + d25, y + d23, z - d24 + d25, d5, d6);
			//cote devant
			tessellator.addVertexWithUV(x - d24 + d25, y + d26, d19 + d25 + d21, d9, d10);
			tessellator.addVertexWithUV(x - d24 + d25, y + d23, d19 + d25 + d21, d9, d12);
			tessellator.addVertexWithUV(x - d24 + d25, y + d23, d20 + d25 - d21, d11, d12);
			tessellator.addVertexWithUV(x - d24 + d25, y + d26, d20 + d25 - d21, d11, d10);
			//cote derriere
			tessellator.addVertexWithUV(x + d24 + d25, y + d22, d20 + d25 - d21, d30, d31);
			tessellator.addVertexWithUV(x + d24 + d25, y + d23, d20 + d25 - d21, d30, d33);
			tessellator.addVertexWithUV(x + d24 + d25, y + d23, d19 + d25 + d21, d32, d33);
			tessellator.addVertexWithUV(x + d24 + d25, y + d22, d19 + d25 + d21, d32, d31);
			//cote droit
			tessellator.addVertexWithUV(d17 + d25 + d21, y + d26, z + d24 + d25, d13, d14);
			tessellator.addVertexWithUV(d17 + d25 + d21, y + d23, z + d24 + d25, d13, d16);
			tessellator.addVertexWithUV(d18 + d25 - d21, y + d23, z + d24 + d25, d15, d16);
			tessellator.addVertexWithUV(d18 + d25 - d21, y + d22, z + d24 + d25, d15, d34);
			//cote gauche
			tessellator.addVertexWithUV(d18 + d25 - d21, y + d22, z - d24 + d25, d13, d34);
			tessellator.addVertexWithUV(d18 + d25 - d21, y + d23, z - d24 + d25, d13, d16);
			tessellator.addVertexWithUV(d17 + d25 + d21, y + d23, z - d24 + d25, d15, d16);
			tessellator.addVertexWithUV(d17 + d25 + d21, y + d26, z - d24 + d25, d15, d14);
			break;
		case PupitreD.PUPITRE_Z_POS:
			renderBlocks.setRenderBounds(0.3D, 0.0D, 0.3D, 0.7D, 0.1D, 0.7D);
			renderBlock(coverBlock, x, y, z);
			renderBlocks.setRenderBounds(0.4D, 0.1D, 0.4D, 0.6D, 0.7D, 0.6D);
			renderBlock(coverBlock, x, y, z);
			d17 = x - 0.5D;
			d18 = x + 0.5D;
			d19 = z - 0.5D;
			d20 = z + 0.5D;
			d21 = 0.2D;
			d22 = 0.75D; //haut 1
			d23 = 0.7D; //bas
			d24 = 0.3D; //pour la largeur
			d25 = 0.5D; //pour centrer
			d26 = 0.85D; //haut 2
			//haut
			tessellator.addVertexWithUV(x - d24 + d25, y + d22, z - d24 + d25, d7, d6);
			tessellator.addVertexWithUV(x - d24 + d25, y + d26, z + d24 + d25, d7, d8);
			tessellator.addVertexWithUV(x + d24 + d25, y + d26, z + d24 + d25, d5, d8);
			tessellator.addVertexWithUV(x + d24 + d25, y + d22, z - d24 + d25, d5, d6);
			//bas
			tessellator.addVertexWithUV(x + d24 + d25, y + d23, z - d24 + d25, d7, d6);
			tessellator.addVertexWithUV(x + d24 + d25, y + d23, z + d24 + d25, d7, d8);
			tessellator.addVertexWithUV(x - d24 + d25, y + d23, z + d24 + d25, d5, d8);
			tessellator.addVertexWithUV(x - d24 + d25, y + d23, z - d24 + d25, d5, d6);
			//cote gauche
			tessellator.addVertexWithUV(x - d24 + d25, y + d22, d19 + d25 + d21, d13, d34);
			tessellator.addVertexWithUV(x - d24 + d25, y + d23, d19 + d25 + d21, d13, d16);
			tessellator.addVertexWithUV(x - d24 + d25, y + d23, d20 + d25 - d21, d15, d16);
			tessellator.addVertexWithUV(x - d24 + d25, y + d26, d20 + d25 - d21, d15, d14);
			//cote droit
			tessellator.addVertexWithUV(x + d24 + d25, y + d26, d20 + d25 - d21, d13, d14);
			tessellator.addVertexWithUV(x + d24 + d25, y + d23, d20 + d25 - d21, d13, d16);
			tessellator.addVertexWithUV(x + d24 + d25, y + d23, d19 + d25 + d21, d15, d16);
			tessellator.addVertexWithUV(x + d24 + d25, y + d22, d19 + d25 + d21, d15, d34);
			//cote derriere
			tessellator.addVertexWithUV(d17 + d25 + d21, y + d26, z + d24 + d25, d9, d10);
			tessellator.addVertexWithUV(d17 + d25 + d21, y + d23, z + d24 + d25, d9, d12);
			tessellator.addVertexWithUV(d18 + d25 - d21, y + d23, z + d24 + d25, d11, d12);
			tessellator.addVertexWithUV(d18 + d25 - d21, y + d26, z + d24 + d25, d11, d10);
			//cote devant
			tessellator.addVertexWithUV(d18 + d25 - d21, y + d22, z - d24 + d25, d30, d31);
			tessellator.addVertexWithUV(d18 + d25 - d21, y + d23, z - d24 + d25, d30, d33);
			tessellator.addVertexWithUV(d17 + d25 + d21, y + d23, z - d24 + d25, d32, d33);
			tessellator.addVertexWithUV(d17 + d25 + d21, y + d22, z - d24 + d25, d32, d31);
			break;
		case PupitreD.PUPITRE_X_NEG:
			renderBlocks.setRenderBounds(0.3D, 0.0D, 0.3D, 0.7D, 0.1D, 0.7D);
			renderBlock(coverBlock, x, y, z);
			renderBlocks.setRenderBounds(0.4D, 0.1D, 0.4D, 0.6D, 0.7D, 0.6D);
			renderBlock(coverBlock, x, y, z);
			d17 = x - 0.5D;
			d18 = x + 0.5D;
			d19 = z - 0.5D;
			d20 = z + 0.5D;
			d21 = 0.2D;
			d22 = 0.75D; //haut 1
			d23 = 0.7D; //bas
			d24 = 0.3D; //pour la largeur
			d25 = 0.5D; //pour centrer
			d26 = 0.85D; //haut 2
			//haut
			tessellator.addVertexWithUV(x - d24 + d25, y + d26, z - d24 + d25, d7, d6);
			tessellator.addVertexWithUV(x - d24 + d25, y + d22, z + d24 + d25, d7, d8);
			tessellator.addVertexWithUV(x + d24 + d25, y + d22, z + d24 + d25, d5, d8);
			tessellator.addVertexWithUV(x + d24 + d25, y + d26, z - d24 + d25, d5, d6);
			//bas
			tessellator.addVertexWithUV(x + d24 + d25, y + d23, z - d24 + d25, d7, d6);
			tessellator.addVertexWithUV(x + d24 + d25, y + d23, z + d24 + d25, d7, d8);
			tessellator.addVertexWithUV(x - d24 + d25, y + d23, z + d24 + d25, d5, d8);
			tessellator.addVertexWithUV(x - d24 + d25, y + d23, z - d24 + d25, d5, d6);
			//cote gauche
			tessellator.addVertexWithUV(x - d24 + d25, y + d26, d19 + d25 + d21, d13, d14);
			tessellator.addVertexWithUV(x - d24 + d25, y + d23, d19 + d25 + d21, d13, d16);
			tessellator.addVertexWithUV(x - d24 + d25, y + d23, d20 + d25 - d21, d15, d16);
			tessellator.addVertexWithUV(x - d24 + d25, y + d22, d20 + d25 - d21, d15, d34);
			//cote droit
			tessellator.addVertexWithUV(x + d24 + d25, y + d22, d20 + d25 - d21, d13, d34);
			tessellator.addVertexWithUV(x + d24 + d25, y + d23, d20 + d25 - d21, d13, d16);
			tessellator.addVertexWithUV(x + d24 + d25, y + d23, d19 + d25 + d21, d15, d16);
			tessellator.addVertexWithUV(x + d24 + d25, y + d26, d19 + d25 + d21, d15, d14);
			//cote derriere
			tessellator.addVertexWithUV(d17 + d25 + d21, y + d22, z + d24 + d25, d30, d31);
			tessellator.addVertexWithUV(d17 + d25 + d21, y + d23, z + d24 + d25, d30, d33);
			tessellator.addVertexWithUV(d18 + d25 - d21, y + d23, z + d24 + d25, d32, d33);
			tessellator.addVertexWithUV(d18 + d25 - d21, y + d22, z + d24 + d25, d32, d31);
			//cote devant
			tessellator.addVertexWithUV(d18 + d25 - d21, y + d26, z - d24 + d25, d9, d10);
			tessellator.addVertexWithUV(d18 + d25 - d21, y + d23, z - d24 + d25, d9, d12);
			tessellator.addVertexWithUV(d17 + d25 + d21, y + d23, z - d24 + d25, d11, d12);
			tessellator.addVertexWithUV(d17 + d25 + d21, y + d26, z - d24 + d25, d11, d10);
			break;
		}
	}
}
