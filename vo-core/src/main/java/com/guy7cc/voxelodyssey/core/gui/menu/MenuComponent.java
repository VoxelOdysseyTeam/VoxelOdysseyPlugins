package com.guy7cc.voxelodyssey.core.gui.menu;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public interface MenuComponent {
    ItemStack getDefaultItemStack();

    void onInventoryClick(InventoryClickEvent event);
}
