package mobiliers.renderer;

import mobiliers.data.FenetreD;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import carpentersblocks.renderer.BlockHandlerBase;
import carpentersblocks.util.BlockProperties;

@SideOnly(Side.CLIENT)
public class Fenetre extends BlockHandlerBase
{
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderBlocks)
	{
		for (int box = 0; box < 7; ++box)
		{
			switch (box)
			{
				case 0:
					renderBlocks.setRenderBounds(0.0D, 0.0D, 0.0D, 0.225D, 0.6D, 1.0D);
					break;
				case 1:
					renderBlocks.setRenderBounds(0.775D, 0.0D, 0.0D, 1.0D, 0.6D, 1.0D);
					break;
				case 2:
					renderBlocks.setRenderBounds(0.0D, 0.6D, 0.0D, 0.3D, 0.7D, 1.0D);
					break;
				case 3:
					renderBlocks.setRenderBounds(0.7D, 0.6D, 0.0D, 1.0D, 0.7D, 1.0D);
					break;
				case 4:
					renderBlocks.setRenderBounds(0.0D, 0.7D, 0.0D, 0.4D, 1.0D, 1.0D);
					break;
				case 5:
					renderBlocks.setRenderBounds(0.6D, 0.7D, 0.0D, 1.0D, 1.0D, 1.0D);
					break;
				case 6:
					renderBlocks.setRenderBounds(0.4D, 0.75D, 0.0D, 0.6D, 1.0D, 1.0D);
			}
			super.renderInventoryBlock(block, metadata, modelID, renderBlocks);
		}
	}

	@Override
	/**
	 * Renders barrier
	 */
	public void renderCarpentersBlock( int x, int y, int z)
	{
		ItemStack coverBlock = BlockProperties.hasCover(TE, 6) == true ? BlockProperties.getCover(TE, coverRendering) : BlockProperties.getCover(TE, 6);

		int data = BlockProperties.getMetadata(TE);
		int type = FenetreD.getType(data);
		switch (type)
		{
			case FenetreD.TYPE_1:
				renderFenetres(coverBlock, x, y, z);
				break;
			case FenetreD.TYPE_2:
				renderFenetres2(coverBlock, x, y, z);
				break;
			case FenetreD.TYPE_3:
				renderFenetres3(coverBlock, x, y, z);
				break;
			case FenetreD.TYPE_4:
				renderFenetres4(coverBlock, x, y, z);
		}
	}

	private void renderFenetres(ItemStack coverBlock, int x, int y, int z)
	{
		int data = BlockProperties.getMetadata(TE);
		data = FenetreD.getRotation(data);
		switch (data)
		{
			case FenetreD.FENETRE_X:
				renderBlocks.setRenderBounds(0.0D, 0.0D, 0.0D, 0.225D, 0.05D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.05D, 0.0D, 0.25D, 0.15D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.15D, 0.0D, 0.225D, 0.2D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.2D, 0.0D, 0.25D, 0.3D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.3D, 0.0D, 0.225D, 0.35D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.35D, 0.0D, 0.25D, 0.45D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.45D, 0.0D, 0.225D, 0.5D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.5D, 0.0D, 0.25D, 0.6D, 1.0D);
				renderBlock(coverBlock, x, y, z);

				renderBlocks.setRenderBounds(0.775D, 0.0D, 0.0D, 1.0D, 0.05D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.75D, 0.05D, 0.0D, 1.0D, 0.15D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.775D, 0.15D, 0.0D, 1.0D, 0.2D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.75D, 0.2D, 0.0D, 1.0D, 0.3D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.775D, 0.3D, 0.0D, 1.0D, 0.35D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.75D, 0.35D, 0.0D, 1.0D, 0.45D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.775D, 0.45D, 0.0D, 1.0D, 0.5D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.75D, 0.5D, 0.0D, 1.0D, 0.6D, 1.0D);
				renderBlock(coverBlock, x, y, z);

				renderBlocks.setRenderBounds(0.35D, 0.725D, 0.0D, 0.4D, 1.0D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.6D, 0.725D, 0.0D, 0.65D, 1.0D, 1.0D);
				renderBlock(coverBlock, x, y, z);

				renderBlocks.setRenderBounds(0.0D, 0.7D, 0.0D, 0.35D, 1.0D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.65D, 0.7D, 0.0D, 1.0D, 1.0D, 1.0D);
				renderBlock(coverBlock, x, y, z);

				renderBlocks.setRenderBounds(0.0D, 0.65D, 0.0D, 0.3D, 0.7D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.65D, 0.0D, 1.0D, 0.7D, 1.0D);
				renderBlock(coverBlock, x, y, z);

				renderBlocks.setRenderBounds(0.0D, 0.6D, 0.0D, 0.275D, 0.65D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.725D, 0.6D, 0.0D, 1.0D, 0.65D, 1.0D);
				renderBlock(coverBlock, x, y, z);

				renderBlocks.setRenderBounds(0.4D, 0.75D, 0.0D, 0.6D, 1.0D, 1.0D);
				renderBlock(coverBlock, x, y, z);

				break;
			case FenetreD.FENETRE_Z:
				renderBlocks.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.05D, 0.225D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.05D, 0.0D, 1.0D, 0.15D, 0.25D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.15D, 0.0D, 1.0D, 0.2D, 0.225D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.2D, 0.0D, 1.0D, 0.3D, 0.25D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.3D, 0.0D, 1.0D, 0.35D, 0.225D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.35D, 0.0D, 1.0D, 0.45D, 0.25D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.45D, 0.0D, 1.0D, 0.5D, 0.225D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.5D, 0.0D, 1.0D, 0.6D, 0.25D);
				renderBlock(coverBlock, x, y, z);

				renderBlocks.setRenderBounds(0.0D, 0.0D, 0.775D, 1.0D, 0.05D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.05D, 0.75D, 1.0D, 0.15D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.15D, 0.775D, 1.0D, 0.2D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.2D, 0.75D, 1.0D, 0.3D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.3D, 0.775D, 1.0D, 0.35D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.35D, 0.75D, 1.0D, 0.45D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.45D, 0.775D, 1.0D, 0.5D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.5D, 0.75D, 1.0D, 0.6D, 1.0D);
				renderBlock(coverBlock, x, y, z);

				renderBlocks.setRenderBounds(0.0D, 0.725D, 0.35D, 1.0D, 1.0D, 0.4D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.725D, 0.6D, 1.0D, 1.0D, 0.65D);
				renderBlock(coverBlock, x, y, z);

				renderBlocks.setRenderBounds(0.0D, 0.7D, 0.0D, 1.0D, 1.0D, 0.35D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.7D, 0.65D, 1.0D, 1.0D, 1.0D);
				renderBlock(coverBlock, x, y, z);

				renderBlocks.setRenderBounds(0.0D, 0.65D, 0.0D, 1.0D, 0.7D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.65D, 0.7D, 1.0D, 0.7D, 1.0D);
				renderBlock(coverBlock, x, y, z);

				renderBlocks.setRenderBounds(0.0D, 0.6D, 0.0D, 1.0D, 0.65D, 0.275D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.6D, 0.725D, 1.0D, 0.65D, 1.0D);
				renderBlock(coverBlock, x, y, z);

				renderBlocks.setRenderBounds(0.0D, 0.75D, 0.4D, 1.0D, 1.0D, 0.6D);
				renderBlock(coverBlock, x, y, z);
		}
	}
	
	private void renderFenetres2(ItemStack coverBlock, int x, int y, int z)
	{
		int data = BlockProperties.getMetadata(TE);
		data = FenetreD.getRotation(data);
		switch (data)
		{
			case FenetreD.FENETRE_X:
				renderBlocks.setRenderBounds(0.0D, 0.0D, 0.0D, 0.25D, 0.1D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.1D, 0.0D, 0.225D, 0.15D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.15D, 0.0D, 0.25D, 0.25D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.25D, 0.0D, 0.225D, 0.3D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.3D, 0.0D, 0.25D, 0.4D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.4D, 0.0D, 0.225D, 0.45D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.45D, 0.0D, 0.25D, 0.55D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.55D, 0.0D, 0.225D, 0.6D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.6D, 0.0D, 0.25D, 0.7D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.7D, 0.0D, 0.225D, 0.75D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.75D, 0.0D, 0.25D, 0.85D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.85D, 0.0D, 0.225D, 0.9D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.9D, 0.0D, 0.25D, 1.0D, 1.0D);
				renderBlock(coverBlock, x, y, z);

				renderBlocks.setRenderBounds(0.75D, 0.0D, 0.0D, 1.0D, 0.1D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.775D, 0.1D, 0.0D, 1.0D, 0.15D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.75D, 0.15D, 0.0D, 1.0D, 0.25D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.775D, 0.25D, 0.0D, 1.0D, 0.3D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.75D, 0.3D, 0.0D, 1.0D, 0.4D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.775D, 0.4D, 0.0D, 1.0D, 0.45D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.75D, 0.45D, 0.0D, 1.0D, 0.55D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.775D, 0.55D, 0.0D, 1.0D, 0.6D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.75D, 0.6D, 0.0D, 1.0D, 0.7D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.775D, 0.7D, 0.0D, 1.0D, 0.75D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.75D, 0.75D, 0.0D, 1.0D, 0.85D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.775D, 0.85D, 0.0D, 1.0D, 0.9D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.75D, 0.9D, 0.0D, 1.0D, 1.0D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				break;
			case FenetreD.FENETRE_Z:
				renderBlocks.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.1D, 0.25D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.1D, 0.0D, 1.0D, 0.15D, 0.225D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.15D, 0.0D, 1.0D, 0.25D, 0.25D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.25D, 0.0D, 1.0D, 0.3D, 0.225D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.3D, 0.0D, 1.0D, 0.4D, 0.25D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.4D, 0.0D, 1.0D, 0.45D, 0.225D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.45D, 0.0D, 1.0D, 0.55D, 0.25D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.55D, 0.0D, 1.0D, 0.6D, 0.225D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.6D, 0.0D, 1.0D, 0.7D, 0.25D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.7D, 0.0D, 1.0D, 0.75D, 0.225D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.75D, 0.0D, 1.0D, 0.85D, 0.25D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.85D, 0.0D, 1.0D, 0.9D, 0.225D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.9D, 0.0D, 1.0D, 1.0D, 0.25D);
				renderBlock(coverBlock, x, y, z);

				renderBlocks.setRenderBounds(0.0D, 0.0D, 0.75D, 1.0D, 0.1D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.1D, 0.775D, 1.0D, 0.15D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.15D, 0.75D, 1.0D, 0.25D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.25D, 0.775D, 1.0D, 0.3D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.3D, 0.75D, 1.0D, 0.4D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.4D, 0.775D, 1.0D, 0.45D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.45D, 0.75D, 1.0D, 0.55D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.55D, 0.775D, 1.0D, 0.6D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.6D, 0.75D, 1.0D, 0.7D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.7D, 0.775D, 1.0D, 0.75D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.75D, 0.75D, 1.0D, 0.85D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.85D, 0.775D, 1.0D, 0.9D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.9D, 0.75D, 1.0D, 1.0D, 1.0D);
				renderBlock(coverBlock, x, y, z);
		}
	}
	
	private void renderFenetres3(ItemStack coverBlock, int x, int y, int z)
	{
		int data = BlockProperties.getMetadata(TE);
		data = FenetreD.getRotation(data);
		switch (data)
		{
			case FenetreD.FENETRE_X:
				renderBlocks.setRenderBounds(0.0D, 0.0D, 0.0D, 0.1D, 1.0D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.1D, 0.0D, 0.1D, 0.2D, 1.0D, 0.9D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.2D, 0.3D, 1.0D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.0D, 0.3D, 0.35D, 1.0D, 0.7D);
				renderBlock(coverBlock, x, y, z);

				renderBlocks.setRenderBounds(0.65D, 0.0D, 0.3D, 0.7D, 1.0D, 0.7D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.7D, 0.0D, 0.2D, 0.8D, 1.0D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.8D, 0.0D, 0.1D, 0.9D, 1.0D, 0.9D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.9D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				break;
			case FenetreD.FENETRE_Z:
				renderBlocks.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.1D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.1D, 0.0D, 0.1D, 0.9D, 1.0D, 0.2D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.2D, 0.8D, 1.0D, 0.3D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.3D, 0.0D, 0.3D, 0.7D, 1.0D, 0.35D);
				renderBlock(coverBlock, x, y, z);

				renderBlocks.setRenderBounds(0.3D, 0.0D, 0.65D, 0.7D, 1.0D, 0.7D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.2D, 0.0D, 0.7D, 0.8D, 1.0D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.1D, 0.0D, 0.8D, 0.9D, 1.0D, 0.9D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.0D, 0.9D, 1.0D, 1.0D, 1.0D);
				renderBlock(coverBlock, x, y, z);
		}
	}
	
	private void renderFenetres4(ItemStack coverBlock, int x, int y, int z)
	{
		int data = BlockProperties.getMetadata(TE);
		data = FenetreD.getRotation(data);
		Block bl = (Block) Block.blockRegistry.getObject("hopper");
		IIcon icon;
		icon = bl.getBlockTextureFromSide(DOWN);//Block.hopperBlock.getHopperIcon("hopper_outside");
		switch (data)
		{
			case FenetreD.FENETRE_X:
				renderBlocks.setRenderBounds(0.0D, 0.9D, 0.0D, 1.0D, 1.0D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.1D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.1D, 0.0D, 0.1D, 0.9D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.9D, 0.1D, 0.0D, 1.0D, 0.9D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				
				//ici les barres
				setIconOverride(6, icon);
				
				renderBlocks.setRenderBounds(0.2D, 0.1D, 0.05D, 0.25D, 0.9D, 0.1D);
				renderBlock(coverBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.35D, 0.1D, 0.05D, 0.4D, 0.9D, 0.1D);
				renderBlock(coverBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.475D, 0.1D, 0.05D, 0.525D, 0.9D, 0.1D);
				renderBlock(coverBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.6D, 0.1D, 0.05D, 0.65D, 0.9D, 0.1D);
				renderBlock(coverBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.75D, 0.1D, 0.05D, 0.8D, 0.9D, 0.1D);
				renderBlock(coverBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.2D, 0.1D, 0.9D, 0.25D, 0.9D, 0.95D);
				renderBlock(coverBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.35D, 0.1D, 0.9D, 0.4D, 0.9D, 0.95D);
				renderBlock(coverBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.475D, 0.1D, 0.9D, 0.525D, 0.9D, 0.95D);
				renderBlock(coverBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.6D, 0.1D, 0.9D, 0.65D, 0.9D, 0.95D);
				renderBlock(coverBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.75D, 0.1D, 0.9D, 0.8D, 0.9D, 0.95D);
				renderBlock(coverBlock, x, y, z);
				
				clearIconOverride(6);
				break;
			case FenetreD.FENETRE_Z:
				renderBlocks.setRenderBounds(0.0D, 0.9D, 0.0D, 1.0D, 1.0D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.1D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.1D, 0.0D, 1.0D, 0.9D, 0.1D);
				renderBlock(coverBlock, x, y, z);
				renderBlocks.setRenderBounds(0.0D, 0.1D, 0.9D, 1.0D, 0.9D, 1.0D);
				renderBlock(coverBlock, x, y, z);
				
				//ici les barres
				setIconOverride(6, icon);
				
				renderBlocks.setRenderBounds(0.05D, 0.1D, 0.2D, 0.1D, 0.9D, 0.25D);
				renderBlock(coverBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.05D, 0.1D, 0.35D, 0.1D, 0.9D, 0.4D);
				renderBlock(coverBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.05D, 0.1D, 0.475D, 0.1D, 0.9D, 0.525D);
				renderBlock(coverBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.05D, 0.1D, 0.6D, 0.1D, 0.9D, 0.65D);
				renderBlock(coverBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.05D, 0.1D, 0.75D, 0.1D, 0.9D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.9D, 0.1D, 0.2D, 0.95D, 0.9D, 0.25D);
				renderBlock(coverBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.9D, 0.1D, 0.35D, 0.95D, 0.9D, 0.4D);
				renderBlock(coverBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.9D, 0.1D, 0.475D, 0.95D, 0.9D, 0.525D);
				renderBlock(coverBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.9D, 0.1D, 0.6D, 0.95D, 0.9D, 0.65D);
				renderBlock(coverBlock, x, y, z);
				
				renderBlocks.setRenderBounds(0.9D, 0.1D, 0.75D, 0.95D, 0.9D, 0.8D);
				renderBlock(coverBlock, x, y, z);
				
				clearIconOverride(6);
		}
	}
}
