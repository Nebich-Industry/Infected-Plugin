package org.nebich.infected.player;

import org.bukkit.entity.Player;

public class InfectedPlayer {
    private final Player player;
    private boolean isZombie;
    private boolean isSurvivor;

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
}
