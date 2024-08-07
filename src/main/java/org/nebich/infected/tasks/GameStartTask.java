package org.nebich.infected.tasks;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.nebich.infected.Infected;
import org.nebich.infected.game.GameManager;
import org.nebich.infected.game.GameStatus;
import org.nebich.infected.utils.TimeUtils;
import org.nebich.infected.worlds.WorldsManager;

import java.util.Collection;

public class GameStartTask extends BukkitRunnable {
    private int startTimer = 120;
    private final Infected infectedPlugin;
    private final WorldsManager worldsManager;
    private final GameManager gameManager;

    public GameStartTask(Infected plugin, WorldsManager worldsManager, GameManager gameManager) {
        this.infectedPlugin = plugin;
        this.worldsManager = worldsManager;
        this.gameManager = gameManager;
    }

    @Override
    public void run() {
        if (this.gameManager.getGameStatus() != GameStatus.PLAYING) {
            Collection<? extends Player> onlinePlayers = this.infectedPlugin.getServer().getOnlinePlayers();
            if (this.startTimer > 0) {
                this.startTimer--;
                for (Player player : onlinePlayers) {
                    if (player.isOnline() && player.getWorld() == this.worldsManager.getCurrentWorld()) {
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("Lancement de la partie dans : " + TimeUtils.convertToMinutes(this.startTimer)));
                    }
                }
            }
            if (this.startTimer == 0 && onlinePlayers.size() > 2) {
                playStartTimer(onlinePlayers);
                this.gameManager.launch(false);
                this.gameManager.setGameStatus(GameStatus.PLAYING);
                cancel();
            }

            if (startTimer == 0 && !(onlinePlayers.size() > 2)) {
                this.startTimer += 30;
            }
        }
    }

    private void playStartTimer(Collection<? extends Player> onlinePlayers) {
        for (int i=1; i <= 10; i++) {
            if (i > 5) {
                for (Player player : onlinePlayers) {
                    if (player.isOnline() && player.getWorld() == this.worldsManager.getCurrentWorld()) {
                        player.sendTitle(String.valueOf(10 - i), null, -1, -1, -1);
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 100f, 1f);
                    }
                }
            }
        }
        for (Player player : onlinePlayers) {
            if (player.isOnline() && player.getWorld() == this.worldsManager.getCurrentWorld()) {
                player.sendTitle("Lancement de la partie", "Bonne chance à tous !", -1, -1, -1);
            }
        }
    }
}
