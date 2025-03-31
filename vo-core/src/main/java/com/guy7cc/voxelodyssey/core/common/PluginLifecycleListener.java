package com.guy7cc.voxelodyssey.core.common;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public interface PluginLifecycleListener {
    default void onPluginEnabled(JavaPlugin plugin) {

    }

    default void onFirstTick(JavaPlugin plugin) {

    }

    default void onPluginDisabled(JavaPlugin plugin) {

    }
}
