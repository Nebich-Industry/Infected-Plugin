package org.nebich.infected;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class Infected extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("[Infected] Plugin started");
        Bukkit.getLogger().log(Level.INFO, "[Infected] Plugin data folder path : %s", getDataFolder().getPath());
        WorldCreator worldCreator = new WorldCreator(getDataFolder().getPath()+"/maps/world_dead_island");
        worldCreator.environment(World.Environment.NORMAL);
        worldCreator.type(WorldType.NORMAL);
        worldCreator.createWorld();
        // Plugin startup logic
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
