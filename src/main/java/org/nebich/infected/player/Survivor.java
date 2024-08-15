package org.nebich.infected.player;

import org.bukkit.entity.Player;
import org.nebich.infected.survivors.Role;

public class Survivor {
    private final Player player;
    private Role role;

    public Survivor(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
