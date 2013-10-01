package mobiliers.renderer;

import mobiliers.data.FenetreD;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;

import org.lwjgl.opengl.GL11;

import carpentersblocks.renderer.BlockHandlerBase;
import carpentersblocks.renderer.helper.RenderHelper;
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

		int data = BlockProperties.getData(TE);
		int type = FenetreD.getType(data);
		switch (type)
		{
			case FenetreD.TYPE_1:
				renderFenetres(TE, renderBlocks, coverBlock, srcBlock, x, y, z);
				break;
			case FenetreD.TYPE_2:
				renderFenetres2(TE, renderBlocks, coverBlock, srcBlock, x, y, z);
				break;
			case FenetreD.TYPE_3:
				renderFenetres3(TE, renderBlocks, coverBlock, srcBlock, x, y, z);
				break;
			case FenetreD.TYPE_4:
				renderFenetres4(TE, renderBlocks, coverBlock, srcBlock, x, y, z);
		}
		return true;
	}

	private void renderFenetres(TECarpentersBlock tE, RenderBlocks renderBlocks, Block coverBlock, Block srcBlock, int x, int y, int z)
	{
		int data = BlockProperties.getData(tE);
		data = FenetreD.getRotation(data);
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
		}
	}
	
	private void renderFenetres2(TECarpentersBlock tE, RenderBlocks renderBlocks, Block coverBlock, Block srcBlock, int x, int y, int z)
	{
		int data = BlockProperties.getData(tE);
		data = FenetreD.getRotation(data);
		switch (data)
		{
			case FenetreD.FENETRE_X:
				renderBlocks.setRenderBounds(0.0D, 0.0D, 0.0D, 0.25D, 0.1D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.1D, 0.0D, 0.225D, 0.15D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.15D, 0.0D, 0.25D, 0.25D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.25D, 0.0D, 0.225D, 0.3D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.3D, 0.0D, 0.25D, 0.4D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.4D, 0.0D, 0.225D, 0.45D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.45D, 0.0D, 0.25D, 0.55D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.55D, 0.0D, 0.225D, 0.6D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.6D, 0.0D, 0.25D, 0.7D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.7D, 0.0D, 0.225D, 0.75D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.75D, 0.0D, 0.25D, 0.85D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.85D, 0.0D, 0.225D, 0.9D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.9D, 0.0D, 0.25D, 1.0D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);

				renderBlocks.setRenderBounds(0.75D, 0.0D, 0.0D, 1.0D, 0.1D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.775D, 0.1D, 0.0D, 1.0D, 0.15D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.75D, 0.15D, 0.0D, 1.0D, 0.25D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.775D, 0.25D, 0.0D, 1.0D, 0.3D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.75D, 0.3D, 0.0D, 1.0D, 0.4D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.775D, 0.4D, 0.0D, 1.0D, 0.45D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.75D, 0.45D, 0.0D, 1.0D, 0.55D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.775D, 0.55D, 0.0D, 1.0D, 0.6D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.75D, 0.6D, 0.0D, 1.0D, 0.7D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.775D, 0.7D, 0.0D, 1.0D, 0.75D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.75D, 0.75D, 0.0D, 1.0D, 0.85D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.775D, 0.85D, 0.0D, 1.0D, 0.9D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.75D, 0.9D, 0.0D, 1.0D, 1.0D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				break;
			case FenetreD.FENETRE_Z:
				renderBlocks.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.1D, 0.25D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.1D, 0.0D, 1.0D, 0.15D, 0.225D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.15D, 0.0D, 1.0D, 0.25D, 0.25D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.25D, 0.0D, 1.0D, 0.3D, 0.225D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.3D, 0.0D, 1.0D, 0.4D, 0.25D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.4D, 0.0D, 1.0D, 0.45D, 0.225D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.45D, 0.0D, 1.0D, 0.55D, 0.25D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.55D, 0.0D, 1.0D, 0.6D, 0.225D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.6D, 0.0D, 1.0D, 0.7D, 0.25D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.7D, 0.0D, 1.0D, 0.75D, 0.225D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.75D, 0.0D, 1.0D, 0.85D, 0.25D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.85D, 0.0D, 1.0D, 0.9D, 0.225D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.9D, 0.0D, 1.0D, 1.0D, 0.25D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);

				renderBlocks.setRenderBounds(0.0D, 0.0D, 0.75D, 1.0D, 0.1D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.1D, 0.775D, 1.0D, 0.15D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.15D, 0.75D, 1.0D, 0.25D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.25D, 0.775D, 1.0D, 0.3D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.3D, 0.75D, 1.0D, 0.4D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.4D, 0.775D, 1.0D, 0.45D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.45D, 0.75D, 1.0D, 0.55D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.55D, 0.775D, 1.0D, 0.6D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.6D, 0.75D, 1.0D, 0.7D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.7D, 0.775D, 1.0D, 0.75D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.75D, 0.75D, 1.0D, 0.85D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.85D, 0.775D, 1.0D, 0.9D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.9D, 0.75D, 1.0D, 1.0D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
		}
	}
	
	private void renderFenetres3(TECarpentersBlock tE, RenderBlocks renderBlocks, Block coverBlock, Block srcBlock, int x, int y, int z)
	{
		int data = BlockProperties.getData(tE);
		data = FenetreD.getRotation(data);
		switch (data)
		{
			case FenetreD.FENETRE_X:
				renderBlocks.setRenderBounds(0.0D, 0.0D, 0.0D, 0.1D, 1.0D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.1D, 0.0D, 0.1D, 0.2D, 1.0D, 0.9D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.2D, 0.3D, 1.0D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.0D, 0.3D, 0.35D, 1.0D, 0.7D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);

				renderBlocks.setRenderBounds(0.65D, 0.0D, 0.3D, 0.7D, 1.0D, 0.7D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.2D, 0.8D, 1.0D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.8D, 0.0D, 0.1D, 0.9D, 1.0D, 0.9D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.9D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				break;
			case FenetreD.FENETRE_Z:
				renderBlocks.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.1D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.1D, 0.0D, 0.1D, 0.9D, 1.0D, 0.2D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.2D, 0.8D, 1.0D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.0D, 0.3D, 0.7D, 1.0D, 0.35D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);

				renderBlocks.setRenderBounds(0.3D, 0.0D, 0.65D, 0.7D, 1.0D, 0.7D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.7D, 0.8D, 1.0D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.1D, 0.0D, 0.8D, 0.9D, 1.0D, 0.9D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.0D, 0.9D, 1.0D, 1.0D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
		}
	}
	
	private void renderFenetres4(TECarpentersBlock tE, RenderBlocks renderBlocks, Block coverBlock, Block srcBlock, int x, int y, int z)
	{
		int data = BlockProperties.getData(tE);
		data = FenetreD.getRotation(data);
		Icon icon;
		icon = Block.hopperBlock.getHopperIcon("hopper_outside");
		switch (data)
		{
			case FenetreD.FENETRE_X:
				renderBlocks.setRenderBounds(0.0D, 0.9D, 0.0D, 1.0D, 1.0D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.1D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.1D, 0.0D, 0.1D, 0.9D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.9D, 0.1D, 0.0D, 1.0D, 0.9D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				//ici les barres
				setIconOverride(6, icon);
				
				renderBlocks.setRenderBounds(0.2D, 0.1D, 0.05D, 0.25D, 0.9D, 0.1D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.35D, 0.1D, 0.05D, 0.4D, 0.9D, 0.1D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.475D, 0.1D, 0.05D, 0.525D, 0.9D, 0.1D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.6D, 0.1D, 0.05D, 0.65D, 0.9D, 0.1D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.75D, 0.1D, 0.05D, 0.8D, 0.9D, 0.1D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.2D, 0.1D, 0.9D, 0.25D, 0.9D, 0.95D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.35D, 0.1D, 0.9D, 0.4D, 0.9D, 0.95D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.475D, 0.1D, 0.9D, 0.525D, 0.9D, 0.95D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.6D, 0.1D, 0.9D, 0.65D, 0.9D, 0.95D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.75D, 0.1D, 0.9D, 0.8D, 0.9D, 0.95D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				clearIconOverride(6);
				break;
			case FenetreD.FENETRE_Z:
				renderBlocks.setRenderBounds(0.0D, 0.9D, 0.0D, 1.0D, 1.0D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.1D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.1D, 0.0D, 1.0D, 0.9D, 0.1D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.1D, 0.9D, 1.0D, 0.9D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				//ici les barres
				setIconOverride(6, icon);
				
				renderBlocks.setRenderBounds(0.05D, 0.1D, 0.2D, 0.1D, 0.9D, 0.25D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.05D, 0.1D, 0.35D, 0.1D, 0.9D, 0.4D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.05D, 0.1D, 0.475D, 0.1D, 0.9D, 0.525D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.05D, 0.1D, 0.6D, 0.1D, 0.9D, 0.65D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.05D, 0.1D, 0.75D, 0.1D, 0.9D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.9D, 0.1D, 0.2D, 0.95D, 0.9D, 0.25D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.9D, 0.1D, 0.35D, 0.95D, 0.9D, 0.4D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.9D, 0.1D, 0.475D, 0.95D, 0.9D, 0.525D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.9D, 0.1D, 0.6D, 0.95D, 0.9D, 0.65D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.9D, 0.1D, 0.75D, 0.95D, 0.9D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				
				clearIconOverride(6);
		}
	}
}
