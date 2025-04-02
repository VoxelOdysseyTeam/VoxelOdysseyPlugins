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

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SimpleSidebarComponent implements SidebarComponent {
    private int lastTick = -1;
    private int score;
    private String text = "";
    private final Set<SidebarBridge> containers = new HashSet<>();

    @Override
    public void tick(int globalTick) {
        if (globalTick > lastTick) internalTick(globalTick);
        lastTick = globalTick;
    }

    protected void internalTick(int globalTick) {

    }

    @Override
    public Collection<SidebarBridge> getContainers() {
        return Collections.unmodifiableCollection(containers);
    }

    @Override
    public void addContainer(SidebarBridge bridge) {
        containers.add(bridge);
    }

    @Override
    public void removeContainer(SidebarBridge bridge) {
        containers.remove(bridge);
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        if (!this.text.equals(text)) {
            refresh();
        }
        this.text = text;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void setScore(int score) {
        if (this.score != score) {
            refresh();
        }
        this.score = score;
    }

    @Override
    public boolean shouldBeRemoved() {
        return false;
    }
}
