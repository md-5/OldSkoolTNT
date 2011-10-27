package com.md_5.oldskooltnt;

import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.plugin.PluginManager;

public class EntityListener extends org.bukkit.event.entity.EntityListener {

    private final OldSkoolTNT plugin;

    public EntityListener(final OldSkoolTNT plugin) {
        this.plugin = plugin;
    }

    public void registerEvents(final PluginManager pm) {
        pm.registerEvent(Type.EXPLOSION_PRIME, this, Priority.Normal, plugin);
        pm.registerEvent(Type.ENTITY_DAMAGE, this, Priority.High, plugin);
    }

    @Override
    public void onExplosionPrime(ExplosionPrimeEvent event) {
        if (event.isCancelled()) {
            return;
        }
        if (!Config.getIgnoredWorlds().contains(event.getEntity().getWorld().getName())) {
            if (Config.getFire() && event.getEntity() instanceof TNTPrimed) {
                event.setFire(true);
            }
             if (Config.getInstant() && event.getEntity() instanceof TNTPrimed) {
                 event.getEntity().remove();
                if (Config.getFire()) {
                event.getEntity().getWorld().createExplosion(event.getEntity().getLocation(), 4F, true);
                } else {
                    event.getEntity().getWorld().createExplosion(event.getEntity().getLocation(), 4F);
                }
            }
        }
    }

    @Override
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player && event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_EXPLOSION)) {
            Player player = (Player) event.getEntity();         
            player.setHealth(player.getHealth() + event.getDamage());
            event.setCancelled(false);
        }
    }
}
