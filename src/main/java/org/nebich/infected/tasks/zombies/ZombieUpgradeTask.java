package org.nebich.infected.tasks.zombies;

import org.bukkit.scheduler.BukkitRunnable;
import org.nebich.infected.Infected;
import org.nebich.infected.game.GameStatus;
import org.nebich.infected.player.InfectedPlayer;
import org.nebich.infected.zombies.Pigman;

import java.security.SecureRandom;

public class ZombieUpgradeTask extends BukkitRunnable {
    private final InfectedPlayer infectedPlayer;
    private final Infected plugin;
    private boolean hasBeenTransformed;

    public ZombieUpgradeTask(Infected plugin, InfectedPlayer infectedPlayer) {
        this.infectedPlayer = infectedPlayer;
        this.plugin = plugin;
        runTaskTimer(plugin, 0, 20);
    }

    @Override
    public void run() {
        if (this.plugin.getGameManager().getGameStatus() != GameStatus.PLAYING) {
            cancel();
        }

        if (this.infectedPlayer.getKilledPlayersAsZombie().size() >= 3 && !this.hasBeenTransformed) {
            this.hasBeenTransformed = true;
            int luck = new SecureRandom().nextInt(100);
            if (luck <= 50) {
                this.setRandomZombieRole(infectedPlayer);
            } else {
                this.infectedPlayer.transformInSurvivor();
            }
        }
    }

    private void setRandomZombieRole(InfectedPlayer infectedPlayer) {
        int random = new SecureRandom().nextInt(100);
        if (random <= 33) {
            infectedPlayer.setZombieRole(new Pigman(infectedPlayer));
        }
        infectedPlayer.getZombieRole().transform();
    }
}
