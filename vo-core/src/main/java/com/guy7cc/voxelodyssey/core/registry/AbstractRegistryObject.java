package com.guy7cc.voxelodyssey.core.registry;

public abstract class AbstractRegistryObject implements RegistryObject {
    private final Key key;

    public AbstractRegistryObject(Key key) {
        this.key = key;
    }

    @Override
    public Key getKey() {
        return key;
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || this.getClass() != obj.getClass())
            return false;
        AbstractRegistryObject other = (AbstractRegistryObject) obj;
        return this.key.equals(other.key);
    }
}
