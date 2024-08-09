package org.nebich.infected.worlds;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.WorldCreator;
import org.nebich.infected.Infected;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

public class WorldsManager {
    private World currentWorld = null;
    private final Set<World> worlds = new HashSet<>();
    private final Infected plugin;

    public WorldsManager(Infected plugin) {
        this.plugin = plugin;
    }

    public World getCurrentWorld() {
        return this.currentWorld;
    }

    public void setCurrentWorld(World world) {
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
        this.setWorldBorder();
        Bukkit.getLogger().info("[Infected] Chargement des mondes effectué avec succès");
    }

    public void setRandomWorld() {
        int randomWorlIndex = new Random().nextInt(this.worlds.size());
        Iterator<World> worldsIterator = this.worlds.iterator();
        for(int i = 0; i < randomWorlIndex; i++) {
            worldsIterator.next();
        }
        this.setCurrentWorld(worldsIterator.next());
        this.setWorldBorder();
    }

    private void setWorldBorder() {
        WorldBorder currentWorldBorder = this.currentWorld.getWorldBorder();
        Location borderCenter = this.plugin.getConfig().getLocation("worlds.world_infected_city.border.center");
        double borderRadius = this.plugin.getConfig().getDouble("worlds.world_infected_city.border.radius");
        Bukkit.getLogger().info(String.format("[Infected] Radius : %s", borderRadius));
        currentWorldBorder.setSize(borderRadius);
        if (borderCenter != null) {
            currentWorldBorder.setCenter(borderCenter);
            Bukkit.getLogger().info("[Infected] Configuration du centre de la world border");
        }
        Bukkit.getLogger().info("[Infected] Configuration de la taille de la world border");
        Bukkit.getLogger().info("[Infected] World border créée avec succès");
    }
}
