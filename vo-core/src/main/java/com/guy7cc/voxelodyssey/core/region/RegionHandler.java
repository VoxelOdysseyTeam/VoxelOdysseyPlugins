package com.guy7cc.voxelodyssey.core.region;

import com.guy7cc.voxelodyssey.core.entity.player.VOPlayer;
import org.bukkit.event.Listener;

import java.util.Collection;

public interface RegionHandler extends Listener {
    void onEnter(VOPlayer voplayer);

    void onStay(int globalTick, Collection<VOPlayer> voplayers);

    void onExit(VOPlayer voplayer);
}
