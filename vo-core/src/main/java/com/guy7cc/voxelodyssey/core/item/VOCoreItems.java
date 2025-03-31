package com.guy7cc.voxelodyssey.core.item;

import com.guy7cc.voxelodyssey.core.VOCorePlugin;
import com.guy7cc.voxelodyssey.core.registry.Registry;

public class VOCoreItems {
    public static final Registry<VOItem> REGISTRY;

    static {
        REGISTRY = new Registry<>(VOCorePlugin::getPlugin);
    }

    private VOCoreItems(){

    }

    private static VOItem register(VOItem object) {
        REGISTRY.register(object);
        return object;
    }
}
