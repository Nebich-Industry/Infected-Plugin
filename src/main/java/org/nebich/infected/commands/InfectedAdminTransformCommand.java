package org.nebich.infected.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.nebich.infected.Infected;
import org.nebich.infected.game.GameStatus;

public class InfectedAdminTransformCommand implements CommandExecutor {
    private final Infected plugin;

    protected InfectedAdminTransformCommand(Infected plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player && command.getName().equals("infected")) {
            if (this.plugin.getGameManager().getGameStatus() == GameStatus.PLAYING) {
                this.plugin.getPlayerManager().addZombie(player);
                player.sendMessage("Vous venez de vous transformer en zombie.");
                return true;
            } else {
                player.sendMessage("Vous ne pouvez pas vous transformer en zombie car la partie n'a pas encore commencé.");
            }
        }
        return false;
    }
}
