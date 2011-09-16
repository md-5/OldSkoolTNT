package com.md_5.oldskooltnt;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import java.util.logging.Logger;

public class OldSkoolTNT extends JavaPlugin {

    static final Logger logger = Bukkit.getServer().getLogger();

    public void onEnable() {
        PluginManager pm = this.getServer().getPluginManager();

        BlockListener blockListener = new BlockListener(this);
        blockListener.registerEvents(pm);

        EntityListener entityListener = new EntityListener(this);
        entityListener.registerEvents(pm);

        Config.initConfig();
        
        logger.info(String.format("OldSkoolTNT v%1$s enabled", this.getDescription().getVersion()));
    }

    public void onDisable() {
        logger.info(String.format("OldSkoolTNT v%1$s disabled", this.getDescription().getVersion()));
    }
}
