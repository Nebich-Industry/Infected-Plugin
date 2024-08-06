package org.nebich.infected.player;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Zombie implements Listener {
    private Player player;
    private boolean isSelectedAtStart;

    public Zombie(Player player) {
        this.player = player;
        this.isSelectedAtStart = false;
    }

    public Zombie(Player player, boolean isSelectedAtStart) {
        this.player = player;
        this.isSelectedAtStart = isSelectedAtStart;
    }

    public Player getPlayer() {
        return player;
    }

    public void transform() {

    }
}
