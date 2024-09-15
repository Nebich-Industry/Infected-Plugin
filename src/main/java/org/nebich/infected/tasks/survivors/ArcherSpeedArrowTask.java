package org.nebich.infected.tasks.survivors;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionType;
import org.bukkit.scheduler.BukkitRunnable;
import org.nebich.infected.Infected;
import org.nebich.infected.game.GameStatus;
import org.nebich.infected.utils.TimeUtils;

public class ArcherSpeedArrowTask extends BukkitRunnable {
    private final Player player;
    private final Infected plugin;

    public ArcherSpeedArrowTask(Infected plugin, Player player) {
        runTaskTimer(plugin, 0, TimeUtils.seconds(30));
        this.player = player;
        this.plugin = plugin;
    }

    @Override
    public void run() {
        if (this.plugin.getGameManager().getGameStatus() == GameStatus.PLAYING) {
            ItemStack speedArrow = new ItemStack(Material.TIPPED_ARROW);
            PotionMeta potionMeta = (PotionMeta) speedArrow.getItemMeta();
            potionMeta.setBasePotionType(PotionType.SWIFTNESS);
            speedArrow.setItemMeta(potionMeta);
            this.player.getInventory().addItem(speedArrow);
        } else {
            cancel();
        }
    }
}
