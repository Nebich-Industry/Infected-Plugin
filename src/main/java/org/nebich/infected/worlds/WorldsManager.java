package org.nebich.infected.worlds;

import org.bukkit.World;

public class WorldsManager {
    private World currentWorld = null;

    public WorldsManager() {

    }

    public World getCurrentWorld() {
        return this.currentWorld;
    }

    public void setCurrentWorld(World world) {
        this.currentWorld = world;
    }
}
