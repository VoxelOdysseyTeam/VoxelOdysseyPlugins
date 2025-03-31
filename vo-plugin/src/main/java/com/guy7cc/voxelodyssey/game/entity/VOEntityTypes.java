package com.guy7cc.voxelodyssey.game.entity;

import com.guy7cc.voxelodyssey.core.VOCorePlugin;
import com.guy7cc.voxelodyssey.core.entity.VOCoreEntityTypes;
import com.guy7cc.voxelodyssey.core.entity.VOEntity;
import com.guy7cc.voxelodyssey.core.entity.VOEntityFactoryArgs;
import com.guy7cc.voxelodyssey.core.entity.VOEntityType;
import com.guy7cc.voxelodyssey.core.entity.player.NoOpVOPlayer;
import com.guy7cc.voxelodyssey.core.entity.player.VOPlayer;
import com.guy7cc.voxelodyssey.game.entity.player.VOPlayerImpl;

public class VOEntityTypes {
    public static final VOEntityType<VOPlayer, VOPlayer.FactoryArgs> VOPLAYER;
    public static final VOEntityType<VODamageNum, VODamageNum.FactoryArgs> DAMAGE_NUM;

    static {
        VOPLAYER = register(new VOPlayerImpl.Type());
        DAMAGE_NUM = register(new VODamageNum.Type());
    }

    private VOEntityTypes(){

    }

    private static <T extends VOEntity<?>, S extends VOEntityFactoryArgs> VOEntityType<T, S> register(VOEntityType<T, S> object) {
        VOCoreEntityTypes.REGISTRY.register(object);
        return object;
    }
}
