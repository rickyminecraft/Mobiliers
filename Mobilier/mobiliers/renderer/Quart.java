package mobiliers.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import carpentersblocks.renderer.BlockHandlerBase;

@SideOnly(Side.CLIENT)
public class Quart extends BlockHandlerBase
{
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderBlocks)
	{
		renderBlocks.setRenderBounds(0.25D, 0.0D, 0.0D, 0.75D, 1.0D, 0.5D);
		super.renderInventoryBlock(block, metadata, modelID, renderBlocks);
	}
}
