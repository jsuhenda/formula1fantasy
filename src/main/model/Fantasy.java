package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

public class Fantasy implements Writable {
    private String name;
    private List<League> fantasy;

    // EFFECTS : constructs a fantasy that contains a list of leagues
    public Fantasy(String name) {
        this.name = name;
        fantasy = new ArrayList<League>();
    }

    public String getName() {
        return name;
    }

    public List<League> getLeagues() {
        return fantasy;
    }

    // REQUIRES : the league is not already in the fantasy
    // MODIFIES : this
    // EFFECTS  : adds a league to the fantasy
    public boolean addLeague(League l) {
        if (!fantasy.contains(l)) {
            fantasy.add(l);
            return true;
        } else {
            return false;
        }
    }

    // MODIFIES : this
    // EFFECTS : removes a team from the league
    public void removeLeague(League l) {
        fantasy.remove(l);
    }

    // EFFECTS: returns number of thingies in this workroom
    public int numLeagues() {
        return fantasy.size();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("fantasy", leagueToJson());
        return json;
    }

    // EFFECTS: returns leagues in this fantasy as a JSON array
    private JSONArray leagueToJson() {
        JSONArray jsonArray = new JSONArray();

        for (League l : fantasy) {
            jsonArray.put(l.toJson());
        }

        return jsonArray;
    }
}
