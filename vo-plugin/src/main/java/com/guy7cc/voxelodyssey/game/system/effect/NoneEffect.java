package com.guy7cc.voxelodyssey.game.system.effect;

import com.guy7cc.voxelodyssey.core.common.Copyable;
import com.guy7cc.voxelodyssey.core.registry.Key;
import com.guy7cc.voxelodyssey.core.util.LogUtil;
import com.guy7cc.voxelodyssey.game.VOPlugin;

import java.util.Optional;

public class NoneEffect extends VOEffect<NoneEffect.None> {
    public NoneEffect() {
        super(Key.vo("none"));
    }

    @Override
    public Optional<NoneEffect.None> onEffectAdded(VOEffectPipeline.States states, VOEffectState added, VOEffectApplicable<?> receiver) {
        states.effects.clear();
        return Optional.empty();
    }

    @Override
    public Optional<NoneEffect.None> onModifierAdded(VOEffectPipeline.States states, VOModifierState added, VOEffectApplicable<?> receiver) {
        states.modifiers.clear();
        return Optional.empty();
    }

    public static class None implements Copyable<None> {
        @Override
        public None clone() {
            try {
                return (None) super.clone();
            } catch (CloneNotSupportedException exception) {
                LogUtil.exception(VOPlugin.getPlugin().getLogger(), exception);
            }
            return null;
        }
    }
}
