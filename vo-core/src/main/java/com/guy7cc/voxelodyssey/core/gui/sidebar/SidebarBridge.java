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
package com.guy7cc.voxelodyssey.core.gui.sidebar;

import com.guy7cc.voxelodyssey.core.common.Tickable;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.Collection;

public interface SidebarBridge extends Tickable {
    @Override
    default void tick(int globalTick){
        Tickable.super.tick(globalTick);
        getComponents().removeIf(SidebarComponent::shouldBeRemoved);
    }

    @Override
    default Collection<? extends Tickable> getTickables() {
        return getComponents();
    }

    Player getPlayer();

    Collection<SidebarComponent> getComponents();

    void addComponent(SidebarComponent component);

    void setComponent(SidebarComponent component, int index);

    void removeComponent(SidebarComponent component);

    void refreshComponent(SidebarComponent component);

    default void refreshAll() {
        getComponents().forEach(this::refreshComponent);
    }

    void setDisplay(Component display);

    void setVisible(Boolean visible);

    void dispose();
}
