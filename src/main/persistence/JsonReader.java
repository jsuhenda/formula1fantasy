package persistence;

import model.Driver;
import model.League;
import model.Team;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public League read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseLeague(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses league from JSON object and returns it
    private League parseLeague(JSONObject jsonObject) {
        String leagueName = jsonObject.getString("name");
        League league = new League(leagueName);
        addTeams(league, jsonObject);
        return league;
    }

    // MODIFIES: wr
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addTeams(League league, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("teams");
        for (Object json : jsonArray) {
            JSONObject nextTeam = (JSONObject) json;
            addTeam(league, nextTeam);
        }
    }

    private void addTeam(League league, JSONObject teamObject) {
        String teamName = teamObject.getString("name");
        Team team = new Team(teamName);
        addDrivers(team, teamObject);
        league.addTeam(team);
    }

    private void addDrivers(Team team, JSONObject teamObject) {
        JSONArray driverArray = teamObject.getJSONArray("drivers");
        for (Object json : driverArray) {
            JSONObject driverObject = (JSONObject) json;
            String driverName = driverObject.getString("name");
            double driverValue = driverObject.getDouble("value");
            Driver driver = new Driver(driverName, driverValue);
            team.addDriver(driver);
        }
    }
}
