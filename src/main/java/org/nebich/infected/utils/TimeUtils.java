package org.nebich.infected.utils;

public class TimeUtils {
    private static final int tickToSecond = 20;
    private static final int secondToMinute = 60;

    public static int Seconds(int seconds) {
        return seconds * tickToSecond;
    }

    public static int Minutes(int minutes) {
        return minutes * secondToMinute * tickToSecond;
    }

    public static String convertToMinutes(int secondsToConvert) {
        int minutes = Math.round((float) secondsToConvert / 60);
        int seconds = secondsToConvert % 60;
        return minutes + "min" + seconds + "s";
    }
}
