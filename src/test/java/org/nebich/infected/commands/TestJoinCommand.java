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
    @DisplayName("Test if the command teleport in the current game world")
    public void testCommand() {
        // TODO: Find a way to mock the method server.getWorldCOntainer()
        WorldMock infectedWorld = serverMock.addSimpleWorld("world_infected_dead_island");
        Player player = serverMock.addPlayer();
        player.setOp(true);

        serverMock.execute("infected", player, "join").assertSucceeded();
        Assertions.assertEquals(-60, player.getLocation().getBlockX());
        Assertions.assertEquals(-28, player.getLocation().getBlockZ());
        Assertions.assertEquals(Objects.requireNonNull(player.getLocation().getWorld()).getName(), infectedWorld.getName());
    }
}
