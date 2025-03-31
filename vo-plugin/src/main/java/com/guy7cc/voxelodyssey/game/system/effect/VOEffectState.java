package com.guy7cc.voxelodyssey.game.system.effect;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.guy7cc.voxelodyssey.core.data.DataFormatException;
import com.guy7cc.voxelodyssey.core.property.AbstractState;
import com.guy7cc.voxelodyssey.core.property.VOCoreProperties;
import com.guy7cc.voxelodyssey.core.registry.Key;

public class VOEffectState extends AbstractState<VOEffect<?>, VOEffectState> {
    private VOEffect<?> effect;

    public VOEffectState(VOEffect<?> effect) {
        setOwner(effect);
    }

    @Override
    public VOEffect<?> getOwner() {
        return effect;
    }

    private void setOwner(VOEffect<?> effect) {
        this.effect = effect;
        setProperty(VOCoreProperties.OWNER, effect.getKey());
    }

    @Override
    public VOEffectState initialize() {
        super.initialize();
        setOwner(VOEffects.NONE);
        return this;
    }

    @Override
    public JsonElement toJson() {
        return super.toJson();
    }

    @Override
    public VOEffectState fromJson(JsonElement j) throws DataFormatException {
        try {
            JsonObject o = j.getAsJsonObject();
            String key = o.get(VOCoreProperties.OWNER.getKey().toString()).getAsString();
            effect = VOEffects.REGISTRY.get(Key.fromString(key));
            if (effect == null) throw new DataFormatException("Cannot find VOEffect with " + key);
            super.fromJson(j);
            return this;
        } catch (Exception e) {
            throw new DataFormatException(getClass(), e);
        }
    }
}
