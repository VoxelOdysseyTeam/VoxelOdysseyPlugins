package com.guy7cc.voxelodyssey.core.region;

import org.bukkit.util.Vector;

public interface RegionShape {
    boolean contains(Vector v);

    boolean contains(RegionShape shape);
}
