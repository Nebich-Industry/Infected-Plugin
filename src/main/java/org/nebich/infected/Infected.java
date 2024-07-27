package org.nebich.infected;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Infected extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("[Infected] Plugin started");
        // Plugin startup logic
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
