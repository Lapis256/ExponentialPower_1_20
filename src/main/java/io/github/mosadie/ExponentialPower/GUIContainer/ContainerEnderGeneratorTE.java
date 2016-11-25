package io.github.mosadie.ExponentialPower.GUIContainer;

import io.github.mosadie.ExponentialPower.TileEntitys.EnderGeneratorTE;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerEnderGeneratorTE extends Container {
	
	private EnderGeneratorTE te;

    public ContainerEnderGeneratorTE(IInventory playerInv, EnderGeneratorTE te) {
        this.te = te;
        
        // Tile Entity, Slot 0, Slot IDs 0 
                this.addSlotToContainer(new Slot(te, 0, 80, 35));

        // Player Inventory, Slot 9-35, Slot IDs 1-24
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
            }
        }

        // Player Inventory, Slot 0-8, Slot IDs 25-36
        for (int x = 0; x < 9; ++x) {
            this.addSlotToContainer(new Slot(playerInv, x, 8 + x * 18, 142));
        }
    }
    
    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
        ItemStack previous = null;
        Slot slot = (Slot) this.inventorySlots.get(fromSlot);

        if (slot != null && slot.getHasStack()) {
            ItemStack current = slot.getStack();
            previous = current.copy();

            if (fromSlot == 0) {
                // From TE Inventory to Player Inventory
            	if (current.stackSize == 1) return null;
            	current.stackSize -= 1;
            	if (current.stackSize <= 0) return null;
                if (!this.mergeItemStack(current, 1, 36, true))
                    return null;
            } else {
                // From Player Inventory to TE Inventory
                if (!this.mergeItemStack(current, 0, 1, false))
                    return null;
            }

            if (current.stackSize == 0)
                slot.putStack((ItemStack) null);
            else
                slot.onSlotChanged();

            if (current.stackSize == previous.stackSize)
                return null;
            slot.onPickupFromSlot(playerIn, current);
        }
        return previous;
    }
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.te.isUseableByPlayer(playerIn);
	}

}
