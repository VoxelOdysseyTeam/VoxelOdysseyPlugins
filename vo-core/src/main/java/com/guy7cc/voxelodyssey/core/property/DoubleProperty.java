package com.guy7cc.voxelodyssey.core.property;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.guy7cc.voxelodyssey.core.data.DataFormatException;
import com.guy7cc.voxelodyssey.core.registry.Key;

public class DoubleProperty extends AbstractProperty<Double> {
    public DoubleProperty(Key key, Double defaultValue) {
        super(key, defaultValue);
    }

    @Override
    public JsonElement parseJson(Double value) {
        return new JsonPrimitive(value);
    }

    @Override
    public Double parseValue(JsonElement j) throws DataFormatException {
        try {
            return j.getAsDouble();
        } catch (Exception exception) {
            throw new DataFormatException(getClass(), exception);
        }
    }
}
