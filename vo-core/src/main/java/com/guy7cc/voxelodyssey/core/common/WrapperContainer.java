package com.guy7cc.voxelodyssey.core.common;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class WrapperContainer<T, W extends Wrapper<? extends T>> {
    private final Map<Object, W> wrapperMap;

    public WrapperContainer(int initialCapacity){
        wrapperMap = new HashMap<>(initialCapacity);
    }

    protected abstract Object createContainerKey(T handle);

    public W get(T handle){
        return wrapperMap.get(createContainerKey(handle));
    }

    public Collection<W> values(){
        return wrapperMap.values();
    }

    protected W put(T handle, W wrapper){
        return wrapperMap.put(createContainerKey(handle), wrapper);
    }

    protected W remove(T handle){
        return wrapperMap.remove(createContainerKey(handle));
    }
}
