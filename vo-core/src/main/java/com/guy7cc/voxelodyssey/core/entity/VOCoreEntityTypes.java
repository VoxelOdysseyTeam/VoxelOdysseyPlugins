package com.guy7cc.voxelodyssey.core.entity;

import com.guy7cc.voxelodyssey.core.VOCorePlugin;
import com.guy7cc.voxelodyssey.core.entity.player.NoOpVOPlayer;
import com.guy7cc.voxelodyssey.core.entity.player.VOPlayer;
import com.guy7cc.voxelodyssey.core.registry.Registry;

public class VOCoreEntityTypes {
    public static final Registry<VOEntityType<?, ?>> REGISTRY;

    public static final VOEntityType<VOPlayer, VOPlayer.FactoryArgs> VOPLAYER;

    static {
        REGISTRY = new Registry<>(VOCorePlugin::getPlugin);

        VOPLAYER = register(new NoOpVOPlayer.Type());
    }

    private VOCoreEntityTypes(){

    }

    private static <T extends VOEntity<?>, S extends VOEntityFactoryArgs> VOEntityType<T, S> register(VOEntityType<T, S> object) {
        REGISTRY.register(object);
        return object;
    }
}
