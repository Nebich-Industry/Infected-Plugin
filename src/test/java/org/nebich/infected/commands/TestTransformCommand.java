package org.nebich.infected.commands;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import org.bukkit.entity.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nebich.infected.Infected;
import org.nebich.infected.game.GameStatus;

public class TestTransformCommand {
    private static ServerMock serverMock;
    private static Infected infectedPlugin;

    @BeforeEach
    public void load() {
        serverMock = MockBukkit.mock();
        infectedPlugin = MockBukkit.load(Infected.class);
    }

    @AfterEach
    public void unload() {
        MockBukkit.unmock();
    }

    @Test
    @DisplayName("It should force the player to become a zombie")
    public void testAdminTransformCommand() {
        serverMock.addSimpleWorld("world_infected_city");
        Player player = serverMock.addPlayer();
        player.setOp(true);

        player.performCommand("infected join");
        player.performCommand("infected admin launch");
        player.performCommand("infected admin transform");

        Assertions.assertEquals(GameStatus.PLAYING ,infectedPlugin.getGameManager().getGameStatus());
        Assertions.assertEquals(1, infectedPlugin.getPlayerManager().getZombies().size());
    }

    @Test
    @DisplayName("It should not force the player to become a zombie")
    public void testAdminTransformCommandWithoutPermission() {
        serverMock.addSimpleWorld("world_infected_city");
        Player player = serverMock.addPlayer();

        player.performCommand("infected join");
        player.performCommand("infected admin launch");
        player.performCommand("infected admin transform");

        Assertions.assertEquals(GameStatus.WAITING ,infectedPlugin.getGameManager().getGameStatus());
        Assertions.assertEquals(0, infectedPlugin.getPlayerManager().getZombies().size());
    }
}
