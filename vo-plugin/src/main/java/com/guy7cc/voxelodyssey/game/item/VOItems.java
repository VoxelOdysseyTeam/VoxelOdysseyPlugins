package com.guy7cc.voxelodyssey.game.item;

import com.guy7cc.voxelodyssey.core.item.VOCoreItems;
import com.guy7cc.voxelodyssey.core.item.VOItem;

public class VOItems {

    private VOItems() {

    }

    private static <T extends VOItem> T register(T item) {
        VOCoreItems.REGISTRY.register(item);
        return item;
    }
}
