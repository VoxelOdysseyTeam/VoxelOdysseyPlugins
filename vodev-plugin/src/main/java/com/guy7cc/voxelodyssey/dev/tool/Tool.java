package com.guy7cc.voxelodyssey.dev.tool;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public abstract class Tool {
    public final String name;

    public Tool(String name) {
        this.name = name;
    }

    public abstract void apply(Player player, Location loc);

}
