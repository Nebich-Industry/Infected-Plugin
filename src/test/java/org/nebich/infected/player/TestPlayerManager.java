package org.nebich.infected.player;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import org.bukkit.entity.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nebich.infected.Infected;

public class TestPlayerManager {
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
    @DisplayName("It should choose zombie")
    public void testChooseZombie() {
        serverMock.addSimpleWorld("world_infected_city");
        Player player = serverMock.addPlayer();
        Player player2 = serverMock.addPlayer();

        infectedPlugin.getPlayerManager().addPlayer(player);
        infectedPlugin.getPlayerManager().addPlayer(player2);
        Assertions.assertEquals(2, infectedPlugin.getPlayerManager().getSurvivors().size());

        infectedPlugin.getPlayerManager().chooseFirstZombies();
        Assertions.assertEquals(1, infectedPlugin.getPlayerManager().getZombies().size());
    }
}
