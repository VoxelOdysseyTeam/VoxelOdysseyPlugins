package com.guy7cc.voxelodyssey.core.property;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.guy7cc.voxelodyssey.core.data.DataFormatException;
import com.guy7cc.voxelodyssey.core.registry.Key;
import org.bukkit.NamespacedKey;

import java.util.UUID;

public class UuidProperty implements Property<UUID> {
    private final Key key;

    public UuidProperty(Key key) {
        this.key = key;
    }

    @Override
    public Key getKey() {
        return key;
    }

    @Override
    public UUID defaultValue() {
        return UUID.randomUUID();
    }

    @Override
    public JsonElement parseJson(UUID value) {
        return new JsonPrimitive(value.toString());
    }

    @Override
    public UUID parseValue(JsonElement j) throws DataFormatException {
        try {
            return UUID.fromString(j.getAsString());
        } catch (Exception e) {
            throw new DataFormatException(getClass(), e);
        }
    }
}
