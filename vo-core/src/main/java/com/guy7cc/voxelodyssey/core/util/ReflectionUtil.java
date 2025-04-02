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

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReflectionUtil {
    public static List<Object> collectFields(Object obj, Predicate<Object> cond, Logger logger){
        Class<?> clazz = obj.getClass();
        List<Object> list = new ArrayList<>();
        for(Field field : clazz.getDeclaredFields()){
            if(Modifier.isStatic(field.getModifiers())) continue;
            field.setAccessible(true);
            try {
                Object fieldObj = field.get(obj);
                if(cond.test(fieldObj)) list.add(fieldObj);
            } catch (IllegalAccessException e) {
                logger.log(
                        Level.SEVERE,
                        String.format("DataLoader detected illegal access to field %s, ignoring it", field.getName()),
                        e
                );
            }
        }
        return list;
    }

    public static List<Object> collectStaticFields(Class<?> clazz, Predicate<Object> cond, Logger logger){
        List<Object> list = new ArrayList<>();
        for(Field field : clazz.getDeclaredFields()){
            if(!Modifier.isStatic(field.getModifiers())) continue;
            field.setAccessible(true);
            try {
                Object fieldObj = field.get(null);
                if(cond.test(fieldObj)) list.add(fieldObj);
            } catch (IllegalAccessException e) {
                logger.log(
                        Level.SEVERE,
                        String.format("DataLoader detected illegal access to field %s, ignoring it", field.getName()),
                        e
                );
            }
        }
        return list;
    }
}
