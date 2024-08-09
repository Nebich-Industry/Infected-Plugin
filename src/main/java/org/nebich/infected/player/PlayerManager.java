package org.nebich.infected.player;

import org.bukkit.entity.Player;
import org.nebich.infected.Infected;
import org.nebich.infected.game.GameStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayerManager {
    private final List<Survivor> survivors = new ArrayList<>();
    private final List<Zombie> zombies = new ArrayList<>();
    private final Infected plugin;

    public PlayerManager(Infected plugin) {
        this.plugin = plugin;
    }

    public void chooseFirstZombies() {
        int survivorsSize = this.survivors.size();
        int zombiesToChoose = survivorsSize <= 10 ? 1 : Math.max(Math.round((float) survivorsSize / 10), 5);
        for (int i=0; i < zombiesToChoose; i++) {
            int randomIndex = new Random().nextInt(survivorsSize);
            Survivor s = this.survivors.get(randomIndex);
            Player playerSelected = s.getPlayer();
            this.zombies.add(new Zombie(playerSelected, true));
            this.survivors.remove(randomIndex);
            playerSelected.sendMessage("Vous venez de vous transformer en zombie.");
        }
    }

    public void addPlayer(Player player) {
        if (this.plugin.getGameManager().getGameStatus() == GameStatus.PLAYING) {
            this.zombies.add(new Zombie(player));
        } else {
            this.survivors.add(new Survivor(player));
        }
    }

    public void addZombie(Player player) {
        this.survivors.removeIf(survivor -> survivor.getPlayer().getUniqueId() == player.getUniqueId());
        this.zombies.add(new Zombie(player));
    }

    public List<Survivor> getSurvivors() {
        return this.survivors;
    }

    public List<Zombie> getZombies() {
        return this.zombies;
    }
}
