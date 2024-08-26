package org.nebich.infected.items.bonus;

import org.bukkit.Bukkit;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.nebich.infected.Infected;

public abstract class Bonus implements Listener {
    private ItemStack item;

    protected Bonus(Infected plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public ItemStack getItem() {
        return this.item;
    }

    public void setItem(ItemStack itemStack) {
        this.item = itemStack;
    }

    public abstract ItemStack createItem();

    @EventHandler
    public void handleDispawn(ItemDespawnEvent despawnEvent) {
        Item despawnEventEntity = despawnEvent.getEntity();
        if (despawnEventEntity.getName().toLowerCase().contains("fumigene")) {
            despawnEvent.setCancelled(true);
        }
    }
}
