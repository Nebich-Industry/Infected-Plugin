package org.nebich.infected.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.nebich.infected.Infected;

import java.util.HashMap;
import java.util.Map;

public class InfectedCommand implements CommandExecutor {
    private final Map<String, CommandExecutor> subCommandsMap = new HashMap<>();

    public InfectedCommand(Infected plugin) {
        this.subCommandsMap.put(CommandPrefix.JOIN, new JoinGameCommand(plugin));
        this.subCommandsMap.put("admin", new InfectedAdminCommand(plugin));
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (commandSender instanceof Player player && command.getName().equals("infected")) {
            if (args[0].isEmpty()) {
                player.sendMessage("Usage : /infected <command>");
                if (player.isOp()) {
                    player.sendMessage("Commands available : join, admin");
                } else {
                    player.sendMessage("Commands available : join");
                }
                return true;
            }

            if (args[0].equals(CommandPrefix.JOIN)) {
                this.subCommandsMap.get(CommandPrefix.JOIN).onCommand(commandSender, command, s, args);
                return true;
            }
            if (args[0].equals(CommandPrefix.ADMIN)) {
                this.subCommandsMap.get(CommandPrefix.ADMIN).onCommand(commandSender, command, s, args);
                return true;
            }
        }
        return false;
    }
}
