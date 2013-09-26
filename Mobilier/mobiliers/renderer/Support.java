package mobiliers.renderer;

import mobiliers.data.SupportD;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.opengl.GL11;

import carpentersblocks.renderer.BlockHandlerBase;
import carpentersblocks.tileentity.TECarpentersBlock;
import carpentersblocks.util.BlockProperties;

public class Support extends BlockHandlerBase
{
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderBlocks)
	{
		Tessellator tessellator = Tessellator.instance;
		GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

		for (int box = 0; box < 3; ++box)
		{
			switch (box)
			{
				case 0:
					renderBlocks.setRenderBounds(0.0F, 0.9F, 0.45F, 0.9F, 1.0F, 0.55F);
					break;
				case 1:
					renderBlocks.setRenderBounds(0.9F, 0.0F, 0.45F, 1.0F, 1.0F, 0.55F);
					break;
				case 2:
					renderBlocks.setRenderBounds(0.7F, 0.7F, 0.475F, 0.9F, 0.9F, 0.525F);
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
	
	/**
	 * Will return stairs boundaries for data.
	 */
	public float[] genBounds(int box, int data, boolean updown)
	{
		++box;
		if (!updown)
		{
			switch (data)
			{
				case SupportD.SUPPORT_X_NEG:
					switch (box)
					{
						case 1:
							return new float[] { 0.0F, 0.9F, 0.45F, 1.0F, 1.0F, 0.55F };
						case 2:
							return new float[] { 0.9F, 0.0F, 0.45F, 1.0F, 0.9F, 0.55F };
						case 3:
							return new float[] { 0.7F, 0.7F, 0.475F, 0.9F, 0.9F, 0.525F };
						case 4:
							return new float[] { 0.8F, 0.6F, 0.475F, 0.9F, 0.7F, 0.525F };
						case 5:
							return new float[] { 0.6F, 0.8F, 0.475F, 0.7F, 0.9F, 0.525F };
						case 6:
							return new float[] { 0.4F, 0.85F, 0.475F, 0.6F, 0.9F, 0.525F };
						case 7:
							return new float[] { 0.85F, 0.4F, 0.475F, 0.9F, 0.6F, 0.525F };
						default:
							return null;
					}
				case SupportD.SUPPORT_X_POS:
					switch (box)
					{
						case 1:
							return new float[] { 0.0F, 0.9F, 0.45F, 1.0F, 1.0F, 0.55F };
						case 2:
							return new float[] { 0.0F, 0.0F, 0.45F, 0.1F, 0.9F, 0.55F };
						case 3:
							return new float[] { 0.1F, 0.7F, 0.475F, 0.3F, 0.9F, 0.525F };
						case 4:
							return new float[] { 0.1F, 0.6F, 0.475F, 0.2F, 0.7F, 0.525F };
						case 5:
							return new float[] { 0.3F, 0.8F, 0.475F, 0.4F, 0.9F, 0.525F };
						case 6:
							return new float[] { 0.4F, 0.85F, 0.475F, 0.6F, 0.9F, 0.525F };
						case 7:
							return new float[] { 0.1F, 0.4F, 0.475F, 0.15F, 0.6F, 0.525F };
						default:
							return null;
					}
				case SupportD.SUPPORT_Z_NEG:
					switch (box)
					{
						case 1:
							return new float[] { 0.45F, 0.9F, 0.0F, 0.55F, 1.0F, 1.0F };
						case 2:
							return new float[] { 0.45F, 0.0F, 0.9F, 0.55F, 0.9F, 1.0F };
						case 3:
							return new float[] { 0.475F, 0.7F, 0.7F, 0.525F, 0.9F, 0.9F };
						case 4:
							return new float[] { 0.475F, 0.6F, 0.8F, 0.525F, 0.7F, 0.9F };
						case 5:
							return new float[] { 0.475F, 0.8F, 0.6F, 0.525F, 0.9F, 0.7F };
						case 6:
							return new float[] { 0.475F, 0.85F, 0.4F, 0.525F, 0.9F, 0.6F };
						case 7:
							return new float[] { 0.475F, 0.4F, 0.85F, 0.525F, 0.6F, 0.9F };
						default:
							return null;
					}
				case SupportD.SUPPORT_Z_POS:
					switch (box)
					{
						case 1:
							return new float[] { 0.45F, 0.9F, 0.0F, 0.55F, 1.0F, 1.0F };
						case 2:
							return new float[] { 0.45F, 0.0F, 0.0F, 0.55F, 0.9F, 0.1F };
						case 3:
							return new float[] { 0.475F, 0.7F, 0.1F, 0.525F, 0.9F, 0.3F };
						case 4:
							return new float[] { 0.475F, 0.6F, 0.1F, 0.525F, 0.7F, 0.2F };
						case 5:
							return new float[] { 0.475F, 0.8F, 0.3F, 0.525F, 0.9F, 0.4F };
						case 6:
							return new float[] { 0.475F, 0.85F, 0.4F, 0.525F, 0.9F, 0.6F };
						case 7:
							return new float[] { 0.475F, 0.4F, 0.1F, 0.525F, 0.6F, 0.15F };
						default:
							return null;
					}
			}
		}
		else
		{
			switch (data)
			{
				case SupportD.SUPPORT_X_NEG:
					switch (box)
					{
						case 1:
							return new float[] { 0.0F, 0.0F, 0.45F, 1.0F, 0.1F, 0.55F };
						case 2:
							return new float[] { 0.9F, 0.1F, 0.45F, 1.0F, 1.0F, 0.55F };
						case 3:
							return new float[] { 0.7F, 0.1F, 0.475F, 0.9F, 0.3F, 0.525F };
						case 4:
							return new float[] { 0.8F, 0.3F, 0.475F, 0.9F, 0.4F, 0.525F };
						case 5:
							return new float[] { 0.6F, 0.1F, 0.475F, 0.7F, 0.2F, 0.525F };
						case 6:
							return new float[] { 0.4F, 0.1F, 0.475F, 0.6F, 0.15F, 0.525F };
						case 7:
							return new float[] { 0.85F, 0.4F, 0.475F, 0.9F, 0.6F, 0.525F };
						default:
							return null;
					}
				case SupportD.SUPPORT_X_POS:
					switch (box)
					{
						case 1:
							return new float[] { 0.0F, 0.0F, 0.45F, 1.0F, 0.1F, 0.55F };
						case 2:
							return new float[] { 0.0F, 0.1F, 0.45F, 0.1F, 1.0F, 0.55F };
						case 3:
							return new float[] { 0.1F, 0.1F, 0.475F, 0.3F, 0.3F, 0.525F };
						case 4:
							return new float[] { 0.1F, 0.3F, 0.475F, 0.2F, 0.4F, 0.525F };
						case 5:
							return new float[] { 0.3F, 0.1F, 0.475F, 0.4F, 0.2F, 0.525F };
						case 6:
							return new float[] { 0.4F, 0.1F, 0.475F, 0.6F, 0.15F, 0.525F };
						case 7:
							return new float[] { 0.1F, 0.4F, 0.475F, 0.15F, 0.6F, 0.525F };
						default:
							return null;
					}
				case SupportD.SUPPORT_Z_NEG:
					switch (box)
					{
						case 1:
							return new float[] { 0.45F, 0.0F, 0.0F, 0.55F, 0.1F, 1.0F };
						case 2:
							return new float[] { 0.45F, 0.1F, 0.9F, 0.55F, 1.0F, 1.0F };
						case 3:
							return new float[] { 0.475F, 0.1F, 0.7F, 0.525F, 0.3F, 0.9F };
						case 4:
							return new float[] { 0.475F, 0.3F, 0.8F, 0.525F, 0.4F, 0.9F };
						case 5:
							return new float[] { 0.475F, 0.1F, 0.6F, 0.525F, 0.2F, 0.7F };
						case 6:
							return new float[] { 0.475F, 0.1F, 0.4F, 0.525F, 0.15F, 0.6F };
						case 7:
							return new float[] { 0.475F, 0.4F, 0.85F, 0.525F, 0.6F, 0.9F };
						default:
							return null;
					}
				case SupportD.SUPPORT_Z_POS:
					switch (box)
					{
						case 1:
							return new float[] { 0.45F, 0.0F, 0.0F, 0.55F, 0.1F, 1.0F };
						case 2:
							return new float[] { 0.45F, 0.1F, 0.0F, 0.55F, 1.0F, 0.1F };
						case 3:
							return new float[] { 0.475F, 0.1F, 0.1F, 0.525F, 0.3F, 0.3F };
						case 4:
							return new float[] { 0.475F, 0.3F, 0.1F, 0.525F, 0.4F, 0.2F };
						case 5:
							return new float[] { 0.475F, 0.1F, 0.3F, 0.525F, 0.2F, 0.4F };
						case 6:
							return new float[] { 0.475F, 0.1F, 0.4F, 0.525F, 0.15F, 0.6F };
						case 7:
							return new float[] { 0.475F, 0.4F, 0.1F, 0.525F, 0.6F, 0.15F };
						default:
							return null;
					}
			}
		}
		
		return null;
	}

	@Override
	/**
	 * Renders stairs at the given coordinates
	 */
	public boolean renderCarpentersBlock(TECarpentersBlock TE, RenderBlocks renderBlocks, Block srcBlock, int renderPass, int x, int y, int z)
	{
		int data = BlockProperties.getData(TE);
		boolean flag = (data & 8) > 0;
		data &= 7;
		
		Block coverBlock = isSideCover ? BlockProperties.getCoverBlock(TE, coverRendering) : BlockProperties.getCoverBlock(TE, 6);

		for (int box = 0; box < 8; ++box)
		{
			float[] bounds = genBounds(box, data, flag);

			if (bounds != null)
			{
				renderBlocks.setRenderBounds(bounds[0], bounds[1], bounds[2], bounds[3], bounds[4], bounds[5]);
				renderStandardBlock(TE, renderBlocks, coverBlock, srcBlock, x, y, z);
			}
		}

		return true;
	}
}
