package org.nebich.infected.zombies;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.nebich.infected.Infected;
import org.nebich.infected.player.InfectedPlayer;
import org.nebich.infected.utils.TimeUtils;

import java.security.SecureRandom;

public class Husk implements ZombieRole, Listener {
    private final InfectedPlayer infectedPlayer;

    public Husk(Infected plugin, InfectedPlayer infectedPlayer) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
        this.infectedPlayer = infectedPlayer;
    }

    @Override
    public void transform() {
        // Do nothing
    }

    @EventHandler
    public void handleOnDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player damagerPlayer
                && event.getEntity() instanceof Player attackedPlayer
                && damagerPlayer.getUniqueId() == this.infectedPlayer.getPlayer().getUniqueId()) {
            int luck = new SecureRandom().nextInt(100);
            if (luck <= 30) {
                attackedPlayer.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, TimeUtils.seconds(5), 0));
            }
        }
    }
}
