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
