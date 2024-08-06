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

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("[Infected] Plugin activé");
        this.worldsManager = new WorldsManager(this);
        this.playerManager = new PlayerManager(this, this.gameManager);
        this.gameManager = new GameManager(this, this.worldsManager, this.playerManager);
        this.worldsManager.loadWorlds();
        this.gameManager.startWaitingTask();
        this.loadCommands();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void loadCommands() {
        Objects.requireNonNull(this.getCommand("infected")).setExecutor(new InfectedCommand(this.worldsManager, this.playerManager));
        Bukkit.getLogger().info("[Infected] Chargement des commandes effectué avec succès");
    }
}
