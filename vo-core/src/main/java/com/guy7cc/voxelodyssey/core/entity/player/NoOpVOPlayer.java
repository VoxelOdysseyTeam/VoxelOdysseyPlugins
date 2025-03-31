package com.guy7cc.voxelodyssey.core.entity.player;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.guy7cc.voxelodyssey.core.data.DataFormatException;
import com.guy7cc.voxelodyssey.core.entity.VOEntityType;
import com.guy7cc.voxelodyssey.core.registry.Key;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public final class NoOpVOPlayer implements VOPlayer {
    private final Player handle;

    public NoOpVOPlayer(Player player){
        this.handle = player;
    }

    @Override
    public Player getHandle() {
        return handle;
    }

    @Override
    public VOPlayer initialize() {
        return this;
    }

    @Override
    public JsonElement toJson() {
        return new JsonObject();
    }

    @Override
    public VOPlayer fromJson(JsonElement j) throws DataFormatException {
        return this;
    }

    public static final class Type extends VOEntityType<VOPlayer, FactoryArgs> {
        public Type() {
            super(Key.vo("voplayer"));
        }

        @Override
        public VOPlayer create(FactoryArgs args) {
            throw new UnsupportedOperationException("Cannot spawn player");
        }

        @Override
        public VOPlayer wrap(Entity entity, FactoryArgs args) {
            if(!(entity instanceof Player player)) return null;
            return new NoOpVOPlayer(player);
        }
    }
}
