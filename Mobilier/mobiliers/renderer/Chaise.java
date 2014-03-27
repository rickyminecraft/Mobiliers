package mobiliers.renderer;

import mobiliers.data.ChaiseD;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import carpentersblocks.renderer.BlockHandlerBase;
import carpentersblocks.tileentity.TEBase;
import carpentersblocks.util.BlockProperties;

@SideOnly(Side.CLIENT)
public class Chaise extends BlockHandlerBase
{
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderBlocks)
	{
		for (int box = 0; box < 8; ++box)
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
					renderBlocks.setRenderBounds(0.7D, 0.5D, 0.7D, 0.8D, 1.0D, 0.8D);
					break;
				case 3:
					renderBlocks.setRenderBounds(0.7D, 0.0D, 0.2D, 0.8D, 0.4D, 0.3D);
					break;
				case 4:
					renderBlocks.setRenderBounds(0.2D, 0.0D, 0.7D, 0.3D, 0.4D, 0.8D);
					break;
				case 5:
					renderBlocks.setRenderBounds(0.2D, 0.5D, 0.7D, 0.3D, 1.0D, 0.8D);
					break;
				case 6:
					renderBlocks.setRenderBounds(0.2D, 0.4D, 0.2D, 0.8D, 0.5D, 0.8D);
					break;
				case 7:
					renderBlocks.setRenderBounds(0.3D, 0.7D, 0.7D, 0.7D, 1.0D, 0.8D);
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

		int data = BlockProperties.getMetadata(TE);
		int type = ChaiseD.getType(data);
		switch (type)
		{
			case ChaiseD.TYPE_1:
				renderChaise(coverBlock, x, y, z);
				break;
			case ChaiseD.TYPE_2:
				renderChaise_2(coverBlock, x, y, z);
				break;
			case ChaiseD.TYPE_3:
				renderChaise_3(coverBlock, x, y, z);
				break;
			case ChaiseD.TYPE_4:
				renderChaise_4(coverBlock, x, y, z);
		}
	}
	
	private void renderChaise(ItemStack coverBlock, int x, int y, int z)
	{
		int data = BlockProperties.getMetadata(TE);
		int tmp = ChaiseD.getRotation(data);
		switch (tmp)
		{
			case ChaiseD.CHAISE_X_NEG:
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.2D, 0.3D, 0.4D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.7D, 0.8D, 0.4D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.5D, 0.7D, 0.8D, 1.0D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.2D, 0.8D, 0.4D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.5D, 0.2D, 0.8D, 1.0D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.7D, 0.3D, 0.4D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.7D, 0.3D, 0.8D, 1.0D, 0.7D);
				renderBlock(coverBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.2D, 0.4D, 0.2D, 0.3D, 0.5D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.4D, 0.2D, 0.8D, 0.5D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.2D, 0.7D, 0.5D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.7D, 0.7D, 0.5D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.3D, 0.7D, 0.5D, 0.7D);
				renderBlock(coverBlock, x, y, z);
				break;
			case ChaiseD.CHAISE_X_POS:
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.2D, 0.3D, 0.4D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.5D, 0.2D, 0.3D, 1.0D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.7D, 0.8D, 0.4D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.2D, 0.8D, 0.4D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.7D, 0.3D, 0.4D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.5D, 0.7D, 0.3D, 1.0D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.7D, 0.3D, 0.3D, 1.0D, 0.7D);
				renderBlock(coverBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.2D, 0.4D, 0.2D, 0.3D, 0.5D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.4D, 0.2D, 0.8D, 0.5D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.2D, 0.7D, 0.5D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.7D, 0.7D, 0.5D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.3D, 0.7D, 0.5D, 0.7D);
				renderBlock(coverBlock, x, y, z);
				break;
			case ChaiseD.CHAISE_Z_NEG:
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.2D, 0.3D, 0.4D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.7D, 0.8D, 0.4D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.5D, 0.7D, 0.8D, 1.0D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.2D, 0.8D, 0.4D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.7D, 0.3D, 0.4D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.5D, 0.7D, 0.3D, 1.0D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.7D, 0.7D, 0.7D, 1.0D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.2D, 0.4D, 0.2D, 0.3D, 0.5D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.4D, 0.2D, 0.8D, 0.5D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.2D, 0.7D, 0.5D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.7D, 0.7D, 0.5D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.3D, 0.7D, 0.5D, 0.7D);
				renderBlock(coverBlock, x, y, z);
				break;
			case ChaiseD.CHAISE_Z_POS:
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.2D, 0.3D, 0.4D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.5D, 0.2D, 0.3D, 1.0D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.7D, 0.8D, 0.4D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.2D, 0.8D, 0.4D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.5D, 0.2D, 0.8D, 1.0D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.7D, 0.3D, 0.4D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.7D, 0.2D, 0.7D, 1.0D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.2D, 0.4D, 0.2D, 0.3D, 0.5D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.4D, 0.2D, 0.8D, 0.5D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.2D, 0.7D, 0.5D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.7D, 0.7D, 0.5D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.3D, 0.7D, 0.5D, 0.7D);
				renderBlock(coverBlock, x, y, z);
		}
	}
	
	private void renderChaise_2(ItemStack coverBlock, int x, int y, int z)
	{
		int data = BlockProperties.getMetadata(TE);
		int tmp = ChaiseD.getRotation(data);
		switch (tmp)
		{
			case ChaiseD.CHAISE_X_NEG:
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.2D, 0.3D, 0.4D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.7D, 0.8D, 0.4D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.5D, 0.7D, 0.8D, 1.0D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.2D, 0.8D, 0.4D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.5D, 0.2D, 0.8D, 1.0D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.7D, 0.3D, 0.4D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.7D, 0.65D, 0.3D, 0.8D, 0.75D, 0.7D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.85D, 0.3D, 0.8D, 0.95D, 0.7D);
				renderBlock(coverBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.2D, 0.4D, 0.2D, 0.3D, 0.5D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.4D, 0.2D, 0.8D, 0.5D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.2D, 0.7D, 0.5D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.7D, 0.7D, 0.5D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.3D, 0.7D, 0.5D, 0.7D);
				renderBlock(coverBlock, x, y, z);
				break;
			case ChaiseD.CHAISE_X_POS:
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.2D, 0.3D, 0.4D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.5D, 0.2D, 0.3D, 1.0D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.7D, 0.8D, 0.4D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.2D, 0.8D, 0.4D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.7D, 0.3D, 0.4D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.5D, 0.7D, 0.3D, 1.0D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.2D, 0.65D, 0.3D, 0.3D, 0.75D, 0.7D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.85D, 0.3D, 0.3D, 0.95D, 0.7D);
				renderBlock(coverBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.2D, 0.4D, 0.2D, 0.3D, 0.5D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.4D, 0.2D, 0.8D, 0.5D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.2D, 0.7D, 0.5D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.7D, 0.7D, 0.5D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.3D, 0.7D, 0.5D, 0.7D);
				renderBlock(coverBlock, x, y, z);
				break;
			case ChaiseD.CHAISE_Z_NEG:
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.2D, 0.3D, 0.4D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.7D, 0.8D, 0.4D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.5D, 0.7D, 0.8D, 1.0D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.2D, 0.8D, 0.4D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.7D, 0.3D, 0.4D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.5D, 0.7D, 0.3D, 1.0D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.3D, 0.65D, 0.7D, 0.7D, 0.75D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.85D, 0.7D, 0.7D, 0.95D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.2D, 0.4D, 0.2D, 0.3D, 0.5D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.4D, 0.2D, 0.8D, 0.5D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.2D, 0.7D, 0.5D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.7D, 0.7D, 0.5D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.3D, 0.7D, 0.5D, 0.7D);
				renderBlock(coverBlock, x, y, z);
				break;
			case ChaiseD.CHAISE_Z_POS:
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.2D, 0.3D, 0.4D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.5D, 0.2D, 0.3D, 1.0D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.7D, 0.8D, 0.4D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.2D, 0.8D, 0.4D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.5D, 0.2D, 0.8D, 1.0D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.7D, 0.3D, 0.4D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.3D, 0.65D, 0.2D, 0.7D, 0.75D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.85D, 0.2D, 0.7D, 0.95D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.2D, 0.4D, 0.2D, 0.3D, 0.5D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.4D, 0.2D, 0.8D, 0.5D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.2D, 0.7D, 0.5D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.7D, 0.7D, 0.5D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.3D, 0.7D, 0.5D, 0.7D);
				renderBlock(coverBlock, x, y, z);
		}
	}
	
	private void renderChaise_3(ItemStack coverBlock, int x, int y, int z)
	{
		int data = BlockProperties.getMetadata(TE);
		int tmp = ChaiseD.getRotation(data);
		switch (tmp)
		{
			case ChaiseD.CHAISE_X_NEG:
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.2D, 0.3D, 0.4D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.65D, 0.8D, 0.4D, 0.75D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.75D, 0.5D, 0.65D, 0.8D, 0.95D, 0.75D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.25D, 0.8D, 0.4D, 0.35D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.75D, 0.5D, 0.25D, 0.8D, 0.95D, 0.35D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.7D, 0.3D, 0.4D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.7D, 0.2D, 0.75D, 1.0D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.2D, 0.4D, 0.2D, 0.3D, 0.5D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.4D, 0.2D, 0.8D, 0.5D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.2D, 0.7D, 0.5D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.7D, 0.7D, 0.5D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.3D, 0.7D, 0.5D, 0.7D);
				renderBlock(coverBlock, x, y, z);
				break;
			case ChaiseD.CHAISE_X_POS:
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.25D, 0.3D, 0.4D, 0.35D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.5D, 0.25D, 0.25D, 0.95D, 0.35D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.7D, 0.8D, 0.4D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.2D, 0.8D, 0.4D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.65D, 0.3D, 0.4D, 0.75D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.5D, 0.65D, 0.25D, 0.95D, 0.75D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.25D, 0.7D, 0.2D, 0.3D, 1.0D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.2D, 0.4D, 0.2D, 0.3D, 0.5D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.4D, 0.2D, 0.8D, 0.5D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.2D, 0.7D, 0.5D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.7D, 0.7D, 0.5D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.3D, 0.7D, 0.5D, 0.7D);
				renderBlock(coverBlock, x, y, z);
				break;
			case ChaiseD.CHAISE_Z_NEG:
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.2D, 0.3D, 0.4D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.65D, 0.0D, 0.7D, 0.75D, 0.4D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.65D, 0.5D, 0.75D, 0.75D, 0.95D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.2D, 0.8D, 0.4D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.25D, 0.0D, 0.7D, 0.35D, 0.4D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.25D, 0.5D, 0.75D, 0.35D, 0.95D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.7D, 0.7D, 0.8D, 1.0D, 0.75D);
				renderBlock(coverBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.2D, 0.4D, 0.2D, 0.3D, 0.5D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.4D, 0.2D, 0.8D, 0.5D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.2D, 0.7D, 0.5D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.7D, 0.7D, 0.5D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.3D, 0.7D, 0.5D, 0.7D);
				renderBlock(coverBlock, x, y, z);
				break;
			case ChaiseD.CHAISE_Z_POS:
				renderBlocks.setRenderBounds(0.25D, 0.0D, 0.2D, 0.35D, 0.4D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.25D, 0.5D, 0.2D, 0.35D, 0.95D, 0.25D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.7D, 0.8D, 0.4D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.65D, 0.0D, 0.2D, 0.75D, 0.4D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.65D, 0.5D, 0.2D, 0.75D, 0.95D, 0.25D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.7D, 0.3D, 0.4D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.7D, 0.25D, 0.8D, 1.0D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.2D, 0.4D, 0.2D, 0.3D, 0.5D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.4D, 0.2D, 0.8D, 0.5D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.2D, 0.7D, 0.5D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.7D, 0.7D, 0.5D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.3D, 0.7D, 0.5D, 0.7D);
				renderBlock(coverBlock, x, y, z);
		}
	}
	
	private void renderChaise_4(ItemStack coverBlock, int x, int y, int z)
	{
		int data = BlockProperties.getMetadata(TE);
		int tmp = ChaiseD.getRotation(data);
		switch (tmp)
		{
			case ChaiseD.CHAISE_X_NEG:
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.2D, 0.3D, 0.4D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.7D, 0.8D, 0.4D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.5D, 0.7D, 0.8D, 1.0D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.2D, 0.8D, 0.4D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.5D, 0.2D, 0.8D, 1.0D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.7D, 0.3D, 0.4D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.72D, 0.7D, 0.3D, 0.78D, 0.75D, 0.7D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.72D, 0.8D, 0.3D, 0.78D, 0.85D, 0.7D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.72D, 0.9D, 0.3D, 0.78D, 0.95D, 0.7D);
				renderBlock(coverBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.2D, 0.4D, 0.2D, 0.3D, 0.5D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.4D, 0.2D, 0.8D, 0.5D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.2D, 0.7D, 0.5D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.7D, 0.7D, 0.5D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.3D, 0.7D, 0.5D, 0.7D);
				renderBlock(coverBlock, x, y, z);
				break;
			case ChaiseD.CHAISE_X_POS:
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.2D, 0.3D, 0.4D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.5D, 0.2D, 0.3D, 1.0D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.7D, 0.8D, 0.4D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.2D, 0.8D, 0.4D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.7D, 0.3D, 0.4D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.5D, 0.7D, 0.3D, 1.0D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.22D, 0.7D, 0.3D, 0.28D, 0.75D, 0.7D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.22D, 0.8D, 0.3D, 0.28D, 0.85D, 0.7D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.22D, 0.9D, 0.3D, 0.28D, 0.95D, 0.7D);
				renderBlock(coverBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.2D, 0.4D, 0.2D, 0.3D, 0.5D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.4D, 0.2D, 0.8D, 0.5D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.2D, 0.7D, 0.5D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.7D, 0.7D, 0.5D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.3D, 0.7D, 0.5D, 0.7D);
				renderBlock(coverBlock, x, y, z);
				break;
			case ChaiseD.CHAISE_Z_NEG:
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.2D, 0.3D, 0.4D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.7D, 0.8D, 0.4D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.5D, 0.7D, 0.8D, 1.0D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.2D, 0.8D, 0.4D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.7D, 0.3D, 0.4D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.5D, 0.7D, 0.3D, 1.0D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.3D, 0.7D, 0.72D, 0.7D, 0.75D, 0.78D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.8D, 0.72D, 0.7D, 0.85D, 0.78D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.9D, 0.72D, 0.7D, 0.95D, 0.78D);
				renderBlock(coverBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.2D, 0.4D, 0.2D, 0.3D, 0.5D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.4D, 0.2D, 0.8D, 0.5D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.2D, 0.7D, 0.5D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.7D, 0.7D, 0.5D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.3D, 0.7D, 0.5D, 0.7D);
				renderBlock(coverBlock, x, y, z);
				break;
			case ChaiseD.CHAISE_Z_POS:
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.2D, 0.3D, 0.4D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.5D, 0.2D, 0.3D, 1.0D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.7D, 0.8D, 0.4D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.2D, 0.8D, 0.4D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.5D, 0.2D, 0.8D, 1.0D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.7D, 0.3D, 0.4D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.3D, 0.7D, 0.22D, 0.7D, 0.75D, 0.28D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.8D, 0.22D, 0.7D, 0.85D, 0.28D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.9D, 0.22D, 0.7D, 0.95D, 0.28D);
				renderBlock(coverBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.2D, 0.4D, 0.2D, 0.3D, 0.5D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.4D, 0.2D, 0.8D, 0.5D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.2D, 0.7D, 0.5D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.7D, 0.7D, 0.5D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.4D, 0.3D, 0.7D, 0.5D, 0.7D);
				renderBlock(coverBlock, x, y, z);
		}
	}
}
