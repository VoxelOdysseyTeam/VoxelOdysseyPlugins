package com.guy7cc.voxelodyssey.game;

import com.guy7cc.voxelodyssey.core.VoxelOdysseyCore;
import com.guy7cc.voxelodyssey.game.registry.VORegistryTypes;
import com.guy7cc.voxelodyssey.game.system.effect.VOEffects;
import org.bukkit.plugin.java.JavaPlugin;

public final class VOPlugin extends JavaPlugin {
    private static VOPlugin plugin;

    @Override
    public void onEnable(){
        plugin = this;

        VoxelOdysseyCore.addRegistry(VORegistryTypes.EFFECT, VOEffects.REGISTRY);
    }

    @Override
    public void onDisable(){

    }

    public static VOPlugin getPlugin(){
        return plugin;
    }
}
