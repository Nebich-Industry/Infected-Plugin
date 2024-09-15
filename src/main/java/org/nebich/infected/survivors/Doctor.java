package org.nebich.infected.survivors;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.nebich.infected.Infected;
import org.nebich.infected.player.InfectedPlayer;

import java.security.SecureRandom;
import java.util.Optional;

public class Doctor implements Role, Listener {
    private final Infected plugin;

    public Doctor(Infected plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
    }

    @EventHandler
    public void handlePlayerDeath(final EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player victim && event.getDamager() instanceof Player attacker) {
            Optional<InfectedPlayer> attackerInfectedPlayer = this.plugin.getPlayerManager().getPlayer(attacker.getUniqueId());
            Optional<InfectedPlayer> victimInfectedPlayer = this.plugin.getPlayerManager().getPlayer(attacker.getUniqueId());
            if (    victim.getHealth() - event.getFinalDamage() == 0 &&
                    victimInfectedPlayer.isPresent() &&
                    victimInfectedPlayer.get().isZombie() &&
                    attackerInfectedPlayer.isPresent() &&
                    attackerInfectedPlayer.get().getRole() instanceof Doctor) {
                SecureRandom secureRandom = new SecureRandom();
                int chance = secureRandom.nextInt(100);
                if (chance <= 20) {
                    double amountToHeal = Math.min(20.0, attacker.getHealth() + 4.0);
                    attacker.setHealth(amountToHeal);
                }
            }
        }
    }
}
