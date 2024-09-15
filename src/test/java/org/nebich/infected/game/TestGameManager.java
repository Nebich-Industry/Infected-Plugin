package org.nebich.infected.game;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nebich.infected.Infected;

public class TestGameManager {
    private static ServerMock serverMock;
    private GameManager gameManager;
    private static Infected infectedPlugin;

    @BeforeEach
    public void load() {
        serverMock = MockBukkit.mock();
        infectedPlugin = MockBukkit.load(Infected.class);
        this.gameManager = infectedPlugin.getGameManager();
    }

    @AfterEach
    public void unload() {
        MockBukkit.unmock();
    }

    @Test
    @DisplayName("It should launch the game")
    public void testLaunch() {
        serverMock.addSimpleWorld("world_infected_city");
        Player player = serverMock.addPlayer();

        player.performCommand("infected join");

        this.gameManager.launch(false);

        Assertions.assertNotNull(infectedPlugin.getWorldsManager().getCurrentWorld());
        Assertions.assertEquals(1, infectedPlugin.getPlayerManager().getZombies().size());
        Assertions.assertEquals(GameStatus.PLAYING, infectedPlugin.getGameManager().getGameStatus());
        Assertions.assertEquals(-1, player.getInventory().first(Material.NETHER_STAR));
    }

    @Test
    @DisplayName("It should launch the game without choosing zombies")
    public void testLaunchWithOPPlayer() {
        serverMock.addSimpleWorld("world_infected_city");
        Player player = serverMock.addPlayer();
        player.setOp(true);

        player.performCommand("infected join");

        this.gameManager.launch(true);

        Assertions.assertNotNull(infectedPlugin.getWorldsManager().getCurrentWorld());
        Assertions.assertEquals(0, infectedPlugin.getPlayerManager().getZombies().size());
        Assertions.assertEquals(GameStatus.PLAYING, infectedPlugin.getGameManager().getGameStatus());
    }

    @Test
    @DisplayName("It should launch the game without current world set")
    public void testLaunchWithoutCurrentWorld() {
        serverMock.addSimpleWorld("world_infected_city");
        Player player = serverMock.addPlayer();

        player.performCommand("infected join");

        infectedPlugin.getWorldsManager().setCurrentWorld(null);
        this.gameManager.launch(false);

        Assertions.assertNotNull(infectedPlugin.getWorldsManager().getCurrentWorld());
        Assertions.assertEquals(1, infectedPlugin.getPlayerManager().getZombies().size());
        Assertions.assertEquals(GameStatus.PLAYING, infectedPlugin.getGameManager().getGameStatus());
    }
}
