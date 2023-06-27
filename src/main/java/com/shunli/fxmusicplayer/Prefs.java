package com.shunli.fxmusicplayer;

import java.io.*;
import java.util.Date;
import java.util.Properties;

public class Prefs {
    private static final String USER_HOME = System.getProperty("user.home");
    private static final File PROP_FILE = new File(USER_HOME, MainApp.class.getPackageName() + "/.prefs");

    private static boolean makeSureExits() {
        if (!PROP_FILE.exists()) {
            if (!PROP_FILE.getParentFile().mkdirs()) {
                System.out.println("Prefs directory create failed!");
                return false;
            }
            try {
                if (!PROP_FILE.createNewFile()) {
                    System.out.println("Prefs file create failed!");
                    return false;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public static void save(String key, String value) {
        if (!makeSureExits()) {
            return;
        }
        try {
            FileOutputStream out = new FileOutputStream(PROP_FILE, true);
            Properties properties = new Properties();
            properties.setProperty(key, value);
            properties.store(out, "save prop, time: " + new Date());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key, String def) {
        try {
            if (!makeSureExits()) {
                return def;
            }
            BufferedReader reader = new BufferedReader(new FileReader(PROP_FILE));
            Properties properties = new Properties();
            properties.load(reader);
            reader.close();
            return (String) properties.getOrDefault(key, def);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return def;
    }
}
