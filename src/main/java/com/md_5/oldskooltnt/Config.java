package com.md_5.oldskooltnt;

import java.io.File;
import java.util.ArrayList;
import org.bukkit.util.config.Configuration;
import java.util.List;

public class Config {

    static File configFile = new File("plugins" + File.separator + "OldSkoolTNT" + File.separator + "config.yml");

    public static Configuration load() {
        Configuration config = new Configuration(configFile);
        config.load();
        return config;
    }

    public static List<String> getIgnoredWorlds() {
        Configuration config = load();
        List<String> ignoredWorldsList = (List) config.getProperty("ignoredWorlds");
        if (ignoredWorldsList != null) {
            return ignoredWorldsList;
        } else {
            return null;
        }
    }

    public static List<Integer> getBreakableTool() {
        Configuration config = load();
        List<Integer> breakableToolList = (List) config.getProperty("breakableTools");
        if (breakableToolList != null) {
            return breakableToolList;
        } else {
            return null;
        }
    }

    public static boolean getFire() {
        Configuration config = load();
        boolean fireTNT = true;
        fireTNT = config.getBoolean("fireTNT", fireTNT);
        return fireTNT;
    }
    public static boolean getInstant() {
        Configuration config = load();
        boolean instantTNT = false;
        instantTNT = config.getBoolean("instantTNT", instantTNT);
        return instantTNT;
    }

    public static void initConfig() {
        configFile.getParentFile().mkdir();
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
            } catch (Exception e) {
            }
        }
        Configuration config = load();
        if (!config.getKeys().contains("ignoredWorlds")) {
            List<String> tempList = new ArrayList<String>();
            tempList.add("ignoredworld");
            config.setProperty("ignoredWorlds", tempList);
            config.save();
        }
        if (!config.getKeys().contains("breakableTools")) {
            List<Integer> tempList = new ArrayList<Integer>();
            tempList.add(359);
            config.setProperty("breakableTools", tempList);
            config.save();
        }
        if (!config.getKeys().contains("fireTNT")) {
            boolean tempList = true;
            config.setProperty("fireTNT", tempList);
            config.save();
        }
        /*if (!config.getKeys().contains("instantTNT")) {
            boolean tempList = false;
            config.setProperty("instantTNT", tempList);
            config.save();
        }*/
    }
}
