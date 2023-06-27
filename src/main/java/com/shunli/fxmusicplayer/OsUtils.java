package com.shunli.fxmusicplayer;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

public class OsUtils {
    public static String getOsName() {
        return System.getProperty("os.name");
    }

    public static void printProperties() {
        Properties properties = System.getProperties();
        for (Object key : properties.keySet()) {
            Object value = properties.get(key);
            System.out.println(key + ":" + value);
        }
    }

    public static boolean isWindows() {
        String osName = getOsName();
        return osName != null && osName.toLowerCase(Locale.ENGLISH).contains("windows");
    }

    public static boolean isMac() {
        String osName = getOsName();
        return osName != null && osName.toLowerCase(Locale.ENGLISH).contains("mac");
    }

    public static String getUserHome() {
        return getProp("user.home");
    }

    /**
     * Get dir of current app.
     *
     * @return Dir of current app.
     */
    public static String getUserDir() {
        return getProp("user.dir");
    }

    public static String getUserName() {
        return getProp("user.name");
    }

    /**
     * eg.C:\Users\ADMINI~1\AppData\Local\Temp\
     *
     * @return
     */
    public static String getTmpDir() {
        return getProp("java.io.tmpdir");
    }

    public static String getDesktop() {
        return new File(getUserHome(), "Desktop").getAbsolutePath();
    }

    public static String getDownloads() {
        return new File(getUserHome(), "Downloads").getAbsolutePath();
    }

    private static String getProp(String key) {
        return System.getProperty(key);
    }

    public static void openSystemPath(String path) {
        String cmd;
        if (isWindows()) {
            cmd = String.format("explorer /select, %s", path);
        } else if (isMac()) {
            cmd = String.format("open -R %s", path);
        } else {
            cmd = String.format("xdg-open %s", path);
        }
        try {
            Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
