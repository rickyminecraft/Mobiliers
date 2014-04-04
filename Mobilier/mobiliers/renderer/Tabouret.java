package mobiliers.renderer;

import mobiliers.data.TabouretD;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import carpentersblocks.renderer.BlockHandlerBase;
import carpentersblocks.util.BlockProperties;

@SideOnly(Side.CLIENT)
public class Tabouret extends BlockHandlerBase
{
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderBlocks)
	{
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

		int data = BlockProperties.getMetadata(TE);
		int type = TabouretD.getType(data);
		switch (type)
		{
			case TabouretD.TYPE_1:
				renderTabouretBas(coverBlock, x, y, z);
				break;
			case TabouretD.TYPE_2:
				renderTabouret(coverBlock, x, y, z);
				break;
		}
	}

	private void renderTabouretBas(ItemStack coverBlock, int x, int y, int z)
	{
		renderBlocks.setRenderBounds(0.2D, 0.0D, 0.2D, 0.3D, 0.4D, 0.3D);
		renderBlock(coverBlock, x, y, z);
		renderBlocks.setRenderBounds(0.7D, 0.0D, 0.7D, 0.8D, 0.4D, 0.8D);
		renderBlock(coverBlock, x, y, z);
		renderBlocks.setRenderBounds(0.7D, 0.0D, 0.2D, 0.8D, 0.4D, 0.3D);
		renderBlock(coverBlock, x, y, z);
		renderBlocks.setRenderBounds(0.2D, 0.0D, 0.7D, 0.3D, 0.4D, 0.8D);
		renderBlock(coverBlock, x, y, z);
		renderBlocks.setRenderBounds(0.15D, 0.4D, 0.15D, 0.85D, 0.5D, 0.85D);
		renderBlock(coverBlock, x, y, z);
	}
	
	private void renderTabouret(ItemStack coverBlock, int x, int y, int z)
	{
		//barres
		renderBlocks.setRenderBounds(0.3D, 0.0D, 0.3D, 0.4D, 0.7D, 0.4D);
		renderBlock(coverBlock, x, y, z);
		renderBlocks.setRenderBounds(0.6D, 0.0D, 0.6D, 0.7D, 0.7D, 0.7D);
		renderBlock(coverBlock, x, y, z);
		renderBlocks.setRenderBounds(0.6D, 0.0D, 0.3D, 0.7D, 0.7D, 0.4D);
		renderBlock(coverBlock, x, y, z);
		renderBlocks.setRenderBounds(0.3D, 0.0D, 0.6D, 0.4D, 0.7D, 0.7D);
		renderBlock(coverBlock, x, y, z);
		
		//b centres
		renderBlocks.setRenderBounds(0.4D, 0.3D, 0.3D, 0.6D, 0.4D, 0.4D);
		renderBlock(coverBlock, x, y, z);
		renderBlocks.setRenderBounds(0.4D, 0.3D, 0.6D, 0.6D, 0.4D, 0.7D);
		renderBlock(coverBlock, x, y, z);
		renderBlocks.setRenderBounds(0.6D, 0.3D, 0.4D, 0.7D, 0.4D, 0.6D);
		renderBlock(coverBlock, x, y, z);
		renderBlocks.setRenderBounds(0.3D, 0.3D, 0.4D, 0.4D, 0.4D, 0.6D);
		renderBlock(coverBlock, x, y, z);
		
		//haut
		renderBlocks.setRenderBounds(0.2D, 0.7D, 0.2D, 0.8D, 0.8D, 0.8D);
		renderBlock(coverBlock, x, y, z);
	}
}
