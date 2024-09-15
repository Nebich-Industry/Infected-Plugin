package org.nebich.infected.game;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.nebich.infected.Infected;
import org.nebich.infected.events.GameStartingEvent;
import org.nebich.infected.tasks.bonus.SpawnBonusTask;
import org.nebich.infected.tasks.game.GameStartTask;
import org.nebich.infected.tasks.game.GameTimerTask;

public class GameManager {
    private GameStatus gameStatus = GameStatus.WAITING;
    private final Infected plugin;
    private int gameTimer = 5 * 60;

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
        for (Player player : this.plugin.getWorldsManager().getCurrentWorld().getPlayers()) {
            player.getInventory().clear();
        }
        new GameTimerTask(this.plugin);
        new SpawnBonusTask(this.plugin);
        Bukkit.getPluginManager().callEvent(new GameStartingEvent());
    }

    public void startWaitingTask() {
        new GameStartTask(this.plugin);
    }

    public void end() {
        this.gameStatus = GameStatus.WAITING;
    }

    public int getGameTimer() {
        return this.gameTimer;
    }

    public void decrementGameTimer() {
        this.gameTimer--;
    }

    public int getWaitingTimer() {
        // Temps en secondes soit 2 minutes
        return 2 * 60;
    }
}
