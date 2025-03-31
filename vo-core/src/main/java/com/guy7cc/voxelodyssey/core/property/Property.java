package com.guy7cc.voxelodyssey.core.property;

import com.google.gson.JsonElement;
import com.guy7cc.voxelodyssey.core.registry.RegistryObject;
import com.guy7cc.voxelodyssey.core.data.DataFormatException;

public interface Property<T> extends RegistryObject {
    T defaultValue();

    JsonElement parseJson(T value);

    T parseValue(JsonElement j) throws DataFormatException;
}
