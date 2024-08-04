package org.nebich.infected.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.nebich.infected.worlds.WorldsManager;

import java.util.logging.Level;

public class JoinGameCommand implements CommandExecutor {
    private final WorldsManager worldsManager;

    public JoinGameCommand(WorldsManager worldsManager) {
        this.worldsManager = worldsManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player) {
            try {
                World currentWorldPlaying = this.worldsManager.getCurrentWorld();
                Location teleportToGame = new Location(currentWorldPlaying, -60, 67, -28);
                player.teleport(teleportToGame);
                return true;
            } catch (Exception e) {
                Bukkit.getLogger().log(Level.WARNING, "[Infected] Failed to teleport");
            }
        }
        return false;
    }
}
