package org.nebich.infected.game;

import org.nebich.infected.Infected;
import org.nebich.infected.player.PlayerManager;
import org.nebich.infected.tasks.GameEndTask;
import org.nebich.infected.tasks.GameStartTask;
import org.nebich.infected.utils.TimeUtils;
import org.nebich.infected.worlds.WorldsManager;

public class GameManager {
    private final WorldsManager worldsManager;
    private final PlayerManager playerManager;
    private GameStatus gameStatus = GameStatus.WAITING;
    private final int gameDuration = 300;
    private final Infected plugin;

    public GameManager(Infected plugin, WorldsManager worldsManager, PlayerManager playerManager) {
        this.worldsManager = worldsManager;
        this.playerManager = playerManager;
        this.plugin = plugin;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public void launch(boolean isAdminLaunch) {
        if (this.worldsManager.getCurrentWorld() == null) {
            this.worldsManager.setRandomWorld();
        }
        if (!isAdminLaunch) {
            this.playerManager.chooseFirstZombies();
        }
        this.gameStatus = GameStatus.PLAYING;
        new GameEndTask(this.playerManager, this).runTaskTimer(this.plugin, 0, TimeUtils.Seconds(1));
    }

    public void startWaitingTask() {
        new GameStartTask(this.plugin, this.worldsManager, this).runTaskTimer(this.plugin, 0, TimeUtils.Seconds(1));
    }

    public void end() {

    }

    public int getGameDuration() {
        return gameDuration;
    }
}
