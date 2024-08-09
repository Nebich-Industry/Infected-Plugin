package org.nebich.infected.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.nebich.infected.player.PlayerManager;

public class InfectedAdminTransformCommand implements CommandExecutor {
    private final PlayerManager playerManager;

    protected InfectedAdminTransformCommand(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player && command.getName().equals("infected")) {
            this.playerManager.addZombie(player);
            player.sendMessage("Vous venez de vous transformer en zombie.");
            return true;
        }
        return false;
    }
}
