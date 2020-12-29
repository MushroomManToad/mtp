package mmt.playground.charm;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;

public class CharmLoader 
{
	public static IInventory loadInventoryFromCharmData(PlayerEntity playerIn)
	{
		return new Inventory(1);
	}
}
