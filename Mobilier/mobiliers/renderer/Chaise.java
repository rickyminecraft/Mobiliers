package mobiliers.renderer;

import mobiliers.data.ChaiseD;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;

import org.lwjgl.opengl.GL11;

import carpentersblocks.renderer.BlockHandlerBase;
import carpentersblocks.tileentity.TECarpentersBlock;
import carpentersblocks.util.BlockProperties;

public class Chaise extends BlockHandlerBase
{
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderBlocks)
	{
		Tessellator tessellator = Tessellator.instance;
		GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

		for (int box = 0; box < 8; ++box)
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
					renderBlocks.setRenderBounds(0.7D, 0.5D, 0.7D, 0.8D, 1.0D, 0.8D);
					break;
				case 3:
					renderBlocks.setRenderBounds(0.7D, 0.0D, 0.2D, 0.8D, 0.4D, 0.3D);
					break;
				case 4:
					renderBlocks.setRenderBounds(0.2D, 0.0D, 0.7D, 0.3D, 0.4D, 0.8D);
					break;
				case 5:
					renderBlocks.setRenderBounds(0.2D, 0.5D, 0.7D, 0.3D, 1.0D, 0.8D);
					break;
				case 6:
					renderBlocks.setRenderBounds(0.2D, 0.4D, 0.2D, 0.8D, 0.5D, 0.8D);
					break;
				case 7:
					renderBlocks.setRenderBounds(0.3D, 0.7D, 0.7D, 0.7D, 1.0D, 0.8D);
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

		int data = BlockProperties.getData(TE);
		int type = ChaiseD.getType(data);
		switch (type)
		{
			case ChaiseD.TYPE_1:
				renderChaise(TE, renderBlocks, coverBlock, srcBlock, x, y, z);
				break;
			case ChaiseD.TYPE_2:
				renderChaise_2(TE, renderBlocks, coverBlock, srcBlock, x, y, z);
				break;
			case ChaiseD.TYPE_3:
				renderChaise_3(TE, renderBlocks, coverBlock, srcBlock, x, y, z);
				break;
			case ChaiseD.TYPE_4:
				renderChaise_4(TE, renderBlocks, coverBlock, srcBlock, x, y, z);
		}
		return true;
	}
	
	private void renderChaise(TECarpentersBlock tE, RenderBlocks renderBlocks, Block coverBlock, Block srcBlock, int x, int y, int z)
	{
		int data = BlockProperties.getData(tE);
		int tmp = ChaiseD.getRotation(data);
		switch (tmp)
		{
			case ChaiseD.CHAISE_X_NEG:
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.2D, 0.3D, 0.4D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.7D, 0.8D, 0.4D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.5D, 0.7D, 0.8D, 1.0D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.2D, 0.8D, 0.4D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.5D, 0.2D, 0.8D, 1.0D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.7D, 0.3D, 0.4D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.7D, 0.3D, 0.8D, 1.0D, 0.7D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.2D, 0.4D, 0.2D, 0.3D, 0.5D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.4D, 0.2D, 0.8D, 0.5D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.2D, 0.7D, 0.5D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.7D, 0.7D, 0.5D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.3D, 0.7D, 0.5D, 0.7D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				break;
			case ChaiseD.CHAISE_X_POS:
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.2D, 0.3D, 0.4D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.5D, 0.2D, 0.3D, 1.0D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.7D, 0.8D, 0.4D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.2D, 0.8D, 0.4D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.7D, 0.3D, 0.4D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.5D, 0.7D, 0.3D, 1.0D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.7D, 0.3D, 0.3D, 1.0D, 0.7D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.2D, 0.4D, 0.2D, 0.3D, 0.5D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.4D, 0.2D, 0.8D, 0.5D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.2D, 0.7D, 0.5D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.7D, 0.7D, 0.5D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.3D, 0.7D, 0.5D, 0.7D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				break;
			case ChaiseD.CHAISE_Z_NEG:
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.2D, 0.3D, 0.4D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.7D, 0.8D, 0.4D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.5D, 0.7D, 0.8D, 1.0D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.2D, 0.8D, 0.4D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.7D, 0.3D, 0.4D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.5D, 0.7D, 0.3D, 1.0D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.7D, 0.7D, 0.7D, 1.0D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.2D, 0.4D, 0.2D, 0.3D, 0.5D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.4D, 0.2D, 0.8D, 0.5D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.2D, 0.7D, 0.5D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.7D, 0.7D, 0.5D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.3D, 0.7D, 0.5D, 0.7D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				break;
			case ChaiseD.CHAISE_Z_POS:
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.2D, 0.3D, 0.4D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.5D, 0.2D, 0.3D, 1.0D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.7D, 0.8D, 0.4D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.2D, 0.8D, 0.4D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.5D, 0.2D, 0.8D, 1.0D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.7D, 0.3D, 0.4D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.7D, 0.2D, 0.7D, 1.0D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.2D, 0.4D, 0.2D, 0.3D, 0.5D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.4D, 0.2D, 0.8D, 0.5D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.2D, 0.7D, 0.5D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.7D, 0.7D, 0.5D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.3D, 0.7D, 0.5D, 0.7D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
		}
	}
	
	private void renderChaise_2(TECarpentersBlock tE, RenderBlocks renderBlocks, Block coverBlock, Block srcBlock, int x, int y, int z)
	{
		int data = BlockProperties.getData(tE);
		int tmp = ChaiseD.getRotation(data);
		switch (tmp)
		{
			case ChaiseD.CHAISE_X_NEG:
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.2D, 0.3D, 0.4D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.7D, 0.8D, 0.4D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.5D, 0.7D, 0.8D, 1.0D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.2D, 0.8D, 0.4D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.5D, 0.2D, 0.8D, 1.0D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.7D, 0.3D, 0.4D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.7D, 0.65D, 0.3D, 0.8D, 0.75D, 0.7D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.85D, 0.3D, 0.8D, 0.95D, 0.7D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.2D, 0.4D, 0.2D, 0.3D, 0.5D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.4D, 0.2D, 0.8D, 0.5D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.2D, 0.7D, 0.5D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.7D, 0.7D, 0.5D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.3D, 0.7D, 0.5D, 0.7D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				break;
			case ChaiseD.CHAISE_X_POS:
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.2D, 0.3D, 0.4D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.5D, 0.2D, 0.3D, 1.0D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.7D, 0.8D, 0.4D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.2D, 0.8D, 0.4D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.7D, 0.3D, 0.4D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.5D, 0.7D, 0.3D, 1.0D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.2D, 0.65D, 0.3D, 0.3D, 0.75D, 0.7D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.85D, 0.3D, 0.3D, 0.95D, 0.7D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.2D, 0.4D, 0.2D, 0.3D, 0.5D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.4D, 0.2D, 0.8D, 0.5D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.2D, 0.7D, 0.5D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.7D, 0.7D, 0.5D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.3D, 0.7D, 0.5D, 0.7D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				break;
			case ChaiseD.CHAISE_Z_NEG:
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.2D, 0.3D, 0.4D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.7D, 0.8D, 0.4D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.5D, 0.7D, 0.8D, 1.0D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.2D, 0.8D, 0.4D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.7D, 0.3D, 0.4D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.5D, 0.7D, 0.3D, 1.0D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.3D, 0.65D, 0.7D, 0.7D, 0.75D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.85D, 0.7D, 0.7D, 0.95D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.2D, 0.4D, 0.2D, 0.3D, 0.5D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.4D, 0.2D, 0.8D, 0.5D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.2D, 0.7D, 0.5D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.7D, 0.7D, 0.5D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.3D, 0.7D, 0.5D, 0.7D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				break;
			case ChaiseD.CHAISE_Z_POS:
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.2D, 0.3D, 0.4D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.5D, 0.2D, 0.3D, 1.0D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.7D, 0.8D, 0.4D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.2D, 0.8D, 0.4D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.5D, 0.2D, 0.8D, 1.0D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.7D, 0.3D, 0.4D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.3D, 0.65D, 0.2D, 0.7D, 0.75D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.85D, 0.2D, 0.7D, 0.95D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.2D, 0.4D, 0.2D, 0.3D, 0.5D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.4D, 0.2D, 0.8D, 0.5D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.2D, 0.7D, 0.5D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.7D, 0.7D, 0.5D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.3D, 0.7D, 0.5D, 0.7D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
		}
	}
	
	private void renderChaise_3(TECarpentersBlock tE, RenderBlocks renderBlocks, Block coverBlock, Block srcBlock, int x, int y, int z)
	{
		int data = BlockProperties.getData(tE);
		int tmp = ChaiseD.getRotation(data);
		switch (tmp)
		{
			case ChaiseD.CHAISE_X_NEG:
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.2D, 0.3D, 0.4D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.65D, 0.8D, 0.4D, 0.75D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.75D, 0.5D, 0.65D, 0.8D, 0.95D, 0.75D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.25D, 0.8D, 0.4D, 0.35D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.75D, 0.5D, 0.25D, 0.8D, 0.95D, 0.35D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.7D, 0.3D, 0.4D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.7D, 0.2D, 0.75D, 1.0D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.2D, 0.4D, 0.2D, 0.3D, 0.5D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.4D, 0.2D, 0.8D, 0.5D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.2D, 0.7D, 0.5D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.7D, 0.7D, 0.5D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.3D, 0.7D, 0.5D, 0.7D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				break;
			case ChaiseD.CHAISE_X_POS:
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.25D, 0.3D, 0.4D, 0.35D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.5D, 0.25D, 0.25D, 0.95D, 0.35D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.7D, 0.8D, 0.4D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.2D, 0.8D, 0.4D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.65D, 0.3D, 0.4D, 0.75D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.5D, 0.65D, 0.25D, 0.95D, 0.75D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.25D, 0.7D, 0.2D, 0.3D, 1.0D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.2D, 0.4D, 0.2D, 0.3D, 0.5D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.4D, 0.2D, 0.8D, 0.5D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.2D, 0.7D, 0.5D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.7D, 0.7D, 0.5D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.3D, 0.7D, 0.5D, 0.7D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				break;
			case ChaiseD.CHAISE_Z_NEG:
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.2D, 0.3D, 0.4D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.65D, 0.0D, 0.7D, 0.75D, 0.4D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.65D, 0.5D, 0.75D, 0.75D, 0.95D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.2D, 0.8D, 0.4D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.25D, 0.0D, 0.7D, 0.35D, 0.4D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.25D, 0.5D, 0.75D, 0.35D, 0.95D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.7D, 0.7D, 0.8D, 1.0D, 0.75D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.2D, 0.4D, 0.2D, 0.3D, 0.5D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.4D, 0.2D, 0.8D, 0.5D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.2D, 0.7D, 0.5D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.7D, 0.7D, 0.5D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.3D, 0.7D, 0.5D, 0.7D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				break;
			case ChaiseD.CHAISE_Z_POS:
				renderBlocks.setRenderBounds(0.25D, 0.0D, 0.2D, 0.35D, 0.4D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.25D, 0.5D, 0.2D, 0.35D, 0.95D, 0.25D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.7D, 0.8D, 0.4D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.65D, 0.0D, 0.2D, 0.75D, 0.4D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.65D, 0.5D, 0.2D, 0.75D, 0.95D, 0.25D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.7D, 0.3D, 0.4D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.7D, 0.25D, 0.8D, 1.0D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.2D, 0.4D, 0.2D, 0.3D, 0.5D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.4D, 0.2D, 0.8D, 0.5D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.2D, 0.7D, 0.5D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.7D, 0.7D, 0.5D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.3D, 0.7D, 0.5D, 0.7D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
		}
	}
	
	private void renderChaise_4(TECarpentersBlock tE, RenderBlocks renderBlocks, Block coverBlock, Block srcBlock, int x, int y, int z)
	{
		int data = BlockProperties.getData(tE);
		int tmp = ChaiseD.getRotation(data);
		switch (tmp)
		{
			case ChaiseD.CHAISE_X_NEG:
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.2D, 0.3D, 0.4D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.7D, 0.8D, 0.4D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.5D, 0.7D, 0.8D, 1.0D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.2D, 0.8D, 0.4D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.5D, 0.2D, 0.8D, 1.0D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.7D, 0.3D, 0.4D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.72D, 0.7D, 0.3D, 0.78D, 0.75D, 0.7D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.72D, 0.8D, 0.3D, 0.78D, 0.85D, 0.7D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.72D, 0.9D, 0.3D, 0.78D, 0.95D, 0.7D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.2D, 0.4D, 0.2D, 0.3D, 0.5D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.4D, 0.2D, 0.8D, 0.5D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.2D, 0.7D, 0.5D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.7D, 0.7D, 0.5D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.3D, 0.7D, 0.5D, 0.7D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				break;
			case ChaiseD.CHAISE_X_POS:
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.2D, 0.3D, 0.4D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.5D, 0.2D, 0.3D, 1.0D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.7D, 0.8D, 0.4D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.2D, 0.8D, 0.4D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.7D, 0.3D, 0.4D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.5D, 0.7D, 0.3D, 1.0D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.22D, 0.7D, 0.3D, 0.28D, 0.75D, 0.7D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.22D, 0.8D, 0.3D, 0.28D, 0.85D, 0.7D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.22D, 0.9D, 0.3D, 0.28D, 0.95D, 0.7D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.2D, 0.4D, 0.2D, 0.3D, 0.5D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.4D, 0.2D, 0.8D, 0.5D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.2D, 0.7D, 0.5D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.7D, 0.7D, 0.5D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.3D, 0.7D, 0.5D, 0.7D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				break;
			case ChaiseD.CHAISE_Z_NEG:
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.2D, 0.3D, 0.4D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.7D, 0.8D, 0.4D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.5D, 0.7D, 0.8D, 1.0D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.2D, 0.8D, 0.4D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.7D, 0.3D, 0.4D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.5D, 0.7D, 0.3D, 1.0D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.3D, 0.7D, 0.72D, 0.7D, 0.75D, 0.78D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.8D, 0.72D, 0.7D, 0.85D, 0.78D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.9D, 0.72D, 0.7D, 0.95D, 0.78D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.2D, 0.4D, 0.2D, 0.3D, 0.5D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.4D, 0.2D, 0.8D, 0.5D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.2D, 0.7D, 0.5D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.7D, 0.7D, 0.5D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.3D, 0.7D, 0.5D, 0.7D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				break;
			case ChaiseD.CHAISE_Z_POS:
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.2D, 0.3D, 0.4D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.5D, 0.2D, 0.3D, 1.0D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.7D, 0.8D, 0.4D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.2D, 0.8D, 0.4D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.5D, 0.2D, 0.8D, 1.0D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.7D, 0.3D, 0.4D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.3D, 0.7D, 0.22D, 0.7D, 0.75D, 0.28D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.8D, 0.22D, 0.7D, 0.85D, 0.28D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.9D, 0.22D, 0.7D, 0.95D, 0.28D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.2D, 0.4D, 0.2D, 0.3D, 0.5D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.4D, 0.2D, 0.8D, 0.5D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.2D, 0.7D, 0.5D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.7D, 0.7D, 0.5D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.3D, 0.7D, 0.5D, 0.7D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
		}
	}
}
