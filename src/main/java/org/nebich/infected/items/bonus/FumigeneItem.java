package org.nebich.infected.items.bonus;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.nebich.infected.Infected;
import org.nebich.infected.tasks.bonus.FumigeneTask;

public class FumigeneItem implements Listener {
    private final ItemStack item;
    private final Infected plugin;

    public FumigeneItem(Infected plugin) {
        this.plugin = plugin;
        this.item = new ItemStack(Material.SNOWBALL);
        ItemMeta meta = this.item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.BOLD + ChatColor.GRAY.toString() + "Fumigène");
        }
        this.item.setItemMeta(meta);
    }

    public ItemStack getItem() {
        return this.item;
    }

    @EventHandler
    public void handleHitOnTheGround(ProjectileHitEvent event) {
        Projectile projectile = event.getEntity();
        if (projectile.getShooter() instanceof Player) {
            if (projectile instanceof FumigeneItem) {
                Block blockHited = event.getHitBlock();
                if (blockHited != null) {
                    new FumigeneTask(((Player) projectile.getShooter()).getPlayer(), blockHited.getLocation(), this.plugin);
                }
            }
        }
    }
}
