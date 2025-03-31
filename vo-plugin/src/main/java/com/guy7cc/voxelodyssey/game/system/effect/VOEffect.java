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
