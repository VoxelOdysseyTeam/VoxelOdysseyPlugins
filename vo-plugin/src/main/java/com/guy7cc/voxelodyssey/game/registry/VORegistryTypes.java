package com.guy7cc.voxelodyssey.game.registry;

import com.guy7cc.voxelodyssey.core.registry.Key;
import com.guy7cc.voxelodyssey.core.registry.RegistryType;
import com.guy7cc.voxelodyssey.game.system.effect.VOEffect;

public class VORegistryTypes {
    public static final RegistryType<VOEffect<?>> EFFECT = new RegistryType<>(Key.vo("effect"));
}
