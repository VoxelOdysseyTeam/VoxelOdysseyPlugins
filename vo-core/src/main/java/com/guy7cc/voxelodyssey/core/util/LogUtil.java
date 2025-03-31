package com.guy7cc.voxelodyssey.core.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

public class LogUtil {
    public static void exception(Logger logger, Exception exception) {
        logger.severe(getStackTraceAsString(exception));
    }

    public static void exception(Logger logger, String message, Exception exception) {
        logger.severe(message);
        exception(logger, exception);
    }

    public static String getStackTraceAsString(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        return sw.toString();
    }
}
