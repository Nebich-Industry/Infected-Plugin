package org.nebich.infected.zombies;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.nebich.infected.Infected;
import org.nebich.infected.player.InfectedPlayer;
import org.nebich.infected.tasks.zombies.SkeletonGiveArrowsTask;

public class Skeleton implements ZombieRole {
    private final InfectedPlayer infectedPlayer;
    private final Infected plugin;

    public Skeleton(Infected plugin, InfectedPlayer infectedPlayer) {
        this.infectedPlayer = infectedPlayer;
        this.plugin = plugin;
    }

    @Override
    public void transform() {
        final Player player = this.infectedPlayer.getPlayer();
        ItemStack bow = new ItemStack(Material.BOW);
        ItemMeta bowItemMeta = bow.getItemMeta();
        bowItemMeta.setUnbreakable(true);
        bow.setItemMeta(bowItemMeta);

        ItemStack arrows = new ItemStack(Material.ARROW, 5);
        player.getInventory().addItem(bow);
        player.getInventory().addItem(arrows);

        new SkeletonGiveArrowsTask(this.plugin, this.infectedPlayer);
    }
}
