package com.guy7cc.voxelodyssey.core.registry;

import java.util.Objects;

public final class RegistryType<T> implements Keyed {
    private final Key key;

    public RegistryType(Key key){
        this.key = key;
    }

    @Override
    public Key getKey(){
        return key;
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        RegistryType<?> that = (RegistryType<?>) obj;
        return Objects.equals(key, that.key);
    }

    @Override
    public String toString() {
        return "RegistryType{key='" + key.toString() + "'}";
    }
}
