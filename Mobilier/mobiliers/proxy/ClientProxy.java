package mobiliers.proxy;

import mobiliers.mobilier;
import mobiliers.renderer.*;
import mobiliers.tileEntity.TileBookRenderer;
import mobiliers.tileEntity.TileEntityBook;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import mobiliers.proxy.CommonProxy;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderInformation()
	{
		mobilier.Poteau_baseRenderID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(mobilier.Poteau_baseRenderID, new Poteau_base());
		mobilier.TabouretRenderID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(mobilier.TabouretRenderID, new Tabouret());
		mobilier.SupportRenderID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(mobilier.SupportRenderID, new Support());
		mobilier.PlateauRenderID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(mobilier.PlateauRenderID, new Plateau());
		mobilier.RecipientRenderID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(mobilier.RecipientRenderID, new Recipient());
		mobilier.EscalierRenderID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(mobilier.EscalierRenderID, new Escaliers());
		mobilier.TableRenderID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(mobilier.TableRenderID, new Table());
		mobilier.TangleRenderID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(mobilier.TangleRenderID, new Tangle());
		mobilier.ChaiseRenderID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(mobilier.ChaiseRenderID, new Chaise());
		mobilier.QuartRenderID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(mobilier.QuartRenderID, new Quart());
		mobilier.FenetreRenderID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(mobilier.FenetreRenderID, new Fenetre());
		mobilier.BancID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(mobilier.BancID, new Banc());
		mobilier.BancBordID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(mobilier.BancBordID, new Banc_bord());
		mobilier.CommodeID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(mobilier.CommodeID, new Commode());
		mobilier.CreuxID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(mobilier.CreuxID, new Creux());
		mobilier.Support_TorcheID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(mobilier.Support_TorcheID, new Support_torche());
		mobilier.PoteauID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(mobilier.PoteauID, new Poteau_indicateur());
		mobilier.PupitreID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(mobilier.PupitreID, new Pupitre());
		mobilier.ChainesID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(mobilier.ChainesID, new Chaines());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBook.class, new TileBookRenderer());
	}
}
