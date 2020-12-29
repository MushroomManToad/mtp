package mmt.playground.init;

import java.util.ArrayList;

import mmt.playground.charm.Augment;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

@SuppressWarnings("unused")
public class ModAugments 
{	
	public static Augment MOTH_WINGS;
	public static Augment SHADE_CLOAK;
	
	public static void registerAll()
	{
		MOTH_WINGS = new Augment("moth_wings", new TranslationTextComponent("mtp.hk.augment.moth_wings"), (boolean) false, new ResourceLocation("minecraft:feather"));
		SHADE_CLOAK = new Augment("shade_cloak", new TranslationTextComponent("mtp.hk.augment.shade_cloak"), (boolean) false, new ResourceLocation("minecraft:ender_pearl"));
	}
}
