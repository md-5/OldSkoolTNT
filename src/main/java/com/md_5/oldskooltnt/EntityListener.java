package com.md_5.oldskooltnt;

import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.plugin.PluginManager;

public class EntityListener extends org.bukkit.event.entity.EntityListener {

    private final OldSkoolTNT plugin;

    public EntityListener(final OldSkoolTNT plugin) {
        this.plugin = plugin;
    }

    public void registerEvents(final PluginManager pm) {
        pm.registerEvent(Type.EXPLOSION_PRIME, this, Priority.Normal, plugin);
    }

    @Override
    public void onExplosionPrime(ExplosionPrimeEvent event) {
        if (event.isCancelled()) {
            return;
        }
        if (!Config.getIgnoredWorlds().contains(event.getEntity().getWorld().getName())) {
            if (Config.getFire()) {
                event.setFire(true);
            }
        }
    }
}
