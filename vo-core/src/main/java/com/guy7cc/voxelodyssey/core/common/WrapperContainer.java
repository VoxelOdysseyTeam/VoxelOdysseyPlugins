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
import java.util.HashMap;
import java.util.Map;

public abstract class WrapperContainer<T, W extends Wrapper<? extends T>> {
    private final Map<Object, W> wrapperMap;

    public WrapperContainer(int initialCapacity){
        wrapperMap = new HashMap<>(initialCapacity);
    }

    protected abstract Object createContainerKey(T handle);

    public W get(T handle){
        return wrapperMap.get(createContainerKey(handle));
    }

    public Collection<W> values(){
        return wrapperMap.values();
    }

    protected W put(T handle, W wrapper){
        return wrapperMap.put(createContainerKey(handle), wrapper);
    }

    protected W remove(T handle){
        return wrapperMap.remove(createContainerKey(handle));
    }
}
