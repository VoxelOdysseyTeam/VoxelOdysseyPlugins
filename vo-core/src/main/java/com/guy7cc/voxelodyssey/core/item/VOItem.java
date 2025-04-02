/*
 * Copyright (C) 2025 TeamVoxelOdyssey
 *
 * This file is part of VoxelOdysseyPlugins.
 *
 * VoxelOdysseyPlugins is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * VoxelOdysseyPlugins is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with VoxelOdysseyPlugins. If not, see <https://www.gnu.org/licenses/>.
 */
package com.guy7cc.voxelodyssey.core.item;

import com.guy7cc.voxelodyssey.core.registry.AbstractRegistryObject;
import com.guy7cc.voxelodyssey.core.registry.Key;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class VOItem extends AbstractRegistryObject {
    public final Material material;
    public final int loreLineNum;
    public final VOItemUsage usage;

    protected VOItem(Key key, Material material, int loreLineNum, VOItemUsage usage) {
        super(key);
        this.material = material;
        this.loreLineNum = loreLineNum;
        this.usage = usage;
    }

    public VOItemStackWrapper getDefaultState() {
        ItemStack itemStack = new ItemStack(material);
        VOItemStackWrapper state = new VOItemStackWrapper(itemStack);
        state.setOwner(this);
        state.updateName();
        state.setItemLore();
        state.setModel();
        return state;
    }

    public VOItemInteractionResult use(VOItemStackWrapper state, Player player, EquipmentSlot slot, Action action) {
        return VOItemInteractionResult.UNAVAILABLE;
    }

    public VOItemInteractionResult useOnEntity(VOItemStackWrapper state, Player player, EquipmentSlot slot, Entity entity) {
        return VOItemInteractionResult.UNAVAILABLE;
    }

    public void onSetToInventory(VOItemStackWrapper state, Player player, int slot) {

    }

    public void tickInEquipmentSlot(VOItemStackWrapper state, Player player, EquipmentSlot slot) {

    }

    public void tickInQuickBar(VOItemStackWrapper state, Player player, int index) {

    }

    public void onInventoryChanged(Player player) {

    }
}
