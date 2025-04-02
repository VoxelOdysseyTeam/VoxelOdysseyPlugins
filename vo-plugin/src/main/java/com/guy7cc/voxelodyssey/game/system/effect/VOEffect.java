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
package com.guy7cc.voxelodyssey.game.system.effect;

import com.guy7cc.voxelodyssey.core.common.Copyable;
import com.guy7cc.voxelodyssey.core.registry.AbstractRegistryObject;
import com.guy7cc.voxelodyssey.core.registry.Key;

import java.util.Optional;

public class VOEffect<T extends Copyable<T>> extends AbstractRegistryObject {
    public VOEffect(Key key) {
        super(key);
    }

    public VOEffectState getDefaultEffectState() {
        return new VOEffectState(this);
    }

    public VOModifierState getDefaultModifierState() {
        return new VOModifierState(this);
    }

    public Optional<T> onEffectAdded(VOEffectPipeline.States states, VOEffectState added, VOEffectApplicable<?> receiver) {
        return Optional.empty();
    }

    public void passOnEffectAdded(VOEffectPipeline.States states, T value, T baseValue, VOModifierState state, VOEffectApplicable<?> receiver) {

    }

    public void applyOnEffectAdded(VOEffectPipeline.States states, T finalValue, VOEffectApplicable<?> receiver) {

    }

    public Optional<T> onModifierAdded(VOEffectPipeline.States states, VOModifierState added, VOEffectApplicable<?> receiver) {
        return Optional.empty();
    }

    public void passOnModifierAdded(VOEffectPipeline.States states, T value, T baseValue, VOModifierState state, VOEffectApplicable<?> receiver) {

    }

    public void applyOnModifierAdded(VOEffectPipeline.States states, T finalValue, VOEffectApplicable<?> receiver) {

    }

    public Optional<T> onTick(VOEffectPipeline.States states, VOEffectApplicable<?> receiver, int tick) {
        return Optional.empty();
    }

    public void passOnTick(VOEffectPipeline.States states, T value, T baseValue, VOModifierState state, VOEffectApplicable<?> receiver, int tick) {

    }

    public void applyOnTick(VOEffectPipeline.States states, T finalValue, VOEffectApplicable<?> receiver, int tick) {

    }
}
