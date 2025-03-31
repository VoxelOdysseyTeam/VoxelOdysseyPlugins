package com.guy7cc.voxelodyssey.dev.command;

import com.guy7cc.voxelodyssey.core.VoxelOdysseyCore;
import com.guy7cc.voxelodyssey.core.command.CommandArg;
import com.guy7cc.voxelodyssey.core.command.CommandComposition;
import com.guy7cc.voxelodyssey.core.command.CommandDescriptor;
import com.guy7cc.voxelodyssey.dev.banner.BannerFactory;
import com.guy7cc.voxelodyssey.dev.banner.Banners;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class VODevBannerCommand implements CommandDescriptor {
    private final CommandComposition composition = new CommandComposition()
            .add(
                    (args, sender) -> {
                        if(sender instanceof Player player){
                            BannerFactory factory = ((BannerFactory) args.get("banner"));
                            player.getInventory().addItem(factory.supply());
                            return true;
                        }
                        return false;
                    },
                    CommandArg.registry("banner", Banners.REGISTRY, VoxelOdysseyCore.NAMESPACE)
            );

    @Override
    public String getName() {
        return "vodevbanner";
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
