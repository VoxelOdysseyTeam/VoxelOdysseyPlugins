package com.guy7cc.voxelodyssey.core.command;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RangedDoubleArg implements CommandArg<Double> {
    private final String name;
    private final double min;
    private final double max;

    public RangedDoubleArg(String name, double min, double max) {
        this.name = name;
        this.min = min;
        this.max = max;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean matches(String arg) {
        try {
            double value = Double.parseDouble(arg);
            return min <= value && value <= max;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public Double get(String arg) {
        try {
            double value = Double.parseDouble(arg);
            if (min <= value && value <= max) return value;
            return 0d;
        } catch (NumberFormatException e) {
            return 0d;
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(String arg) {
        if (arg.isEmpty()) {
            return List.of(String.valueOf(min), String.valueOf(max));
        } else return List.of();
    }
}
