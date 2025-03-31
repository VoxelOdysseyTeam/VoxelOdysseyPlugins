package com.guy7cc.voxelodyssey.core.item;

import java.util.Locale;

public enum VOItemUsage {
    NONE,
    LEFT_CLICK,
    RIGHT_CLICK,
    LEFT_CLICK_ON_ENTITY,
    RIGHT_CLICK_ON_ENTITY,
    ONE_IN_INVENTORY,
    SOME_IN_INVENTORY;

    public String getKey() {
        return toString().toLowerCase(Locale.ROOT);
    }
}
