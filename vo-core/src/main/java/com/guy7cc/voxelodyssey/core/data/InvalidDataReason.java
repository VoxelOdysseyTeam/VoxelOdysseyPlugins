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
