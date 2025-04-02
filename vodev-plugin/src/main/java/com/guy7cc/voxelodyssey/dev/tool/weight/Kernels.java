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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class Kernels {
    private static final Map<String, Function<Integer, Kernel<Float>>> kernels = new HashMap<>();

    static {
        kernels.put("gauss0.5", size -> new GaussianDistribution(size, 0.5f));
        kernels.put("gauss", size -> new GaussianDistribution(size, 1));
        kernels.put("gauss3", size -> new GaussianDistribution(size, 3));
        kernels.put("average", size -> new MovingAverage(size));
    }

    public static Set<String> names() {
        return kernels.keySet();
    }

    public static Function<Integer, Kernel<Float>> get(String name) {
        return kernels.get(name);
    }
}
