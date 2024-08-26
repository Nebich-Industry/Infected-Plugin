package org.nebich.infected.tasks.bonus;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nebich.infected.Infected;
import org.nebich.infected.utils.TimeUtils;

import java.util.List;

public class TestSpawnBonusTask {
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

        infectedPlugin.getGameManager().launch(false);

        serverMock.getScheduler().performTicks(TimeUtils.minutes(2));
        List<Item> entities = infectedPlugin.getWorldsManager().getCurrentWorld().getEntitiesByClass(Item.class).stream().toList();
        Assertions.assertTrue(entities.stream().anyMatch(entity -> entity.getItemStack().getType() == Material.SNOWBALL));
    }
}
