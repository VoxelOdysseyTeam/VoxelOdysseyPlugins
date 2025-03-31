/**
 * This code is based on the following source. I sincerely appreciate it.
 * https://qiita.com/myui/items/6597087d6a0648ca1dec
 */
package com.guy7cc.voxelodyssey.core.common;

import java.util.function.Consumer;

@FunctionalInterface
public interface ThrowingConsumer<T> extends Consumer<T> {
    @Override
    default void accept(final T e) {
        try {
            accept0(e);
        } catch (Throwable ex) {
            SneakyThrower.sneakyThrow(ex);
        }
    }

    void accept0(T e) throws Throwable;
}
