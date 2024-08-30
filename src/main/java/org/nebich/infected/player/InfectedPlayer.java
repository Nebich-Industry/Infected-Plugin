package org.nebich.infected.player;

import org.bukkit.entity.Player;
import org.nebich.infected.survivors.Role;

public class InfectedPlayer {
    private final Player player;
    private Role role;
    private boolean isZombie;
    private boolean isSurvivor;
    private boolean isSelectedAtStart = false;

    protected InfectedPlayer(Player player) {
        this.player = player;
        this.isSurvivor = true;
        this.isZombie = false;
    }

    protected InfectedPlayer(Player player, boolean isZombie) {
        this.player = player;
        this.isSurvivor = !isZombie;
        this.isZombie = isZombie;
    }

    public Player getPlayer() {
        return this.player;
    }

    public boolean isZombie() {
        return this.isZombie;
    }

    public void setIsZombie(boolean bool) {
        this.isZombie = bool;
    }

    public boolean isSurvivor() {
        return this.isSurvivor;
    }

    public void setIsSurvivor(boolean bool) {
        this.isSurvivor = bool;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void transform() {
        // Will be implemented with zombies features
    }

    public boolean isSelectedAtStart() {
        return this.isSelectedAtStart;
    }

    public void setSelectedAtStart(boolean selectedAtStart) {
        this.isSelectedAtStart = selectedAtStart;
    }
}
