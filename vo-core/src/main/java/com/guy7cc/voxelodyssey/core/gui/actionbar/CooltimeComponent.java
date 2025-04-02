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

import com.guy7cc.voxelodyssey.core.item.CoolDown;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

public class CooltimeComponent implements ActionBarComponent {
    private final Player player;
    private final CoolDown coolDown;

    public CooltimeComponent(Player player, CoolDown coolDown){
        this.player = player;
        this.coolDown = coolDown;
    }

    @Override
    public int getRank() {
        return 1;
    }

    @Override
    public Component getPaperComponent() {
        return coolDown.getPaperComponentForActionBar(player);
    }

    @Override
    public boolean shouldSendMessage() {
        return coolDown.shouldSendMessage(player);
    }

    @Override
    public boolean shouldBeRemoved() {
        return false;
    }

    @Override
    public void tick(int globalTick) {

    }
}
