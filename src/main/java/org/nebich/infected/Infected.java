package org.nebich.infected;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.java.JavaPlugin;
import org.nebich.infected.commands.JoinGameCommand;
import org.nebich.infected.worlds.WorldsManager;

import java.util.Objects;
import java.util.logging.Level;

public final class Infected extends JavaPlugin {
    private WorldsManager worldsManager;

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("[Infected] Plugin started");
        this.worldsManager = new WorldsManager();
        this.loadWorlds();
        this.loadCommands();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void loadWorlds() {
        try {
            WorldCreator worldCreator = new WorldCreator("world_infected_dead_island");
            World  createdWorld = worldCreator.createWorld();
            this.worldsManager.setCurrentWorld(createdWorld);
            Bukkit.getLogger().info("[Infected] Loaded world(s) successfully");
        } catch (Exception e) {
            Bukkit.getLogger().log(Level.WARNING, "[Infected] Error while loading world(s)");
        }
    }

    private void loadCommands() {
        Objects.requireNonNull(this.getCommand("join")).setExecutor(new JoinGameCommand(this.worldsManager));
    }
}
