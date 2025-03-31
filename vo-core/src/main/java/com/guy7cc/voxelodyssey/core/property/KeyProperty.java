package com.guy7cc.voxelodyssey.core.property;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.guy7cc.voxelodyssey.core.data.DataFormatException;
import com.guy7cc.voxelodyssey.core.registry.Key;

public class KeyProperty extends AbstractProperty<Key> {
    public KeyProperty(Key key, Key defaultValue) {
        super(key, defaultValue);
    }

    @Override
    public JsonElement parseJson(Key value) {
        return new JsonPrimitive(value.toString());
    }

    @Override
    public Key parseValue(JsonElement j) throws DataFormatException {
        try {
            return Key.fromString(j.getAsString());
        } catch (Exception exception) {
            throw new DataFormatException(getClass(), exception);
        }
    }
}