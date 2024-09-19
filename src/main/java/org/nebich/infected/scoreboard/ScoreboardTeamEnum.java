package org.nebich.infected.scoreboard;

public enum ScoreboardTeamEnum {
    SURVIVORS("TEAM_SURVIVORS"),
    ZOMBIES("TEAM_ZOMBIES");

    private final String entryId;

    ScoreboardTeamEnum(String entryId) {
        this.entryId = entryId;
    }

    public String getEntryId() {
        return entryId;
    }
}
