package com.guy7cc.voxelodyssey.dev.tool.weight;

public class GaussianDistribution implements Kernel<Float> {
    private final int size;
    private final int diameter;
    private final float[][] w;

    public GaussianDistribution(int size, float sigma) {
        if (size <= 0) throw new IllegalArgumentException("HeightMap line num must be more than 0");
        this.size = size;
        this.diameter = 2 * size + 1;

        this.w = new float[diameter][];

        double c = 2 * sigma * sigma;
        float sum = 0;
        for (int i = -size; i <= size; i++) {
            w[i + size] = new float[diameter];
            for (int j = -size; j <= size; j++) {
                float value = (float) (Math.exp(-(i * i + j * j) / c));
                w[i + size][j + size] = value;
                sum += value;
            }
        }

        for (int i = -size; i <= size; i++) {
            for (int j = -size; j <= size; j++) {
                w[i + size][j + size] = w[i + size][j + size] / sum;
            }
        }
    }

    @Override
    public Float get(int i, int j) {
        return this.w[Math.clamp(i, -size, size) + size][Math.clamp(j, -size, size) + size];
    }

    @Override
    public int getDiameter() {
        return diameter;
    }
}
