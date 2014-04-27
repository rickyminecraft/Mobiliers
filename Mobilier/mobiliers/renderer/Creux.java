package mobiliers.renderer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mobiliers.data.CreuxD;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.item.ItemStack;
import carpentersblocks.renderer.BlockHandlerBase;
import carpentersblocks.util.BlockProperties;

@SideOnly(Side.CLIENT)
public class Creux extends BlockHandlerBase
{
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderBlocks)
	{
		for(int box = 0; box < 4; box++)
		{
			switch (box)
			{
				case 0:
					renderBlocks.setRenderBounds(0.0D, 0.0D, 0.0D, 0.2D, 1.0D, 1.0D);
					break;
				case 1:
					renderBlocks.setRenderBounds(0.8D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
					break;
				case 2:
					renderBlocks.setRenderBounds(0.2D, 0.0D, 0.0D, 0.8D, 1.0D, 0.2D);
					break;
				case 3:
					renderBlocks.setRenderBounds(0.2D, 0.0D, 0.8D, 0.8D, 1.0D, 1.0D);
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
		renderCreux(coverBlock, x, y, z);
//		int data = BlockProperties.getMetadata(TE);
//		int type = CreuxD.getType(data);
//		switch (type)
//		{
//			case CreuxD.TYPE_1:
//				renderCreux(coverBlock, x, y, z);
//				break;
//			case CreuxD.TYPE_2:
//				renderCreux_1(coverBlock, x, y, z);
//				break;
//			case CreuxD.TYPE_3:
//				renderCreux_2(coverBlock, x, y, z);
//				break;
//			case CreuxD.TYPE_4:
//				renderCreux_3(coverBlock, x, y, z);
//		}
	}
	
	private void renderCreux(ItemStack coverBlock, int x, int y, int z)
	{
		int data = BlockProperties.getMetadata(TE);
		int rotation = CreuxD.getRotation(data);
		switch (rotation)
		{
		case CreuxD.CREUX_X_NEG:
		case CreuxD.CREUX_X_POS:
			renderBlocks.setRenderBounds(0.0D, 0.0D, 0.2D, 0.2D, 1.0D, 1.0D);
			renderBlock(coverBlock, x, y, z);
			renderBlocks.setRenderBounds(0.8D, 0.0D, 0.2D, 1.0D, 1.0D, 1.0D);
			renderBlock(coverBlock, x, y, z);
			renderBlocks.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.2D);
			renderBlock(coverBlock, x, y, z);
			renderBlocks.setRenderBounds(0.2D, 0.0D, 0.8D, 0.8D, 1.0D, 1.0D);
			renderBlock(coverBlock, x, y, z);
			break;
		case CreuxD.CREUX_Y_NEG:
		case CreuxD.CREUX_Y_POS:
			renderBlocks.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.2D, 1.0D);
			renderBlock(coverBlock, x, y, z);
			renderBlocks.setRenderBounds(0.0D, 0.8D, 0.0D, 1.0D, 1.0D, 1.0D);
			renderBlock(coverBlock, x, y, z);
			renderBlocks.setRenderBounds(0.0D, 0.2D, 0.0D, 1.0D, 0.8D, 0.2D);
			renderBlock(coverBlock, x, y, z);
			renderBlocks.setRenderBounds(0.0D, 0.2D, 0.8D, 1.0D, 0.8D, 1.0D);
			renderBlock(coverBlock, x, y, z);
			break;
		case CreuxD.CREUX_Z_NEG:
		case CreuxD.CREUX_Z_POS:
			renderBlocks.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.2D, 1.0D);
			renderBlock(coverBlock, x, y, z);
			renderBlocks.setRenderBounds(0.0D, 0.8D, 0.0D, 1.0D, 1.0D, 1.0D);
			renderBlock(coverBlock, x, y, z);
			renderBlocks.setRenderBounds(0.0D, 0.2D, 0.0D, 0.2D, 0.8D, 1.0D);
			renderBlock(coverBlock, x, y, z);
			renderBlocks.setRenderBounds(0.8D, 0.2D, 0.0D, 1.0D, 0.8D, 1.0D);
			renderBlock(coverBlock, x, y, z);
		}
	}
}
