package com.guy7cc.voxelodyssey.core.common;

import com.guy7cc.voxelodyssey.core.util.ReflectionUtil;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;
import java.util.List;

public class GeneralEventHandler {
    private final JavaPlugin plugin;

    public GeneralEventHandler(JavaPlugin plugin){
        this.plugin = plugin;
    }

    public void add(Listener listener){
        PluginManager pluginManager = plugin.getServer().getPluginManager();
        pluginManager.registerEvents(listener, plugin);
    }

    public void add(Collection<Listener> listeners){
        listeners.forEach(this::add);
    }

    public void collect(Object obj){
        List<Object> list = ReflectionUtil.collectFields(obj, o -> o instanceof Listener, plugin.getLogger());
        add(list.stream().map(o -> (Listener) o).toList());
    }

    public void collectStatic(Class<?> clazz){
        List<Object> list = ReflectionUtil.collectStaticFields(clazz, o -> o instanceof Listener, plugin.getLogger());
        add(list.stream().map(o -> (Listener) o).toList());
    }
}
