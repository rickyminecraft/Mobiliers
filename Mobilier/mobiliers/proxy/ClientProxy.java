package mobiliers.proxy;

import mobiliers.mobilier;
import mobiliers.renderer.*;
import cpw.mods.fml.client.registry.RenderingRegistry;
import carpentersblocks.proxy.CommonProxy;

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
	}
}
