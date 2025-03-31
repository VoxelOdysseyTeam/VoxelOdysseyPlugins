package com.guy7cc.voxelodyssey.dev.registry;

import com.guy7cc.voxelodyssey.core.entity.VOEntityType;
import com.guy7cc.voxelodyssey.core.registry.Key;
import com.guy7cc.voxelodyssey.core.registry.RegistryType;
import com.guy7cc.voxelodyssey.dev.banner.BannerFactory;

public class VODevRegistryTypes {
    public static final RegistryType<BannerFactory> BANNER = new RegistryType<>(Key.vo("banner"));
}
