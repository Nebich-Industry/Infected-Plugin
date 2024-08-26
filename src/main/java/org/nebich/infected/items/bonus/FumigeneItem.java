package org.nebich.infected.items.bonus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.nebich.infected.Infected;
import org.nebich.infected.tasks.bonus.FumigeneTask;

public class FumigeneItem extends Bonus implements Listener{
    private final Infected plugin;

    public FumigeneItem(Infected plugin) {
        super(plugin);
        Bukkit.getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
        this.setItem(createItem());
    }

    @EventHandler
    public void handleHitOnTheGround(ProjectileHitEvent event) {
        Projectile projectile = event.getEntity();
        if (projectile.getShooter() instanceof Player && projectile instanceof Snowball) {
            Block blockHited = event.getHitBlock();
            if (blockHited != null) {
                new FumigeneTask(((Player) projectile.getShooter()).getPlayer(), blockHited.getLocation(), this.plugin);
            }
        }
    }

    public ItemStack createItem() {
        ItemStack item = new ItemStack(Material.SNOWBALL);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.BOLD + ChatColor.GRAY.toString() + "Fumigene");
        }
        item.setItemMeta(meta);
        return item;
    }
}
