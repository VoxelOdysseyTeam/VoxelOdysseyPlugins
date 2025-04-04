package com.guy7cc.voxelodyssey.dev.tool.impl;

import com.guy7cc.voxelodyssey.dev.tool.Tool;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.world.block.BlockType;
import com.sk89q.worldedit.world.block.BlockTypes;
import com.guy7cc.voxelodyssey.dev.util.TerrainUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Set;

public class ExtendBottom extends Tool {
    private int size;
    private int bottom;

    public ExtendBottom(int size, int bottom) {
        super("extend_bottom");
        this.size = size;
        this.bottom = bottom;
    }

    @Override
    public void apply(Player player, Location loc) {
        com.sk89q.worldedit.entity.Player actor = BukkitAdapter.adapt(player);
        LocalSession localSession = WorldEdit.getInstance().getSessionManager().get(actor);
        BlockVector3 targetPos = new BlockVector3(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
        try (EditSession session = localSession.createEditSession(actor)) {
            try {
                int r = size;
                for (int x = -r; x <= r; x++) {
                    for (int z = -r; z <= r; z++) {
                        if (x * x + z * z > r * r) continue;
                        BlockVector3 top = targetPos.add(x, 0, z);
                        int patience = 0;
                        while (session.getBlock(top).getBlockType() == BlockTypes.AIR && patience <= 64) {
                            top = top.add(0, 1, 0);
                            patience++;
                        }
                        BlockType type = session.getBlock(top).getBlockType();
                        top = top.add(0, -1, 0);
                        if (patience > 64) continue;

                        while (top.y() >= bottom) {
                            TerrainUtil.replace(session, top, type, Set.of(BlockTypes.AIR));
                            top = top.add(0, -1, 0);
                        }
                    }
                }
            } catch (MaxChangedBlocksException exception) {
                player.sendMessage("Changed too many blocks");
            }

            localSession.remember(session);
            session.commit();
        }
    }
}
