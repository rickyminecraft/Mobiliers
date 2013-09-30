package mobiliers.renderer;

import mobiliers.data.FenetreD;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;

import org.lwjgl.opengl.GL11;

import carpentersblocks.renderer.BlockHandlerBase;
import carpentersblocks.tileentity.TECarpentersBlock;
import carpentersblocks.util.BlockProperties;

public class Fenetre extends BlockHandlerBase
{
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderBlocks)
	{
		Tessellator tessellator = Tessellator.instance;
		GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

		for (int box = 0; box < 7; ++box)
		{
			switch (box)
			{
				case 0:
					renderBlocks.setRenderBounds(0.0D, 0.0D, 0.0D, 0.225D, 0.6D, 1.0D);
					break;
				case 1:
					renderBlocks.setRenderBounds(0.775D, 0.0D, 0.0D, 1.0D, 0.6D, 1.0D);
					break;
				case 2:
					renderBlocks.setRenderBounds(0.0D, 0.6D, 0.0D, 0.3D, 0.7D, 1.0D);
					break;
				case 3:
					renderBlocks.setRenderBounds(0.7D, 0.6D, 0.0D, 1.0D, 0.7D, 1.0D);
					break;
				case 4:
					renderBlocks.setRenderBounds(0.0D, 0.7D, 0.0D, 0.4D, 1.0D, 1.0D);
					break;
				case 5:
					renderBlocks.setRenderBounds(0.6D, 0.7D, 0.0D, 1.0D, 1.0D, 1.0D);
					break;
				case 6:
					renderBlocks.setRenderBounds(0.4D, 0.75D, 0.0D, 0.6D, 1.0D, 1.0D);
			}

			tessellator.startDrawingQuads();
			tessellator.setNormal(0.0F, -1.0F, 0.0F);
			renderBlocks.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSide(0));
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.setNormal(0.0F, 1.0F, 0.0F);
			renderBlocks.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSide(1));
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.setNormal(0.0F, 0.0F, -1.0F);
			renderBlocks.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSide(2));
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.setNormal(0.0F, 0.0F, 1.0F);
			renderBlocks.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSide(3));
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.setNormal(-1.0F, 0.0F, 0.0F);
			renderBlocks.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSide(4));
			tessellator.draw();
			tessellator.startDrawingQuads();
			tessellator.setNormal(1.0F, 0.0F, 0.0F);
			renderBlocks.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSide(5));
			tessellator.draw();
		}

		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}
	
	@Override
	/**
	 * Renders barrier
	 */
	public boolean renderCarpentersBlock(TECarpentersBlock TE, RenderBlocks renderBlocks, Block srcBlock, int renderPass, int x, int y, int z)
	{
		Block coverBlock = isSideCover ? BlockProperties.getCoverBlock(TE, coverRendering) : BlockProperties.getCoverBlock(TE, 6);

		renderFenetres(TE, renderBlocks, coverBlock, srcBlock, x, y, z);
		return true;
	}

	private void renderFenetres(TECarpentersBlock tE, RenderBlocks renderBlocks, Block coverBlock, Block srcBlock, int x, int y, int z)
	{
		int data = BlockProperties.getData(tE);
		switch (data)
		{
			case FenetreD.FENETRE_X:
				renderBlocks.setRenderBounds(0.0D, 0.0D, 0.0D, 0.225D, 0.05D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.05D, 0.0D, 0.25D, 0.15D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.15D, 0.0D, 0.225D, 0.2D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.2D, 0.0D, 0.25D, 0.3D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.3D, 0.0D, 0.225D, 0.35D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.35D, 0.0D, 0.25D, 0.45D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.45D, 0.0D, 0.225D, 0.5D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.5D, 0.0D, 0.25D, 0.6D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.775D, 0.0D, 0.0D, 1.0D, 0.05D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.75D, 0.05D, 0.0D, 1.0D, 0.15D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.775D, 0.15D, 0.0D, 1.0D, 0.2D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.75D, 0.2D, 0.0D, 1.0D, 0.3D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.775D, 0.3D, 0.0D, 1.0D, 0.35D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.75D, 0.35D, 0.0D, 1.0D, 0.45D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.775D, 0.45D, 0.0D, 1.0D, 0.5D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.75D, 0.5D, 0.0D, 1.0D, 0.6D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.35D, 0.725D, 0.0D, 0.4D, 1.0D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.6D, 0.725D, 0.0D, 0.65D, 1.0D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.0D, 0.7D, 0.0D, 0.35D, 1.0D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.65D, 0.7D, 0.0D, 1.0D, 1.0D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.0D, 0.65D, 0.0D, 0.3D, 0.7D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.65D, 0.0D, 1.0D, 0.7D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.0D, 0.6D, 0.0D, 0.275D, 0.65D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.725D, 0.6D, 0.0D, 1.0D, 0.65D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.4D, 0.75D, 0.0D, 0.6D, 1.0D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);

				break;
			case FenetreD.FENETRE_Z:
				renderBlocks.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.05D, 0.225D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.05D, 0.0D, 1.0D, 0.15D, 0.25D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.15D, 0.0D, 1.0D, 0.2D, 0.225D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.2D, 0.0D, 1.0D, 0.3D, 0.25D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.3D, 0.0D, 1.0D, 0.35D, 0.225D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.35D, 0.0D, 1.0D, 0.45D, 0.25D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.45D, 0.0D, 1.0D, 0.5D, 0.225D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.5D, 0.0D, 1.0D, 0.6D, 0.25D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.0D, 0.0D, 0.775D, 1.0D, 0.05D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.05D, 0.75D, 1.0D, 0.15D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.15D, 0.775D, 1.0D, 0.2D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.2D, 0.75D, 1.0D, 0.3D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.3D, 0.775D, 1.0D, 0.35D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.35D, 0.75D, 1.0D, 0.45D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.45D, 0.775D, 1.0D, 0.5D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.5D, 0.75D, 1.0D, 0.6D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.0D, 0.725D, 0.35D, 1.0D, 1.0D, 0.4D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.725D, 0.6D, 1.0D, 1.0D, 0.65D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.0D, 0.7D, 0.0D, 1.0D, 1.0D, 0.35D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.7D, 0.65D, 1.0D, 1.0D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.0D, 0.65D, 0.0D, 1.0D, 0.7D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.65D, 0.7D, 1.0D, 0.7D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.0D, 0.6D, 0.0D, 1.0D, 0.65D, 0.275D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.6D, 0.725D, 1.0D, 0.65D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.0D, 0.75D, 0.4D, 1.0D, 1.0D, 0.6D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				break;
		}
	}
}
