package mmt.playground.init;

import java.util.function.Supplier;

import javax.annotation.Nonnull;

import mmt.playground.Main;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public final class ItemGroups {

	public static final ItemGroup PLAYGROUND = new PlaygroundItemGroup(Main.MODID, () -> new ItemStack(Items.STICK));

	public static final class PlaygroundItemGroup extends ItemGroup 
	{
		@Nonnull
		private final Supplier<ItemStack> iconSupplier;

		public PlaygroundItemGroup(@Nonnull final String name, @Nonnull final Supplier<ItemStack> iconSupplier) 
		{
			super(name);
			this.iconSupplier = iconSupplier;
		}

		@Override
		@Nonnull
		public ItemStack createIcon() 
		{
			return iconSupplier.get();
		}
	}
}