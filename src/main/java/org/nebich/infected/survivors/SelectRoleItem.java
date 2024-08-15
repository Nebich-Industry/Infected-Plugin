package org.nebich.infected.survivors;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.nebich.infected.Infected;

import java.util.Objects;

public class SelectRoleItem implements Listener {
    private final ItemStack itemStack;
    private final Infected plugin;

    public SelectRoleItem(Infected plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;

        ItemStack itemStack = new ItemStack(Material.NETHER_STAR);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.BOLD + ChatColor.AQUA.toString() + "Sélection de classes");
        itemMeta.setItemName("role-selection");
        itemStack.setItemMeta(itemMeta);

        this.itemStack = itemStack;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    @EventHandler
    public void handleClickOnItem(PlayerInteractEvent event) {
        if (event.getItem() == null) return;
        ItemStack itemClicked = event.getItem();

        if ((event.getAction() != Action.PHYSICAL && Objects.requireNonNull(itemClicked.getItemMeta()).getItemName().equals("role-selection"))) {
            event.setCancelled(true);
            Player player = event.getPlayer();
            player.openInventory(this.plugin.getSelectClassGUI().getInventory());
        }
    }
}
