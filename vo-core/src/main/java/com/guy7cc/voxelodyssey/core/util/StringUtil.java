package com.guy7cc.voxelodyssey.core.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StringUtil {
    public static List<String> getOptions(String arg, Collection<String> allOptions) {
        List<String> completion = new ArrayList<>();
        org.bukkit.util.StringUtil.copyPartialMatches(arg, allOptions, completion);
        return completion;
    }
}
