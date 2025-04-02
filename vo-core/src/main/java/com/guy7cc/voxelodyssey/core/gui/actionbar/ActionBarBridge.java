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
package com.guy7cc.voxelodyssey.core.gui.actionbar;

import com.guy7cc.voxelodyssey.core.common.Tickable;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.SortedSet;

public interface ActionBarBridge extends Tickable {
    @Override
    default void tick(int globalTick) {
        Tickable.super.tick(globalTick);
        SortedSet<ActionBarComponent> components = getComponents();
        if(getVisible()){
            for(var component : getComponents()){
                if(component.shouldSendMessage()){
                    getPlayer().sendMessage(component.getPaperComponent());
                    break;
                }
            }
        }
        components.removeIf(ActionBarComponent::shouldBeRemoved);
    }

    @Override
    default Collection<? extends Tickable> getTickables(){
        return getComponents();
    }

    Player getPlayer();

    SortedSet<ActionBarComponent> getComponents();

    void addComponent(ActionBarComponent component);

    void removeComponent(ActionBarComponent component);

    boolean getVisible();

    void setVisible(boolean visible);
}
