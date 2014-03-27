package mobiliers;

import carpentersblocks.CarpentersBlocks;
import mobiliers.blocks.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.ShapedOreRecipe;
import mobiliers.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "mobilier", name = "extensions carpenter", version = "v1.0", dependencies = "required-after:CarpentersBlocks")

public class mobilier
{
	@Instance("mobilier")
	public static mobilier instance;

	@SidedProxy(clientSide = "mobiliers.proxy.ClientProxy", serverSide = "mobiliers.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static Block Poteau_base, Tabouret, Support, Plateau, Recipient, Escalier, Table, Tangle, Chaise, Quart, Fenetre;

	public static int Poteau_baseRenderID, TabouretRenderID, SupportRenderID, PlateauRenderID, RecipientRenderID, EscalierRenderID, TableRenderID, TangleRenderID, ChaiseRenderID, QuartRenderID, FenetreRenderID;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		Poteau_base = new Poteau_base(Material.wood).setHardness(0.2F).setBlockName("blockPoteau").setCreativeTab(CarpentersBlocks.creativeTab).setBlockTextureName(CarpentersBlocks.MODID + ":" + "general/quartered_frame");
		GameRegistry.registerBlock(Poteau_base, "blockPoteau");
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Poteau_base, 2), " X ", " U ", 'X', Block.blockRegistry.getObject("fence"), 'U', carpentersblocks.util.registry.BlockRegistry.blockCarpentersBlock));

		Tabouret = new Tabouret(Material.wood).setHardness(0.2F).setBlockName("blockTabouret").setCreativeTab(CarpentersBlocks.creativeTab).setBlockTextureName(CarpentersBlocks.MODID + ":" + "general/solid");
		GameRegistry.registerBlock(Tabouret, "blockTabouret");
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Tabouret, 2), "XX", "UU", 'X', carpentersblocks.util.registry.BlockRegistry.blockCarpentersBlock, 'U', "stickWood"));

		Support = new Support(Material.wood).setHardness(0.2F).setBlockName("blockSupport").setCreativeTab(CarpentersBlocks.creativeTab).setBlockTextureName(CarpentersBlocks.MODID + ":" + "general/solid");
		GameRegistry.registerBlock(Support, "blockSupport");
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Support, 2), "XXX", "X  ", "X  ", 'X', "plankWood"));

		Plateau = new Plateau(Material.wood).setHardness(0.2F).setBlockName("blockPlateau").setCreativeTab(CarpentersBlocks.creativeTab).setBlockTextureName(CarpentersBlocks.MODID + ":" + "general/quartered_frame");
		GameRegistry.registerBlock(Plateau, "blockPlateau");
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Plateau, 2), "XXX", 'X', "stickWood"));

		Recipient = new Recipient(Material.wood).setHardness(0.2F).setBlockName("blockRecipient").setCreativeTab(CarpentersBlocks.creativeTab).setBlockTextureName(CarpentersBlocks.MODID + ":" + "general/solid");
		GameRegistry.registerBlock(Recipient, "blockRecipient");
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Recipient, 2), "X", 'X', carpentersblocks.util.registry.BlockRegistry.blockCarpentersButton));
		
		Escalier = new Escaliers(Material.wood).setHardness(0.2F).setBlockName("blockEscalier").setCreativeTab(CarpentersBlocks.creativeTab).setBlockTextureName(CarpentersBlocks.MODID + ":" + "general/quartered_frame");
		GameRegistry.registerBlock(Escalier, "blockEscalier");
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Escalier, 2), "XX ", "   "," XX", 'X', carpentersblocks.util.registry.BlockRegistry.blockCarpentersBlock));
		
		Table = new Table(Material.wood).setHardness(0.2F).setBlockName("blockTable").setCreativeTab(CarpentersBlocks.creativeTab).setBlockTextureName(CarpentersBlocks.MODID + ":" + "general/quartered_frame");
		GameRegistry.registerBlock(Table, "blockTable");
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Table, 2), "XXX", " U ", " U ", 'X', carpentersblocks.util.registry.BlockRegistry.blockCarpentersBlock, 'U', "stickWood"));
		
		Tangle = new Tangle(Material.wood).setHardness(0.2F).setBlockName("blockTangle").setCreativeTab(CarpentersBlocks.creativeTab).setBlockTextureName(CarpentersBlocks.MODID + ":" + "general/quartered_frame");
		GameRegistry.registerBlock(Tangle, "blockTangle");
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Tangle, 2), "XXX", "U  ",  "U  ", 'X', carpentersblocks.util.registry.BlockRegistry.blockCarpentersBlock, 'U', "stickWood"));
		
		Chaise = new Chaise(Material.wood).setHardness(0.2F).setBlockName("blockChaise").setCreativeTab(CarpentersBlocks.creativeTab).setBlockTextureName(CarpentersBlocks.MODID + ":" + "general/solid");
		GameRegistry.registerBlock(Chaise, "blockChaise");
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Chaise, 2), "X  ", "UU ", "XX ", 'X', "stickWood", 'U', carpentersblocks.util.registry.BlockRegistry.blockCarpentersBlock));
		
		Quart = new Quart(Material.wood).setHardness(0.2F).setBlockName("blockQuart").setCreativeTab(CarpentersBlocks.creativeTab).setBlockTextureName(CarpentersBlocks.MODID + ":" + "general/solid");
		GameRegistry.registerBlock(Quart, "blockQuart");
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Quart, 2), "X", "X", "X", 'X', carpentersblocks.util.registry.BlockRegistry.blockCarpentersBlock));
		
		Fenetre = new Fenetre(Material.wood).setHardness(0.2F).setBlockName("blockFenetre").setCreativeTab(CarpentersBlocks.creativeTab).setBlockTextureName(CarpentersBlocks.MODID + ":" + "general/quartered_frame");
		GameRegistry.registerBlock(Fenetre, "blockFenetre");
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Fenetre, 2), "XXX", "X X", "X X", 'X', "stickWood"));
		
		EntityRegistry.registerModEntity(EntityMountableBlock.class, "EntityMountableBlock", 1,  this, 250, 5, false); //pour s'asseoir
		proxy.registerRenderInformation();
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{

	}
}
