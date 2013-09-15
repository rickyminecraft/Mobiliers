package mobiliers;

import mobiliers.blocks.*;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.oredict.ShapedOreRecipe;
import mobiliers.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
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

	public static Block Poteau_base, Tabouret, Support, Plateau, Recipient, Escalier, Table, Tangle;

	public static int Poteau_baseID, TabouretID, SupportID, PlateauID, RecipientID, EscalierID, TableID, TangleID;

	public static int Poteau_baseRenderID, TabouretRenderID, SupportRenderID, PlateauRenderID, RecipientRenderID, EscalierRenderID, TableRenderID, TangleRenderID;

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
		EscalierID = config.getBlock("Escalier", BaseID++).getInt(BaseID);
		TableID = config.getBlock("Table", BaseID++).getInt(BaseID);
		TangleID = config.getBlock("Tangle", BaseID++).getInt(BaseID);
		config.save();
		proxy.registerRenderInformation();
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		Poteau_base = (new Poteau_base(Poteau_baseID));
		GameRegistry.registerBlock(Poteau_base, "blockPoteau");
		LanguageRegistry.addName(new ItemStack(Poteau_base), "Poteau");
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Poteau_base, 2), " X ", " U ", 'X', Block.fence, 'U', carpentersblocks.CarpentersBlocks.blockCarpentersBlock));

		Tabouret = (new Tabouret(TabouretID));
		GameRegistry.registerBlock(Tabouret, "blockTabouret");
		LanguageRegistry.addName(new ItemStack(Tabouret), "Tabouret");
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Tabouret, 2), "XX", "UU", 'X', carpentersblocks.CarpentersBlocks.blockCarpentersBlock, 'U', "stickWood"));

		Support = (new Support(SupportID));
		GameRegistry.registerBlock(Support, "blockSupport");
		LanguageRegistry.addName(new ItemStack(Support), "Support");
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Support, 2), "XXX", "X  ", "X  ", 'X', "plankWood"));

		Plateau = (new Plateau(PlateauID));
		GameRegistry.registerBlock(Plateau, "blockPlateau");
		LanguageRegistry.addName(new ItemStack(Plateau), "Plateau");
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Plateau, 2), "XXX", 'X', "stickWood"));

		Recipient = (new Recipient(RecipientID));
		GameRegistry.registerBlock(Recipient, "blockRecipient");
		LanguageRegistry.addName(new ItemStack(Recipient), "Recipients");
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Recipient, 2), "X", 'X', carpentersblocks.CarpentersBlocks.blockCarpentersButton));
		
		Escalier = (new Escaliers(EscalierID));
		GameRegistry.registerBlock(Escalier, "blockEscalier");
		LanguageRegistry.addName(new ItemStack(Escalier), "Escalier");
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Escalier, 2), "XX ", "   "," XX", 'X', carpentersblocks.CarpentersBlocks.blockCarpentersBlock));
		
		Table = (new Table(TableID));
		GameRegistry.registerBlock(Table, "blockTable");
		LanguageRegistry.addName(new ItemStack(Table), "Table");
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Table, 2), "XXX", " U ", " U ", 'X', carpentersblocks.CarpentersBlocks.blockCarpentersBlock, 'U', "stickWood"));
		
		Tangle = (new Tangle(TangleID));
		GameRegistry.registerBlock(Tangle, "blockTangle");
		LanguageRegistry.addName(new ItemStack(Tangle), "Table angle");
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Tangle, 2), "XXX", " U ", " U ", 'X', carpentersblocks.CarpentersBlocks.blockCarpentersBlock, 'U', "stickWood"));
		
		EntityRegistry.registerModEntity(EntityMountableBlock.class, "EntityMountableBlock", 1,  this, 250, 5, false); //pour s'asseoir
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{

	}
}
