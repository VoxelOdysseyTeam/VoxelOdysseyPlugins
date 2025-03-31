package com.guy7cc.voxelodyssey.core;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class VOCorePlugin extends JavaPlugin {
    private static VOCorePlugin plugin;

    @Override
    public void onLoad() {
        plugin = this;
    }

    @Override
    public void onEnable(){
        VoxelOdysseyCore.setup(this);

        VoxelOdysseyCore.getLifecycleHandler().onPluginEnabled();

        Bukkit.getScheduler().runTask(this, VoxelOdysseyCore.getLifecycleHandler()::onFirstTick);

        Bukkit.getScheduler().runTaskTimer(this, VoxelOdysseyCore.getTicker()::tick, 1L, 1L);
    }

    @Override
    public void onDisable(){
        VoxelOdysseyCore.getLifecycleHandler().onPluginDisabled();
    }

    public static VOCorePlugin getPlugin() {
        return plugin;
    }
}
