package persistence;

import model.Team;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkTeam(String name, Team team) {
        assertEquals(name, team.getName());
//        assertEquals(drivers, team.getDrivers());
    }
}
