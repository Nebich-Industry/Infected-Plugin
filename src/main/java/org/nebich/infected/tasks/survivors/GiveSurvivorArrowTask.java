package org.nebich.infected.tasks.survivors;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.nebich.infected.Infected;
import org.nebich.infected.game.GameStatus;
import org.nebich.infected.player.InfectedPlayer;
import org.nebich.infected.utils.TimeUtils;

public class GiveSurvivorArrowTask extends BukkitRunnable {
    private final Infected plugin;
    private final InfectedPlayer player;

    public GiveSurvivorArrowTask(Infected plugin, InfectedPlayer infectedPlayer) {
        this.player = infectedPlayer;
        this.plugin = plugin;
        runTaskTimer(plugin, 0, TimeUtils.seconds(20));
    }

    @Override
    public void run() {
        if (this.plugin.getGameManager().getGameStatus() == GameStatus.WAITING) {
            cancel();
        }

        if (this.player.isSurvivor()) {
            this.player.getPlayer().getInventory().addItem(new ItemStack(Material.ARROW));
        }
    }
}
