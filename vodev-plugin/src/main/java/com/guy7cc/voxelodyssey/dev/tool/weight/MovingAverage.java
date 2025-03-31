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
