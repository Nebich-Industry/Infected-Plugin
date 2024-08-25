package org.nebich.infected.tasks.game;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.nebich.infected.Infected;
import org.nebich.infected.game.GameStatus;
import org.nebich.infected.utils.TimeUtils;

import java.util.Collection;

public class GameTimerTask extends BukkitRunnable {
    private final Infected plugin;

    public GameTimerTask(Infected plugin) {
        this.plugin = plugin;
        this.runTaskTimer(this.plugin, 0, TimeUtils.seconds(1));
    }

    @Override
    public void run() {
        if (this.plugin.getGameManager().getGameStatus() == GameStatus.PLAYING) {
            this.plugin.getGameManager().decrementGameTimer();
            int gameTimer = this.plugin.getGameManager().getGameTimer();
            if (gameTimer > 0) {
                Collection<? extends Player> onlinePlayers = this.plugin.getServer().getOnlinePlayers();
                for (Player player : onlinePlayers) {
                    if (player.isOnline() && player.getWorld() == this.plugin.getWorldsManager().getCurrentWorld()) {
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("Temps restant avant la découverte du remède : " + TimeUtils.convertToMinutes(gameTimer)));
                    }
                }
            } else {
                this.plugin.getGameManager().end();
            }
        } else {
            cancel();
        }
    }
}
