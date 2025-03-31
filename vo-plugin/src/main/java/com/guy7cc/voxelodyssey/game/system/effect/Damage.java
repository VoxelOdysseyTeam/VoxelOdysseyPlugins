package com.guy7cc.voxelodyssey.game.system.effect;

import com.guy7cc.voxelodyssey.core.property.ImmutableValueHolder;
import com.guy7cc.voxelodyssey.core.property.VOCoreProperties;
import com.guy7cc.voxelodyssey.core.registry.Key;

import java.util.Optional;

public class Damage extends VOEffect<ImmutableValueHolder<Double>> {
    public Damage(Key key) {
        super(key);
    }

    @Override
    public VOEffectState getDefaultEffectState() {
        return getEffectState(0d);
    }

    public VOEffectState getEffectState(double d) {
        return super.getDefaultEffectState()
                .setProperty(VOCoreProperties.DAMAGE, d);
    }

    @Override
    public Optional<ImmutableValueHolder<Double>> onEffectAdded(VOEffectPipeline.States states, VOEffectState added, VOEffectApplicable<?> receiver) {
        double value = 0d;
        for (var state : states.effects) {
            value += state.getProperty(VOCoreProperties.DAMAGE);
        }
        states.effects.clear();
        return Optional.of(ImmutableValueHolder.of(value));
    }

    @Override
    public void passOnEffectAdded(VOEffectPipeline.States states, ImmutableValueHolder<Double> value, ImmutableValueHolder<Double> baseValue, VOModifierState state, VOEffectApplicable<?> receiver) {
        if (state.containsProperty(VOCoreProperties.MUL)) {
            value.pass(d -> d * state.getProperty(VOCoreProperties.MUL));
        }
        if (state.containsProperty(VOCoreProperties.ADD)) {
            value.pass(d -> d + state.getProperty(VOCoreProperties.ADD));
        }
        value.pass(d -> Math.max(d, 0d));
    }

    @Override
    public void applyOnEffectAdded(VOEffectPipeline.States states, ImmutableValueHolder<Double> finalValue, VOEffectApplicable<?> receiver) {
        receiver.damageSilent(finalValue.value);
    }
}