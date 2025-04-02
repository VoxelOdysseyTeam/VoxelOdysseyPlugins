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
package com.guy7cc.voxelodyssey.core.property;

import com.guy7cc.voxelodyssey.core.VoxelOdysseyCore;
import com.guy7cc.voxelodyssey.core.common.Copyable;
import com.guy7cc.voxelodyssey.core.util.LogUtil;

import java.util.function.Function;

public class ImmutableValueHolder<T> implements Copyable<ImmutableValueHolder<T>> {
    public T value;

    private ImmutableValueHolder(T value) {
        this.value = value;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ImmutableValueHolder<T> clone() {
        try {
            return (ImmutableValueHolder<T>) super.clone();
        } catch (CloneNotSupportedException exception) {
            LogUtil.exception(VoxelOdysseyCore.getLogger(), exception);
        }
        return null;
    }

    public static <U> ImmutableValueHolder<U> of(U value) {
        return new ImmutableValueHolder<>(value);
    }

    public void pass(Function<T, T> func) {
        value = func.apply(value);
    }
}
