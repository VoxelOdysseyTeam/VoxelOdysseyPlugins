package com.guy7cc.voxelodyssey.dev;

import com.guy7cc.voxelodyssey.core.VoxelOdysseyCore;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class VODevPlugin extends JavaPlugin {
    private static VODevPlugin plugin;

    @Override
    public void onLoad(){
        plugin = this;

    }

    @Override
    public void onEnable() {
        VoxelOdysseyDeveloperTools.setup(this);

        VoxelOdysseyDeveloperTools.getLifecycleHandler().onPluginEnabled();

        Bukkit.getScheduler().runTask(this, VoxelOdysseyDeveloperTools.getLifecycleHandler()::onFirstTick);

        Bukkit.getScheduler().runTaskTimer(this, VoxelOdysseyDeveloperTools.getTicker()::tick, 1L, 1L);
    }

    @Override
    public void onDisable(){
        VoxelOdysseyDeveloperTools.getLifecycleHandler().onPluginDisabled();
    }

    public static VODevPlugin getPlugin(){
        return plugin;
    }
}
