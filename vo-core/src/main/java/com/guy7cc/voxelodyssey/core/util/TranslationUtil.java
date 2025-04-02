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
package com.guy7cc.voxelodyssey.core.util;

import com.guy7cc.voxelodyssey.core.VoxelOdysseyCore;
import com.guy7cc.voxelodyssey.core.registry.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TranslatableComponent;
import org.jetbrains.annotations.NotNull;

import java.util.StringJoiner;

public class TranslationUtil {
    public static String key(@NotNull String prefix, @NotNull String key, @NotNull String... suffix) {
        StringJoiner sj = new StringJoiner(".");
        sj.add(prefix);
        sj.add(key);
        for (String s : suffix) sj.add(s);
        return sj.toString();
    }

    public static String key(@NotNull String prefix, @NotNull Key key, @NotNull String... suffix) {
        return key(prefix, key.namespace() + "." + key.key(), suffix);
    }

    public static TranslatableComponent component(@NotNull String prefix, @NotNull String key, @NotNull String... suffix){
        return Component.translatable(key(prefix, key, suffix));
    }

    public static TranslatableComponent component(@NotNull String prefix, @NotNull Key key, @NotNull String... suffix){
        return Component.translatable(key(prefix, key, suffix));
    }

    public static String itemKey(@NotNull Key key, @NotNull String... suffix) {
        return key("item", key, suffix);
    }

    public static TranslatableComponent item(@NotNull Key key, @NotNull String... suffix){
        return Component.translatable(itemKey(key, suffix));
    }

    public static String guiKey(@NotNull String... suffix) {
        return key("gui", VoxelOdysseyCore.NAMESPACE, suffix);
    }

    public static TranslatableComponent gui(@NotNull String... suffix){
        return Component.translatable(guiKey(suffix));
    }
}
