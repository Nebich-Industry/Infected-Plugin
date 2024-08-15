package org.nebich.infected.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestTimeUtils {
    @Test
    @DisplayName("It should convert to Minecraft seconds")
    public void testSeconds() {
        Assertions.assertEquals(20, TimeUtils.Seconds(1));
    }

    @Test
    @DisplayName("It should convert to Minecraft minutes")
    public void testMinutes() {
        Assertions.assertEquals(1200, TimeUtils.Minutes(1));
    }
}
