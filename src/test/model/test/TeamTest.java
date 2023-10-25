package model.test;

import model.Driver;
import model.Team;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TeamTest {
    private Team testTeam;
    private Team teamRbr;
    private Team teamMcl;

    @BeforeEach
    void runBefore() {
        testTeam = new Team("My Team");
        teamRbr = new Team("Red Bull Racing");
        teamMcl = new Team("McLaren Racing");
        teamMcl.addDriver(Driver.d8);
    }

    @Test
    void testConstructor() {
        assertEquals("My Team", testTeam.getName());
        assertEquals(0, testTeam.getDrivers().size());
        assertEquals(0, testTeam.calculateTotalCost());
    }

    @Test
    void testAddDriver() {
        boolean success1 = testTeam.addDriver(Driver.d1);
        assertTrue(success1);
        assertEquals(1, testTeam.getDrivers().size());
        assertEquals("Max Verstappen", testTeam.getDrivers().get(0).getName());
        assertEquals(32.8, testTeam.getDrivers().get(0).getValue());
        assertEquals(32.8, testTeam.calculateTotalCost());
    }

    @Test
    void testAddMultipleDriver() {
        boolean success1 = testTeam.addDriver(Driver.d1);
        boolean success2 = testTeam.addDriver(Driver.d3);
        assertTrue(success1);
        assertTrue(success2);
        assertEquals(2, testTeam.getDrivers().size());
        assertEquals("Max Verstappen", testTeam.getDrivers().get(0).getName());
        assertEquals("Lewis Hamilton", testTeam.getDrivers().get(1).getName());
        assertEquals(32.8, testTeam.getDrivers().get(0).getValue());
        assertEquals(30.0, testTeam.getDrivers().get(1).getValue());
        assertEquals(62.8, testTeam.calculateTotalCost());
    }

    @Test
    void testAdd5DriversFail() {
        testTeam.addDriver(Driver.d1);
        testTeam.addDriver(Driver.d3);
        testTeam.addDriver(Driver.d4);
        testTeam.addDriver(Driver.d5);

        assertTrue(testTeam.getDrivers().contains(Driver.d1));
        assertTrue(testTeam.getDrivers().contains(Driver.d3));
        assertTrue(testTeam.getDrivers().contains(Driver.d4));
        assertFalse(testTeam.getDrivers().contains(Driver.d5)); // Adding a fourth driver should fail due to cost cap
        assertEquals("My Team", testTeam.getName());
        assertEquals(3, testTeam.getDrivers().size());
        assertEquals(85.4, testTeam.calculateTotalCost());
    }

    @Test
    void testAdd5DriversSuccess() {
        teamRbr.addDriver(Driver.d1);
        teamRbr.addDriver(Driver.d2);
        teamRbr.addDriver(Driver.d13);
        teamRbr.addDriver(Driver.d14);
        teamRbr.addDriver(Driver.d16);

        assertTrue(teamRbr.getDrivers().contains(Driver.d1));
        assertTrue(teamRbr.getDrivers().contains(Driver.d2));
        assertTrue(teamRbr.getDrivers().contains(Driver.d13));
        assertTrue(teamRbr.getDrivers().contains(Driver.d14));
        assertTrue(teamRbr.getDrivers().contains(Driver.d16));
        assertEquals("Red Bull Racing", teamRbr.getName());
        assertEquals(5, teamRbr.getDrivers().size());
        assertEquals(74.8, teamRbr.calculateTotalCost());
    }

    @Test
    void testAdd5DriversOverMax() {
        testTeam.addDriver(Driver.d1);
        testTeam.addDriver(Driver.d11);
        testTeam.addDriver(Driver.d12);
        testTeam.addDriver(Driver.d13);
        testTeam.addDriver(Driver.d14);
        testTeam.addDriver(Driver.d15); // Adding a sixth driver should fail due to exceeding the driver limit

        assertTrue(testTeam.getDrivers().contains(Driver.d1));
        assertTrue(testTeam.getDrivers().contains(Driver.d11));
        assertTrue(testTeam.getDrivers().contains(Driver.d12));
        assertTrue(testTeam.getDrivers().contains(Driver.d13));
        assertTrue(testTeam.getDrivers().contains(Driver.d14));
        assertFalse(testTeam.getDrivers().contains(Driver.d15));
        assertEquals("My Team", testTeam.getName());
        assertEquals(5, testTeam.getDrivers().size());
        assertEquals(70.3, testTeam.calculateTotalCost());
    }

    @Test
    void testAddExistingDriverFail() {
        boolean success1 = teamMcl.addDriver(Driver.d7);
        boolean fail1 = teamMcl.addDriver(Driver.d8);

        assertTrue(success1);
        assertFalse(fail1); // Adding the same driver twice will fail
        assertEquals(2, teamMcl.getDrivers().size());
        assertEquals("McLaren Racing", teamMcl.getName());
        assertEquals(30.9, teamMcl.calculateTotalCost());
    }

    @Test
    void testRemoveDriver() {
        teamMcl.addDriver(Driver.d7);
        teamMcl.removeDriver(Driver.d8);
        teamMcl.removeDriver(Driver.d7);
        assertFalse(teamMcl.getDrivers().contains(Driver.d8));
        assertFalse(teamMcl.getDrivers().contains(Driver.d7));
        assertEquals(0, teamMcl.getDrivers().size());
        assertEquals(0, teamMcl.calculateTotalCost());
    }

    @Test
    public void driverToJsonTest() {
        Driver testDriver = Driver.d1;
        JSONObject json = testDriver.toJson();
        String name = json.getString("name");
        Double value = json.getDouble("value");
        assertEquals(name, "Max Verstappen");
        assertEquals(value, 32.8);
    }

    @Test
    public void driversToJsonTest() {
        Driver testDriver = Driver.d1;
        Driver testDriver2 = Driver.d2;

        teamRbr.addDriver(testDriver);
        teamRbr.addDriver(testDriver2);

        JSONObject jsonTeam = teamRbr.toJson();
        String name = jsonTeam.getString("name"); //team name
        JSONArray driverJson = jsonTeam.getJSONArray("drivers");

        assertEquals(name, "Red Bull Racing");

        JSONObject driver1 = (JSONObject) driverJson.get(0);
        JSONObject driver2 = (JSONObject) driverJson.get(1);

        assertEquals(driver1.getString("name"), "Max Verstappen");
        assertEquals(driver1.getDouble("value"), 32.8);
        assertEquals(driver2.getString("name"), "Sergio Perez");
        assertEquals(driver2.getDouble("value"), 20.4);
    }

    @Test
    public void testToString() {
        teamRbr.addDriver(Driver.d5);
        teamRbr.addDriver(Driver.d6);
        String testString = teamRbr.toString();
        String expectedString = "Team: Red Bull Racing\nDrivers: [" + Driver.d5 + ", " + Driver.d6 + "]Total Value: " +
                teamRbr.calculateTotalCost();
        assertEquals(testString, expectedString);
    }
}


