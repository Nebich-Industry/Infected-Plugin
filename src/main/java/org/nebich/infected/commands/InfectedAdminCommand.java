package org.nebich.infected.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.nebich.infected.Infected;

import java.util.HashMap;
import java.util.Map;

public class InfectedAdminCommand implements CommandExecutor {
    private final Map<String, CommandExecutor> subCommandsMap = new HashMap<>();

    protected InfectedAdminCommand(Infected plugin) {
        this.subCommandsMap.put(CommandPrefix.LAUNCH, new InfectedAdminStartCommand(plugin));
        this.subCommandsMap.put(CommandPrefix.TRANSFORM, new InfectedAdminTransformCommand(plugin));
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (commandSender instanceof Player player && command.getName().equals("infected") && player.isOp()) {
            if (args.length == 1) {
                player.sendMessage("Usage : /infected admin <command>");
                player.sendMessage("Commands available : launch, transform");
                return true;
            }
            if (!args[0].isEmpty()) {
                if (args[1].equals(CommandPrefix.LAUNCH)) {
                    this.subCommandsMap.get(CommandPrefix.LAUNCH).onCommand(commandSender, command, s, args);
                }
                if (args[1].equals(CommandPrefix.TRANSFORM)) {
                    this.subCommandsMap.get(CommandPrefix.TRANSFORM).onCommand(commandSender, command, s, args);
                }
            }
        }
        return false;
    }
}
