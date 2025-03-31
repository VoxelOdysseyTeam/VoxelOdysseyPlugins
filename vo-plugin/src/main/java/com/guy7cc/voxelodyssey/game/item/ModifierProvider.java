package com.guy7cc.voxelodyssey.game.item;

import com.guy7cc.voxelodyssey.core.item.VOItemStackWrapper;
import com.guy7cc.voxelodyssey.game.system.effect.VOModifierState;
import org.bukkit.entity.Player;

import java.util.List;

public interface ModifierProvider {
    List<VOModifierState> createModifier(VOItemStackWrapper state, Player player, int slot);
}
