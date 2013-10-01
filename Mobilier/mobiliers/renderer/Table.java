package mobiliers.renderer;

import mobiliers.data.TableD;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;

import org.lwjgl.opengl.GL11;

import carpentersblocks.renderer.BlockHandlerBase;
import carpentersblocks.renderer.helper.RenderHelper;
import carpentersblocks.tileentity.TECarpentersBlock;
import carpentersblocks.util.BlockProperties;

public class Table extends BlockHandlerBase
{
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderBlocks)
	{
		Tessellator tessellator = Tessellator.instance;
		GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

		for (int box = 0; box < 4; ++box)
		{
			switch (box)
			{
				case 0:
					renderBlocks.setRenderBounds(1.0D, 0.9D, 1.0D, 0.0D, 1.0D, 0.0D);
					break;
				case 1:
					renderBlocks.setRenderBounds(0.55D, 0.1D, 0.55D, 0.45D, 0.9D, 0.45D);
					break;
				case 2:
					renderBlocks.setRenderBounds(0.65D, 0.05D, 0.65D, 0.35D, 0.1D, 0.35D);
					break;
				case 3:
					renderBlocks.setRenderBounds(0.7D, 0.0D, 0.7D, 0.3D, 0.05D, 0.3D);
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
		Block coverBlock = isSideCover ? BlockProperties.getCoverBlock(TE, coverRendering) : BlockProperties.getCoverBlock(TE, 6);

		int data = BlockProperties.getData(TE);
		int type = TableD.getType(data);
		switch (type)
		{
			case TableD.TYPE_NORMAL:
				renderTable(TE, renderBlocks, coverBlock, srcBlock, x, y, z);
				break;
			case TableD.TYPE_GLASS:
				renderTableGlass(TE, renderBlocks, coverBlock, srcBlock, x, y, z);
				break;
			case TableD.TYPE_BAS:
				renderTableBasse(TE, renderBlocks, coverBlock, srcBlock, x, y, z);
				break;
			case TableD.TYPE_BAS_GLASS:
				renderTableBasseGlass(TE, renderBlocks, coverBlock, srcBlock, x, y, z);
		}
		return true;
	}
	
	private void renderTable(TECarpentersBlock tE, RenderBlocks renderBlocks, Block coverBlock, Block srcBlock, int x, int y, int z)
	{

		renderBlocks.setRenderBounds(0.7D, 0.85D, 0.7D, 0.3D, 0.9D, 0.3D);
		renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
		renderBlocks.setRenderBounds(0.65D, 0.8D, 0.65D, 0.35D, 0.85D, 0.35D);
		renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
		renderBlocks.setRenderBounds(0.55D, 0.1D, 0.55D, 0.45D, 0.8D, 0.45D);
		renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
		renderBlocks.setRenderBounds(0.65D, 0.05D, 0.65D, 0.35D, 0.1D, 0.35D);
		renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
		renderBlocks.setRenderBounds(0.7D, 0.0D, 0.7D, 0.3D, 0.05D, 0.3D);
		renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
		renderBlocks.setRenderBounds(0.0D, 0.9D, 0.0D, 1.0D, 1.0D, 1.0D);
		renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
	}
	
	private void renderTableGlass(TECarpentersBlock tE, RenderBlocks renderBlocks, Block coverBlock, Block srcBlock, int x, int y, int z)
	{
		Icon icon;
		icon = Block.glass.getIcon(0, 0);
		
		renderBlocks.setRenderBounds(0.7D, 0.85D, 0.7D, 0.3D, 0.9D, 0.3D);
		renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
		renderBlocks.setRenderBounds(0.65D, 0.8D, 0.65D, 0.35D, 0.85D, 0.35D);
		renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
		renderBlocks.setRenderBounds(0.55D, 0.1D, 0.55D, 0.45D, 0.8D, 0.45D);
		renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
		renderBlocks.setRenderBounds(0.65D, 0.05D, 0.65D, 0.35D, 0.1D, 0.35D);
		renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
		renderBlocks.setRenderBounds(0.7D, 0.0D, 0.7D, 0.3D, 0.05D, 0.3D);
		renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
		
		renderBlocks.setRenderBounds(0.0D, 0.9D, 0.0D, 1.0D, 1.0D, 0.1D);
		renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
		renderBlocks.setRenderBounds(0.0D, 0.9D, 0.9D, 1.0D, 1.0D, 1.0D);
		renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
		renderBlocks.setRenderBounds(0.0D, 0.9D, 0.1D, 0.1D, 1.0D, 0.9D);
		renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
		renderBlocks.setRenderBounds(0.9D, 0.9D, 0.1D, 1.0D, 1.0D, 0.9D);
		renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
		renderBlocks.setRenderBounds(0.45D, 0.9D, 0.45D, 0.55D, 1.0D, 0.55D);
		renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
		//ici la vitre
		setIconOverride(6, icon);
		renderBlocks.setRenderBounds(0.09D, 0.91D, 0.09D, 0.91D, 0.95D, 0.91D);
		renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
		clearIconOverride(6);
	}
	
	private void renderTableBasse(TECarpentersBlock tE, RenderBlocks renderBlocks, Block coverBlock, Block srcBlock, int x, int y, int z)
	{
		int data = BlockProperties.getData(tE);
		int Rotation = TableD.getRotation(data);

		renderBlocks.setRenderBounds(0.6D, 0.45D, 0.6D, 0.4D, 0.5D, 0.4D);
		renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
		renderBlocks.setRenderBounds(0.55D, 0.1D, 0.55D, 0.45D, 0.45D, 0.45D);
		renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
		renderBlocks.setRenderBounds(0.6D, 0.05D, 0.6D, 0.4D, 0.1D, 0.4D);
		renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
		renderBlocks.setRenderBounds(0.7D, 0.0D, 0.7D, 0.3D, 0.05D, 0.3D);
		renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
		switch (Rotation)
		{
			case TableD.ROTATE_0:
				renderBlocks.setRenderBounds(0.2D, 0.5D, 0.0D, 0.8D, 0.6D, 0.1D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.5D, 0.9D, 0.8D, 0.6D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.5D, 0.1D, 0.3D, 0.6D, 0.9D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.5D, 0.1D, 0.8D, 0.6D, 0.9D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.50D, 0.1D, 0.7D, 0.55D, 0.9D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				break;
			case TableD.ROTATE_1:
				renderBlocks.setRenderBounds(0.0D, 0.5D, 0.2D, 1.0D, 0.6D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.5D, 0.7D, 1.0D, 0.6D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.5D, 0.3D, 0.1D, 0.6D, 0.7D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.9D, 0.5D, 0.3D, 1.0D, 0.6D, 0.7D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.1D, 0.50D, 0.3D, 0.9D, 0.55D, 0.7D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
		}
	}
	
	private void renderTableBasseGlass(TECarpentersBlock tE, RenderBlocks renderBlocks, Block coverBlock, Block srcBlock, int x, int y, int z)
	{
		int data = BlockProperties.getData(tE);
		Icon icon;
		icon = Block.glass.getIcon(0, 0);
		int Rotation = TableD.getRotation(data);

		renderBlocks.setRenderBounds(0.6D, 0.45D, 0.6D, 0.4D, 0.5D, 0.4D);
		renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
		renderBlocks.setRenderBounds(0.55D, 0.1D, 0.55D, 0.45D, 0.45D, 0.45D);
		renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
		renderBlocks.setRenderBounds(0.6D, 0.05D, 0.6D, 0.4D, 0.1D, 0.4D);
		renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
		renderBlocks.setRenderBounds(0.7D, 0.0D, 0.7D, 0.3D, 0.05D, 0.3D);
		renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
		switch (Rotation)
		{
			case TableD.ROTATE_0:
				renderBlocks.setRenderBounds(0.2D, 0.5D, 0.0D, 0.8D, 0.6D, 0.1D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.5D, 0.9D, 0.8D, 0.6D, 1.0D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.5D, 0.1D, 0.3D, 0.6D, 0.9D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.5D, 0.1D, 0.8D, 0.6D, 0.9D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				break;
			case TableD.ROTATE_1:
				renderBlocks.setRenderBounds(0.0D, 0.5D, 0.2D, 1.0D, 0.6D, 0.3D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.5D, 0.7D, 1.0D, 0.6D, 0.8D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.5D, 0.3D, 0.1D, 0.6D, 0.7D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
				renderBlocks.setRenderBounds(0.9D, 0.5D, 0.3D, 1.0D, 0.6D, 0.7D);
				renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
		}
		renderBlocks.setRenderBounds(0.45D, 0.5D, 0.45D, 0.55D, 0.6D, 0.55D);
		renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
		//ici la vitre
		setIconOverride(6, icon);
		switch (Rotation)
		{
			case TableD.ROTATE_0:
				renderBlocks.setRenderBounds(0.29D, 0.51D, 0.09D, 0.71D, 0.55D, 0.91D);
				break;
			case TableD.ROTATE_1:
				renderBlocks.setRenderBounds(0.09D, 0.51D, 0.29D, 0.91D, 0.55D, 0.71D);
		}
		renderStandardBlock(tE, renderBlocks, coverBlock, srcBlock, x, y, z);
		clearIconOverride(6);
	}
}
