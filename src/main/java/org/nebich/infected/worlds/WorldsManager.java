package org.nebich.infected.worlds;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.ConfigurationSection;
import org.nebich.infected.Infected;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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
        ConfigurationSection configurationSection = this.plugin.getConfig().getConfigurationSection("worlds");

        if (configurationSection != null) {
            return new ArrayList<>(configurationSection.getKeys(false));
        } else {
            Bukkit.getLogger().warning("[Infected] La clé de configuration worlds n'est pas présente dans le fichier conf.yml !");
            return null;
        }
    }

    public void loadWorlds() {
        List<String> worlds = this.getAllWorlds();
        if (worlds != null) {
            for (String worldName : worlds) {
                WorldCreator worldCreator = new WorldCreator(worldName);
                World createdWorld = worldCreator.createWorld();
                this.worlds.add(createdWorld);
            }
            this.setCurrentWorld(this.worlds.iterator().next());
            this.setWorldBorder();
            Bukkit.getLogger().info("[Infected] Chargement des mondes effectué avec succès");
        }
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
        Location borderCenter = new Location(this.currentWorld, this.plugin.getConfig().getDouble("worlds."+this.currentWorld.getName()+".spawn.x"), this.plugin.getConfig().getDouble("worlds."+this.currentWorld.getName()+".spawn.y"), this.plugin.getConfig().getDouble("worlds."+this.currentWorld.getName()+".spawn.z"));
        double borderRadius = this.plugin.getConfig().getDouble("worlds."+this.currentWorld.getName()+".border.radius");
        Bukkit.getLogger().info(String.format("[Infected] Radius : %s", borderRadius));
        currentWorldBorder.setSize(borderRadius);
        currentWorldBorder.setCenter(borderCenter);
        Bukkit.getLogger().info("[Infected] Configuration du centre de la world border");
        Bukkit.getLogger().info("[Infected] Configuration de la taille de la world border");
        Bukkit.getLogger().info("[Infected] World border créée avec succès");
    }
}
