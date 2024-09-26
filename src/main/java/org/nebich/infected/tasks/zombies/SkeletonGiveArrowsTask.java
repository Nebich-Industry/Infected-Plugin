package org.nebich.infected.tasks.zombies;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.checkerframework.checker.units.qual.Time;
import org.nebich.infected.Infected;
import org.nebich.infected.game.GameStatus;
import org.nebich.infected.player.InfectedPlayer;
import org.nebich.infected.utils.TimeUtils;
import org.nebich.infected.zombies.Skeleton;

public class SkeletonGiveArrowsTask extends BukkitRunnable {
    private final Infected plugin;
    private final InfectedPlayer infectedPlayer;

    public SkeletonGiveArrowsTask(Infected plugin, InfectedPlayer infectedPlayer) {
        this.plugin = plugin;
        this.infectedPlayer = infectedPlayer;
        runTaskTimer(plugin, 0, TimeUtils.seconds(10));
    }

    @Override
    public void run() {
        if (this.plugin.getGameManager().getGameStatus() != GameStatus.PLAYING) {
            cancel();
        }

        final Player player = this.infectedPlayer.getPlayer();
        player.getInventory().addItem(new ItemStack(Material.ARROW));
    }
}
