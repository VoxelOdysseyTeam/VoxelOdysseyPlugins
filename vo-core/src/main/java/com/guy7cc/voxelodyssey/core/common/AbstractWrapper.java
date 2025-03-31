package com.guy7cc.voxelodyssey.core.common;

public class AbstractWrapper<T> implements Wrapper<T> {
    protected T handle;

    public AbstractWrapper() {

    }

    public AbstractWrapper(T handle) {
        this.handle = handle;
    }

    @Override
    public T getHandle() {
        return handle;
    }
}
