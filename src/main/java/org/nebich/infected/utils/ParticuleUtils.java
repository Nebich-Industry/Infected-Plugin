package org.nebich.infected.utils;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class ParticuleUtils {
    private ParticuleUtils() {
        throw new IllegalStateException("ParticuleUtils class");
    }

    public static void generateSphere(Location center, Particle particle, Player player, boolean halfSphere) {
        double[][] coordinates = new double[11 * 10 * 2][];
        int arrayLocation = 0;
        double loopLimit = Math.PI;
        if (halfSphere) {
            loopLimit = loopLimit / 2;
        }
        for (double i = 0; i <= loopLimit; i += Math.PI / 10) {
            double radius = Math.sin(i);
            double y = Math.cos(i);
            for (double a = 0; a < loopLimit * 2; a += Math.PI / 10) {
                double x = Math.cos(a) * radius;
                double z = Math.sin(a) * radius;
                coordinates[arrayLocation++] = new double[] { x, y, z };
            }
        }
        for (double[] coordinate : coordinates) {
            center.add(coordinate[0], coordinate[1], coordinate[2]);
            player.spawnParticle(particle, center, 1);
            center.subtract(coordinate[0], coordinate[1], coordinate[2]);
        }
    }
}
