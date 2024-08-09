package org.nebich.infected;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.nebich.infected.commands.InfectedCommand;
import org.nebich.infected.game.GameManager;
import org.nebich.infected.player.PlayerManager;
import org.nebich.infected.worlds.WorldsManager;

import java.util.Objects;

public class Infected extends JavaPlugin {
    private WorldsManager worldsManager;
    private PlayerManager playerManager;
    private GameManager gameManager;
    private static Infected instance;

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("[Infected] Plugin activé");
        instance = this;
        this.worldsManager = new WorldsManager(this);
        this.playerManager = new PlayerManager(this);
        this.gameManager = new GameManager(this);
        this.worldsManager.loadWorlds();
        this.gameManager.startWaitingTask();
        this.loadCommands();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void loadCommands() {
        Objects.requireNonNull(this.getCommand("infected")).setExecutor(new InfectedCommand(this));
        Bukkit.getLogger().info("[Infected] Chargement des commandes effectué avec succès");
    }

    public static Infected getInstance() {
        return instance;
    }

    public GameManager getGameManager() {
        return this.gameManager;
    }

    public PlayerManager getPlayerManager() {
        return this.playerManager;
    }

    public WorldsManager getWorldsManager() {
        return this.worldsManager;
    }
}
