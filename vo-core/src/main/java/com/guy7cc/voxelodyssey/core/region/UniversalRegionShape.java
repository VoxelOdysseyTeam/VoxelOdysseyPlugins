package com.guy7cc.voxelodyssey.core.region;

import org.bukkit.util.Vector;

public final class UniversalRegionShape implements RegionShape {
    @Override
    public boolean contains(Vector v) {
        return true;
    }

    @Override
    public boolean contains(RegionShape shape) {
        return true;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof UniversalRegionShape;
    }
}
