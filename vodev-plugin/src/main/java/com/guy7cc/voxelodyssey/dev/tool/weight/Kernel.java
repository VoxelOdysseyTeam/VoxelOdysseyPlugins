package com.guy7cc.voxelodyssey.dev.tool.weight;

public interface Kernel<T> {
    T get(int i, int j);

    int getDiameter();
}
