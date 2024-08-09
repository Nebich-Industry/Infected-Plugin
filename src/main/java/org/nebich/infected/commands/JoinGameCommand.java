package org.nebich.infected.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.nebich.infected.Infected;
import org.nebich.infected.player.PlayerManager;
import org.nebich.infected.worlds.WorldsManager;

import java.util.logging.Level;

public class JoinGameCommand implements CommandExecutor {
    private final WorldsManager worldsManager;
    private final PlayerManager playerManager;
    private final Infected plugin;

    public JoinGameCommand(WorldsManager worldsManager, PlayerManager playerManager, Infected plugin) {
        this.worldsManager = worldsManager;
        this.playerManager = playerManager;
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player) {
            try {
                World currentWorldPlaying = this.worldsManager.getCurrentWorld();
                Location teleportToGame = new Location(currentWorldPlaying, this.plugin.getConfig().getDouble("worlds."+currentWorldPlaying.getName()+".spawn.x"), this.plugin.getConfig().getDouble("worlds."+currentWorldPlaying.getName()+".spawn.y"), this.plugin.getConfig().getDouble("worlds."+currentWorldPlaying.getName()+".spawn.z"));
                player.setGameMode(GameMode.ADVENTURE);
                player.teleport(teleportToGame);
                this.playerManager.addPlayer(player);
                return true;
            } catch (Exception e) {
                player.sendMessage("Une erreur s'est produite lors de la téléportation");
                Bukkit.getLogger().log(Level.WARNING, "[Infected] Erreur lors de la téléportation.");
                e.printStackTrace();
            }
        }
        return false;
    }
}
