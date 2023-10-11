package ui;

import model.Team;
import model.Driver;
import model.League;

public class Main {

    @SuppressWarnings("methodlength")
    public static void main(String[] args) {
        Team team1 = new Team("Scuderia Ferrari");

        // Attempt to add drivers to the team
        boolean success1 = team1.addDriver(Driver.d3);
        boolean success2 = team1.addDriver(Driver.d4);
        boolean success3 = team1.addDriver(Driver.d5);

        // Adding a fourth driver should fail due to salary cap
        boolean fail1 = team1.addDriver(Driver.d1);

        System.out.println("Driver 1 added successfully: " + success1);
        System.out.println("Driver 2 added successfully: " + success2);
        System.out.println("Driver 3 added successfully: " + success3);
        System.out.println("Driver 4 added successfully: " + fail1);
        System.out.println(team1.getName() + ": " + team1.calculateTotalCost());

        // Adding drivers to new team
        Team team2 = new Team("Mercedes AMG");
        boolean success4 = team2.addDriver(Driver.d1);
        boolean success5 = team2.addDriver(Driver.d7);
        boolean success6 = team2.addDriver(Driver.d8);
        boolean success7 = team2.addDriver(Driver.d15);

        // Adding the same driver twice will fail
        boolean fail2 = team2.addDriver(Driver.d8);

        System.out.println("Driver 1 added successfully: " + success4);
        System.out.println("Driver 2 added successfully: " + success5);
        System.out.println("Driver 3 added successfully: " + success6);
        System.out.println("Driver 4 added successfully: " + success7);
        System.out.println("Driver 5 added successfully: " + fail2);
        System.out.println(team2.getName() + ": " + team2.calculateTotalCost());

        Team team3 = new Team("Red Bull Racing");
        boolean success8 = team3.addDriver(Driver.d1);
        boolean success9 = team3.addDriver(Driver.d2);
        boolean success10 = team3.addDriver(Driver.d13);
        boolean success11 = team3.addDriver(Driver.d14);
        boolean success12 = team3.addDriver(Driver.d16);

        System.out.println("Driver 1 added successfully: " + success8);
        System.out.println("Driver 2 added successfully: " + success9);
        System.out.println("Driver 3 added successfully: " + success10);
        System.out.println("Driver 4 added successfully: " + success11);
        System.out.println("Driver 5 added successfully: " + success12);
        System.out.println(team3.getName() + ": " + team3.calculateTotalCost());

        League f1League = new League("Formula One Fantasy League");
        f1League.addTeam(team1);
        f1League.addTeam(team2);
        f1League.addTeam(team3);

        System.out.println(f1League.getName() + ": " + f1League.getTeams());


    }
}
