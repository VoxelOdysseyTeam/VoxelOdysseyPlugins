package com.guy7cc.voxelodyssey.core.command;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;

public interface CommandDescriptor extends CommandExecutor, TabCompleter {
    String getName();
}
