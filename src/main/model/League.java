package model;

import java.util.ArrayList;
import java.util.List;

// The class League represents a Formula 1 fantasy league. The league can contain an arbitrary amount of teams.
public class League {
    private String name;
    private List<Team> teams;

    public League(String name) {
        this.name = name;
        this.teams = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Team> getTeams() {
        return teams;
    }

    // MODIFIES : this
    // EFFECTS  : adds a team to the league
    public void addTeam(Team t) {
        teams.add(t);
    }
}
