package org.nebich.infected;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.nebich.infected.commands.InfectedCommand;
import org.nebich.infected.worlds.WorldsManager;

import java.util.Objects;

public class Infected extends JavaPlugin {
    private WorldsManager worldsManager;

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("[Infected] Plugin started");
        this.worldsManager = new WorldsManager();
        this.worldsManager.loadWorlds();
        this.loadCommands();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void loadCommands() {
        Objects.requireNonNull(this.getCommand("infected")).setExecutor(new InfectedCommand(this.worldsManager));
        Bukkit.getLogger().info("[Infected] Loaded commands successfully");
    }
}
