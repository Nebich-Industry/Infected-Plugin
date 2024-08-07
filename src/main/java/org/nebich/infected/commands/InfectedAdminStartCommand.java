package org.nebich.infected.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.nebich.infected.game.GameManager;

public class InfectedAdminStartCommand implements CommandExecutor {
    private final GameManager gameManager;

    protected InfectedAdminStartCommand(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player && command.getName().equals("infected")) {
            this.gameManager.launch(true);
            player.sendMessage("Vous venez de lancer la partie.");
            return true;
        }
        return false;
    }
}
