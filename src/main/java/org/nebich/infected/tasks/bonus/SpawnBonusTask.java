package org.nebich.infected.tasks.bonus;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Item;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.nebich.infected.Infected;
import org.nebich.infected.items.bonus.Bonus;
import org.nebich.infected.items.bonus.FumigeneItem;
import org.nebich.infected.items.bonus.HealItem;
import org.nebich.infected.items.bonus.InstakillItem;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public class SpawnBonusTask extends BukkitRunnable {
    private final Infected plugin;
    private final Map<Integer, Bonus> bonusMap = new HashMap<>();
    private final SecureRandom random = new SecureRandom();

    public SpawnBonusTask(Infected plugin) {
        this.plugin = plugin;
        this.bonusMap.put(0, new FumigeneItem(this.plugin));
        this.bonusMap.put(1, new HealItem(this.plugin));
        this.bonusMap.put(2, new InstakillItem(this.plugin));
        this.bonusMap.put(3, new InvisibilityZombieBonus(this.plugin));
        this.runTaskTimer(this.plugin, 0, 20);
    }

    @Override
    public void run() {
        int gameTimer = this.plugin.getGameManager().getGameTimer();
        if (gameTimer <= 4 * 60 && gameTimer % 20 == 0 && gameTimer > 1) {
            Location generatedLocation = this.plugin.getWorldsManager().getCurrentWorld().getHighestBlockAt(this.getRandomLocationInsideWorldBorder()).getLocation().add(0, 2, 0);
            Bukkit.getLogger().info(String.format("DEBUG: Bonus location : %s", generatedLocation));
            Item itemDropped = this.plugin.getWorldsManager().getCurrentWorld().dropItem(generatedLocation, getBonusToSpawn().getItem());
            itemDropped.setGravity(false);
            itemDropped.setVelocity(new Vector(0,0,0));
        }
    }

    private Location getRandomLocationInsideWorldBorder() {
        World currentGameWorld = this.plugin.getWorldsManager().getCurrentWorld();
        double wbSize = currentGameWorld.getWorldBorder().getSize() / 2;
        Location wbCenter = currentGameWorld.getWorldBorder().getCenter();
        Location randomLocation;

        double x = this.random.nextDouble(-wbSize, wbSize);
        double z = this.random.nextDouble(-wbSize, wbSize);
        // Récupération de la valeur du spawn du monde de la config car le centre de la world border est set à Y = 0
        String worldConfigKey = "worlds."+this.plugin.getWorldsManager().getCurrentWorld().getName();
        double worldSpawnY = this.plugin.getConfig().getDouble(worldConfigKey+".spawn.y");
        randomLocation = wbCenter.add(x, worldSpawnY, z);

        while (!currentGameWorld.getWorldBorder().isInside(randomLocation)) {
            x = this.random.nextDouble(-wbSize, wbSize);
            z = this.random.nextDouble(-wbSize, wbSize);
            randomLocation = wbCenter.add(x, wbCenter.getY(), z);
        }

        return randomLocation;
    }

    private Bonus getBonusToSpawn() {
        int randomIndex = this.random.nextInt(this.bonusMap.values().size());
        return bonusMap.get(randomIndex);
    }
}
