package com.guy7cc.voxelodyssey.core.command;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class RangedIntegerArg implements CommandArg<Integer> {
    private final String name;
    private final int min;
    private final int max;

    public RangedIntegerArg(String name, int min, int max) {
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
            int value = Integer.parseInt(arg);
            return min <= value && value <= max;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public Integer get(String arg) {
        try {
            int value = Integer.parseInt(arg);
            if (min <= value && value <= max) return value;
            return 0;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(String arg) {
        if (arg.isEmpty()) {
            return List.of(String.valueOf(min), String.valueOf(max));
        } else return List.of();
    }
}
