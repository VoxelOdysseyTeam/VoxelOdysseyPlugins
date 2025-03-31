package com.guy7cc.voxelodyssey.core.property;

import org.jetbrains.annotations.NotNull;

public interface State<T, S extends State<T, S>> {
    T getOwner();

    boolean containsProperty(@NotNull Property<?> property);

    <U> U getProperty(@NotNull Property<U> property);

    <U> S setProperty(@NotNull Property<U> property, U value);

    default <U> S setDefaultProperty(@NotNull Property<U> property) {
        return setProperty(property, property.defaultValue());
    }
}
