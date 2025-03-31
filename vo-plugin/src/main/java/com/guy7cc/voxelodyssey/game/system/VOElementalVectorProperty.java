package com.guy7cc.voxelodyssey.game.system;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.guy7cc.voxelodyssey.core.data.DataFormatException;
import com.guy7cc.voxelodyssey.core.property.AbstractProperty;
import com.guy7cc.voxelodyssey.core.registry.Key;

public class VOElementalVectorProperty extends AbstractProperty<VOElementalVector> {
    public VOElementalVectorProperty(Key key, VOElementalVector defaultValue) {
        super(key, defaultValue);
    }

    @Override
    public JsonElement parseJson(VOElementalVector value) {
        return value.toJson();
    }

    @Override
    public VOElementalVector parseValue(JsonElement j) throws DataFormatException {
        try {
            VOElementalVector v = new VOElementalVector();
            v.fromJson((JsonArray) j);
            return v;
        } catch (Exception exception) {
            throw new DataFormatException(getClass(), exception);
        }
    }
}
