package org.nebich.infected.items.bonus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.nebich.infected.Infected;
import org.nebich.infected.utils.TimeUtils;

public class HealItem extends Bonus implements Listener {
    public HealItem(Infected plugin) {
        super(plugin);
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public ItemStack createItem() {
        ItemStack item = new ItemStack(Material.POTION);
        PotionMeta potionMeta = (PotionMeta) item.getItemMeta();
        if (potionMeta != null) {
            potionMeta.setBasePotionType(PotionType.HEALING);
            potionMeta.setDisplayName(ChatColor.BOLD + ChatColor.RED.toString() + "Bonus Heal Potion");
        }
        item.setItemMeta(potionMeta);
        return item;
    }

    @EventHandler
    public void handlePickupHealItem(EntityPickupItemEvent event) {
        Item item = event.getItem();
        LivingEntity livingEntity = event.getEntity();
        if (livingEntity instanceof Player player && item.getItemStack().getItemMeta() != null && item.getItemStack().getItemMeta().getDisplayName().toLowerCase().contains("bonus heal")) {
            player.getInventory().remove(item.getItemStack());
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, TimeUtils.seconds(5), 0));
            double amountToHeal = Math.min(20.0, player.getHealth() + 4.0);
            player.setHealth(amountToHeal);
        }
    }
}
