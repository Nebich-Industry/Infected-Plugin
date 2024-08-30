package org.nebich.infected.player;

import org.bukkit.entity.Player;
import org.nebich.infected.Infected;
import org.nebich.infected.game.GameStatus;
import org.nebich.infected.survivors.Role;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

public class PlayerManager {
    private final List<InfectedPlayer> playerList = new ArrayList<>();
    private final Infected plugin;

    public PlayerManager(Infected plugin) {
        this.plugin = plugin;
    }

    public void chooseFirstZombies() {
        int survivorsSize = this.playerList.size();
        int zombiesToChoose = survivorsSize <= 10 ? 1 : Math.max(Math.round((float) survivorsSize / 10), 5);
        for (int i=0; i < zombiesToChoose; i++) {
            Random random = new SecureRandom();
            int randomIndex = random.nextInt(survivorsSize);
            InfectedPlayer playerToTransform = this.playerList.get(randomIndex);
            playerToTransform.setIsZombie(true);
            playerToTransform.setIsSurvivor(false);
            ((Zombie) playerToTransform).setSelectedAtStart(true);
            playerToTransform.getPlayer().sendMessage("Vous venez de vous transformer en zombie.");
        }
    }

    public void addPlayer(Player player) {
        if (this.plugin.getGameManager().getGameStatus() == GameStatus.PLAYING) {
            this.playerList.add(new Zombie(player));
        } else {
            this.playerList.add(new Survivor(player));
        }
    }

    public void selectSurvivorClass(Player player, Role role) {
        Optional<InfectedPlayer> survivor = this.playerList.stream().filter(s -> s.getPlayer().getUniqueId() == player.getUniqueId()).findFirst();
        survivor.ifPresent(infectedPlayer -> ((Survivor) infectedPlayer).setRole(role));
    }

    public void addZombie(Player player) {
        this.playerList.add(new Zombie(player));
    }

    public List<InfectedPlayer> getPlayers() {
        return this.playerList;
    }

    public Optional<InfectedPlayer> getPlayer(UUID uuid) {
        return this.playerList.stream().filter(player -> player.getPlayer().getUniqueId() == uuid).findFirst();
    }

    public List<InfectedPlayer> getSurvivors() {
        return this.playerList.stream().filter(InfectedPlayer::isSurvivor).toList();
    }

    public List<InfectedPlayer> getZombies() {
        return this.playerList.stream().filter(InfectedPlayer::isZombie).toList();
    }
}
