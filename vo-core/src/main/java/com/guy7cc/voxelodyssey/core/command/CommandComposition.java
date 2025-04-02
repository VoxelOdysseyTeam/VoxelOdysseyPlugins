/*
 * Copyright (C) 2025 TeamVoxelOdyssey
 *
 * This file is part of VoxelOdysseyPlugins.
 *
 * VoxelOdysseyPlugins is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * VoxelOdysseyPlugins is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with VoxelOdysseyPlugins. If not, see <https://www.gnu.org/licenses/>.
 */
package com.guy7cc.voxelodyssey.core.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.BiFunction;

public class CommandComposition implements CommandExecutor, TabCompleter {
    private final List<Composition> compositions = new ArrayList<>();

    public CommandComposition add(BiFunction<NamedArgs, CommandSender, Boolean> executor, CommandArg<?>... args) {
        compositions.add(new Composition(executor, Arrays.stream(args).toList()));
        return this;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {
        for (var c : compositions) {
            if (c.matches(args)) {
                NamedArgs namedArgs = new NamedArgs(c, args);
                return c.executor.apply(namedArgs, commandSender);
            }
        }
        commandSender.sendMessage(s + "コマンドの実行に失敗しました。");
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {
        List<String> completion = new ArrayList<>();
        for (var c : compositions) {
            completion.addAll(c.onTabComplete(args));
        }
        return completion;
    }

    public static class NamedArgs {
        private final Map<String, Object> map = new HashMap<>();

        protected NamedArgs(Composition composition, String[] args) {
            for (int i = 0; i < args.length; i++) {
                CommandArg<?> commandArg = composition.args.get(i);
                map.put(commandArg.getName(), commandArg.get(args[i]));
            }
        }

        public boolean contains(String name) {
            return map.containsKey(name);
        }

        public Object get(String name) {
            return map.get(name);
        }
    }

    protected static class Composition {
        public final BiFunction<NamedArgs, CommandSender, Boolean> executor;
        public final List<CommandArg<?>> args;

        public Composition(BiFunction<NamedArgs, CommandSender, Boolean> executor, List<CommandArg<?>> args) {
            this.executor = executor;
            this.args = args;
        }

        public boolean matches(String[] args) {
            if (this.args.size() != args.length) return false;
            for (int i = 0; i < args.length; i++) {
                if (!this.args.get(i).matches(args[i])) return false;
            }
            return true;
        }

        public List<String> onTabComplete(String[] args) {
            if (this.args.size() < args.length) return List.of();
            for (int i = 0; i < args.length - 1; i++) {
                if (!this.args.get(i).matches(args[i])) return List.of();
            }
            return this.args.get(args.length - 1).onTabComplete(args[args.length - 1]);
        }
    }
}
