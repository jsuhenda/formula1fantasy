package model;

import java.util.ArrayList;
import java.util.List;

// The class League represents a Formula 1 fantasy league. The league can contain an arbitrary amount of teams.
public class League {
    private final String name;
    private final List<Team> league;

    public League(String name) {
        this.name = name;
        this.league = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Team> getTeams() {
        return league;
    }

    // REQUIRES : the team is not already in the league
    // MODIFIES : this
    // EFFECTS  : adds a team to the league
    public boolean addTeam(Team t) {
        if (!league.contains(t)) {
            league.add(t);
            return true;
        } else {
            return false;
        }
    }

    // MODIFIES : this
    // EFFECTS : removes a team from the league
    public void removeTeam(Team t) {
        league.remove(t);
    }
}
