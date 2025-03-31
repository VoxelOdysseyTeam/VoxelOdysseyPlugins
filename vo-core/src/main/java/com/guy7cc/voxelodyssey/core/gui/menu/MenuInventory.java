package com.guy7cc.voxelodyssey.core.gui.menu;

import com.guy7cc.voxelodyssey.core.common.AbstractWrapper;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class MenuInventory extends AbstractWrapper<Inventory> {
    private MenuInventoryProperty property;

    public MenuInventory(MenuInventoryProperty property) {
        this.property = property;
        handle = Bukkit.createInventory(null, property.lineNum, property.title);
        for (int i = 0; i < property.lineNum * 9; i++) {
            if (property.components[i] != null) {
                handle.setItem(i, property.components[i].getDefaultItemStack());
            }
        }
    }

    public void onInventoryClick(InventoryClickEvent event) {
        if (property.components[event.getSlot()] != null) {
            property.components[event.getSlot()].onInventoryClick(event);
        } else {
            event.setCancelled(true);
        }
    }
}
