package com.guy7cc.voxelodyssey.dev.tool;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.util.RayTraceResult;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ToolManager implements Listener {
    private final Map<UUID, Map<Material, Tool>> map = new HashMap<>();

    public void set(Player player, Material material, Tool tool){
        map.computeIfAbsent(player.getUniqueId(), uuid -> new HashMap<>()).put(material, tool);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        map.remove(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Material material = player.getInventory().getItemInMainHand().getType();
        RayTraceResult result = player.getWorld().rayTraceBlocks(player.getEyeLocation(), player.getLocation().getDirection(), 64);
        if (result != null && result.getHitBlock() != null && map.containsKey(player.getUniqueId()) && map.get(player.getUniqueId()).containsKey(material)) {
            Tool organizer = map.get(player.getUniqueId()).get(material);
            organizer.apply(player, result.getHitBlock().getLocation());
            event.setCancelled(true);
        }
    }
}
