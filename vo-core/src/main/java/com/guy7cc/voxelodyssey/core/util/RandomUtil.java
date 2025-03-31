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
