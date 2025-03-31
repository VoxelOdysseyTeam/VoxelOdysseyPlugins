package com.guy7cc.voxelodyssey.core.command;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class RangedLongArg implements CommandArg<Long> {
    private final String name;
    private final long min;
    private final long max;

    public RangedLongArg(String name, long min, long max) {
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
    public Long get(String arg) {
        try {
            long value = Long.parseLong(arg);
            if (min <= value && value <= max) return value;
            return 0L;
        } catch (NumberFormatException e) {
            return 0L;
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(String arg) {
        if (arg.isEmpty()) {
            return List.of(String.valueOf(min), String.valueOf(max));
        } else return List.of();
    }
}
