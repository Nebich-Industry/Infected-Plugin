package org.nebich.infected.tasks.bonus;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.nebich.infected.Infected;
import org.nebich.infected.player.InfectedPlayer;
import org.nebich.infected.player.Survivor;
import org.nebich.infected.survivors.Ninja;
import org.nebich.infected.utils.ParticuleUtils;
import org.nebich.infected.utils.TimeUtils;

import java.util.List;
import java.util.Optional;

public class FumigeneTask extends BukkitRunnable {
    private final Location blockHitedByFumigene;
    private final Player shooter;
    private int timer = 5;
    private final Infected plugin;
    private static final double SPHERE_RADIUS = 5;

    public FumigeneTask(Player player, Location blockHitedByFumigene, Infected plugin) {
        this.blockHitedByFumigene = blockHitedByFumigene;
        this.shooter = player;
        this.plugin = plugin;
        this.runTaskTimer(plugin, 0, 20);
    }

    @Override
    public void run() {
        if (this.timer <= 0 ) {
            cancel();
        }
        ParticuleUtils.generateSphere(this.blockHitedByFumigene, Particle.LARGE_SMOKE, this.shooter);
        List<Player> playerInTheGame = shooter.getWorld().getPlayers().stream().filter(p -> p.getLocation().distance(this.blockHitedByFumigene) <= SPHERE_RADIUS).toList();
        for (Player player : playerInTheGame) {
            Optional<InfectedPlayer> optionalPlayer = this.plugin.getPlayerManager().getPlayer(player.getUniqueId());
            if (optionalPlayer.isPresent()) {
                InfectedPlayer infectedPlayer = optionalPlayer.get();
                if (infectedPlayer.isSurvivor() && ((Survivor) infectedPlayer).getRole() instanceof Ninja) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, TimeUtils.seconds(2), 0));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, TimeUtils.seconds(2), 0));
                } else {
                    if (player.getUniqueId() != this.shooter.getUniqueId()) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, TimeUtils.seconds(2), 0));
                    }
                }
            }
        }
        this.timer--;
    }
}
