package org.nebich.infected.survivors;

import org.bukkit.entity.Player;
import org.nebich.infected.Infected;
import org.nebich.infected.player.InfectedPlayer;

import java.util.Optional;

public abstract class Role {
    private final Player player;
    private final Infected plugin;

    protected Role(Infected plugin, Player player) {
        this.player = player;
        this.plugin = plugin;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Optional<InfectedPlayer> getInfectedPlayer() {
        return this.plugin.getPlayerManager().getPlayer(this.player.getUniqueId());
    }
}
