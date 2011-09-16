package com.md_5.oldskooltnt;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;

public class BlockListener extends org.bukkit.event.block.BlockListener {

    private final OldSkoolTNT plugin;

    public BlockListener(final OldSkoolTNT plugin) {
        this.plugin = plugin;
    }

    public void registerEvents(final PluginManager pm) {
        pm.registerEvent(Type.BLOCK_DAMAGE, this, Priority.Normal, plugin);
    }

    @Override
    public void onBlockDamage(BlockDamageEvent event) {
        if (event.isCancelled()) {
            return;
        }
        Block block = event.getBlock();
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getItemInHand();

        if (block.getType() == Material.TNT) {
            if (!Config.getIgnoredWorlds().contains(player.getWorld().getName())) {
                if (!Config.getBreakableTool().contains(itemInHand.getTypeId())) {
                    Location location = new Location(block.getWorld(), block.getX() + 0.5D, block.getY() + 0.5D, block.getZ() + 0.5D);
                    block.getWorld().spawn(location, TNTPrimed.class);
                    block.setType(Material.AIR);
                    event.setCancelled(true);
                }
            }
        }
    }
}
