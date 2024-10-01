package org.nebich.infected.items.bonus;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.nebich.infected.Infected;
import org.nebich.infected.player.InfectedPlayer;
import org.nebich.infected.utils.TimeUtils;

import java.util.Optional;

public class InvisibilityZombieItem extends Bonus implements Listener {
    private final Infected plugin;

    public InvisibilityZombieItem(Infected plugin) {
        super(plugin);
        this.plugin = plugin;
    }

    @Override
    public ItemStack createItem() {
        ItemStack item = new ItemStack(Material.POTION);
        PotionMeta potionMeta = (PotionMeta) item.getItemMeta();
        if (potionMeta != null) {
            potionMeta.setBasePotionType(PotionType.INVISIBILITY);
            potionMeta.setDisplayName(ChatColor.BOLD + ChatColor.AQUA.toString() + "Bonus Invisibility Potion");
        }
        item.setItemMeta(potionMeta);
        return item;
    }

    @EventHandler
    public void handlePickupHealItem(EntityPickupItemEvent event) {
        ItemMeta itemMeta = event.getItem().getItemStack().getItemMeta();
        LivingEntity livingEntity = event.getEntity();
        if (livingEntity instanceof Player player && itemMeta != null && itemMeta.getDisplayName().toLowerCase().contains("bonus invisibility")) {
            Optional<InfectedPlayer> infectedPlayer = this.plugin.getPlayerManager().getPlayer(player.getUniqueId());
            if (infectedPlayer.isPresent() && infectedPlayer.get().isZombie()) {
                player.getInventory().remove(event.getItem().getItemStack());
                player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, TimeUtils.seconds(5), 0));
            }
        }
    }
}