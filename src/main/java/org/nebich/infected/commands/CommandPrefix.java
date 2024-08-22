package org.nebich.infected.commands;

public final class CommandPrefix {
    public static final String TRANSFORM = "transform";
    public static final String LAUNCH = "launch";
    public static final String ADMIN = "admin";
    public static final String JOIN = "join";

    private CommandPrefix() {
        throw new IllegalStateException("CommandPrefix class");
    }
}
