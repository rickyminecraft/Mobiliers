package mobiliers;

import mobiliers.blocks.*;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import carpentersblocks.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "mobilier", name = "extensions carpenter", version = "v0.01", dependencies = "required-after:CarpentersBlocks")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class mobilier
{
	@Instance("mobilier")
	public static mobilier instance;

	@SidedProxy(clientSide = "mobiliers.proxy.ClientProxy", serverSide = "mobiliers.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static Block Poteau_base, Tabouret, Support, Plateau, Recipient;

	public static int Poteau_baseID, TabouretID, SupportID, PlateauID, RecipientID;

	public static int Poteau_baseRenderID, TabouretRenderID, SupportRenderID, PlateauRenderID, RecipientRenderID;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		int BaseID = 500;
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		Poteau_baseID = config.getBlock("Poteau_base", BaseID++).getInt(BaseID);
		TabouretID = config.getBlock("Tabouret", BaseID++).getInt(BaseID);
		SupportID = config.getBlock("Support", BaseID++).getInt(BaseID);
		PlateauID = config.getBlock("Plateau", BaseID++).getInt(BaseID);
		RecipientID = config.getBlock("Recipient", BaseID++).getInt(BaseID);
		config.save();
		proxy.registerRenderInformation();
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		Poteau_base = (new Poteau_base(Poteau_baseID));
		GameRegistry.registerBlock(Poteau_base, "blockPoteau");
		LanguageRegistry.addName(new ItemStack(Poteau_base), "Poteau");

		Tabouret = (new Tabouret(TabouretID));
		GameRegistry.registerBlock(Tabouret, "blockTabouret");
		LanguageRegistry.addName(new ItemStack(Tabouret), "Tabouret");

		Support = (new Support(SupportID));
		GameRegistry.registerBlock(Support, "blockSupport");
		LanguageRegistry.addName(new ItemStack(Support), "Support");
		
		Plateau = (new Plateau(PlateauID));
		GameRegistry.registerBlock(Plateau, "blockPlateau");
		LanguageRegistry.addName(new ItemStack(Plateau), "Plateau");

		Recipient = (new Recipient(RecipientID));
		GameRegistry.registerBlock(Recipient, "blockRecipient");
		LanguageRegistry.addName(new ItemStack(Recipient), "Recipients");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{

	}
}
