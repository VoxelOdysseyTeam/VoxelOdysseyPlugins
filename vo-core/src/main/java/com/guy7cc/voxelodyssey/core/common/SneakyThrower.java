/**
 * This code is based on the following source. I sincerely appreciate it.
 * https://qiita.com/myui/items/6597087d6a0648ca1dec
 */
package com.guy7cc.voxelodyssey.core.common;

import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public final class SneakyThrower {
    private SneakyThrower() {

    }

    @NotNull
    public static <T> Consumer<T> sneaky(@NotNull final ThrowingConsumer<T> consumer) {
        return consumer;
    }

    @SuppressWarnings("unchecked")
    public static <E extends Throwable> void sneakyThrow(@NotNull Throwable throwable) throws E {
        throw (E) throwable;
    }
}
