package mobiliers.tileEntity;

import mobiliers.data.PupitreD;
import mobiliers.model.Book;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import carpentersblocks.tileentity.TEBase;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileBookRenderer extends TileEntitySpecialRenderer
{
    /** The ModelSign instance used by the TileEntitySignRenderer */
    private Book modelBook = new Book();
    private static final ResourceLocation texture = new ResourceLocation("mobiliers:textures/entity/book.png");
	
	
	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d0, double d1,
			double d2, float f)
	{
		TEBase TE = (TEBase) tileentity.getWorldObj().getTileEntity(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord);
    	int Meta = TE.metadata;
    	if (PupitreD.getType(Meta) ==  PupitreD.PLEIN)
		{
    		GL11.glPushMatrix();
    		float f1 = 0.6666667F;
    		float f2;
    		float f3;
            int i = PupitreD.getRotation(Meta);
            f2 = 0.0F;
            f3 = -90.0F;

            if (i == PupitreD.PUPITRE_X_POS)
            {
                f2 = 180.0F;
            }

            if (i == PupitreD.PUPITRE_Z_POS)
            {
                f2 = 90.0F;
            }

            if (i == PupitreD.PUPITRE_X_NEG)
            {
                f2 = -90.0F;
            }
    		GL11.glTranslatef((float)d0 + 0.5F, (float)d1 + 0.6F * f1, (float)d2 + 0.5F);
    		GL11.glRotatef(-f2, 0.0F, 1.0F, 0.0F);
    		//ici tout un tas de rotation pour bien aligner l'entite
    		//a signaler que l'entite et a l'envers (c ma faute) et que j'ai retourner la texture
    		GL11.glRotatef(-f3, 1.0F, 0.0F, 0.0F);
    		GL11.glRotatef(90, 0.0F, 0.0F, 1.0F);
    		GL11.glRotatef(10, 1.0F, 0.0F, 0.0F);
            GL11.glTranslatef(0.0F, -0.3125F, -0.4375F);
            this.bindTexture(texture);
            GL11.glPushMatrix();
            GL11.glScalef(f1, -f1, -f1);
    		this.modelBook.renderSign();
    		GL11.glPopMatrix();
    		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    		GL11.glPopMatrix();
		}
	}
}
