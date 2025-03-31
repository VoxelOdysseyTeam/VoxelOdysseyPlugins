package com.guy7cc.voxelodyssey.core.command;

import com.guy7cc.voxelodyssey.core.common.ExecutionOrder;
import com.guy7cc.voxelodyssey.core.common.PluginLifecycleListener;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandManager implements PluginLifecycleListener {
    private final JavaPlugin plugin;
    private final CommandDescriptor[] handlers;

    public CommandManager(JavaPlugin plugin, CommandDescriptor... handlers){
        this.plugin = plugin;
        this.handlers = handlers;
    }

    @Override
    @ExecutionOrder(ExecutionOrder.Order.MIDDLE)
    public void onPluginEnabled(JavaPlugin plugin) {
        for(CommandDescriptor handler : handlers) {
            setupCommand(handler.getName(), handler, plugin);
        }
    }

    private static <T extends CommandDescriptor> void setupCommand(String name, T executor, JavaPlugin plugin) {
        PluginCommand command = plugin.getCommand(name);
        if (command == null) return;
        command.setExecutor(executor);
        command.setTabCompleter(executor);
    }
}
