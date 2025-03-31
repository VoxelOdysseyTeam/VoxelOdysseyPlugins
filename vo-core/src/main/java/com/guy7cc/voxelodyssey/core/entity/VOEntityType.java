package com.guy7cc.voxelodyssey.core.entity;

import com.guy7cc.voxelodyssey.core.registry.AbstractRegistryObject;
import com.guy7cc.voxelodyssey.core.registry.Key;
import org.bukkit.entity.Entity;

public abstract class VOEntityType<T extends VOEntity<?>, S extends VOEntityFactoryArgs> extends AbstractRegistryObject {
    public VOEntityType(Key key) {
        super(key);
    }

    public abstract T create(S args);

    public abstract T wrap(Entity entity, S args);
}
