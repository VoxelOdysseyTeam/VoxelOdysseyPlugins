package com.guy7cc.voxelodyssey.dev.command;

import com.guy7cc.voxelodyssey.core.command.CommandDescriptor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class VODevHatCommand implements CommandDescriptor {
    @Override
    public String getName() {
        return "vodevhat";
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command command, @NotNull String label, String[] args) {
        if (sender instanceof Player player) {
            ItemStack helmet = player.getInventory().getHelmet();
            ItemStack mainHand = player.getInventory().getItemInMainHand();
            player.getInventory().setHelmet(mainHand);
            player.getInventory().setItemInMainHand(helmet);
            return true;
        }
        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return List.of();
    }
}
