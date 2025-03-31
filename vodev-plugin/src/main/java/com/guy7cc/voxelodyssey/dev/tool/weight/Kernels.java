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
