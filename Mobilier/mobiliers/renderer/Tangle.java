package mobiliers.renderer;

import mobiliers.data.TangleD;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import carpentersblocks.renderer.BlockHandlerBase;
import carpentersblocks.util.BlockProperties;

@SideOnly(Side.CLIENT)
public class Tangle extends BlockHandlerBase
{
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderBlocks)
	{
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
			super.renderInventoryBlock(block, metadata, modelID, renderBlocks);
		}
	}
	
	@Override
	/**
	 * Renders stairs at the given coordinates
	 */
	public void renderCarpentersBlock(int x, int y, int z)
	{
		ItemStack coverBlock = BlockProperties.hasCover(TE, 6) == true ? BlockProperties.getCover(TE, coverRendering) : BlockProperties.getCover(TE, 6);

		renderTableAngle(coverBlock, x, y, z);
	}
	
	private void renderTableAngle(ItemStack coverBlock, int x, int y, int z)
	{
		int data = BlockProperties.getMetadata(TE);
		switch (data)
		{
			case TangleD.TANGLE_X_NEG:
				renderBlocks.setRenderBounds(0.1D, 0.0D, 0.1D, 0.2D, 0.9D, 0.2D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.9D, 0.0D, 1.0D, 1.0D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				break;
			case TangleD.TANGLE_X_POS:
				renderBlocks.setRenderBounds(0.8D, 0.0D, 0.8D, 0.9D, 0.9D, 0.9D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.9D, 0.0D, 1.0D, 1.0D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				break;
			case TangleD.TANGLE_Z_NEG:
				renderBlocks.setRenderBounds(0.8D, 0.0D, 0.1D, 0.9D, 0.9D, 0.2D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.9D, 0.0D, 1.0D, 1.0D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				break;
			case TangleD.TANGLE_Z_POS:
				renderBlocks.setRenderBounds(0.1D, 0.0D, 0.8D, 0.2D, 0.9D, 0.9D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.9D, 0.0D, 1.0D, 1.0D, 1.0D);
				renderBlock(coverBlock, x, y, z);
		}
	}
}
