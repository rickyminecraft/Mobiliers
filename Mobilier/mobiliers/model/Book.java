package mobiliers.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class Book extends ModelBase
{
    /** The board on a sign that has the writing on it. */
    public ModelRenderer signBoard = new ModelRenderer(this, 0, 0);
    
    public Book()
    {
        this.signBoard.addBox(-6.0F, -11.0F, -1.0F, 12, 12, 2, 0.0F);
    }
    
    /**
     * Renders the sign model through TileEntitySignRenderer
     */
    public void renderSign()
    {
        this.signBoard.render(0.0625F);
    }
}
