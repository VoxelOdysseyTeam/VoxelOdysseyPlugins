package com.guy7cc.voxelodyssey.core.region;

import com.guy7cc.voxelodyssey.core.common.Tickable;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.Map;

public class RegionManager implements Tickable, Listener {
    private Map<String, RegionGraph> graphs = new HashMap<>();
    private Map<Player, RegionContext> contexts = new HashMap<>();

    public void add(String world, RegionShape shape, RegionHandler handler){
        graphs.computeIfAbsent(world, w -> new RegionGraph()).addHandler(shape, handler);
    }
}
