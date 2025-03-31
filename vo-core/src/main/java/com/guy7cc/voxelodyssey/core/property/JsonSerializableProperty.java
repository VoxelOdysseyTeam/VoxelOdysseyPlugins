package com.guy7cc.voxelodyssey.core.property;

import com.google.gson.JsonElement;
import com.guy7cc.voxelodyssey.core.registry.AbstractRegistryObject;
import com.guy7cc.voxelodyssey.core.data.JsonSerializable;
import com.guy7cc.voxelodyssey.core.data.DataFormatException;
import com.guy7cc.voxelodyssey.core.registry.Key;

import java.util.function.Supplier;

public class JsonSerializableProperty<T extends JsonSerializable<T>> extends AbstractRegistryObject implements Property<T> {
    private final Supplier<T> supplier;

    public JsonSerializableProperty(Key key, Supplier<T> supplier) {
        super(key);
        this.supplier = supplier;
    }

    @Override
    public T defaultValue() {
        T value = supplier.get();
        value.initialize();
        return value;
    }

    @Override
    public JsonElement parseJson(T value) {
        return value.toJson();
    }

    @Override
    public T parseValue(JsonElement j) throws DataFormatException {
        try {
            T value = supplier.get();
            value.fromJson(j);
            return value;
        } catch (Exception e) {
            throw new DataFormatException(getClass(), e);
        }
    }
}
