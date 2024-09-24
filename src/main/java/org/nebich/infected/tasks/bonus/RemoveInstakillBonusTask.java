package org.nebich.infected.tasks.bonus;

import org.bukkit.scheduler.BukkitRunnable;
import org.nebich.infected.Infected;
import org.nebich.infected.player.InfectedPlayer;
import org.nebich.infected.utils.TimeUtils;

public class RemoveInstakillBonusTask extends BukkitRunnable {
    private final InfectedPlayer infectedPlayer;

    public RemoveInstakillBonusTask(Infected plugin, InfectedPlayer infectedPlayer) {
        this.infectedPlayer = infectedPlayer;
        runTaskLater(plugin, TimeUtils.seconds(10));
    }

    @Override
    public void run() {
        this.infectedPlayer.setHasInstakillBonus(false);
    }
}
