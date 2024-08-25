package org.nebich.infected.tasks.bonus;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Item;
import org.bukkit.scheduler.BukkitRunnable;
import org.nebich.infected.Infected;
import org.nebich.infected.items.bonus.Bonus;
import org.nebich.infected.items.bonus.FumigeneItem;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SpawnBonusTask extends BukkitRunnable {
    private final Infected plugin;
    private final Map<Integer, Bonus> bonusMap = new HashMap<>();

    public SpawnBonusTask(Infected plugin) {
        this.plugin = plugin;
        this.bonusMap.put(0, new FumigeneItem(this.plugin));
        this.bonusMap.put(1, new FumigeneItem(this.plugin));
        this.bonusMap.put(2, new FumigeneItem(this.plugin));
        this.runTaskTimer(this.plugin, 0, 20);
    }

    @Override
    public void run() {
        int gameTimer = this.plugin.getGameManager().getGameTimer();
        if (gameTimer < 4 * 60 && gameTimer % 20 == 0) {
            Location generatedLocation = this.plugin.getWorldsManager().getCurrentWorld().getHighestBlockAt(this.getRandomLocationInsideWorldBorder()).getLocation();
            generatedLocation.setY(generatedLocation.getY() + 1);
            Item itemDropped = this.plugin.getWorldsManager().getCurrentWorld().dropItem(generatedLocation, getBonusToSpawn().getItem());
            itemDropped.setGravity(false);
        }
    }

    private Location getRandomLocationInsideWorldBorder() {
        World currentGameWorld = this.plugin.getWorldsManager().getCurrentWorld();
        double wbSize = currentGameWorld.getWorldBorder().getSize() / 2;
        Location wbCenter = currentGameWorld.getWorldBorder().getCenter();
        Location randomLocation;

        Random random = new Random();
        double x = random.nextDouble(-wbSize, wbSize);
        double z = random.nextDouble(-wbSize, wbSize);
        randomLocation = wbCenter.add(x, wbCenter.getY(), z);

        while (!currentGameWorld.getWorldBorder().isInside(randomLocation)) {
            x = random.nextDouble(-wbSize, wbSize);
            z = random.nextDouble(-wbSize, wbSize);
            randomLocation = wbCenter.add(x, wbCenter.getY(), z);
        }

        return randomLocation;
    }

    private Bonus getBonusToSpawn() {
        Random random = new Random();
        int randomIndex = random.nextInt(this.bonusMap.values().size());
        return bonusMap.get(randomIndex);
    }
}
