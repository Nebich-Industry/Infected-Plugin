package org.nebich.infected.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.nebich.infected.Infected;

public class InfectedScoreboard {
    private final Scoreboard scoreboard;
    private final Team survivorsTeam;
    private final Team zombiesTeam;
    private final Infected plugin;

    public InfectedScoreboard(Infected plugin) {
        this.plugin = plugin;
        this.scoreboard = Bukkit.getServer().getScoreboardManager().getNewScoreboard();

        Objective scoreboardObjective = this.scoreboard.registerNewObjective("infectedBoard", Criteria.DUMMY, ChatColor.DARK_GREEN + "Infected");
        scoreboardObjective.setDisplaySlot(DisplaySlot.SIDEBAR);

        this.survivorsTeam = this.scoreboard.registerNewTeam(ScoreboardTeamEnum.SURVIVORS.getEntryId());
        this.survivorsTeam.setColor(ChatColor.AQUA);
        this.survivorsTeam.setSuffix(String.valueOf(this.plugin.getPlayerManager().getSurvivors().size()));
        this.survivorsTeam.setAllowFriendlyFire(false);
        this.survivorsTeam.addEntry("Survivants : ");

        this.zombiesTeam = this.scoreboard.registerNewTeam(ScoreboardTeamEnum.ZOMBIES.getEntryId());
        this.zombiesTeam.setColor(ChatColor.GREEN);
        this.zombiesTeam.setSuffix(String.valueOf(this.plugin.getPlayerManager().getZombies().size()));
        this.zombiesTeam.setAllowFriendlyFire(false);
        this.zombiesTeam.addEntry("Zombies : ");

        scoreboardObjective.getScore("  ").setScore(4);
        scoreboardObjective.getScore("Survivants : ").setScore(3);
        scoreboardObjective.getScore(" ").setScore(2);
        scoreboardObjective.getScore("Zombies : ").setScore(1);
    }

    public Scoreboard getScoreboard() {
        return this.scoreboard;
    }

    public void addSurvivorTeamEntry(Player player) {
        this.survivorsTeam.addEntry(player.getUniqueId().toString());
        this.survivorsTeam.setSuffix(String.valueOf(this.plugin.getPlayerManager().getSurvivors().size()));
    }

    public void removeSurvivorTeamEntry(Player player) {
        this.survivorsTeam.removeEntry(player.getUniqueId().toString());
        this.survivorsTeam.setSuffix(String.valueOf(this.plugin.getPlayerManager().getSurvivors().size()));
    }

    public void addZombieTeamEntry(Player player) {
        this.zombiesTeam.addEntry(player.getUniqueId().toString());
        this.zombiesTeam.setSuffix(String.valueOf(this.plugin.getPlayerManager().getZombies().size()));
    }

    public void removeZombieTeamEntry(Player player) {
        this.zombiesTeam.removeEntry(player.getUniqueId().toString());
        this.zombiesTeam.setSuffix(String.valueOf(this.plugin.getPlayerManager().getZombies().size()));
    }
}
