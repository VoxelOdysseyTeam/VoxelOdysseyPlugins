package com.guy7cc.voxelodyssey.core.region;

import org.bukkit.util.Vector;

import java.util.Objects;

public final class AABB implements RegionShape {
    private final Vector min;
    private final Vector max;

    public AABB(Vector v0, Vector v1){
        min = new Vector(Math.min(v0.getX(), v1.getX()), Math.min(v0.getY(), v1.getY()), Math.min(v0.getZ(), v1.getZ()));
        max = new Vector(Math.max(v0.getX(), v1.getX()), Math.max(v0.getY(), v1.getY()), Math.max(v0.getZ(), v1.getZ()));
    }

    public Vector[] getAllVertices(){
        Vector[] array = new Vector[8];
        for(int i = 0; i < 8; i++){
            Vector v = new Vector();
            v.setX(i % 2 == 0 ? min.getX() : max.getX());
            v.setY((i >> 1) % 2 == 0 ? min.getY() : max.getY());
            v.setZ((i >> 2) % 2 == 0 ? min.getZ() : max.getZ());
            array[i] = v;
        }
        return array;
    }

    @Override
    public boolean contains(Vector v) {
        return v.isInAABB(min, max);
    }

    @Override
    public boolean contains(RegionShape shape) {
        if(shape instanceof AABB other){
            return contains(other.min) && contains(other.max);
        } else if(shape instanceof Sphere sphere){
            Vector c = sphere.getCenter();
            double r = sphere.getRadius();
            return min.getX() <= c.getX() - r && c.getX() + r <= max.getX()
                    && min.getY() <= c.getY() - r && c.getY() + r <= max.getY()
                    && min.getZ() <= c.getZ() - r && c.getZ() + r <= max.getZ();
        } else return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(min, max);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof AABB aabb))
            return false;
        return min.equals(aabb.min) && max.equals(aabb.max);
    }

    @Override
    public String toString() {
        return "AABB{min=(" + min.toString() + "),max=(" + max.toString() + ")}";
    }
}
