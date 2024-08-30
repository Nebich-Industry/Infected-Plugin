package org.nebich.infected.player;

import org.bukkit.entity.Player;
import org.nebich.infected.survivors.Role;

public class Survivor extends InfectedPlayer {
    private Role role;

    public Survivor(Player player) {
        super(player);
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
