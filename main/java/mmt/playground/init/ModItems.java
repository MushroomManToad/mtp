package mmt.playground.init;

import mmt.playground.Main;
import mmt.playground.items.CharmItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class ModItems {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MODID);

	public static final RegistryObject<Item> SHADE_STONE = ITEMS.register("shade_stone", () -> new Item(new Item.Properties().group(ItemGroups.PLAYGROUND)));

	public static final RegistryObject<Item> HK_CHARM = ITEMS.register("hk_charm", () -> new CharmItem(new Item.Properties().group(ItemGroups.PLAYGROUND), ModAugments.MOTH_WINGS, ModAugments.SHADE_CLOAK));
}
