package org.nebich.infected.utils;

public class TimeUtils {
    private static final int TICK_TO_SECOND = 20;
    private static final int SECOND_TO_MINUTE = 60;

    private TimeUtils() {
        throw new IllegalStateException("TimeUtils class");
    }

    public static int seconds(int seconds) {
        return seconds * TICK_TO_SECOND;
    }

    public static int minutes(int minutes) {
        return minutes * SECOND_TO_MINUTE * TICK_TO_SECOND;
    }

    public static String convertToMinutes(int secondsToConvert) {
        int minutes = secondsToConvert / 60;
        int seconds = secondsToConvert % 60;
        return minutes + "min" + seconds + "s";
    }
}
