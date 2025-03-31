package com.guy7cc.voxelodyssey.core.command;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BooleanArg implements CommandArg<Boolean> {
    private final String name;

    public BooleanArg(String name){
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean matches(String arg) {
        return arg.equalsIgnoreCase("true") || arg.equalsIgnoreCase("false");
    }

    @Override
    public Boolean get(String arg) {
        return arg.equalsIgnoreCase("true");
    }

    @Override
    public @Nullable List<String> onTabComplete(String arg) {
        List<String> completion = new ArrayList<>();
        org.bukkit.util.StringUtil.copyPartialMatches(arg, List.of("true", "false"), completion);
        return completion;
    }
}
