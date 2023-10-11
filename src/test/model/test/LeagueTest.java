package model.test;

import model.League;
import model.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class LeagueTest {
    private League f1League;
    private Team team1;
    private Team team2;
    private Team team3;

    @BeforeEach
    void runBefore() {
        f1League = new League("Formula One Fantasy League");
        team1 = new Team("Scuderia Ferrari");
        team2 = new Team("Red Bull Racing");
        team3 = new Team("Mercedes AMG Formula One Team");

    }
    @Test
    void testConstructor() {
        assertEquals("Formula One Fantasy League", f1League.getName());
        assertEquals(0, f1League.getTeams().size());
    }

    @Test
    void testAddTeam() {
        f1League.addTeam(team1);
        assertEquals("Scuderia Ferrari", f1League.getTeams().get(0).getName());
        assertEquals(1, f1League.getTeams().size());
    }

    @Test
    void testAddExistingTeam() {
        League testLeague = new League("My League");
        testLeague.addTeam(team1);
        assertFalse(testLeague.addTeam(team1));
        assertEquals("Scuderia Ferrari", testLeague.getTeams().get(0).getName());
        assertEquals(1, testLeague.getTeams().size());
    }

    @Test
    void testAddMultipleTeams() {
        f1League.addTeam(team1);
        f1League.addTeam(team2);
        f1League.addTeam(team3);
        assertEquals("Scuderia Ferrari", f1League.getTeams().get(0).getName());
        assertEquals("Red Bull Racing", f1League.getTeams().get(1).getName());
        assertEquals("Mercedes AMG Formula One Team", f1League.getTeams().get(2).getName());
        assertEquals(3, f1League.getTeams().size());
    }

    @Test
    void testRemoveTeam() {
        f1League.addTeam(team1);
        f1League.removeTeam(team1);
        assertEquals(0, f1League.getTeams().size());
    }

}
