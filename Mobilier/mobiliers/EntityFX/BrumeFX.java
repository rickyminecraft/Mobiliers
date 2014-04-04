package mobiliers.EntityFX;

import mobiliers.blocks.Effets;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

public class BrumeFX extends EntityFX
{
	   float particleScaleOverTime;

	    public BrumeFX(World par1World, double par2, double par4, double par6, double par8, double par10, double par12, float f, Effets effets)
	    {
	        super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
	        //this.motionX *= 0.009999999776482582D;
	        //this.motionY *= 0.009999999776482582D;
	        //this.motionZ *= 0.009999999776482582D;
	        this.motionX *= 0.15D;
	        this.motionZ *= 0.15D;
	        this.motionY *= 0.02D;
	        this.particleRed = this.particleGreen = this.particleBlue = 1.0F - (float)(Math.random() * 0.30000001192092896D);
	        this.particleScale *= 2.75F;
	        this.particleScale *= f;
	        this.particleScaleOverTime = this.particleScale;
	        this.particleMaxAge = 128;
	        this.noClip = false;
	        this.setParticleIcon(effets.getIcon(0, 100));
	    }

	    @Override
		public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7)
	    {
	        float f6 = (this.particleAge + par2) / this.particleMaxAge * 32.0F;

	        if (f6 < 0.0F)
	        {
	            f6 = 0.0F;
	        }

	        if (f6 > 1.0F)
	        {
	            f6 = 1.0F;
	        }
	        this.particleScale = this.particleScaleOverTime * f6;
	        super.renderParticle(par1Tessellator, par2, par3, par4, par5, par6, par7);
	    }
	    
	    @Override
		public int getFXLayer()
	    {
	        return 1;
	    }

	    /**
	     * Called to update the entity's position/logic.
	     */
	    @Override
		public void onUpdate()
	    {
	        this.prevPosX = this.posX;
	        this.prevPosY = this.posY;
	        this.prevPosZ = this.posZ;

	        if (this.particleAge++ >= this.particleMaxAge)
	        {
	            this.setDead();
	        }

	        this.moveEntity(this.motionX, this.motionY, this.motionZ);

	        if (this.posY == this.prevPosY)
	        {
	            this.motionX *= 1.1D;
	            this.motionZ *= 1.1D;
	        }

	        //this.motionX *= 0.8600000143051147D;
	        //this.motionY *= 0.8600000143051147D;
	        //this.motionZ *= 0.8600000143051147D;

	        if (this.onGround)
	        {
	            this.motionX *= 0.699999988079071D;
	            this.motionZ *= 0.699999988079071D;
	        }
	    }
}
