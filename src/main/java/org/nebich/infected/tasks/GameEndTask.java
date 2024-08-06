package org.nebich.infected.tasks;

import org.bukkit.scheduler.BukkitRunnable;
import org.nebich.infected.game.GameManager;
import org.nebich.infected.player.PlayerManager;

public class GameEndTask extends BukkitRunnable {
    private final PlayerManager playerManager;
    private final GameManager gameManager;
    int gameDurationTimer;

    public GameEndTask(PlayerManager playerManager, GameManager gameManager) {
        this.playerManager = playerManager;
        this.gameManager = gameManager;
        this.gameDurationTimer = this.gameManager.getGameDuration();
    }

    @Override
    public void run() {
        if (this.playerManager.getSurvivors().isEmpty() || this.gameDurationTimer == 0) {
            this.gameManager.end();
            cancel();
        }
        this.gameDurationTimer--;
    }
}
