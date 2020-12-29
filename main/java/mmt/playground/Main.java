package mmt.playground;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mmt.playground.init.ModAugments;
import mmt.playground.init.ModBlocks;
import mmt.playground.init.ModContainers;
import mmt.playground.init.ModItems;
import mmt.playground.network.MTPPacketHandler;
import mmt.playground.util.key.KeyBindingList;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Main.MODID)
public final class Main {

	public static final String MODID = "mtplayground";

	public static final Logger LOGGER = LogManager.getLogger(MODID);
	
	MTPPacketHandler networkHandler = new MTPPacketHandler();

	public Main() {
		LOGGER.debug("Hello from Example Mod!");
		
		//final ModLoadingContext modLoadingContext = ModLoadingContext.get();
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		
		networkHandler.registerPackets();
		
		KeyBindingList.register();
		
		// Register Deferred Registers (Does not need to be before Configs)
		ModAugments.registerAll();
		ModBlocks.BLOCKS.register(modEventBus);
		ModItems.ITEMS.register(modEventBus);
		ModContainers.CONTAINERS.register(modEventBus);
		//ModEntityTypes.ENTITY_TYPES.register(modEventBus);
		//ModTileEntityTypes.TILE_ENTITY_TYPES.register(modEventBus);
		// Register Configs (Does not need to be after Deferred Registers)
		//modLoadingContext.registerConfig(ModConfig.Type.CLIENT, ConfigHolder.CLIENT_SPEC);
		//modLoadingContext.registerConfig(ModConfig.Type.SERVER, ConfigHolder.SERVER_SPEC);
	}

}
