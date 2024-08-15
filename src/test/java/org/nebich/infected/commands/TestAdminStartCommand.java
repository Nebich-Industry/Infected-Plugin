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

public class TestAdminStartCommand {
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
    @DisplayName("It should force the start of a game")
    public void testAdminLaunchCommand() {
        serverMock.addSimpleWorld("world_infected_dead_island");
        Player player = serverMock.addPlayer();
        player.setOp(true);

        player.performCommand("infected join");
        player.performCommand("infected admin launch");

        Assertions.assertEquals(GameStatus.PLAYING ,infectedPlugin.getGameManager().getGameStatus());
    }

    @Test
    @DisplayName("It should not force the start of a game")
    public void testAdminLaunchCommandWithoutPermission() {
        serverMock.addSimpleWorld("world_infected_dead_island");
        Player player = serverMock.addPlayer();

        player.performCommand("infected join");
        boolean cmdResult = player.performCommand("infected admin launch");

        Assertions.assertFalse(cmdResult);
        Assertions.assertEquals(GameStatus.WAITING ,infectedPlugin.getGameManager().getGameStatus());
    }
}
