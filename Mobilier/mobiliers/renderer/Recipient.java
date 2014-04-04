package mobiliers.renderer;

import mobiliers.data.RecipientD;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import carpentersblocks.renderer.BlockHandlerBase;
import carpentersblocks.util.BlockProperties;

@SideOnly(Side.CLIENT)
public class Recipient extends BlockHandlerBase
{
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderBlocks)
	{
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
			super.renderInventoryBlock(block, metadata, modelID, renderBlocks);
		}
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
	public void renderCarpentersBlock(int x, int y, int z)
	{
		IIcon icon1 = BlockLiquid.getLiquidIcon("water_still");
		int data = BlockProperties.getMetadata(TE);
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
		
		ItemStack coverBlock = BlockProperties.hasCover(TE, 6) == true ? BlockProperties.getCover(TE, coverRendering) : BlockProperties.getCover(TE, 6);

		for (int box = 0; box < 8; ++box)
		{
			float[] bounds = genBounds(box, data);

			if (bounds != null)
			{
				renderBlocks.setRenderBounds(bounds[0], bounds[1], bounds[2], bounds[3], bounds[4], bounds[5]);
				renderBlock(coverBlock, x, y, z);
			}
		}
	}
}
