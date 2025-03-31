package com.guy7cc.voxelodyssey.core.entity.player;

import com.guy7cc.voxelodyssey.core.data.JsonSerializable;
import com.guy7cc.voxelodyssey.core.entity.VOEntity;
import com.guy7cc.voxelodyssey.core.entity.VOEntityFactoryArgs;
import org.bukkit.entity.Player;

public interface VOPlayer extends VOEntity<Player>, JsonSerializable<VOPlayer> {
    class FactoryArgs extends VOEntityFactoryArgs {

    }
}
