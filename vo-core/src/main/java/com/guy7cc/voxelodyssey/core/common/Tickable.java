/*
 * Copyright (C) 2025 TeamVoxelOdyssey
 *
 * This file is part of VoxelOdysseyPlugins.
 *
 * VoxelOdysseyPlugins is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * VoxelOdysseyPlugins is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with VoxelOdysseyPlugins. If not, see <https://www.gnu.org/licenses/>.
 */
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
