package com.guy7cc.voxelodyssey.core.property;

import com.guy7cc.voxelodyssey.core.registry.AbstractRegistryObject;
import com.guy7cc.voxelodyssey.core.registry.Key;

public abstract class AbstractProperty<T> extends AbstractRegistryObject implements Property<T> {
    private final T defaultValue;

    public AbstractProperty(Key key, T defaultValue) {
        super(key);
        this.defaultValue = defaultValue;
    }

    public final T defaultValue() {
        return defaultValue;
    }
}
