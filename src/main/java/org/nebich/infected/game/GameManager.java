package org.nebich.infected.game;

import org.nebich.infected.Infected;
import org.nebich.infected.tasks.GameStartTask;
import org.nebich.infected.tasks.GameTimerTask;
import org.nebich.infected.utils.TimeUtils;

public class GameManager {
    private GameStatus gameStatus = GameStatus.WAITING;
    private final Infected plugin;

    public GameManager(Infected plugin) {
        this.plugin = plugin;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public void launch(boolean isAdminLaunch) {
        if (this.plugin.getWorldsManager().getCurrentWorld() == null) {
            this.plugin.getWorldsManager().setRandomWorld();
        }
        if (!isAdminLaunch) {
            this.plugin.getPlayerManager().chooseFirstZombies();
        }
        this.gameStatus = GameStatus.PLAYING;
        new GameTimerTask(this, this.plugin, this.plugin.getWorldsManager()).runTaskTimer(this.plugin, 0, TimeUtils.Seconds(1));
//        new GameEndTask(this.plugin.getPlayerManager(), this).runTaskTimer(this.plugin, 0, TimeUtils.Seconds(1));
    }

    public void startWaitingTask() {
        new GameStartTask(this.plugin, this.plugin.getWorldsManager(), this).runTaskTimer(this.plugin, 0, TimeUtils.Seconds(1));
    }

    public void end() {
        this.gameStatus = GameStatus.WAITING;
    }

    public int getGameTimer() {
        // Temps en secondes soit 5 minutes
        return 5 * 60;
    }

    public int getWaitingTimer() {
        // Temps en secondes soit 2 minutes
        return 2 * 60;
    }
}
