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

import java.util.Locale;

public enum VOItemUsage {
    NONE,
    LEFT_CLICK,
    RIGHT_CLICK,
    LEFT_CLICK_ON_ENTITY,
    RIGHT_CLICK_ON_ENTITY,
    ONE_IN_INVENTORY,
    SOME_IN_INVENTORY;

    public String getKey() {
        return toString().toLowerCase(Locale.ROOT);
    }
}
