package mobiliers.blocks;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import carpentersblocks.block.BlockCoverable;
import carpentersblocks.tileentity.TEBase;
import carpentersblocks.util.BlockProperties;
import mobiliers.mobilier;
import mobiliers.EntityFX.BrumeFX;
import mobiliers.data.EffetsD;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class Effets extends BlockCoverable
{
	private static int Compteur;
	private static int Tickrate;
	
	public Effets(Material material)
	{
		super(material);
		Compteur = 0;
		Tickrate = 15;
		setTickRandomly(true);
	}

	@Override
    /**
     * Toggles post.
     */
    protected boolean onHammerLeftClick(TEBase TE, EntityPlayer entityPlayer)
    {
		int data = BlockProperties.getMetadata(TE);
		int Effet = EffetsD.getEffet(data);
		if (++Effet > EffetsD.SMOKE)
		{
			Effet = EffetsD.FOG;
		}
		EffetsD.setEffet(TE, Effet);
		switch (Effet)
		{
			case EffetsD.FOG:
				Tickrate = 15;
				break;
			case EffetsD.BRUME:
				Tickrate = 5;
				break;
			case EffetsD.SMOKE:
				Tickrate = 30;
		}
		TE.updateEntity();
        return true;
    }
    
    @Override
    /**
     * Alters barrier type or sub-type.
     */
    protected boolean onHammerRightClick(TEBase TE, EntityPlayer entityPlayer)
    {
//		int data = BlockProperties.getMetadata(TE);
//		int type = BancD.getType(data);
//		if (++type > BancD.BANC)
//		{
//			type = BancD.BORD;
//		}
//		BancD.setType(TE, type);
//		
        return false;
    }
    
    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    @Override
	public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    @Override
	public boolean isOpaqueCube()
    {
        return false;
    }
    
    /**
     * Returns the quantity of items to drop on block destruction.
     */
    @Override
	public int quantityDropped(Random par1Random)
    {
        return 0;
    }
    
    /**
     * How many world ticks before ticking
     */
    @Override
	public int tickRate(World par1World)
    {
        return Tickrate;
    }
    
    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    @Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
    }
    
    @Override
	public void addCollisionBoxesToList(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity)
    {
    	setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.1F, 1.0F);
    }
    
    @Override
	@SideOnly(Side.CLIENT)

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World world, int par2, int par3, int par4, Random par5Random)
    {
    	TEBase TE = getTileEntityStrict(world, par2, par3, par4);
    	int data = BlockProperties.getMetadata(TE);
    	if (EffetsD.getEffet(data) == EffetsD.FOG)
    	{
    		float f;
    		float f1;
    		float f2;
    		EntityFX entityfx = null;
    		Minecraft mc = Minecraft.getMinecraft();
    		for (int l = 0; l < 2; ++l)
    		{
    			f = par2 + par5Random.nextFloat();
    			f1 = par3;// + par5Random.nextFloat();
    			f2 = par4 + par5Random.nextFloat();
    			world.spawnParticle("reddust", f, f1, f2, 0.0D, 0.0D, 0.0D);
    			world.spawnParticle("witchMagic", f, f1, f2, 0.0D, 0.0D, 0.0D);
    		}
    	}
    	if (EffetsD.getEffet(data) == EffetsD.BRUME)
    	{
        	if (Compteur >= 5)
        	{
        		float f;
        		float f1;
        		float f2;
        		EntityFX entityfx = null;
        		Minecraft mc = Minecraft.getMinecraft();
        		for (int l = 0; l < 1; ++l)
        		{
        			f = par2 + par5Random.nextFloat();
        			f1 = par3 + par5Random.nextFloat();
        			f2 = par4 + par5Random.nextFloat();
        			entityfx = new BrumeFX(world, f, f1, f2, 0.0D, 0.0D, 0.0D,2.0f, this);
        			mc.effectRenderer.addEffect(entityfx);
        		}
        		Compteur = -1;
        	}
        	Compteur ++;
    	}
    	if (EffetsD.getEffet(data) == EffetsD.SMOKE)
    	{
            float f;
            float f1;
            float f2;
            for (int l = 0; l < 3; ++l)
            {
                f = par2 + par5Random.nextFloat();
                f1 = par3 + par5Random.nextFloat();
                f2 = par4 + par5Random.nextFloat();
                world.spawnParticle("largesmoke", f, f1, f2, 0.0D, 0.0D, 0.0D);
            }
    	}
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
        	return mobilier.Smoke;
        }
    	if (par2 == 100)
    	{
    		return mobilier.Brume;
    	}
        return mobilier.Smoke;
    }
    
    @Override
	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
    	mobilier.Smoke = par1IconRegister.registerIcon("mobiliers:smoke");
    	mobilier.Brume = par1IconRegister.registerIcon("mobiliers:brume");
    	mobilier.Fog = par1IconRegister.registerIcon("mobiliers:smoke");
    }
}
