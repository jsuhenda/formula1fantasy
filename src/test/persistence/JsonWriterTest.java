package persistence;

import model.League;
import model.Team;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            League league = new League("My league");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyLeague() {
        try {
            League league = new League("My league");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyLeague.json");
            writer.open();
            writer.write(league);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyLeague.json");
            league = reader.read();
            assertEquals("My league", league.getName());
            assertEquals(0, league.numTeams());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralLeague() {
        try {
            League league = new League("My league");
            league.addTeam(new Team("McLaren"));
            league.addTeam(new Team("Ferrari"));
            JsonWriter writer = new JsonWriter("./data/testWriterF1League.json");
            writer.open();
            writer.write(league);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterF1League.json");
            league = reader.read();
            assertEquals("My league", league.getName());
            List<Team> leagueTeams = league.getTeams();
            assertEquals(2, leagueTeams.size());
//            List<Driver> drivers = new ArrayList<>();
//            drivers.add(Driver.d1);
//            drivers.add(Driver.d2);
//            drivers.add(Driver.d3);
            checkTeam("McLaren", leagueTeams.get(0));
            checkTeam("Ferrari", leagueTeams.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
