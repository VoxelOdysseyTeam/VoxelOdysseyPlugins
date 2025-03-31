package com.guy7cc.voxelodyssey.game.system.effect;

import com.guy7cc.voxelodyssey.core.property.VOCoreProperties;
import com.guy7cc.voxelodyssey.core.registry.IndexedKey;
import com.guy7cc.voxelodyssey.core.registry.Key;
import com.guy7cc.voxelodyssey.game.property.VOProperties;
import com.guy7cc.voxelodyssey.game.system.VOElementalVector;

import java.util.Optional;

public class FieldDamage extends VOEffect<VOElementalVector> {
    public FieldDamage(Key key) {
        super(key);
    }

    @Override
    public VOEffectState getDefaultEffectState() {
        return getEffectState(new VOElementalVector());
    }

    public VOEffectState getEffectState(VOElementalVector v) {
        return new VOEffectState(this)
                .setProperty(VOProperties.ELEMENT, v);
    }

    @Override
    public Optional<VOElementalVector> onTick(VOEffectPipeline.States states, VOEffectApplicable<?> receiver, int tick) {
        for (var modifier : states.modifiers.stream().filter(m -> m.isFrom(IndexedKey.KEY_POTENTIAL)).toList()) {
            if (modifier.containsProperty(VOCoreProperties.LEFT_TICK)) {
                int leftTick = modifier.getProperty(VOCoreProperties.LEFT_TICK);
                modifier.setProperty(VOCoreProperties.LEFT_TICK, leftTick - 1);
            }
        }
        states.modifiers.removeIf(m ->
                m.isFrom(IndexedKey.KEY_POTENTIAL) && m.containsProperty(VOCoreProperties.LEFT_TICK) && m.getProperty(VOCoreProperties.LEFT_TICK) <= 0
        );
        boolean potentialResistance = states.modifiers.stream().anyMatch(m -> m.isFrom(IndexedKey.KEY_POTENTIAL));
        if (!states.effects.isEmpty()) {
            VOElementalVector v = new VOElementalVector();
            for (VOEffectState state : states.effects) {
                v.max(state.getProperty(VOProperties.ELEMENT));
            }
            states.effects.clear();
            if (!potentialResistance) {
                states.modifiers.add(
                        new VOModifierState(this, IndexedKey.potential(), 100)
                                .setProperty(VOCoreProperties.LEFT_TICK, 20)
                );
                return Optional.of(v);
            } else {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }

    @Override
    public void passOnTick(VOEffectPipeline.States states, VOElementalVector value, VOElementalVector baseValue, VOModifierState state, VOEffectApplicable<?> receiver, int tick) {
        if (state.containsProperty(VOCoreProperties.MUL)) {
            value.mul(state.getProperty(VOProperties.MUL_VECTOR));
        }
        if (state.containsProperty(VOCoreProperties.ADD)) {
            value.add(state.getProperty(VOProperties.ADD_VECTOR));
        }
        value.rectify();
    }

    @Override
    public void applyOnTick(VOEffectPipeline.States states, VOElementalVector finalValue, VOEffectApplicable<?> receiver, int tick) {
        receiver.damage(finalValue.sum());
    }
}
