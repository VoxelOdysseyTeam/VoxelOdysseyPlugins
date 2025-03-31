package com.guy7cc.voxelodyssey.core.property;

import com.guy7cc.voxelodyssey.core.VoxelOdysseyCore;
import com.guy7cc.voxelodyssey.core.common.Copyable;
import com.guy7cc.voxelodyssey.core.util.LogUtil;

import java.util.function.Function;

public class ImmutableValueHolder<T> implements Copyable<ImmutableValueHolder<T>> {
    public T value;

    private ImmutableValueHolder(T value) {
        this.value = value;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ImmutableValueHolder<T> clone() {
        try {
            return (ImmutableValueHolder<T>) super.clone();
        } catch (CloneNotSupportedException exception) {
            LogUtil.exception(VoxelOdysseyCore.getLogger(), exception);
        }
        return null;
    }

    public static <U> ImmutableValueHolder<U> of(U value) {
        return new ImmutableValueHolder<>(value);
    }

    public void pass(Function<T, T> func) {
        value = func.apply(value);
    }
}
