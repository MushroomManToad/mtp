package mmt.playground.client.gui.charms;

import mmt.playground.init.ModContainers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ShulkerBoxSlot;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class ContainerCharm extends Container
{
	private final IInventory inventory;

	public ContainerCharm(int id, PlayerInventory playerInventory) {
		this(id, playerInventory, new Inventory(1));
	}

	public ContainerCharm(int id, PlayerInventory playerInventory, IInventory inventory) 
	{
		super(ModContainers.CHARM_CONTAINER.get(), id);
		assertInventorySize(inventory, 1);
		this.inventory = inventory;


		this.addSlot(new CharmSlot(inventory,0, 8, 18 + 0));
		
		inventory.openInventory(playerInventory.player);
		
		for(int i1 = 0; i1 < 3; ++i1) {
			for(int k1 = 0; k1 < 9; ++k1) {
				this.addSlot(new Slot(playerInventory, k1 + i1 * 9 + 9, 8 + k1 * 18, 84 + i1 * 18));
			}
		}

		for(int j1 = 0; j1 < 9; ++j1) {
			this.addSlot(new Slot(playerInventory, j1, 8 + j1 * 18, 142));
		}
	}

	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return true;
	}
	
	   /**
	    * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player
	    * inventory and the other inventory(s).
	    */
	   public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
	      ItemStack itemstack = ItemStack.EMPTY;
	      Slot slot = this.inventorySlots.get(index);
	      if (slot != null && slot.getHasStack()) {
	         ItemStack itemstack1 = slot.getStack();
	         itemstack = itemstack1.copy();
	         if (index < this.inventory.getSizeInventory()) {
	            if (!this.mergeItemStack(itemstack1, this.inventory.getSizeInventory(), this.inventorySlots.size(), true)) {
	               return ItemStack.EMPTY;
	            }
	         } else if (!this.mergeItemStack(itemstack1, 0, this.inventory.getSizeInventory(), false)) {
	            return ItemStack.EMPTY;
	         }

	         if (itemstack1.isEmpty()) {
	            slot.putStack(ItemStack.EMPTY);
	         } else {
	            slot.onSlotChanged();
	         }
	      }

	      return itemstack;
	   }

	   /**
	    * Called when the container is closed.
	    */
	   public void onContainerClosed(PlayerEntity playerIn) {
	      super.onContainerClosed(playerIn);
	      this.inventory.closeInventory(playerIn);
	   }
}
