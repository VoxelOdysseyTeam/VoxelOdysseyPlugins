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
package com.guy7cc.voxelodyssey.core.registry;

import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class Registry<T extends RegistryObject> {
    private final Supplier<JavaPlugin> plugin;
    private final Map<Key, T> map = new HashMap<>(128);

    public Registry(Supplier<JavaPlugin> plugin) {
        this.plugin = plugin;
    }

    public T register(T object) {
        if (map.containsKey(object.getKey()) && plugin.get() != null) {
            plugin.get().getLogger().warning(String.format("A registry object keyed by %s is already registered, overriding it", object.getKey()));
        }
        map.put(object.getKey(), object);
        return object;
    }

    public T get(Key key) {
        return map.get(key);
    }

    public boolean containsKey(Key key) {
        return map.containsKey(key);
    }

    public Set<Key> keySet() {
        return map.keySet();
    }

    public Collection<T> objects() {
        return map.values();
    }
}
