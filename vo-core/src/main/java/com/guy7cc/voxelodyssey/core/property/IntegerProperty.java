package com.guy7cc.voxelodyssey.core.property;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.guy7cc.voxelodyssey.core.data.DataFormatException;
import com.guy7cc.voxelodyssey.core.registry.Key;

public class IntegerProperty extends AbstractProperty<Integer> {
    public IntegerProperty(Key key, Integer defaultValue) {
        super(key, defaultValue);
    }

    @Override
    public JsonElement parseJson(Integer value) {
        return new JsonPrimitive(value);
    }

    @Override
    public Integer parseValue(JsonElement j) throws DataFormatException {
        try {
            return j.getAsInt();
        } catch (Exception exception) {
            throw new DataFormatException(getClass(), exception);
        }
    }
}
