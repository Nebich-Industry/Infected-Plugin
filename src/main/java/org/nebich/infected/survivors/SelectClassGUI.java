package org.nebich.infected.survivors;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.nebich.infected.Infected;

import java.util.Objects;

public class SelectClassGUI implements InventoryHolder, Listener {

    private final Inventory inventory;
    private final Infected plugin;

    public SelectClassGUI(Infected plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
        // Create an Inventory with 9 slots, `this` here is our InventoryHolder.
        this.inventory = plugin.getServer().createInventory(this, 9, ChatColor.BLACK + "Sélection de classes");
        ItemStack ninjaRoletem = this.createNinjaItem();
        ItemStack doctorRoleItem = this.createDoctorItem();
        ItemStack archerRoleItem = this.createArcherItem();

        this.inventory.setItem(2, ninjaRoletem);
        this.inventory.setItem(4, doctorRoleItem);
        this.inventory.setItem(6, archerRoleItem);
    }

    @NotNull
    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

    @EventHandler
    public void handleInventoryClick(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();
        // Check if the holder is our MyInventory,
        // if yes, use instanceof pattern matching to store it in a variable immediately.
        if (!(inventory.getHolder() instanceof SelectClassGUI) || event.getCurrentItem() == null) {
            // It's not our inventory, ignore it.
            return;
        }

        event.setCancelled(true);

        ItemStack clickedItem = event.getCurrentItem();
        Player player = (Player) event.getWhoClicked();

        String itemName = Objects.requireNonNull(Objects.requireNonNull(clickedItem).getItemMeta()).getDisplayName().toLowerCase();
        if (itemName.contains("ninja")) {
            player.sendMessage("Vous avez choisi la classe ninja.");
            plugin.getPlayerManager().selectSurvivorClass(player, new Ninja());
        }
        if (itemName.contains("docteur")) {
            player.sendMessage("Vous avez choisi la classe docteur.");
            plugin.getPlayerManager().selectSurvivorClass(player, new Doctor());
        }
        if (itemName.contains("archer")) {
            player.sendMessage("Vous avez choisi la classe archer.");
            plugin.getPlayerManager().selectSurvivorClass(player, new Archer());
        }
    }

    private ItemStack createNinjaItem() {
        ItemStack ninjaRole = new ItemStack(Material.NETHER_STAR);
        ItemMeta ninjaRoleMetaData = ninjaRole.getItemMeta();
        if (ninjaRoleMetaData != null) {
            ninjaRoleMetaData.setDisplayName(ChatColor.BLACK + "Ninja");
        }
        ninjaRole.setItemMeta(ninjaRoleMetaData);

        return ninjaRole;
    }

    private ItemStack createDoctorItem() {
        ItemStack doctorRole = new ItemStack(Material.SPLASH_POTION);
        ItemMeta doctorRoleMetaData = doctorRole.getItemMeta();
        if (doctorRoleMetaData != null) {
            doctorRoleMetaData.setDisplayName(ChatColor.DARK_RED + "Docteur");
        }
        doctorRole.setItemMeta(doctorRoleMetaData);

        return doctorRole;
    }

    private ItemStack createArcherItem() {
        ItemStack archerRole = new ItemStack(Material.BOW);
        ItemMeta archerRoleMetaData = archerRole.getItemMeta();
        if (archerRoleMetaData != null) {
            archerRoleMetaData.setDisplayName(ChatColor.DARK_GREEN + "Archer");
        }
        archerRole.setItemMeta(archerRoleMetaData);

        return archerRole;
    }
}
