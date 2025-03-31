package com.guy7cc.voxelodyssey.core.command;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class LiteralArg implements CommandArg<String> {
    private final String name;
    private final boolean anyMatches;
    private final Supplier<Set<String>> options;

    public LiteralArg(String name){
        this.name = name;
        this.anyMatches = true;
        this.options = Set::of;
    }

    public LiteralArg(String name, String option){
        this(name, Set.of(option));
    }

    public LiteralArg(String name, Set<String> options){
        this(name, () -> options);
    }

    public LiteralArg(String name, Supplier<Set<String>> options) {
        this.name = name;
        this.anyMatches = false;
        this.options = options;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean matches(String arg) {
        return anyMatches || options.get().contains(arg);
    }

    @Override
    public String get(String arg) {
        return arg;
    }

    @Override
    @Nullable
    public List<String> onTabComplete(String arg) {
        if(anyMatches) return List.of();
        List<String> completion = new ArrayList<>();
        org.bukkit.util.StringUtil.copyPartialMatches(arg, options.get(), completion);
        return completion;
    }
}
