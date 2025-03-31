package com.guy7cc.voxelodyssey.core.command;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RangedFloatArg implements CommandArg<Float> {
    private final String name;
    private final float min;
    private final float max;

    public RangedFloatArg(String name, float min, float max) {
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
            float value = Float.parseFloat(arg);
            return min <= value && value <= max;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public Float get(String arg) {
        try {
            float value = Float.parseFloat(arg);
            if (min <= value && value <= max) return value;
            return 0f;
        } catch (NumberFormatException e) {
            return 0f;
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(String arg) {
        if (arg.isEmpty()) {
            return List.of(String.valueOf(min), String.valueOf(max));
        } else return List.of();
    }
}
