package mobiliers;

import carpentersblocks.CarpentersBlocks;
import mobiliers.blocks.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.oredict.ShapedOreRecipe;
import mobiliers.proxy.CommonProxy;
import mobiliers.tileEntity.TileEntityBook;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "mobilier", name = "extensions carpenter", version = "v1.0", dependencies = "required-after:CarpentersBlocks")

public class mobilier
{
	@Instance("mobilier")
	public static mobilier instance;

	@SidedProxy(clientSide = "mobiliers.proxy.ClientProxy", serverSide = "mobiliers.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static Block Poteau_base, Tabouret, Support, Plateau, Recipient, Escalier, Table, Tangle, Chaise, Quart, Fenetre, Banc, Banc_Bord, Commode, Creux, Storche, Storche2, Poteau, Pupitre, Chaines, Effets, Armoire, Buffet;

	public static IIcon Chaines1, Chaines2, Fog, Smoke, Brume, IArmoire, IdefautArmoire, IBuffet;
	
	public static int 	Poteau_baseRenderID,
						TabouretRenderID,
						SupportRenderID,
						PlateauRenderID,
						RecipientRenderID,
						EscalierRenderID,
						TableRenderID,
						TangleRenderID,
						ChaiseRenderID,
						QuartRenderID,
						FenetreRenderID,
						BancID,
						BancBordID,
						CommodeID,
						CreuxID,
						Support_TorcheID,
						PoteauID,
						PupitreID,
						ChainesID,
						ArmoireID,
						BuffetID;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		Poteau_base = new Poteau_base(Material.wood).setHardness(0.2F).setBlockName("blockPoteau").setCreativeTab(CarpentersBlocks.creativeTab).setBlockTextureName(CarpentersBlocks.MODID + ":" + "general/quartered_frame");
		GameRegistry.registerBlock(Poteau_base, "blockPoteau");
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Poteau_base, 2), " X ", " U ", 'X', "fence", 'U', carpentersblocks.util.registry.BlockRegistry.blockCarpentersBlock));

		Tabouret = new Tabouret(Material.wood).setHardness(0.2F).setBlockName("blockTabouret").setCreativeTab(CarpentersBlocks.creativeTab).setBlockTextureName(CarpentersBlocks.MODID + ":" + "general/solid");
		GameRegistry.registerBlock(Tabouret, "blockTabouret");
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Tabouret, 2), "XX", "UU", 'X', carpentersblocks.util.registry.BlockRegistry.blockCarpentersBlock, 'U', "stickWood"));

		Support = new Support(Material.wood).setHardness(0.2F).setBlockName("blockSupport").setCreativeTab(CarpentersBlocks.creativeTab).setBlockTextureName(CarpentersBlocks.MODID + ":" + "general/solid");
		GameRegistry.registerBlock(Support, "blockSupport");
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Support, 2), "XXX", "X  ", "X  ", 'X', carpentersblocks.util.registry.BlockRegistry.blockCarpentersBlock));

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
		
		Banc = new Banc(Material.wood).setHardness(0.2F).setBlockName("blockBanc").setCreativeTab(CarpentersBlocks.creativeTab).setBlockTextureName(CarpentersBlocks.MODID + ":" + "general/solid");
		GameRegistry.registerBlock(Banc, "blockBanc");
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Banc, 3), "XX ", "X X", "X X", 'X', "stickWood"));
		
		Banc_Bord = new Banc_bord(Material.wood).setHardness(0.2F).setBlockName("blockBancBord").setCreativeTab(CarpentersBlocks.creativeTab).setBlockTextureName(CarpentersBlocks.MODID + ":" + "general/solid");
		GameRegistry.registerBlock(Banc_Bord, "blockBancBord");
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Banc_Bord, 2), "XX ", "U  ", 'X', carpentersblocks.util.registry.BlockRegistry.blockCarpentersBlock, 'U', "stickWood"));
		
		Commode = new Commode(Material.wood).setHardness(0.2F).setBlockName("blockCommode").setCreativeTab(CarpentersBlocks.creativeTab).setBlockTextureName(CarpentersBlocks.MODID + ":" + "general/solid");
		GameRegistry.registerBlock(Commode, "blockCommode");
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Commode, 1), "XX ", "XX ", 'X', carpentersblocks.util.registry.BlockRegistry.blockCarpentersBlock));
		
		Creux = new Creux(Material.wood).setHardness(0.2F).setBlockName("blockCreux").setCreativeTab(CarpentersBlocks.creativeTab).setBlockTextureName(CarpentersBlocks.MODID + ":" + "general/quartered_frame");
		GameRegistry.registerBlock(Creux, "blockCreux");
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Creux, 2), " X ", "X X", " X ", 'X', "stickWood"));
		
		Storche = new Support_Torche(Material.wood).setLightLevel(0.0F).setHardness(0.2F).setBlockName("blockStorche").setCreativeTab(CarpentersBlocks.creativeTab).setBlockTextureName(CarpentersBlocks.MODID + ":" + "general/solid");
		Storche2 = new Support_Torche1(Material.wood).setLightLevel(1.0F).setHardness(0.2F).setBlockName("blockStorche1").setCreativeTab(CarpentersBlocks.creativeTab).setBlockTextureName(CarpentersBlocks.MODID + ":" + "general/solid");
		GameRegistry.registerBlock(Storche, "blockStorche");
		GameRegistry.registerBlock(Storche2, "blockStorche1");
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Storche, 2), " XY", 'X', carpentersblocks.util.registry.BlockRegistry.blockCarpentersBlock, 'Y', "stickWood"));

		Poteau = new Poteau_indicateur(Material.wood).setHardness(0.2F).setBlockName("blockPoteau_Indicateur").setCreativeTab(CarpentersBlocks.creativeTab).setBlockTextureName(CarpentersBlocks.MODID + ":" + "general/solid");
		GameRegistry.registerBlock(Poteau, "blockPoteau_Indicateur");
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Poteau, 2), "UXU", " U ", 'U', "fence", 'X', "stickWood"));
		
		Pupitre = new Pupitre(Material.wood).setHardness(0.2F).setBlockName("blockPupitre").setCreativeTab(CarpentersBlocks.creativeTab).setBlockTextureName(CarpentersBlocks.MODID + ":" + "general/solid");
		GameRegistry.registerBlock(Pupitre, "blockPupitre");
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Pupitre, 2), "X", "Y", 'X', "book", 'Y', carpentersblocks.util.registry.BlockRegistry.blockCarpentersBlock));
		
		Chaines = new Chaines(Material.wood).setHardness(5.0F).setBlockName("blockChaines").setCreativeTab(CarpentersBlocks.creativeTab).setBlockTextureName(CarpentersBlocks.MODID + ":" + "general/solid");
		GameRegistry.registerBlock(Chaines, "blockChaines");
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Chaines, 2), "X", "X", 'X', "iron_ingot"));
		
		Effets = new Effets(Material.wood).setHardness(0.2F).setBlockName("blockEffets").setCreativeTab(CarpentersBlocks.creativeTab).setBlockTextureName(CarpentersBlocks.MODID + ":" + "general/solid");
		GameRegistry.registerBlock(Effets, "blockEffets");
		
		Armoire = new Armoire(Material.wood).setHardness(0.2F).setBlockName("blockArmoire").setCreativeTab(CarpentersBlocks.creativeTab).setBlockTextureName(CarpentersBlocks.MODID + ":" + "general/quartered_frame");
		GameRegistry.registerBlock(Armoire, "blockArmoire");
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Armoire, 2), "XX ", "XY ", "XX ", 'X', carpentersblocks.util.registry.BlockRegistry.blockCarpentersBlock, 'Y', "stickWood"));
		
		Buffet = new Buffet(Material.wood).setHardness(0.2F).setBlockName("blockBuffet").setCreativeTab(CarpentersBlocks.creativeTab).setBlockTextureName(CarpentersBlocks.MODID + ":" + "general/quartered_frame");
		GameRegistry.registerBlock(Buffet, "blockBuffet");
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Buffet, 2), "XYX", "XXX", 'X', carpentersblocks.util.registry.BlockRegistry.blockCarpentersBlock, 'Y', "stickWood"));
		
		GameRegistry.addRecipe(new ItemStack(Block.getBlockFromName("web"), 3), new Object[] { "X X", " X ", "X X", Character.valueOf('X'), Item.itemRegistry.getObject("string")});
		
		EntityRegistry.registerModEntity(EntityMountableBlock.class, "EntityMountableBlock", 1,  this, 250, 5, false); //pour s'asseoir
		GameRegistry.registerTileEntity(TileEntityBook.class, "TileBook"); //livre du pupitre
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
