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
import org.nebich.infected.survivors.SelectRoleItem;

import java.util.logging.Level;

public class JoinGameCommand implements CommandExecutor {
    private final Infected plugin;

    public JoinGameCommand(Infected plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player) {
            try {
                this.plugin.getWorldsManager().teleportToWorld(player);
                player.setGameMode(GameMode.ADVENTURE);
                this.plugin.getPlayerManager().addPlayer(player);
                player.getInventory().setItem(4, new SelectRoleItem(this.plugin).getItemStack());
                return true;
            } catch (Exception e) {
                player.sendMessage("Une erreur s'est produite lors de la téléportation");
                Bukkit.getLogger().log(Level.WARNING, "[Infected] Erreur lors de la téléportation.");
            }
        }
        return false;
    }
}
