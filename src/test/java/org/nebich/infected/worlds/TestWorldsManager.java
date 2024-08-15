package org.nebich.infected.worlds;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import org.bukkit.World;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nebich.infected.Infected;

public class TestWorldsManager {
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
    @DisplayName("It should load worlds and set world border to the current world")
    public void testLoadWorld() {
        World currentWorld = infectedPlugin.getWorldsManager().getCurrentWorld();
        Assertions.assertNotNull(currentWorld);
        Assertions.assertEquals(infectedPlugin.getConfig().getDouble("worlds."+currentWorld.getName()+".border.radius"), currentWorld.getWorldBorder().getSize());
        Assertions.assertTrue(infectedPlugin.getServer().getWorlds().contains(currentWorld));
    }
}
