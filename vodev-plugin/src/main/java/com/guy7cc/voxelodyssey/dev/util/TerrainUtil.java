package com.guy7cc.voxelodyssey.dev.util;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.world.block.BlockType;
import com.sk89q.worldedit.world.block.BlockTypes;

import java.util.List;
import java.util.Set;

public class TerrainUtil {
    public static void replace(EditSession session, BlockVector3 pos, BlockType type, Set<BlockType> target) throws MaxChangedBlocksException {
        if (target.contains(session.getBlock(pos).getBlockType())) {
            session.setBlock(pos, type.getDefaultState());
        }
    }

    public static boolean isExposed(EditSession session, BlockVector3 pos) {
        List<BlockVector3> list = List.of(
                BlockVector3.at(1, 0, 0),
                BlockVector3.at(-1, 0, 0),
                BlockVector3.at(0, 1, 0),
                BlockVector3.at(0, -1, 0),
                BlockVector3.at(0, 0, 1),
                BlockVector3.at(0, 0, -1)
        );
        for (var offset : list) {
            if (session.getBlock(pos.add(offset)).getBlockType() == BlockTypes.AIR) return true;
        }
        return false;
    }
}
