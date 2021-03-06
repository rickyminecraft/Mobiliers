package mobiliers.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import carpentersblocks.renderer.BlockHandlerBase;
import carpentersblocks.util.BlockProperties;

@SideOnly(Side.CLIENT)
public class Poteau_indicateur extends BlockHandlerBase
{
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderBlocks) 
	{
		Tessellator tessellator = Tessellator.instance;
        for(int box = 0; box < 4; box++)
        {
        	switch (box)
        	{
        	case 0:
        		renderBlocks.setRenderBounds(0.4D, 0.0D, 0.4D, 0.6D, 0.7D, 0.6D);
        		break;
        	case 1:
        		renderBlocks.setRenderBounds(0.4D, 0.4D, 0.0D, 0.6D, 0.6D, 1.0D);
        		break;
        	case 2:
        		renderBlocks.setRenderBounds(0.0D, 0.4D, 0.4D, 0.4D, 0.6D, 0.6D);
        		break;
        	case 3:
        		renderBlocks.setRenderBounds(0.6D, 0.4D, 0.4D, 1.0D, 0.6D, 0.6D);
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
		renderPoteau(coverBlock, x, y, z);
//		int data = BlockProperties.getMetadata(TE);
//		int type = ChaiseD.getType(data);
//		switch (type)
//		{
//			case ChaiseD.TYPE_1:
//				renderChaise(coverBlock, x, y, z);
//				break;
//			case ChaiseD.TYPE_2:
//				renderChaise_2(coverBlock, x, y, z);
//				break;
//			case ChaiseD.TYPE_3:
//				renderChaise_3(coverBlock, x, y, z);
//				break;
//			case ChaiseD.TYPE_4:
//				renderChaise_4(coverBlock, x, y, z);
//		}
	}
	
	private void renderPoteau(ItemStack coverBlock, int x, int y, int z)
	{
		World world = TE.getWorldObj();
		Block block = Blocks.wall_sign;
     	if (world.getBlock(x, y+1, z) != Blocks.air)
    	{
    		renderBlocks.setRenderBounds(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D);
    		renderBlock(coverBlock, x, y, z);
    	}
    	else
    	{
    		renderBlocks.setRenderBounds(0.375D, 0.0D, 0.375D, 0.625D, 0.7D, 0.625D);
    		renderBlock(coverBlock, x, y, z);
    	}
    	if (world.getBlock(x, y, z-1) == block)
    	{
    		//barre 1 haut
    		renderBlocks.setRenderBounds(0.4D, 0.4D, 0.0D, 0.6D, 0.6D, 0.4D);
    		renderBlock(coverBlock, x, y, z);
    	}

    	if (world.getBlock(x-1, y, z) == block)
    	{
    		//barre 2 haut
    		renderBlocks.setRenderBounds(0.0D, 0.4D, 0.4D, 0.4D, 0.6D, 0.6D);
    		renderBlock(coverBlock, x, y, z);
    	}

    	if (world.getBlock(x+1, y, z) == block)
    	{
    		//barre 3 haut
    		renderBlocks.setRenderBounds(0.6D, 0.4D, 0.4D, 1.0D, 0.6D, 0.6D);
    		renderBlock(coverBlock, x, y, z);
    	}

    	if (world.getBlock(x, y, z+1) == block)
    	{
    		//barre 4 haut
    		renderBlocks.setRenderBounds(0.4D, 0.4D, 0.6D, 0.6D, 0.6D, 1.0D);
    		renderBlock(coverBlock, x, y, z);
    	}
	}
}
