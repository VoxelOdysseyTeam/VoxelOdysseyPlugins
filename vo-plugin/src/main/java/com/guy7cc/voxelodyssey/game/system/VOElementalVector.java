package com.guy7cc.voxelodyssey.game.system;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.guy7cc.voxelodyssey.core.common.Copyable;
import com.guy7cc.voxelodyssey.core.data.JsonSerializable;
import com.guy7cc.voxelodyssey.core.data.DataFormatException;
import com.guy7cc.voxelodyssey.core.util.LogUtil;
import com.guy7cc.voxelodyssey.game.VOPlugin;

import java.util.Arrays;
import java.util.Map;

public class VOElementalVector implements Copyable<VOElementalVector>, JsonSerializable<VOElementalVector> {
    public static final int LENGTH = VOElement.values().length;

    private double[] array;

    public VOElementalVector() {
        initialize();
    }

    public VOElementalVector(double allValue) {
        this(Map.of(), allValue);
    }

    public VOElementalVector(Map<VOElement, Double> initValue) {
        this(initValue, 0D);
    }

    public VOElementalVector(Map<VOElement, Double> initValue, double allValue) {
        initialize();
        Arrays.fill(array, allValue);
        initValue.forEach((key, value) -> this.array[key.ordinal()] = value);
    }

    public double get(VOElement element) {
        return array[element.ordinal()];
    }

    public double sum() {
        return Arrays.stream(array).sum();
    }

    public void add(VOElementalVector other) {
        for (int i = 0; i < LENGTH; i++) {
            this.array[i] = this.array[i] + other.array[i];
        }
    }

    public void sub(VOElementalVector other) {
        for (int i = 0; i < LENGTH; i++) {
            this.array[i] = this.array[i] - other.array[i];
        }
    }

    public void mul(VOElementalVector other) {
        for (int i = 0; i < LENGTH; i++) {
            this.array[i] = this.array[i] * other.array[i];
        }
    }

    public void div(VOElementalVector other) {
        for (int i = 0; i < LENGTH; i++) {
            this.array[i] = this.array[i] / other.array[i];
        }
    }

    public void max(VOElementalVector other) {
        for (int i = 0; i < LENGTH; i++) {
            this.array[i] = Math.max(this.array[i], other.array[i]);
        }
    }

    public void rectify() {
        for (int i = 0; i < LENGTH; i++) {
            this.array[i] = Math.max(this.array[i], 0d);
        }
    }

    @Override
    public VOElementalVector clone() {
        try {
            VOElementalVector clone = (VOElementalVector) super.clone();
            clone.array = Arrays.copyOf(this.array, LENGTH);
            return clone;
        } catch (CloneNotSupportedException exception) {
            LogUtil.exception(VOPlugin.getPlugin().getLogger(), exception);
        }
        return null;
    }

    @Override
    public VOElementalVector initialize() {
        array = new double[LENGTH];
        return this;
    }

    @Override
    public JsonArray toJson() {
        JsonArray array = new JsonArray();
        for (double value : this.array) {
            array.add(value);
        }
        return array;
    }

    @Override
    public VOElementalVector fromJson(JsonElement j) throws DataFormatException {
        try {
            JsonArray ja = j.getAsJsonArray();
            if (ja.size() != LENGTH) throw new DataFormatException("Cannot parse JsonArray to VOElementalVector because the length is invalid");
            for (int i = 0; i < LENGTH; i++) {
                array[i] = ja.get(i).getAsDouble();
            }
            return this;
        } catch (Exception exception) {
            throw new DataFormatException(getClass(), exception);
        }
    }
}
