package com.guy7cc.voxelodyssey.dev.tool.weight;

public class KernelImpl<T> implements Kernel<T> {
    protected final int size;
    protected final int diameter;
    protected final Object[][] map;

    public KernelImpl(int size, T defaultValue) {
        if (size <= 0) throw new IllegalArgumentException("Kernel size must be more than 0");
        this.size = size;
        this.diameter = 2 * size + 1;
        this.map = new Object[diameter][];
        for (int i = 0; i < diameter; i++) {
            this.map[i] = new Object[diameter];
            for (int j = 0; j < diameter; j++) {
                this.map[i][j] = defaultValue;
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int i, int j) {
        return (T) this.map[Math.clamp(i, -size, size) + size][Math.clamp(j, -size, size) + size];
    }

    @Override
    public int getDiameter() {
        return diameter;
    }

    public void set(int i, int j, T value) {
        this.map[Math.clamp(i, -size, size) + size][Math.clamp(j, -size, size) + size] = value;
    }
}
