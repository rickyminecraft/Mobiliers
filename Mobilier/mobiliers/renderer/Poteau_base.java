package mobiliers.renderer;

import mobiliers.data.Poteau_baseD;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import carpentersblocks.renderer.BlockHandlerBase;
import carpentersblocks.util.BlockProperties;

@SideOnly(Side.CLIENT)
public class Poteau_base extends BlockHandlerBase
{
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderBlocks)
	{
		for (int box = 0; box < 3; ++box)
		{
			switch (box)
			{
				case 0:
					renderBlocks.setRenderBounds(0.2D, 0.0D, 0.2D, 0.8D, 0.1D, 0.8D);
					break;
				case 1:
					renderBlocks.setRenderBounds(0.3D, 0.1D, 0.3D, 0.7D, 0.2D, 0.7D);
					break;
				case 2:
					renderBlocks.setRenderBounds(0.4D, 0.2D, 0.4D, 0.6D, 1.0D, 0.6D);
			}
			super.renderInventoryBlock(block, metadata, modelID, renderBlocks);
		}
	}

	@Override
	/**
	 * Renders barrier
	 */
	public void renderCarpentersBlock(int x, int y, int z)
	{
		ItemStack coverBlock = BlockProperties.hasCover(TE, 6) == true ? BlockProperties.getCover(TE, coverRendering) : BlockProperties.getCover(TE, 6);

		renderPoteau(coverBlock, x, y, z);
	}

	private void renderPoteau(ItemStack coverBlock, int x, int y, int z)
	{
		int position = BlockProperties.getMetadata(TE);
		switch (position)
		{
			case Poteau_baseD.DOWN:
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.2D, 0.8D, 0.1D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.1D, 0.3D, 0.7D, 0.2D, 0.7D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.375, 0.2D, 0.375, 0.625, 1.0D, 0.625);
				renderBlock(coverBlock, x, y, z);
				break;
			case Poteau_baseD.UP:
				renderBlocks.setRenderBounds(0.2D, 0.9D, 0.2D, 0.8D, 1.0D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.8D, 0.3D, 0.7D, 0.9D, 0.7D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.375, 0.0D, 0.375, 0.625, 0.8D, 0.625);
				renderBlock(coverBlock, x, y, z);
		}
	}
}
