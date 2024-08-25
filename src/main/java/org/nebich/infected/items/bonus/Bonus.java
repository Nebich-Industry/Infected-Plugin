package org.nebich.infected.items.bonus;

import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.inventory.ItemStack;

public abstract class Bonus implements Listener {
    private ItemStack item;

    public Bonus() {}

    public ItemStack getItem() {
        return this.item;
    }

    public void setItem(ItemStack itemStack) {
        this.item = itemStack;
    }

    public abstract ItemStack createItem();

    @EventHandler
    public void handleDispawn(ItemDespawnEvent despawnEvent) {
        Item item = despawnEvent.getEntity();
        if (item.getName().toLowerCase().contains("fumigene")) {
            despawnEvent.setCancelled(true);
        }
    }
}
