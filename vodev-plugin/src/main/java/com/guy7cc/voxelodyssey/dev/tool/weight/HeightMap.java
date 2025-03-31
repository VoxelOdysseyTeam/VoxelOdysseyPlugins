package com.guy7cc.voxelodyssey.dev.tool.weight;

public class HeightMap extends KernelImpl<Float> {
    public HeightMap(int size) {
        super(size, 0f);
    }

    public void add(int i, int j, float value) {
        set(i, j, get(i, j) + value);
    }
}
