package com.guy7cc.voxelodyssey.core.data;

import com.google.gson.JsonElement;

public interface JsonSerializable<T> {
    T initialize();

    JsonElement toJson();

    T fromJson(JsonElement j) throws DataFormatException;
}
