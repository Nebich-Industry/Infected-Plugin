package org.nebich.infected.tasks.bonus;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.nebich.infected.Infected;
import org.nebich.infected.utils.ParticuleUtils;
import org.nebich.infected.utils.TimeUtils;

import java.util.List;

public class FumigeneTask extends BukkitRunnable {
    private final Location blockHitedByFumigene;
    private final Player shooter;
    private int timer = 5;
    private final double sphereRadius = 5;

    public FumigeneTask(Player player, Location blockHitedByFumigene, Infected plugin) {
        this.blockHitedByFumigene = blockHitedByFumigene;
        this.shooter = player;
        this.runTaskTimerAsynchronously(plugin, 0, 20);
    }

    @Override
    public void run() {
        if (this.timer <= 0) {
            cancel();
        }
        ParticuleUtils.generateSphere(this.blockHitedByFumigene, Particle.LARGE_SMOKE, this.shooter, true);
        List<Player> playerInTheGame = shooter.getWorld().getPlayers().stream().filter(p -> p.getLocation().distance(this.blockHitedByFumigene) <= sphereRadius).toList();
        for (Player player : playerInTheGame) {
            if (player.getUniqueId() != this.shooter.getUniqueId()) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, TimeUtils.seconds(2), 0));
            }
        }
        this.timer--;
    }
}
