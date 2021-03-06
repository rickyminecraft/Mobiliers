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
public class Banc extends BlockHandlerBase
{
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderBlocks)
	{
		renderBlocks.setRenderBounds(0.15D, 0.4D, 0.15D, 0.85D, 0.5D, 0.85D);
		super.renderInventoryBlock(block, metadata, modelID, renderBlocks);
	}
	
	@Override
	/**
	 * Renders stairs at the given coordinates
	 */
	public void renderCarpentersBlock(int x, int y, int z)
	{
		ItemStack coverBlock = BlockProperties.hasCover(TE, 6) == true ? BlockProperties.getCover(TE, coverRendering) : BlockProperties.getCover(TE, 6);
		renderBanc(coverBlock, x, y, z);
//		int data = BlockProperties.getMetadata(TE);
//		int type = BancD.getType(data);
//		switch (type)
//		{
//			case BancD.BANC_1:
//				renderBanc(coverBlock, x, y, z);
//				break;
//			case BancD.BANC_2:
//				renderBanc_2(coverBlock, x, y, z);
//				break;
//			case BancD.BANC_3:
//				renderBanc_3(coverBlock, x, y, z);
//				break;
//			case BancD.BANC_4:
//				renderBanc_4(coverBlock, x, y, z);
//		}
	}
	
	private void renderBanc(ItemStack coverBlock, int x, int y, int z)
	{
		int data = BlockProperties.getMetadata(TE);
		int rotation = BancD.getRotation(data);
		switch (rotation)
		{	
			case BancD.BANC_X_NEG:
				renderBlocks.setRenderBounds(0.15D, 0.4D, 0.0D, 0.85D, 0.5D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				break;
			case BancD.BANC_Z_NEG:
				renderBlocks.setRenderBounds(0.0D, 0.4D, 0.15D, 1.0D, 0.5D, 0.85D);
				renderBlock(coverBlock, x, y, z);
		}	
	}
}
