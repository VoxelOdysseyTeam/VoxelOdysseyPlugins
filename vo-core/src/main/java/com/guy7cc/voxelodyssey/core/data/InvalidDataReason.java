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
package com.guy7cc.voxelodyssey.core.data;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Supplier;

public enum InvalidDataReason {
    EMPTY(false, () -> "", "The data is empty. New file will be created."),
    OLD(true, () -> "_old_" + getDate(), "The data is old. New file will be created."),
    BROKEN(true, () -> "_broken_" + getDate(), "The data is broken. New file will be created.");

    private final boolean renameRequired;
    private final Supplier<String> suffixSupplier;
    private final String message;

    InvalidDataReason(boolean renameRequired, Supplier<String> suffix, String message) {
        this.renameRequired = renameRequired;
        this.suffixSupplier = suffix;
        this.message = message;
    }

    public boolean renameRequired(){
        return renameRequired;
    }

    public String addSuffix(String oldStr){
        return oldStr + suffixSupplier.get();
    }

    public File addSuffix(File oldFile) {
        String suffix = suffixSupplier.get();
        if(suffix.isEmpty()) return new File(oldFile.getAbsolutePath());
        String fileName = oldFile.getName();
        int dotIndex = fileName.lastIndexOf(".");
        String newFileName;
        if (dotIndex != -1) {
            newFileName = fileName.substring(0, dotIndex) + suffix + fileName.substring(dotIndex);
        } else {
            newFileName = fileName + suffix;
        }
        return new File(oldFile.getParent(), newFileName);
    }

    public String getMessage(){
        return message;
    }

    private static String getDate() {
        Date now = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd_HHmm");
        return f.format(now);
    }
}
