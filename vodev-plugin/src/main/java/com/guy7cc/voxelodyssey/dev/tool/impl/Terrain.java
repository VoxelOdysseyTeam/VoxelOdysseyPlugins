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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Terrain extends Tool {
    private int size;
    private List<BlockType> preset;

    public Terrain(int size, List<BlockType> preset) {
        super("terrain");
        this.size = size;
        this.preset = preset;
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
                        Set<BlockType> strongTarget = new HashSet<>(preset);
                        strongTarget.remove(BlockTypes.AIR);
                        Set<BlockType> weakTarget = new HashSet<>(strongTarget);
                        weakTarget.add(BlockTypes.AIR);

                        BlockVector3 top = targetPos.add(x, 0, z);
                        while (strongTarget.contains(session.getBlock(top).getBlockType())) {
                            top = top.add(0, 1, 0);
                        }
                        top = top.add(0, -1, 0);
                        if (!strongTarget.contains(session.getBlock(top).getBlockType())) continue;
                        int i = 0;

                        while (i < preset.size() || strongTarget.contains(session.getBlock(top).getBlockType())) {
                            TerrainUtil.replace(session, top, preset.get(Math.min(i, preset.size() - 1)), weakTarget);
                            top = top.add(0, -1, 0);
                            i++;
                        }
                    }
                }
            } catch (MaxChangedBlocksException exception) {
                // do nothing cuz of boring implementation
            }

            localSession.remember(session);
            session.commit();
        }
    }
}
