package org.nebich.infected.tasks.game;

import org.bukkit.scheduler.BukkitRunnable;
import org.nebich.infected.Infected;
import org.nebich.infected.player.InfectedPlayer;

import java.util.List;

public class GameEndTask extends BukkitRunnable {
    private final Infected plugin;

    public GameEndTask(Infected plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        List<InfectedPlayer> survivors = this.plugin.getPlayerManager().getSurvivors();
        if ((survivors != null && survivors.isEmpty()) || this.plugin.getGameManager().getGameTimer() <= 0) {
            this.plugin.getGameManager().end();
            cancel();
        }
    }
}
