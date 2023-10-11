package model.test;

import model.League;
import model.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LeagueTest {
    private League testLeague;
    private Team team1;
    private Team team2;
    private Team team3;

    @BeforeEach
    void runBefore() {
        testLeague = new League("Formula One Fantasy League");
        team1 = new Team("Scuderia Ferrari");
        team2 = new Team("Red Bull Racing");
        team3 = new Team("Mercedes AMG Formula One Team");

    }
    @Test
    void testConstructor() {
        assertEquals("Formula One Fantasy League", testLeague.getName());
        assertEquals(0, testLeague.getTeams().size());
    }

    @Test
    void testAddTeam() {
        testLeague.addTeam(team1);
        assertEquals("Scuderia Ferrari", testLeague.getTeams().get(0).getName());
        assertEquals(1, testLeague.getTeams().size());
    }

    @Test
    void testAddMultipleTeams() {
        testLeague.addTeam(team1);
        testLeague.addTeam(team2);
        testLeague.addTeam(team3);
        assertEquals("Scuderia Ferrari", testLeague.getTeams().get(0).getName());
        assertEquals("Red Bull Racing", testLeague.getTeams().get(1).getName());
        assertEquals("Mercedes AMG Formula One Team", testLeague.getTeams().get(2).getName());
        assertEquals(3, testLeague.getTeams().size());
    }

    @Test
    void testRemoveTeam() {
        testLeague.addTeam(team1);
        testLeague.removeTeam(team1);
        assertEquals(0, testLeague.getTeams().size());
    }

}
