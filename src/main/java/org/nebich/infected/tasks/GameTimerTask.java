package org.nebich.infected.tasks;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.nebich.infected.Infected;
import org.nebich.infected.game.GameManager;
import org.nebich.infected.game.GameStatus;
import org.nebich.infected.utils.TimeUtils;
import org.nebich.infected.worlds.WorldsManager;

import java.util.Collection;

public class GameTimerTask extends BukkitRunnable {
    private int gameTimer;
    private final GameManager gameManager;
    private final Infected plugin;
    private final WorldsManager worldsManager;

    public GameTimerTask(GameManager gameManager, Infected plugin, WorldsManager worldsManager) {
        this.gameManager = gameManager;
        this.plugin = plugin;
        this.worldsManager = worldsManager;
        this.gameTimer = this.gameManager.getGameTimer();
    }

    @Override
    public void run() {
        if (this.gameManager.getGameStatus() == GameStatus.PLAYING) {
            this.gameTimer--;
            if (this.gameTimer > 0) {
                Collection<? extends Player> onlinePlayers = this.plugin.getServer().getOnlinePlayers();
                for (Player player : onlinePlayers) {
                    if (player.isOnline() && player.getWorld() == this.worldsManager.getCurrentWorld()) {
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("Temps restant avant la découverte du remède : " + TimeUtils.convertToMinutes(this.gameTimer)));
                    }
                }
            } else {
                this.gameManager.end();
            }
        } else {
            cancel();
        }
    }
}
