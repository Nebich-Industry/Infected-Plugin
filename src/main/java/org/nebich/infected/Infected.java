package org.nebich.infected;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.nebich.infected.commands.InfectedCommand;
import org.nebich.infected.game.GameManager;
import org.nebich.infected.player.PlayerManager;
import org.nebich.infected.survivors.SelectClassGUI;
import org.nebich.infected.worlds.WorldsManager;

import java.util.Objects;

public class Infected extends JavaPlugin {
    private WorldsManager worldsManager;
    private PlayerManager playerManager;
    private GameManager gameManager;
    private SelectClassGUI selectClassGUI;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        Bukkit.getLogger().info("[Infected] Plugin activé");
        this.worldsManager = new WorldsManager(this);
        this.playerManager = new PlayerManager(this);
        this.gameManager = new GameManager(this);
        this.worldsManager.loadWorlds();
        this.gameManager.startWaitingTask();
        this.selectClassGUI = new SelectClassGUI(this);
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

    public GameManager getGameManager() {
        return this.gameManager;
    }

    public PlayerManager getPlayerManager() {
        return this.playerManager;
    }

    public WorldsManager getWorldsManager() {
        return this.worldsManager;
    }

    public SelectClassGUI getSelectClassGUI() {
        return selectClassGUI;
    }
}
