package com.guy7cc.voxelodyssey.game.system.effect;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.guy7cc.voxelodyssey.core.common.Tickable;
import com.guy7cc.voxelodyssey.core.data.JsonSerializable;
import com.guy7cc.voxelodyssey.core.data.DataFormatException;
import com.guy7cc.voxelodyssey.core.registry.IndexedKey;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class VOEffectRouter implements Tickable, JsonSerializable<VOEffectRouter> {
    private final VOEffectApplicable<?> receiver;
    private final Map<VOEffect<?>, VOEffectPipeline<?>> router = new HashMap<>();

    public VOEffectRouter(VOEffectApplicable<?> receiver) {
        this.receiver = receiver;
        initialize();
    }

    public void addEffect(VOEffectState state) {
        router.get(state.getOwner()).addEffect(state);
    }

    public void addEffect(Collection<VOEffectState> collection) {
        collection.forEach(this::addEffect);
    }

    public void addModifier(VOModifierState state) {
        router.get(state.getOwner()).addModifier(state);
    }

    public void addModifier(Collection<VOModifierState> collection) {
        collection.forEach(this::addModifier);
    }

    public void clearModifierFromInventory(int slot) {
        clearModifier(IndexedKey.fromInventory(slot));
    }

    public void clearModifier(IndexedKey source) {
        for (var pipeline : router.values()) {
            pipeline.clearModifierBySource(source);
        }
    }

    public void clearModifierAll() {
        for (var pipeline : router.values()) {
            pipeline.clearModifierAll();
        }
    }

    @Override
    public Collection<? extends Tickable> getTickables() {
        return router.values();
    }

    @Override
    public VOEffectRouter initialize() {
        router.clear();
        VOEffects.REGISTRY.objects().forEach(effect -> router.put(effect, new VOEffectPipeline<>(receiver, effect)));
        return this;
    }

    @Override
    public JsonObject toJson() {
        JsonObject j = new JsonObject();
        for (var entry : router.entrySet()) {
            j.add(entry.getKey().getKey().toString(), entry.getValue().toJson());
        }
        return j;
    }

    @Override
    public VOEffectRouter fromJson(JsonElement j) throws DataFormatException {
        try {
            JsonObject o = j.getAsJsonObject();
            for (VOEffect<?> effect : router.keySet()) {
                JsonObject obj = o.get(effect.getKey().toString()).getAsJsonObject();
                VOEffectPipeline<?> pipeline = new VOEffectPipeline<>(receiver, effect);
                pipeline.fromJson(obj);
                router.put(effect, pipeline);
            }
            return this;
        } catch (Exception e) {
            throw new DataFormatException(getClass(), e);
        }
    }
}
