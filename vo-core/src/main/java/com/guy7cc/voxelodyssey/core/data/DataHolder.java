package com.guy7cc.voxelodyssey.core.data;

import com.google.gson.JsonObject;

public interface DataHolder {
    String getHolderName();

    void load(JsonObject data);

    JsonObject getWrittenData();
}
