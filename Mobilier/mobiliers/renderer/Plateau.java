package mobiliers.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import carpentersblocks.renderer.BlockHandlerBase;

@SideOnly(Side.CLIENT)
public class Plateau extends BlockHandlerBase
{
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderBlocks)
	{
		renderBlocks.setRenderBounds(0.0D, 0.9D, 0.0D, 1.0D, 1.0D, 1.0D);
		super.renderInventoryBlock(block, metadata, modelID, renderBlocks);
	}
}
