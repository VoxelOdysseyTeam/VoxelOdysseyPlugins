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
package com.guy7cc.voxelodyssey.dev.tool.weight;

public class MovingAverage implements Kernel<Float> {
    private final int diameter;

    public MovingAverage(int size) {
        if (size <= 0) throw new IllegalArgumentException("HeightMap line num must be more than 0");
        this.diameter = 2 * size + 1;
    }

    @Override
    public Float get(int i, int j) {
        return 1f / (diameter * diameter);
    }

    @Override
    public int getDiameter() {
        return diameter;
    }
}
