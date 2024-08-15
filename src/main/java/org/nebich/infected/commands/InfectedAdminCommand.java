package org.nebich.infected.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.nebich.infected.game.GameManager;
import org.nebich.infected.player.PlayerManager;

import java.util.HashMap;
import java.util.Map;

public class InfectedAdminCommand implements CommandExecutor {
    private final Map<String, CommandExecutor> subCommandsMap = new HashMap<>();

    protected InfectedAdminCommand(GameManager gameManager, PlayerManager playerManager) {
        this.subCommandsMap.put("launch", new InfectedAdminStartCommand(gameManager));
        this.subCommandsMap.put("transform", new InfectedAdminTransformCommand(playerManager, gameManager));
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (commandSender instanceof Player player && command.getName().equals("infected")) {
            if (args.length == 1) {
                player.sendMessage("Usage : /infected admin <command>");
                player.sendMessage("Commands available : launch, transform");
                return true;
            }
            if (!args[0].isEmpty()) {
                if (args[1].equals("launch")) {
                    this.subCommandsMap.get("launch").onCommand(commandSender, command, s, args);
                }
                if (args[1].equals("transform")) {
                    this.subCommandsMap.get("transform").onCommand(commandSender, command, s, args);
                }
            }
        }
        return false;
    }
}
