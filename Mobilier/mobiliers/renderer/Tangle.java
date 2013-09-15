package mobiliers.renderer;

import mobiliers.data.TangleD;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;

import org.lwjgl.opengl.GL11;

import carpentersblocks.renderer.BlockHandlerBase;
import carpentersblocks.tileentity.TECarpentersBlock;
import carpentersblocks.util.BlockProperties;

public class Tangle extends BlockHandlerBase
{
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderBlocks)
	{
		Tessellator tessellator = Tessellator.instance;
		GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

		for (int box = 0; box < 2; ++box)
		{
			switch (box)
			{
				case 0:
					renderBlocks.setRenderBounds(0.0D, 0.9D, 0.0D, 1.0D, 1.0D, 1.0D);
					break;
				case 1:
					renderBlocks.setRenderBounds(0.8D, 0.0D, 0.1D, 0.9D, 0.9D, 0.2D);
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
	 * Renders stairs at the given coordinates
	 */
	public boolean renderCarpentersBlock(TECarpentersBlock TE, RenderBlocks renderBlocks, Block srcBlock, int renderPass, int x, int y, int z)
	{
		Block coverBlock = isSideCover ? BlockProperties.getCoverBlock(TE, sideRendering) : BlockProperties.getCoverBlock(TE, 6);

		renderTableAngle(TE, renderBlocks, coverBlock, srcBlock, x, y, z);
		return true;
	}
	
	private void renderTableAngle(TECarpentersBlock tE, RenderBlocks renderBlocks, Block coverBlock, Block srcBlock, int x, int y, int z)
	{
		int data = BlockProperties.getData(tE);
		switch (data)
		{
			case TangleD.TANGLE_X_NEG:
				renderBlocks.setRenderBounds(0.1D, 0.0D, 0.1D, 0.2D, 0.9D, 0.2D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.9D, 0.0D, 1.0D, 1.0D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				break;
			case TangleD.TANGLE_X_POS:
				renderBlocks.setRenderBounds(0.8D, 0.0D, 0.8D, 0.9D, 0.9D, 0.9D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.9D, 0.0D, 1.0D, 1.0D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				break;
			case TangleD.TANGLE_Z_NEG:
				renderBlocks.setRenderBounds(0.8D, 0.0D, 0.1D, 0.9D, 0.9D, 0.2D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.9D, 0.0D, 1.0D, 1.0D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				break;
			case TangleD.TANGLE_Z_POS:
				renderBlocks.setRenderBounds(0.1D, 0.0D, 0.8D, 0.2D, 0.9D, 0.9D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.9D, 0.0D, 1.0D, 1.0D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
		}
	}
}