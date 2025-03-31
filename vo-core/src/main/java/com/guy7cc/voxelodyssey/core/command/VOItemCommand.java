package com.guy7cc.voxelodyssey.core.command;

import com.guy7cc.voxelodyssey.core.VoxelOdysseyCore;
import com.guy7cc.voxelodyssey.core.item.VOCoreItems;
import com.guy7cc.voxelodyssey.core.item.VOItem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class VOItemCommand implements CommandDescriptor {
    private final CommandComposition composition = new CommandComposition()
            .add(
                    (args, sender) -> {
                        VOItem voitem = (VOItem) args.get("voitem");
                        if (sender instanceof Player player) {
                            player.getInventory().addItem(voitem.getDefaultState().getHandle());
                            return true;
                        }
                        return false;
                    },
                    CommandArg.literal("l1", "give"),
                    CommandArg.registry("voitem", VOCoreItems.REGISTRY, VoxelOdysseyCore.NAMESPACE)
            );

    @Override
    public String getName() {
        return "voitem";
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {
        return composition.onCommand(commandSender, command, s, args);
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {
        return composition.onTabComplete(commandSender, command, s, args);
    }
}
