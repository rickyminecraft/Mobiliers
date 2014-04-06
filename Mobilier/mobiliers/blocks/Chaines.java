package mobiliers.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mobiliers.mobilier;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import carpentersblocks.block.BlockCoverable;
import carpentersblocks.tileentity.TEBase;

public class Chaines extends BlockCoverable
{

	public Chaines(Material material)
	{
		super(material);
		float f = 0.2F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
	}
	
	@Override
	/**
	 * Toggles post.
	 */
	protected boolean onHammerLeftClick(TEBase TE, EntityPlayer entityPlayer)
	{
		/*
		 * pour l'instant rien d'utile mais peut etre plus tard
		 */
//		int data = BlockProperties.getMetadata(TE);
//		int Rotation = CreuxD.getRotation(data);
//		if (++Rotation > CreuxD.CREUX_Z_POS)
//		{
//			Rotation = CreuxD.CREUX_X_NEG;
//		}
//		BancD.setRotation(TE, Rotation);
		return false;
	}

	@Override
	/**
	 * Alters barrier type or sub-type.
	 */
	protected boolean onHammerRightClick(TEBase TE, EntityPlayer entityPlayer)
	{
//			int data = BlockProperties.getMetadata(TE);
//			int type = BancD.getType(data);
//			if (++type > BancD.BANC)
//			{
//				type = BancD.BORD;
//			}
//			BancD.setType(TE, type);
//			
		return false;
	}
	
    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    @Override
    @SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2)
    {
        if (par1 == 1)
        {
        	return mobilier.Chaines1;
        }
        return mobilier.Chaines2;
    }
	
    /**
     * The type of render function that is called for this block
     */
    @Override
	public int getRenderType()
    {
    	return mobilier.ChainesID;
    }
	
    /**
     * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
     */
    @Override
	public boolean canBlockStay(World par1World, int par2, int par3, int par4)
    {
        return true;
    }
    
    @Override
	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
    	mobilier.Chaines1 = par1IconRegister.registerIcon("mobiliers:chaines");
    	mobilier.Chaines2 = par1IconRegister.registerIcon("mobiliers:chaines2");
    }
}
