package org.nebich.infected.survivors;

import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.nebich.infected.Infected;
import org.nebich.infected.events.GameStartingEvent;
import org.nebich.infected.player.InfectedPlayer;
import org.nebich.infected.tasks.survivors.ArcherSpeedArrowTask;
import org.nebich.infected.utils.TimeUtils;

import java.util.Optional;

public class Archer extends Role implements Listener {
    private final Infected plugin;

    public Archer(Infected plugin, Player player) {
        super(plugin, player);
        Bukkit.getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
    }

    @EventHandler
    public void handlePlayerHittedByArrow(final EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player hittedPlayer && event.getDamager() instanceof Arrow arrow && arrow.getShooter() instanceof Player player) {
            Optional<InfectedPlayer> infectedPlayer = this.plugin.getPlayerManager().getPlayer(player.getUniqueId());
            if (infectedPlayer.isPresent() && infectedPlayer.get().getRole() instanceof Archer) {
                hittedPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, TimeUtils.seconds(5), 0));
            }
        }
    }

    @EventHandler
    public void handleGameStart(GameStartingEvent event) {
        new ArcherSpeedArrowTask(this.plugin, getPlayer());
    }
}
