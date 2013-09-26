package mobiliers.renderer;

import mobiliers.data.RecipientD;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFluid;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;

import org.lwjgl.opengl.GL11;

import carpentersblocks.renderer.BlockHandlerBase;
import carpentersblocks.tileentity.TECarpentersBlock;
import carpentersblocks.util.BlockProperties;

public class Recipient extends BlockHandlerBase
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
					renderBlocks.setRenderBounds(0.45F, 0.0F, 0.45F, 0.55F, 0.05F, 0.55F);
					break;
				case 1:
					renderBlocks.setRenderBounds(0.4F, 0.05F, 0.4F, 0.45F, 0.3F, 0.60F);
					break;
				case 2:
					renderBlocks.setRenderBounds(0.55F, 0.05F, 0.4F, 0.60F, 0.3F, 0.60F);
					break;
				case 3:
					renderBlocks.setRenderBounds(0.45F, 0.05F, 0.4F, 0.55F, 0.3F, 0.45F);
					break;
				case 4:
					renderBlocks.setRenderBounds(0.45F, 0.05F, 0.55F, 0.55F, 0.3F, 0.60F);
					break;
				case 5:
					renderBlocks.setRenderBounds(0.45F, 0.2F, 0.35F, 0.55F, 0.3F, 0.4F);
					break;
				case 6:
					renderBlocks.setRenderBounds(0.45F, 0.05F, 0.30F, 0.55F, 0.3F, 0.35F);
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
	public float[] genBounds(int box, int data)
	{
		++box;
		switch (data)
		{
			case RecipientD.RECIPIENT_X_NEG:
				switch (box)
				{
					case 1:
						return new float[] { 0.45F, 0.0F, 0.7F, 0.55F, 0.05F, 0.8F };
					case 2:
						return new float[] { 0.4F, 0.05F, 0.65F, 0.45F, 0.3F, 0.85F };
					case 3:
						return new float[] { 0.55F, 0.05F, 0.65F, 0.6F, 0.3F, 0.85F };
					case 4:
						return new float[] { 0.45F, 0.05F, 0.65F, 0.55F, 0.3F, 0.7F };
					case 5:
						return new float[] { 0.45F, 0.05F, 0.8F, 0.55F, 0.3F, 0.85F };
					case 6:
						return new float[] { 0.35F, 0.2F, 0.7F, 0.4F, 0.3F, 0.8F };
					case 7:
						return new float[] { 0.3F, 0.05F, 0.7F, 0.35F, 0.3F, 0.8F };
					default:
						return null;
				}
			case RecipientD.RECIPIENT_X_POS:
				switch (box)
				{
					case 1:
						return new float[] { 0.2F, 0.0F, 0.45F, 0.3F, 0.05F, 0.55F };
					case 2:
						return new float[] { 0.15F, 0.05F, 0.4F, 0.2F, 0.3F, 0.6F };
					case 3:
						return new float[] { 0.3F, 0.05F, 0.4F, 0.35F, 0.3F, 0.6F };
					case 4:
						return new float[] { 0.2F, 0.05F, 0.4F, 0.3F, 0.3F, 0.45F };
					case 5:
						return new float[] { 0.2F, 0.05F, 0.55F, 0.3F, 0.3F, 0.6F };
					case 6:
						return new float[] { 0.2F, 0.2F, 0.35F, 0.3F, 0.3F, 0.4F };
					case 7:
						return new float[] { 0.2F, 0.05F, 0.3F, 0.3F, 0.3F, 0.35F };
					default:
						return null;
				}
			case RecipientD.RECIPIENT_Z_NEG:
				switch (box)
				{
					case 1:
						return new float[] { 0.45F, 0.0F, 0.2F, 0.55F, 0.05F, 0.3F };
					case 2:
						return new float[] { 0.4F, 0.05F, 0.15F, 0.45F, 0.3F, 0.35F };
					case 3:
						return new float[] { 0.55F, 0.05F, 0.15F, 0.6F, 0.3F, 0.35F };
					case 4:
						return new float[] { 0.45F, 0.05F, 0.15F, 0.55F, 0.3F, 0.2F };
					case 5:
						return new float[] { 0.45F, 0.05F, 0.3F, 0.55F, 0.3F, 0.35F };
					case 6:
						return new float[] { 0.6F, 0.2F, 0.2F, 0.65F, 0.3F, 0.3F };
					case 7:
						return new float[] { 0.65F, 0.05F, 0.2F, 0.7F, 0.3F, 0.3F };
					default:
						return null;
				}
			case RecipientD.RECIPIENT_Z_POS:
				switch (box)
				{
					case 1:
						return new float[] { 0.7F, 0.0F, 0.45F, 0.8F, 0.05F, 0.55F };
					case 2:
						return new float[] { 0.65F, 0.05F, 0.4F, 0.7F, 0.3F, 0.6F };
					case 3:
						return new float[] { 0.8F, 0.05F, 0.4F, 0.85F, 0.3F, 0.6F };
					case 4:
						return new float[] { 0.7F, 0.05F, 0.4F, 0.8F, 0.3F, 0.45F };
					case 5:
						return new float[] { 0.7F, 0.05F, 0.55F, 0.8F, 0.3F, 0.6F };
					case 6:
						return new float[] { 0.7F, 0.2F, 0.6F, 0.8F, 0.3F, 0.65F };
					case 7:
						return new float[] { 0.7F, 0.05F, 0.65F, 0.8F, 0.3F, 0.7F };
					default:
						return null;
				}
			default: // RecipientD.CENTRER
				switch (box)
				{
					case 1: // base
						return new float[] { 0.45F, 0.0F, 0.45F, 0.55F, 0.05F, 0.55F };
					case 2:// bord
						return new float[] { 0.4F, 0.05F, 0.4F, 0.45F, 0.3F, 0.6F };
					case 3:// bord
						return new float[] { 0.55F, 0.05F, 0.4F, 0.6F, 0.3F, 0.6F };
					case 4:// bord
						return new float[] { 0.45F, 0.05F, 0.4F, 0.55F, 0.3F, 0.45F };
					case 5:// bord
						return new float[] { 0.45F, 0.05F, 0.55F, 0.55F, 0.3F, 0.6F };
					case 6:// poigne
						return new float[] { 0.45F, 0.2F, 0.6F, 0.55F, 0.3F, 0.65F };
					case 7:// poigne
						return new float[] { 0.45F, 0.05F, 0.65F, 0.55F, 0.3F, 0.7F };
					default:
						return null;
				}
		}
	}

	@Override
	/**
	 * Renders stairs at the given coordinates
	 */
	public boolean renderCarpentersBlock(TECarpentersBlock TE, RenderBlocks renderBlocks, Block srcBlock, int renderPass, int x, int y, int z)
	{
		Icon icon1 = BlockFluid.getFluidIcon("water_still");
		int data = BlockProperties.getData(TE);
		boolean flag = (data & 8) > 0;
		data &= 7;
		if (flag)
		{
			float[] bounds = genBounds(0, data);
			renderBlocks.setRenderBounds(bounds[0], 0.05F, bounds[2], bounds[3], 0.25F, bounds[5]);
			renderBlocks.overrideBlockTexture = icon1;
			renderBlocks.renderStandardBlock(srcBlock, x, y, z);
			renderBlocks.overrideBlockTexture = null;
		}
		
		Block coverBlock = isSideCover ? BlockProperties.getCoverBlock(TE, coverRendering) : BlockProperties.getCoverBlock(TE, 6);

		for (int box = 0; box < 8; ++box)
		{
			float[] bounds = genBounds(box, data);

			if (bounds != null)
			{
				renderBlocks.setRenderBounds(bounds[0], bounds[1], bounds[2], bounds[3], bounds[4], bounds[5]);
				renderStandardBlock(TE, renderBlocks, coverBlock, srcBlock, x, y, z);
			}
		}

		return true;
	}
}
