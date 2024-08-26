package org.nebich.infected.tasks.game;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.nebich.infected.Infected;
import org.nebich.infected.game.GameStatus;
import org.nebich.infected.utils.TimeUtils;

import java.util.Collection;

public class GameStartTask extends BukkitRunnable {
    private int startTimer;
    private final Infected plugin;

    public GameStartTask(Infected plugin) {
        this.plugin = plugin;
        this.startTimer = this.plugin.getGameManager().getWaitingTimer();
        this.runTaskTimer(this.plugin, 0, TimeUtils.seconds(1));
    }

    @Override
    public void run() {
        if (this.plugin.getGameManager().getGameStatus() == GameStatus.PLAYING) {
            cancel();
        }
        Collection<? extends Player> onlinePlayers = this.plugin.getServer().getOnlinePlayers();
        if (this.startTimer > 0) {
            this.startTimer--;
            for (Player player : onlinePlayers) {
                if (player.isOnline() && player.getWorld() == this.plugin.getWorldsManager().getCurrentWorld()) {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("Lancement de la partie dans : " + TimeUtils.convertToMinutes(this.startTimer)));
                }
            }
        }
        if (this.startTimer == 0) {
            if (onlinePlayers.size() > 2) {
                playStartTimer(onlinePlayers);
                this.plugin.getGameManager().launch(false);
                this.plugin.getGameManager().setGameStatus(GameStatus.PLAYING);
                cancel();
            } else {
                this.startTimer += 30;
            }
        }
    }

    private void playStartTimer(Collection<? extends Player> onlinePlayers) {
        for (int i = 1; i <= 10; i++) {
            if (i > 5) {
                for (Player player : onlinePlayers) {
                    if (player.isOnline() && player.getWorld() == this.plugin.getWorldsManager().getCurrentWorld()) {
                        player.sendTitle(String.valueOf(10 - i), null, -1, -1, -1);
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 100f, 1f);
                    }
                }
            }
        }
        for (Player player : onlinePlayers) {
            if (player.isOnline() && player.getWorld() == this.plugin.getWorldsManager().getCurrentWorld()) {
                player.sendTitle("Lancement de la partie", "Bonne chance à tous !", -1, -1, -1);
            }
        }
    }
}
