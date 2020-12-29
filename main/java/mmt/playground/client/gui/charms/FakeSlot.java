package mmt.playground.client.gui.charms;

import mmt.playground.items.CharmItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class FakeSlot extends Slot {
	public FakeSlot(IInventory inventoryIn, int slotIndexIn, int xPosition, int yPosition) 
	{
		super(inventoryIn, slotIndexIn, xPosition, yPosition);
	}

	public boolean isItemValid(ItemStack stack) {
		return false;
	}
}