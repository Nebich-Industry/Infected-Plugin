package org.nebich.infected.items.bonus;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.nebich.infected.Infected;
import org.nebich.infected.player.InfectedPlayer;
import org.nebich.infected.tasks.bonus.RemoveInstakillBonusTask;

import java.util.Optional;

public class InstakillItem extends Bonus implements Listener {
    private final Infected plugin;

    public InstakillItem(Infected plugin) {
        super(plugin);
        this.plugin = plugin;
    }

    @Override
    public ItemStack createItem() {
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.BOLD + ChatColor.RED.toString() + "Bonus Instakill");
        }
        item.setItemMeta(meta);
        return item;
    }

    @EventHandler
    public void handlePickupHealItem(EntityPickupItemEvent event) {
        Item item = event.getItem();
        LivingEntity livingEntity = event.getEntity();
        if (livingEntity instanceof Player player && item.getItemStack().getItemMeta() != null && item.getItemStack().getItemMeta().getDisplayName().toLowerCase().contains("bonus instakill")) {
            player.getInventory().remove(item.getItemStack());
            Optional<InfectedPlayer> optionalInfectedPlayer = this.plugin.getPlayerManager().getPlayer(player.getUniqueId());
            optionalInfectedPlayer.ifPresent(infectedPlayer -> {
                infectedPlayer.setHasInstakillBonus(true);
                new RemoveInstakillBonusTask(this.plugin, infectedPlayer);
            });
        }
    }
}
