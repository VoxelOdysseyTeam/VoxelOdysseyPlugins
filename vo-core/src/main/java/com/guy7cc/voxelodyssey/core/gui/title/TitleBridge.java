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
package com.guy7cc.voxelodyssey.core.gui.title;

import com.guy7cc.voxelodyssey.core.common.Tickable;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.entity.Player;

import java.util.Collection;

public interface TitleBridge extends Tickable {
    @Override
    default void tick(int globalTick) {
        Tickable.super.tick(globalTick);
        Collection<TitleComponent> components = getComponents();
        Component component = Component.empty();
        for (var titleComponent : getComponents()) {
            component = component.append(titleComponent.getPaperComponent());
        }
        getPlayer().showTitle(Title.title(component, Component.empty()));
        components.removeIf(TitleComponent::shouldBeRemoved);
    }

    default Collection<? extends Tickable> getTickables() {
        return getComponents();
    }

    Player getPlayer();

    Collection<TitleComponent> getComponents();

    void addComponent(TitleComponent component);

    void removeComponent(TitleComponent component);

    void setVisible(boolean visible);
}
