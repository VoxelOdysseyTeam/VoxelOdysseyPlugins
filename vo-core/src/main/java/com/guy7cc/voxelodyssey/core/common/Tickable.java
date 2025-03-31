package com.guy7cc.voxelodyssey.core.common;

import java.util.Collection;
import java.util.List;

public interface Tickable {
    default void tick(int globalTick){
        for(Tickable child : getTickables()){
            child.tick(globalTick);
        }
    }

    default Collection<? extends Tickable> getTickables(){
        return List.of();
    }

    interface Generic<T> {
        default void tick(int globalTick, T arg){
            for(Generic<T> child : getChildren()){
                child.tick(globalTick, arg);
            }
        }

        default Collection<Generic<T>> getChildren(){
            return List.of();
        }
    }
}
