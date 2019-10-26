package concavebark.moreMinecraftMod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import concavebark.moreMinecraftMod.config.Config;
import concavebark.moreMinecraftMod.lists.BlockList;
import concavebark.moreMinecraftMod.lists.ItemList;
import concavebark.moreMinecraftMod.lists.ToolMaterialList;
import concavebark.moreMinecraftMod.world.OreGeneration;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod("moreminecraftmod")
public class moreMinecraftMod {
	
	public static moreMinecraftMod instance;
	public static final String modid = "moreminecraftmod";
	public static final Logger logger = LogManager.getLogger(modid);
	
	public static final ItemGroup moreMinecraft = new moreMinecraftModItemGroup();
	
	public moreMinecraftMod() {
		instance = this;
		
		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.server_config);
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.client_config);
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistries);
		
		Config.loadConfig(Config.client_config, FMLPaths.CONFIGDIR.get().resolve("moreminecraftmod-client.toml").toString());
		Config.loadConfig(Config.server_config, FMLPaths.CONFIGDIR.get().resolve("moreminecraftmod-server.toml").toString());
		
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	private void setup(final FMLCommonSetupEvent event) {
		OreGeneration.setupOreGeneration();
		logger.info("Setup method registered");
	}
	
	private void clientRegistries(final FMLClientSetupEvent event) { // client side only
		logger.info("clientRegistries method registered");
	}
	
	@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents {
		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event) {
			event.getRegistry().registerAll(
					//ItemList.breadium = new Item(new Item.Properties().group(moreMinecraft)).setRegistryName(location("breadium")),
					
					ItemList.tutorial_item = new Item(new Item.Properties().group(moreMinecraft)).setRegistryName(location("tutorial_item")),
					
					ItemList.tutorial_axe = new AxeItem(ToolMaterialList.tutorial, -1.0f, 6.0f, new Item.Properties().group(moreMinecraft)).setRegistryName(location("tutorial_axe")),
					ItemList.tutorial_pickaxe = new PickaxeItem(ToolMaterialList.tutorial, -2, 3.0f, new Item.Properties().group(moreMinecraft)).setRegistryName(location("tutorial_pickaxe")),
					ItemList.tutorial_shovel = new ShovelItem(ToolMaterialList.tutorial, 0, 1.0f, new Item.Properties().group(moreMinecraft)).setRegistryName(location("tutorial_shovel")),
					ItemList.tutorial_hoe = new HoeItem(ToolMaterialList.tutorial, 3.0f, new Item.Properties().group(moreMinecraft)).setRegistryName(location("tutorial_hoe")),
					ItemList.tutorial_sword = new SwordItem(ToolMaterialList.tutorial, 0, 1.0f, new Item.Properties().group(moreMinecraft)).setRegistryName(location("tutorial_sword")),
					
					ItemList.tutorial_ore = new BlockItem(BlockList.tutorial_ore, new Item.Properties().group(moreMinecraft)).setRegistryName(BlockList.tutorial_ore.getRegistryName()),
					ItemList.tutorial_ore_nether = new BlockItem(BlockList.tutorial_ore_nether, new Item.Properties().group(moreMinecraft)).setRegistryName(BlockList.tutorial_ore_nether.getRegistryName()),
					ItemList.tutorial_ore_end = new BlockItem(BlockList.tutorial_ore_end, new Item.Properties().group(moreMinecraft)).setRegistryName(BlockList.tutorial_ore_end.getRegistryName()),
					ItemList.tutorial_block = new BlockItem(BlockList.tutorial_block, new Item.Properties().group(moreMinecraft)).setRegistryName(BlockList.tutorial_block.getRegistryName())
			);
			logger.info("items registered");
		}
		
		@SubscribeEvent
		public static void registerBlocks(final RegistryEvent.Register<Block> event) {
			event.getRegistry().registerAll(
					//BlockList.bread_ore = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f, 5.0f).sound(SoundType.STONE)).setRegistryName(location("bread_ore")),
					
					BlockList.tutorial_block = new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(2.0f, 3.0f).lightValue(15).sound(SoundType.METAL)).setRegistryName(location("tutorial_block")),
					BlockList.tutorial_ore = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 15.0f).sound(SoundType.STONE)).setRegistryName(location("tutorial_ore")),
					BlockList.tutorial_ore_nether = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 15.0f).sound(SoundType.STONE)).setRegistryName(location("tutorial_ore_nether")),
					BlockList.tutorial_ore_end = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0f, 15.0f).sound(SoundType.STONE)).setRegistryName(location("tutorial_ore_end"))
			);
			logger.info("blocks registered");
		}
		
		private static ResourceLocation location(String name) {
			return new ResourceLocation(modid, name);
		}
	}
}