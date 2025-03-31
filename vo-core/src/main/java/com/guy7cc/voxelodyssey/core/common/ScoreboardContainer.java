package com.guy7cc.voxelodyssey.core.common;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.HashMap;
import java.util.Map;

public class ScoreboardContainer implements Listener, PluginLifecycleListener {
    private final JavaPlugin plugin;
    private boolean initialized = false;
    private ScoreboardManager manager;
    private final Map<Player, Scoreboard> map = new HashMap<>();

    public ScoreboardContainer(JavaPlugin plugin){
        this.plugin = plugin;
    }

    public boolean isInitialized(){
        return initialized;
    }

    public Scoreboard getScoreboard(Player player){
        return map.get(player);
    }

    private Scoreboard register(Player player){
        if(!initialized) return null;
        Scoreboard sb = manager.getNewScoreboard();
        player.setScoreboard(sb);
        return map.put(player, sb);
    }

    @Override
    @ExecutionOrder(ExecutionOrder.Order.EARLY)
    public void onFirstTick(JavaPlugin plugin){
        if(!initialized){
            manager = Bukkit.getScoreboardManager();
            Bukkit.getOnlinePlayers().forEach(this::register);
            initialized = true;
        } else {
            plugin.getLogger().warning("Scoreboard manager was already initialized");
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerJoin(PlayerJoinEvent event){
        register(event.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        map.remove(event.getPlayer());
    }
}
