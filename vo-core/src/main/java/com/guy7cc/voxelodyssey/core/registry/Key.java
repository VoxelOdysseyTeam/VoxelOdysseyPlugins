package com.guy7cc.voxelodyssey.core.registry;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.guy7cc.voxelodyssey.core.common.Copyable;
import com.guy7cc.voxelodyssey.core.VoxelOdysseyCore;
import com.guy7cc.voxelodyssey.core.data.DataFormatException;
import com.guy7cc.voxelodyssey.core.data.JsonSerializable;
import com.guy7cc.voxelodyssey.core.util.LogUtil;
import org.bukkit.NamespacedKey;

import java.util.Objects;

public final class Key implements Copyable<Key>, JsonSerializable<Key> {
    private static final NamespacedKey DEFAULT = new NamespacedKey(VoxelOdysseyCore.NAMESPACE, "default");

    private NamespacedKey handle;

    private Key(NamespacedKey handle){
        this.handle = handle;
    }

    public Key(){
        this(DEFAULT);
    }

    public Key(String namespace, String key){
        this(new NamespacedKey(namespace, key));
    }

    public static Key fromString(String s){
        return new Key(NamespacedKey.fromString(s));
    }

    public static Key vo(String key){
        return new Key(VoxelOdysseyCore.NAMESPACE, key);
    }

    public static Key vodefault(){
        return new Key(DEFAULT);
    }

    public static Key vovoid(){
        return new Key(VoxelOdysseyCore.NAMESPACE, "void");
    }

    public String namespace(){
        return this.handle.getNamespace();
    }

    public String key(){
        return this.handle.getKey();
    }

    @Override
    public int hashCode() {
        return this.handle.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || this.getClass() != obj.getClass()) return false;
        Key other = (Key) obj;
        return Objects.equals(this.handle, other.handle);
    }

    @Override
    public Key clone() {
        try {
            Key clone = (Key) super.clone();
            clone.handle = new NamespacedKey(this.handle.getNamespace(), this.handle.getKey());
            return clone;
        } catch (CloneNotSupportedException exception) {
            LogUtil.exception(VoxelOdysseyCore.getLogger(), exception);
        }
        return null;
    }

    @Override
    public String toString() {
        return this.handle.toString();
    }

    @Override
    public Key initialize() {
        this.handle = DEFAULT;
        return this;
    }

    @Override
    public JsonPrimitive toJson() {
        return new JsonPrimitive(toString());
    }

    @Override
    public Key fromJson(JsonElement j) throws DataFormatException {
        try {
            this.handle = NamespacedKey.fromString(j.getAsString());
            return this;
        } catch (Exception e) {
            throw new DataFormatException(getClass(), e);
        }
    }
}
