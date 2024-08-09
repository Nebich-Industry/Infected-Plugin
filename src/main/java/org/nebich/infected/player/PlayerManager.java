package org.nebich.infected.player;

import org.bukkit.entity.Player;
import org.nebich.infected.Infected;
import org.nebich.infected.game.GameManager;
import org.nebich.infected.game.GameStatus;

import java.util.List;
import java.util.Random;

public class PlayerManager {
    private List<Survivor> survivors;
    private List<Zombie> zombies;
    private final Infected plugin;
    private final GameManager gameManager;

    public PlayerManager(Infected plugin, GameManager gameManager) {
        this.plugin = plugin;
        this.gameManager = gameManager;
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
        if (this.gameManager.getGameStatus() == GameStatus.PLAYING) {
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
}
