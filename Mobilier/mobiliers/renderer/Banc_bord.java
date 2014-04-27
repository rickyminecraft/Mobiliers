package mobiliers.renderer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mobiliers.data.BancD;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.item.ItemStack;
import carpentersblocks.renderer.BlockHandlerBase;
import carpentersblocks.util.BlockProperties;

@SideOnly(Side.CLIENT)
public class Banc_bord extends BlockHandlerBase
{
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderBlocks)
	{
		renderBlocks.setRenderBounds(0.15D, 0.4D, 0.15D, 0.85D, 0.5D, 1.0D);
		super.renderInventoryBlock(block, metadata, modelID, renderBlocks);
		renderBlocks.setRenderBounds(0.2D, 0.0D, 0.2D, 0.8D, 0.4D, 0.3D);
		super.renderInventoryBlock(block, metadata, modelID, renderBlocks);
	}
	
	@Override
	/**
	 * Renders stairs at the given coordinates
	 */
	public void renderCarpentersBlock(int x, int y, int z)
	{
		ItemStack coverBlock = BlockProperties.hasCover(TE, 6) == true ? BlockProperties.getCover(TE, coverRendering) : BlockProperties.getCover(TE, 6);
		renderBancBord(coverBlock, x, y, z);
//		int data = BlockProperties.getMetadata(TE);
//		int type = BancD.getType(data);
//		switch (type)
//		{
//			case BancD.BANC_1:
//				renderBancBord(coverBlock, x, y, z);
//				break;
//			case BancD.BANC_2:
//				renderBancBord_2(coverBlock, x, y, z);
//				break;
//			case BancD.BANC_3:
//				renderBancBorde_3(coverBlock, x, y, z);
//				break;
//			case BancD.BANC_4:
//				renderBancBord_4(coverBlock, x, y, z);
//		}
	}
	
	private void renderBancBord(ItemStack coverBlock, int x, int y, int z)
	{
		int data = BlockProperties.getMetadata(TE);
		int rotation = BancD.getRotation(data);
		switch (rotation)
		{	
			case BancD.BANC_X_NEG:
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.2D, 0.3D, 0.4D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.15D, 0.4D, 0.15D, 1.0D, 0.5D, 0.85D);
				renderBlock(coverBlock, x, y, z);
				break;
			case BancD.BANC_X_POS:
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.2D, 0.8D, 0.4D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.4D, 0.15D, 0.85D, 0.5D, 0.85D);
				renderBlock(coverBlock, x, y, z);
				break;
			case BancD.BANC_Z_NEG:
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.2D, 0.8D, 0.4D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.15D, 0.4D, 0.15D, 0.85D, 0.5D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				break;
			case BancD.BANC_Z_POS:
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.7D, 0.8D, 0.4D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.15D, 0.4D, 0.0D, 0.85D, 0.5D, 0.85D);
				renderBlock(coverBlock, x, y, z);
		}	
	}
}
