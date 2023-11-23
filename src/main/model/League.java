package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// The class League represents a Formula 1 fantasy league. The league can contain an arbitrary amount of teams.
public class League implements Writable {
    private final String name;
    private final List<Team> teams;

    // EFFECTS : constructs a league that contains a list of teams
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

    // REQUIRES : the team is not already in the league
    // MODIFIES : this
    // EFFECTS  : adds a team to the league
    public boolean addTeam(Team t) {
        if (!teams.contains(t)) {
            teams.add(t);
            return true;
        } else {
            return false;
        }
    }

    // MODIFIES : this
    // EFFECTS : removes a team from the league
    public void removeTeam(Team t) {
        teams.remove(t);
    }

    // EFFECTS: returns number of thingies in this workroom
    public int numTeams() {
        return teams.size();
    }

    // EFFECTS : returns a string representation of the league's name
    @Override
    public String toString() {
        return "League Name: " + name;
    }

    // EFFECTS : converts the league to a JSON object representing the league,
    // including its name and its teams
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("teams", leagueToJson());
        return json;
    }

    // EFFECTS: returns teams in this league as a JSON array
    private JSONArray leagueToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Team t : teams) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }
}

