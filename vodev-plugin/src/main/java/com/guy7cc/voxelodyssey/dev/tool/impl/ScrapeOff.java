package com.guy7cc.voxelodyssey.dev.tool.impl;

import com.guy7cc.voxelodyssey.dev.tool.Tool;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.world.block.BlockTypes;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class ScrapeOff extends Tool {
    public ScrapeOff() {
        super("scrapeoff");
    }

    @Override
    public void apply(Player player, Location loc) {
        com.sk89q.worldedit.entity.Player actor = BukkitAdapter.adapt(player);
        LocalSession localSession = WorldEdit.getInstance().getSessionManager().get(actor);
        BlockVector3 targetPos = new BlockVector3(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
        try (EditSession session = localSession.createEditSession(actor)) {
            try {
                BlockVector3 top = targetPos;
                while (session.getBlock(top).getBlockType() != BlockTypes.AIR) {
                    top = top.add(0, 1, 0);
                }
                BlockVector3 bottom = top.add(0, -1, 0);
                while (session.getBlock(bottom).getBlockType() != BlockTypes.AIR) {
                    bottom = bottom.add(0, -1, 0);
                }
                while (top.y() >= bottom.y()) {
                    session.setBlock(top, BlockTypes.AIR.getDefaultState());
                    top = top.add(0, -1, 0);
                }
            } catch (MaxChangedBlocksException exception) {
                // do nothing cuz of boring implementation
            }

            localSession.remember(session);
            session.commit();
        }
    }
}
