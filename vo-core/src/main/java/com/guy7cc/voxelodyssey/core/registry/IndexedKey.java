package com.guy7cc.voxelodyssey.core.registry;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.guy7cc.voxelodyssey.core.data.JsonSerializable;
import com.guy7cc.voxelodyssey.core.data.DataFormatException;

import java.util.Objects;

public class IndexedKey implements JsonSerializable<IndexedKey> {
    public static final Key KEY_INVENTORY = Key.vo("inventory");
    public static final Key KEY_ITEM = Key.vo("item");
    public static final Key KEY_POTENTIAL = Key.vo("potential");

    private Key key;
    private int index;

    protected IndexedKey() {
        initialize();
    }

    protected IndexedKey(Key key, int index) {
        this.key = key;
        this.index = index;
    }

    public static IndexedKey none() {
        return new IndexedKey();
    }

    public static IndexedKey fromInventory(int slot) {
        return new IndexedKey(KEY_INVENTORY, slot);
    }

    public static IndexedKey fromItem() {
        return new IndexedKey(KEY_ITEM, 0);
    }

    public static IndexedKey potential() {
        return new IndexedKey(KEY_POTENTIAL, 0);
    }

    public Key getKey() {
        return key;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, index);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof IndexedKey other)) return false;
        return key.equals(other.key) && index == other.index;
    }

    @Override
    public String toString() {
        return "IndexedKey{key='" + key.toString() + "', index=" + index + "}";
    }

    @Override
    public IndexedKey initialize() {
        key = Key.vovoid();
        index = 0;
        return this;
    }

    @Override
    public JsonElement toJson() {
        JsonObject j = new JsonObject();
        j.add("key", key.toJson());
        j.addProperty("index", index);
        return j;
    }

    @Override
    public IndexedKey fromJson(JsonElement j) throws DataFormatException {
        try {
            JsonObject obj = j.getAsJsonObject();
            key = new Key().fromJson(obj.get("key"));
            index = obj.get("index").getAsInt();
            return this;
        } catch (Exception e) {
            throw new DataFormatException(getClass(), e);
        }
    }
}
