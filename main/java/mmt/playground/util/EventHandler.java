package mmt.playground.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mmt.playground.Main;
import mmt.playground.init.ItemGroups;
import mmt.playground.init.ModBlocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber(modid = Main.MODID, bus = EventBusSubscriber.Bus.MOD)
public final class EventHandler {

	private static final Logger LOGGER = LogManager.getLogger(Main.MODID + " Mod Event Subscriber");
	
	/**
	 * Thanks @Cadiboo
	 */
	@SubscribeEvent
	public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
		final IForgeRegistry<Item> registry = event.getRegistry();
		ModBlocks.BLOCKS.getEntries().stream()
				.map(RegistryObject::get)
				.forEach(block -> {
					final Item.Properties properties = new Item.Properties().group(ItemGroups.PLAYGROUND);
					final BlockItem blockItem = new BlockItem(block, properties);
					blockItem.setRegistryName(block.getRegistryName());
					registry.register(blockItem);
				});
		LOGGER.debug("Registered BlockItems");
	}
}
