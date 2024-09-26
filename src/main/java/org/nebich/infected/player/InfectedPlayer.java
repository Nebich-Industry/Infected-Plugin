package org.nebich.infected.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.nebich.infected.Infected;
import org.nebich.infected.scoreboard.InfectedScoreboard;
import org.nebich.infected.survivors.Role;
import org.nebich.infected.tasks.zombies.ZombieUpgradeTask;
import org.nebich.infected.zombies.ZombieRole;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class InfectedPlayer implements Listener {
    private final Player player;
    private Role role;
    private ZombieRole zombieRole;
    private boolean isZombie;
    private boolean isSurvivor;
    private boolean isSelectedAtStart = false;
    private boolean hasInstakillBonus = false;
    private final Infected plugin;
    private final List<Player> killedPlayersAsZombie = new ArrayList<>();

    protected InfectedPlayer(Infected plugin, Player player) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
        this.player = player;
        this.isSurvivor = true;
        this.isZombie = false;
        this.plugin = plugin;
    }

    protected InfectedPlayer(Infected plugin, Player player, boolean isZombie) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
        this.player = player;
        this.isSurvivor = !isZombie;
        this.isZombie = isZombie;
        this.plugin = plugin;
        if (isZombie) {
            new ZombieUpgradeTask(this.plugin, this);
        }
    }

    public Player getPlayer() {
        return this.player;
    }

    public boolean isZombie() {
        return this.isZombie;
    }

    public void setIsZombie(boolean bool) {
        this.isZombie = bool;
        this.isSurvivor = !bool;
    }

    public boolean isSurvivor() {
        return this.isSurvivor;
    }

    public void setIsSurvivor(boolean bool) {
        this.isSurvivor = bool;
        this.isZombie = !bool;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void transformInZombie() {
        this.setIsZombie(true);
        InfectedScoreboard infectedScoreboard = this.plugin.getInfectedScoreboard();
        infectedScoreboard.removeSurvivorTeamEntry(this.player);
        infectedScoreboard.addZombieTeamEntry(this.player);
        if (this.role != null) {
            this.setRole(null);
        }
        new ZombieUpgradeTask(this.plugin, this);
    }

    public void transformInSurvivor() {
        this.setIsSurvivor(true);
        InfectedScoreboard infectedScoreboard = this.plugin.getInfectedScoreboard();
        infectedScoreboard.removeZombieTeamEntry(this.player);
        infectedScoreboard.addSurvivorTeamEntry(this.player);
        if (this.zombieRole != null) {
            this.setZombieRole(null);
        }
        this.setRole(this.plugin.getPlayerManager().getRandomRole(this.player));
    }

    public boolean isSelectedAtStart() {
        return this.isSelectedAtStart;
    }

    public void setSelectedAtStart(boolean selectedAtStart) {
        this.isSelectedAtStart = selectedAtStart;
    }

    public boolean hasInstakillBonus() {
        return hasInstakillBonus;
    }

    public void setHasInstakillBonus(boolean hasInstakillBonus) {
        this.hasInstakillBonus = hasInstakillBonus;
    }

    public void addZombieKill(Player player) {
        this.killedPlayersAsZombie.add(player);
    }

    public List<Player> getKilledPlayersAsZombie() {
        return this.killedPlayersAsZombie;
    }

    public ZombieRole getZombieRole() {
        return zombieRole;
    }

    public void setZombieRole(ZombieRole zombieRole) {
        this.zombieRole = zombieRole;
    }

    @EventHandler
    public void handlePlayerDeath(PlayerDeathEvent event) {
        final Player deadPlayer = event.getEntity();
        Optional<InfectedPlayer> optionalDeadPlayerInfected = this.plugin.getPlayerManager().getPlayer(deadPlayer.getUniqueId());
        optionalDeadPlayerInfected.ifPresent(deadPlayerInfected -> {
            if (deadPlayerInfected.isSurvivor()) {
                deadPlayerInfected.transformInZombie();
            }

            if (deadPlayerInfected.getPlayer().getUniqueId() == this.getPlayer().getUniqueId()) {
                this.plugin.getWorldsManager().teleportToWorld(player);
            }
        });

        Optional<InfectedPlayer> optionalKillerInfectedPlayer = this.plugin.getPlayerManager().getPlayer(Objects.requireNonNull(deadPlayer.getKiller()).getUniqueId());
        optionalKillerInfectedPlayer.ifPresent(killerInfectedPlayer -> {
            if (killerInfectedPlayer.isZombie()) {
                killerInfectedPlayer.addZombieKill(deadPlayer);
            }
        });
    }

    @EventHandler
    public void handleSurvivorDamages(EntityDamageByEntityEvent event) {
        event.setCancelled(true);
        if (event.getEntity() instanceof Player attackedPlayer && event.getDamager() instanceof Player damagerPlayer) {
            Optional<InfectedPlayer> attackedOptionalInfectedPlayer = this.plugin.getPlayerManager().getPlayer(attackedPlayer.getUniqueId());
            Optional<InfectedPlayer> damagerOptionalInfectedPlayer = this.plugin.getPlayerManager().getPlayer(damagerPlayer.getUniqueId());

            if (attackedOptionalInfectedPlayer.isPresent() && damagerOptionalInfectedPlayer.isPresent()) {
                InfectedPlayer attackedinfectedPlayer = attackedOptionalInfectedPlayer.get();
                InfectedPlayer damagerInfectedPlayer = damagerOptionalInfectedPlayer.get();

                if (damagerInfectedPlayer.isSurvivor() && attackedinfectedPlayer.isZombie()) {
                    dealSurvivorDamages(attackedinfectedPlayer);
                }
            }
        }
    }

    private void dealSurvivorDamages(InfectedPlayer zombieToAttack) {
        final Player zombieToAttackPlayer = zombieToAttack.getPlayer();
        double damageToDeal = 4;
        if (this.plugin.getGameManager().getGameTimer() <= 210) {
            damageToDeal = 8;
        }
        if (this.plugin.getGameManager().getGameTimer() <= 90) {
            damageToDeal = 16;
        }
        if (this.plugin.getGameManager().getGameTimer() <= 150) {
            damageToDeal = 12;
        }
        if (this.plugin.getGameManager().getGameTimer() <= 30 || zombieToAttack.hasInstakillBonus()) {
            damageToDeal = 20;
        }
        zombieToAttackPlayer.setHealth(zombieToAttackPlayer.getHealth() - damageToDeal);
    }
}
