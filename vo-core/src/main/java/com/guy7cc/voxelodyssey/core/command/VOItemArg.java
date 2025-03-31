package com.guy7cc.voxelodyssey.core.command;

import com.guy7cc.voxelodyssey.core.item.VOCoreItems;
import com.guy7cc.voxelodyssey.core.item.VOItem;
import com.guy7cc.voxelodyssey.core.registry.Key;
import com.guy7cc.voxelodyssey.core.util.StringUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class VOItemArg implements CommandArg<VOItem> {
    private final String name;

    public VOItemArg(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean matches(String arg) {
        Set<String> keySet = VOCoreItems.REGISTRY.keySet().stream().map(Key::toString).collect(Collectors.toSet());
        return keySet.contains(arg);
    }

    @Override
    public VOItem get(String arg) {
        return VOCoreItems.REGISTRY.get(Key.fromString(arg));
    }

    @Override
    public @Nullable List<String> onTabComplete(String arg) {
        return StringUtil.getOptions(arg, VOCoreItems.REGISTRY.keySet().stream().map(Key::toString).collect(Collectors.toSet()));
    }
}
