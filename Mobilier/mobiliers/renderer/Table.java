package mobiliers.renderer;

import mobiliers.data.TableD;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import carpentersblocks.renderer.BlockHandlerBase;
import carpentersblocks.util.BlockProperties;

@SideOnly(Side.CLIENT)
public class Table extends BlockHandlerBase
{
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderBlocks)
	{
		for (int box = 0; box < 4; ++box)
		{
			switch (box)
			{
				case 0:
					renderBlocks.setRenderBounds(1.0D, 0.9D, 1.0D, 0.0D, 1.0D, 0.0D);
					break;
				case 1:
					renderBlocks.setRenderBounds(0.55D, 0.1D, 0.55D, 0.45D, 0.9D, 0.45D);
					break;
				case 2:
					renderBlocks.setRenderBounds(0.65D, 0.05D, 0.65D, 0.35D, 0.1D, 0.35D);
					break;
				case 3:
					renderBlocks.setRenderBounds(0.7D, 0.0D, 0.7D, 0.3D, 0.05D, 0.3D);
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
		int type = TableD.getType(data);
		switch (type)
		{
			case TableD.TYPE_NORMAL:
				renderTable(coverBlock, x, y, z);
				break;
			case TableD.TYPE_GLASS:
				renderTableGlass(coverBlock, x, y, z);
				break;
			case TableD.TYPE_BAS:
				renderTableBasse(coverBlock, x, y, z);
				break;
			case TableD.TYPE_BAS_GLASS:
				renderTableBasseGlass(coverBlock, x, y, z);
		}
	}
	
	private void renderTable(ItemStack coverBlock, int x, int y, int z)
	{

		renderBlocks.setRenderBounds(0.7D, 0.85D, 0.7D, 0.3D, 0.9D, 0.3D);
		renderBlock(coverBlock, x, y, z);
		renderBlocks.setRenderBounds(0.65D, 0.8D, 0.65D, 0.35D, 0.85D, 0.35D);
		renderBlock(coverBlock, x, y, z);
		renderBlocks.setRenderBounds(0.55D, 0.1D, 0.55D, 0.45D, 0.8D, 0.45D);
		renderBlock(coverBlock, x, y, z);
		renderBlocks.setRenderBounds(0.65D, 0.05D, 0.65D, 0.35D, 0.1D, 0.35D);
		renderBlock(coverBlock, x, y, z);
		renderBlocks.setRenderBounds(0.7D, 0.0D, 0.7D, 0.3D, 0.05D, 0.3D);
		renderBlock(coverBlock, x, y, z);
		renderBlocks.setRenderBounds(0.0D, 0.9D, 0.0D, 1.0D, 1.0D, 1.0D);
		renderBlock(coverBlock, x, y, z);
	}
	
	private void renderTableGlass(ItemStack coverBlock, int x, int y, int z)
	{
		Block block = Blocks.glass;
		IIcon icon;
		icon = block.getIcon(0, 0);
		
		renderBlocks.setRenderBounds(0.7D, 0.85D, 0.7D, 0.3D, 0.9D, 0.3D);
		renderBlock(coverBlock, x, y, z);
		renderBlocks.setRenderBounds(0.65D, 0.8D, 0.65D, 0.35D, 0.85D, 0.35D);
		renderBlock(coverBlock, x, y, z);
		renderBlocks.setRenderBounds(0.55D, 0.1D, 0.55D, 0.45D, 0.8D, 0.45D);
		renderBlock(coverBlock, x, y, z);
		renderBlocks.setRenderBounds(0.65D, 0.05D, 0.65D, 0.35D, 0.1D, 0.35D);
		renderBlock(coverBlock, x, y, z);
		renderBlocks.setRenderBounds(0.7D, 0.0D, 0.7D, 0.3D, 0.05D, 0.3D);
		renderBlock(coverBlock, x, y, z);
		
		renderBlocks.setRenderBounds(0.0D, 0.9D, 0.0D, 1.0D, 1.0D, 0.1D);
		renderBlock(coverBlock, x, y, z);
		renderBlocks.setRenderBounds(0.0D, 0.9D, 0.9D, 1.0D, 1.0D, 1.0D);
		renderBlock(coverBlock, x, y, z);
		renderBlocks.setRenderBounds(0.0D, 0.9D, 0.1D, 0.1D, 1.0D, 0.9D);
		renderBlock(coverBlock, x, y, z);
		renderBlocks.setRenderBounds(0.9D, 0.9D, 0.1D, 1.0D, 1.0D, 0.9D);
		renderBlock(coverBlock, x, y, z);
		renderBlocks.setRenderBounds(0.45D, 0.9D, 0.45D, 0.55D, 1.0D, 0.55D);
		renderBlock(coverBlock, x, y, z);
		//ici la vitre
		setIconOverride(6, icon);
		renderBlocks.setRenderBounds(0.09D, 0.91D, 0.09D, 0.91D, 0.95D, 0.91D);
		renderBlock(coverBlock, x, y, z);
		clearIconOverride(6);
	}
	
	private void renderTableBasse(ItemStack coverBlock, int x, int y, int z)
	{
		int data = BlockProperties.getMetadata(TE);
		int Rotation = TableD.getRotation(data);

		renderBlocks.setRenderBounds(0.6D, 0.45D, 0.6D, 0.4D, 0.5D, 0.4D);
		renderBlock(coverBlock, x, y, z);
		renderBlocks.setRenderBounds(0.55D, 0.1D, 0.55D, 0.45D, 0.45D, 0.45D);
		renderBlock(coverBlock, x, y, z);
		renderBlocks.setRenderBounds(0.6D, 0.05D, 0.6D, 0.4D, 0.1D, 0.4D);
		renderBlock(coverBlock, x, y, z);
		renderBlocks.setRenderBounds(0.7D, 0.0D, 0.7D, 0.3D, 0.05D, 0.3D);
		renderBlock(coverBlock, x, y, z);
		switch (Rotation)
		{
			case TableD.ROTATE_0:
				renderBlocks.setRenderBounds(0.2D, 0.5D, 0.0D, 0.8D, 0.6D, 0.1D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.5D, 0.9D, 0.8D, 0.6D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.5D, 0.1D, 0.3D, 0.6D, 0.9D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.5D, 0.1D, 0.8D, 0.6D, 0.9D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.50D, 0.1D, 0.7D, 0.55D, 0.9D);
				renderBlock(coverBlock, x, y, z);
				break;
			case TableD.ROTATE_1:
				renderBlocks.setRenderBounds(0.0D, 0.5D, 0.2D, 1.0D, 0.6D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.5D, 0.7D, 1.0D, 0.6D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.5D, 0.3D, 0.1D, 0.6D, 0.7D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.9D, 0.5D, 0.3D, 1.0D, 0.6D, 0.7D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.1D, 0.50D, 0.3D, 0.9D, 0.55D, 0.7D);
				renderBlock(coverBlock, x, y, z);
		}
	}
	
	private void renderTableBasseGlass(ItemStack coverBlock, int x, int y, int z)
	{
		int data = BlockProperties.getMetadata(TE);
		Block block = Blocks.glass;
		IIcon icon;
		icon = block.getIcon(0, 0);
		int Rotation = TableD.getRotation(data);

		renderBlocks.setRenderBounds(0.6D, 0.45D, 0.6D, 0.4D, 0.5D, 0.4D);
		renderBlock(coverBlock, x, y, z);
		renderBlocks.setRenderBounds(0.55D, 0.1D, 0.55D, 0.45D, 0.45D, 0.45D);
		renderBlock(coverBlock, x, y, z);
		renderBlocks.setRenderBounds(0.6D, 0.05D, 0.6D, 0.4D, 0.1D, 0.4D);
		renderBlock(coverBlock, x, y, z);
		renderBlocks.setRenderBounds(0.7D, 0.0D, 0.7D, 0.3D, 0.05D, 0.3D);
		renderBlock(coverBlock, x, y, z);
		switch (Rotation)
		{
			case TableD.ROTATE_0:
				renderBlocks.setRenderBounds(0.2D, 0.5D, 0.0D, 0.8D, 0.6D, 0.1D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.5D, 0.9D, 0.8D, 0.6D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.5D, 0.1D, 0.3D, 0.6D, 0.9D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.5D, 0.1D, 0.8D, 0.6D, 0.9D);
				renderBlock(coverBlock, x, y, z);
				break;
			case TableD.ROTATE_1:
				renderBlocks.setRenderBounds(0.0D, 0.5D, 0.2D, 1.0D, 0.6D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.5D, 0.7D, 1.0D, 0.6D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.5D, 0.3D, 0.1D, 0.6D, 0.7D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.9D, 0.5D, 0.3D, 1.0D, 0.6D, 0.7D);
				renderBlock(coverBlock, x, y, z);
		}
		renderBlocks.setRenderBounds(0.45D, 0.5D, 0.45D, 0.55D, 0.6D, 0.55D);
		renderBlock(coverBlock, x, y, z);
		//ici la vitre
		setIconOverride(6, icon);
		switch (Rotation)
		{
			case TableD.ROTATE_0:
				renderBlocks.setRenderBounds(0.29D, 0.51D, 0.09D, 0.71D, 0.55D, 0.91D);
				break;
			case TableD.ROTATE_1:
				renderBlocks.setRenderBounds(0.09D, 0.51D, 0.29D, 0.91D, 0.55D, 0.71D);
		}
		renderBlock(coverBlock, x, y, z);
		clearIconOverride(6);
	}
}
