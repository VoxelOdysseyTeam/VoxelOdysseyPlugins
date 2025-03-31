package com.guy7cc.voxelodyssey.core.entity;

import com.guy7cc.voxelodyssey.core.common.Tickable;
import com.guy7cc.voxelodyssey.core.common.Wrapper;
import org.bukkit.entity.Entity;
import org.bukkit.event.Listener;

public interface VOEntity<T extends Entity> extends Wrapper<T>, Tickable, Listener {
    default boolean shouldBeRemoved(){
        return false;
    }
}
