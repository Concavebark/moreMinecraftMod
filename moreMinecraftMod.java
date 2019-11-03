package concavebark.moreMinecraftMod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import concavebark.moreMinecraftMod.config.Config;
import concavebark.moreMinecraftMod.lists.ArmorMaterialList;
import concavebark.moreMinecraftMod.lists.BlockList;
import concavebark.moreMinecraftMod.lists.FoodList;
import concavebark.moreMinecraftMod.lists.ItemList;
import concavebark.moreMinecraftMod.lists.ToolMaterialList;
import concavebark.moreMinecraftMod.world.OreGeneration;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.Item.Properties;
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
					ItemList.bread_crumbs = new Item(new Item.Properties().food(FoodList.breadiumFood).group(moreMinecraft)).setRegistryName(location("bread_crumbs")),
					ItemList.bread_ore = new BlockItem(BlockList.bread_ore, new Item.Properties().group(moreMinecraft)).setRegistryName(BlockList.bread_ore.getRegistryName()),
					ItemList.advanced_bread_ore = new BlockItem(BlockList.advanced_bread_ore, new Item.Properties().group(moreMinecraft)).setRegistryName(BlockList.advanced_bread_ore.getRegistryName()),
					ItemList.bread_oven = new BlockItem(BlockList.bread_oven, new Item.Properties().group(moreMinecraft)).setRegistryName(BlockList.bread_oven.getRegistryName()),
					
					ItemList.artisan_bread = new Item(new Item.Properties().group(moreMinecraft)).setRegistryName(location("artisan_bread")),
					ItemList.sourdough_bread = new Item(new Item.Properties().group(moreMinecraft)).setRegistryName(location("sourdough_bread")),
					ItemList.baguette = new Item(new Item.Properties().group(moreMinecraft)).setRegistryName(location("baguette")),
					ItemList.croissant = new Item(new Item.Properties().group(moreMinecraft)).setRegistryName(location("croissant"))
			);
			logger.info("items registered");
		}
		
		@SubscribeEvent
		public static void registerBlocks(final RegistryEvent.Register<Block> event) {
			event.getRegistry().registerAll(
					BlockList.bread_ore = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f, 5.0f).sound(SoundType.STONE)).setRegistryName(location("bread_ore")),
					BlockList.advanced_bread_ore = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f, 8.0f).sound(SoundType.STONE)).setRegistryName(location("advanced_bread_ore")),
					BlockList.bread_oven = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0f).sound(SoundType.STONE)).setRegistryName(location("bread_oven"))
			);
			logger.info("blocks registered");
		}
		
		private static ResourceLocation location(String name) {
			return new ResourceLocation(modid, name);
		}
	}
}