package ui;

import model.Driver;
import model.League;
import model.Team;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Represents the fantasy application
public class FantasyApp {
    private static final String JSON_STORE = "./data/league.json";
    private Scanner input;
    private League league;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private List<League> leagues;
    private List<Team> teams;

    // EFFECTS : constructs the fantasy and runs application
    public FantasyApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        league = new League("F1 Fantasy");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runFantasy();
    }



    @SuppressWarnings("methodlength")
    public void runFantasy() {
        leagues = new ArrayList<>();
        teams = new ArrayList<>();

        while (true) {
            displayMenu();

            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    createLeague();
                    break;
                case 2:
                    createTeam();
                    break;
                case 3:
                    addTeamsToLeague();
                    break;

                case 4:
                    addDriversToTeam();
                    break;

                case 5:
                    viewDriversInTeam();
                    break;

                case 6:
                    saveLeague();
                    break;

                case 7:
                    loadLeague();
                    break;

                case 8:
                    System.out.println("Exiting the application.");
                    input.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("1 - Create a league");
        System.out.println("2 - Create a team");
        System.out.println("3 - View your created leagues");
        System.out.println("4 - Add drivers to your created teams");
        System.out.println("5 - View your created teams' drivers");
        System.out.println("6 - Save league to file");
        System.out.println("7 - Load league from file");
        System.out.println("8 - Exit");
    }

    // EFFECTS: creates a new league with name
    private void createLeague() {
        System.out.println("Enter the name of the league: ");
        input.nextLine();  // Consume the newline character
        String leagueName = input.nextLine();  // Read the league name
        League newLeague = new League(leagueName);
        leagues.add(newLeague);
        System.out.println("League created: " + newLeague.getName());
    }

    // EFFECTS: creates a new team with name
    private void createTeam() {
        System.out.println("Enter the name of the team: ");
        input.nextLine();
        String teamName = input.nextLine();
        Team newTeam = new Team(teamName);
        teams.add(newTeam);
        System.out.println("Team created: " + newTeam.getName());
    }

    @SuppressWarnings("methodlength")
    // EFFECTS : add created teams to created leagues
    private void addTeamsToLeague() {
        System.out.println("List of created leagues:");
        for (int i = 0; i < leagues.size(); i++) {
            League league = leagues.get(i);
            System.out.println(i + ". " + league.getName());
        }

        System.out.println("Select a league to add a team to (enter the league number):");
        int leagueIndex = input.nextInt();
        if (leagueIndex >= 0 && leagueIndex < leagues.size()) {
            League selectedLeague = leagues.get(leagueIndex);

            while (true) {
                System.out.println("Select a team to add to the league (enter the team number):");
                for (int i = 0; i < teams.size(); i++) {
                    Team team = teams.get(i);
                    System.out.println(i + ". " + team.getName());
                }

                System.out.println("Enter 'e' to exit without adding a team.");
                String teamInput = input.next();

                if (teamInput.equals("e")) {
                    break;
                }

                int teamIndex = Integer.parseInt(teamInput);
                if (teamIndex >= 0 && teamIndex < teams.size()) {
                    Team selectedTeam = teams.get(teamIndex);
                    selectedLeague.addTeam(selectedTeam);
                    System.out.println("Team added to the league: " + selectedTeam.getName());
                } else {
                    System.out.println("Invalid team number.");
                }
            }
        } else {
            System.out.println("Invalid league number.");
        }
    }

    @SuppressWarnings("methodlength")
    // EFFECTS: allows user to choose drivers to join their selected team
    private void addDriversToTeam() {
        System.out.println("List of created teams:");
        for (int i = 0; i < teams.size(); i++) {
            Team team = teams.get(i);
            System.out.println(i + ". " + team.getName());
        }

        System.out.println("Select a team to add drivers to (enter the team number):");
        int teamIndex = input.nextInt();
        if (teamIndex >= 0 && teamIndex < teams.size()) {
            Team selectedTeam = teams.get(teamIndex);

            boolean addMoreDrivers = true;

            while (addMoreDrivers) {

                System.out.println("Available drivers to choose from:");

                // List the drivers using constants
                Driver[] allDrivers = {
                        Driver.d1, Driver.d2, Driver.d3, Driver.d4, Driver.d5,
                        Driver.d6, Driver.d7, Driver.d8, Driver.d9, Driver.d10,
                        Driver.d11, Driver.d12, Driver.d13, Driver.d14, Driver.d15,
                        Driver.d16, Driver.d17, Driver.d18, Driver.d19, Driver.d20
                };

                for (int i = 0; i < allDrivers.length; i++) {
                    System.out.println(i + ". " + allDrivers[i].getName());
                }

                System.out.println("Enter the number of the driver you want to add (0-19) or 'e' to exit:");
                String userInput = input.next();
                if (userInput.equals("e")) {
                    addMoreDrivers = false;
                    break;
                }
                int driverNumber = Integer.parseInt(userInput);
                if (driverNumber >= 0 && driverNumber < allDrivers.length) {
                    Driver selectedDriver = allDrivers[driverNumber];
                    if (selectedTeam.addDriver(selectedDriver)) {
                        System.out.println("Driver added to the team: " + selectedDriver.getName());
                    } else {
                        System.out.println("Add driver unsuccessful");
                    }
                } else {
                    System.out.println("Invalid driver number.");
                }

                System.out.println("Do you want to add another driver? (yes/no)");
                String addAnother = input.next();
                if (!addAnother.equals("yes")) {
                    addMoreDrivers = false;
                }
            }
        } else {
            System.out.println("Invalid team number.");
        }
    }

    // EFFECTS: view the teams' selected drivers
    private void viewDriversInTeam() {
        System.out.println("Select a team to view drivers (enter the team number):");
        for (int i = 0; i < teams.size(); i++) {
            Team team = teams.get(i);
            System.out.println(i + ". " + team.getName());
        }

        int teamViewIndex = input.nextInt();

        if (teamViewIndex >= 0 && teamViewIndex < teams.size()) {
            Team teamToView = teams.get(teamViewIndex);

            List<Driver> driversInTeam = teamToView.getDrivers();

            System.out.println("Drivers in " + teamToView.getName() + ":");
            for (Driver driver : driversInTeam) {
                System.out.println(driver.getName());
            }
        } else {
            System.out.println("Invalid team number.");
        }
    }

    // EFFECTS: saves the workroom to file
    private void saveLeague() {
        try {
            jsonWriter.open();
            jsonWriter.write(league);
            jsonWriter.close();
            System.out.println("Saved " + league.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadLeague() {
        try {
            league = jsonReader.read();
            System.out.println("Loaded " + league.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
