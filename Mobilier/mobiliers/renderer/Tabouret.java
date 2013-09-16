package mobiliers.renderer;

import mobiliers.data.TabouretD;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;

import org.lwjgl.opengl.GL11;

import carpentersblocks.renderer.BlockHandlerBase;
import carpentersblocks.tileentity.TECarpentersBlock;
import carpentersblocks.util.BlockProperties;

public class Tabouret extends BlockHandlerBase
{
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderBlocks)
	{
		Tessellator tessellator = Tessellator.instance;
		GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

		for (int box = 0; box < 5; ++box)
		{
			switch (box)
			{
				case 0:
					renderBlocks.setRenderBounds(0.2D, 0.0D, 0.2D, 0.3D, 0.4D, 0.3D);
					break;
				case 1:
					renderBlocks.setRenderBounds(0.7D, 0.0D, 0.7D, 0.8D, 0.4D, 0.8D);
					break;
				case 2:
					renderBlocks.setRenderBounds(0.7D, 0.0D, 0.2D, 0.8D, 0.4D, 0.3D);
					break;
				case 3:
					renderBlocks.setRenderBounds(0.2D, 0.0D, 0.7D, 0.3D, 0.4D, 0.8D);
					break;
				case 4:
					renderBlocks.setRenderBounds(0.15D, 0.4D, 0.15D, 0.85D, 0.5D, 0.85D);
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
		Block coverBlock = isSideCover ? BlockProperties.getCoverBlock(TE, sideRendering) : BlockProperties.getCoverBlock(TE, 6);

		int data = BlockProperties.getData(TE);
		int type = TabouretD.getType(data);
		switch (type)
		{
			case TabouretD.TYPE_1:
				renderTabouretBas(TE, renderBlocks, coverBlock, srcBlock, x, y, z);
				break;
			case TabouretD.TYPE_2:
				renderTabouret(TE, renderBlocks, coverBlock, srcBlock, x, y, z);
				break;
		}
		return true;
	}

	private void renderTabouretBas(TECarpentersBlock tE, RenderBlocks renderBlocks, Block coverBlock, Block srcBlock, int x, int y, int z)
	{
		renderBlocks.setRenderBounds(0.2D, 0.0D, 0.2D, 0.3D, 0.4D, 0.3D);
		renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
		renderBlocks.setRenderBounds(0.7D, 0.0D, 0.7D, 0.8D, 0.4D, 0.8D);
		renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
		renderBlocks.setRenderBounds(0.7D, 0.0D, 0.2D, 0.8D, 0.4D, 0.3D);
		renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
		renderBlocks.setRenderBounds(0.2D, 0.0D, 0.7D, 0.3D, 0.4D, 0.8D);
		renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
		renderBlocks.setRenderBounds(0.15D, 0.4D, 0.15D, 0.85D, 0.5D, 0.85D);
		renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
	}
	
	private void renderTabouret(TECarpentersBlock tE, RenderBlocks renderBlocks, Block coverBlock, Block srcBlock, int x, int y, int z)
	{
		//barres
		renderBlocks.setRenderBounds(0.3D, 0.0D, 0.3D, 0.4D, 0.7D, 0.4D);
		renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
		renderBlocks.setRenderBounds(0.6D, 0.0D, 0.6D, 0.7D, 0.7D, 0.7D);
		renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
		renderBlocks.setRenderBounds(0.6D, 0.0D, 0.3D, 0.7D, 0.7D, 0.4D);
		renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
		renderBlocks.setRenderBounds(0.3D, 0.0D, 0.6D, 0.4D, 0.7D, 0.7D);
		renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
		
		//b centres
		renderBlocks.setRenderBounds(0.4D, 0.3D, 0.3D, 0.6D, 0.4D, 0.4D);
		renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
		renderBlocks.setRenderBounds(0.4D, 0.3D, 0.6D, 0.6D, 0.4D, 0.7D);
		renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
		renderBlocks.setRenderBounds(0.6D, 0.3D, 0.4D, 0.7D, 0.4D, 0.6D);
		renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
		renderBlocks.setRenderBounds(0.3D, 0.3D, 0.4D, 0.4D, 0.4D, 0.6D);
		renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
		
		//haut
		renderBlocks.setRenderBounds(0.2D, 0.7D, 0.2D, 0.8D, 0.8D, 0.8D);
		renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
	}
}
