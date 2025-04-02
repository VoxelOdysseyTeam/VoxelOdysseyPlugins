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

import com.guy7cc.voxelodyssey.core.VoxelOdysseyCore;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class KeyTest {
    @Test
    public void testEqualsAndHashcode(){
        Key key1 = Key.vo("test");
        Key key2 = Key.fromString(VoxelOdysseyCore.NAMESPACE + ":test");
        Key key3 = new Key("minecraft", "test");
        Key key4 = Key.vo("other");

        assertEquals(key1, key2, "Keys with the same string should be equal.");
        assertNotEquals(key1, key3, "Keys with different namespaces should not be equal.");
        assertNotEquals(key1, key4, "Keys with different keys should not be equal.");

        assertEquals(key1.hashCode(), key2.hashCode(), "If equals() returns true, hashCode() should be the same.");
        assertNotEquals(key1.hashCode(), key3.hashCode(), "Different keys should have different hash codes in most cases.");
        assertNotEquals(key1.hashCode(), key4.hashCode(), "Different keys should have different hash codes in most cases.");
    }

    @Test
    public void testMap(){
        Map<Key, String> map = new HashMap<>();
        Key key1 = Key.vo("test");
        Key key2 = Key.vo("test");
        Key key3 = Key.vo("other");

        map.put(key1, "value1");

        // 同じキーで取得できるか
        assertEquals("value1", map.get(key1), "The value should be retrievable with the registered key.");
        assertEquals("value1", map.get(key2), "Another instance with the same key string should retrieve the same value.");
        assertNull(map.get(key3), "A different key should not retrieve any value.");
    }
}
