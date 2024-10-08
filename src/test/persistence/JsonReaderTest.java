package persistence;

import model.League;
import model.Team;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            League league = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyLeague() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyLeague.json");
        try {
            League league = reader.read();
            assertEquals("My league", league.getName());
            assertEquals(0, league.numTeams());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderF1League() {
        JsonReader reader = new JsonReader("./data/testReaderF1League.json");
        try {
            League league = reader.read();
            assertEquals("F1 Fantasy", league.getName());
            List<Team> leagueTeams = league.getTeams();
            assertEquals(2, league.numTeams());
            checkTeam("Team A", leagueTeams.get(0));
            checkTeam("Team B", leagueTeams.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}