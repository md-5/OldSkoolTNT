package com.md_5.oldskooltnt;

import net.minecraft.server.EntityTNTPrimed;

import net.minecraft.server.WorldServer;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.entity.CraftTNTPrimed;
import org.bukkit.entity.Player;
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
        Block block = event.getBlock();
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getItemInHand();

        if (block.getType() == Material.TNT && !event.isCancelled() && itemInHand.getType() != Material.SHEARS && !plugin.ignoredWorldsList.contains(player.getWorld().getName())) {
            World cWorld = (CraftWorld) block.getWorld();
            World world = block.getWorld();
            //WorldServer server = (CraftServer) block.getLocation().getWorld();
            WorldServer server = cWorld.getHandle();
            EntityTNTPrimed tnt = new EntityTNTPrimed(cWorld.getHandle(), block.getX() + 0.5D, block.getY() + 0.5D, block.getZ() + 0.5D);
            CraftTNTPrimed tnt2 = new CraftTNTPrimed(server, tnt);
           // world.addEntity(tnt);
            world.getEntities().add(tnt);
            world.getEntities().add(player);

            block.setType(Material.AIR);
            event.setCancelled(true);
            
        }
    }
}
