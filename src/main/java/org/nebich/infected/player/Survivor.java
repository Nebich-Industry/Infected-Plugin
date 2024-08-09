package org.nebich.infected.player;

import org.bukkit.entity.Player;

public class Survivor {
    private final Player player;

    public Survivor(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
