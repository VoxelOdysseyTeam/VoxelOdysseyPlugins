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

import com.guy7cc.voxelodyssey.core.registry.Key;
import org.bukkit.NamespacedKey;

import java.util.Random;

public class RandomUtil {
    public static Key generateRandomKey() {
        return generateRandomKey(6, 2);
    }

    public static Key generateRandomKey(int alphabetLength, int numLength) {
        Random random = new Random();
        String s1 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String s2 = "0123456789";
        StringBuilder sb = new StringBuilder(alphabetLength);
        for (int i = 0; i < alphabetLength; i++) {
            int index = random.nextInt(52);
            sb.append(s1.charAt(index));
        }
        for (int i = 0; i < numLength; i++) {
            int index = random.nextInt(10);
            sb.append(s2.charAt(index));
        }
        return Key.vo(sb.toString());
    }
}
