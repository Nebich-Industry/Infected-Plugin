package org.nebich.infected.commands;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.WorldMock;
import org.bukkit.entity.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nebich.infected.Infected;

import java.util.Objects;

public class TestJoinCommand {
    private ServerMock serverMock;
    private Infected infectedPlugin;

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
    @DisplayName("Test if the command teleport in the current game world")
    public void testCommand() {
        WorldMock infectedWorld = serverMock.addSimpleWorld("world_infected_city");
        Player player = serverMock.addPlayer();

        serverMock.execute("infected", player, "join").assertSucceeded();
        Assertions.assertEquals(2, player.getLocation().getBlockX());
        Assertions.assertEquals(13, player.getLocation().getBlockZ());
        Assertions.assertEquals(infectedWorld.getName(), Objects.requireNonNull(player.getLocation().getWorld()).getName());
    }
}
