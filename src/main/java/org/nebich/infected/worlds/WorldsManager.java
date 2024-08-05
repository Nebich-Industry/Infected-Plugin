package org.nebich.infected.worlds;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class WorldsManager {
    private World currentWorld = null;
    private final Set<World> worlds = new HashSet<>();

    public WorldsManager() {

    }

    public World getCurrentWorld() {
        return this.currentWorld;
    }

    private void setCurrentWorld(World world) {
        this.currentWorld = world;
    }

    private List<String> getAllWorlds() {
        List<String> infectedWorlds = new ArrayList<>();
        for (File potentialWorld : Objects.requireNonNull(Bukkit.getServer().getWorldContainer().listFiles())) {
            if (potentialWorld.isDirectory() && Arrays.asList(Objects.requireNonNull(potentialWorld.list())).contains("level.dat") && potentialWorld.getName().startsWith("world_infected")) {
                infectedWorlds.add(potentialWorld.getName());
            }
        }
        return infectedWorlds;
    }

    public void loadWorlds() {
        for (String worldName : this.getAllWorlds()) {
            WorldCreator worldCreator = new WorldCreator(worldName);
            World createdWorld = worldCreator.createWorld();
            this.worlds.add(createdWorld);
        }
        this.setCurrentWorld(this.worlds.iterator().next());
        Bukkit.getLogger().info("[Infected] Loaded world(s) successfully");
    }
}
