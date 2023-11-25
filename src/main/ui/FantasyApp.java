package ui;

import model.Driver;
import model.League;
import model.Team;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// Represents the fantasy application
public class FantasyApp {
    private static final String JSON_STORE = "./data/league.json";
    private Scanner input;
    private League league;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private List<Team> teams;

    // EFFECTS : constructs the fantasy and runs application
    public FantasyApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        league = new League("F1 Fantasy");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runFantasy();
    }


    // EFFECTS
    @SuppressWarnings("methodlength")
    public void runFantasy() {
//        leagues = new ArrayList<>();
        teams = league.getTeams();

        while (true) {
            displayMenu();

            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    createTeam();
                    break;
                case 2:
                    printTeams();
                    break;
                case 3:
                    addDriversToTeam();
                    break;
                case 4:
                    viewDriversInTeam();
                    break;
                case 5:
                    saveLeague();
                    break;
                case 6:
                    loadLeague();
                    break;
                case 7:
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
        System.out.println("1 - Create a team");
        System.out.println("2 - Print teams");
        System.out.println("3 - Add drivers to your created teams");
        System.out.println("4 - View your created teams' drivers");
        System.out.println("5 - Save league to file");
        System.out.println("6 - Load league from file");
        System.out.println("7 - Exit");
    }

    // EFFECTS: creates a new team with name
    protected void createTeam() {
        System.out.println("Enter the name of the team: ");

        input.nextLine();
        String teamName = input.nextLine();

        Team newTeam = new Team(teamName);
        league.addTeam(newTeam);
        System.out.println("Team created: " + newTeam.getName());
    }

    // EFFECTS: prints the list of teams created
    private void printTeams() {
        System.out.println("List of created teams:");
        List<Team> teams = league.getTeams(); // Use the teams from the loaded league
        for (int i = 0; i < teams.size(); i++) {
            Team team = teams.get(i);
            System.out.println(i + ". " + team.getName());
            List<Driver> driversInTeam = team.getDrivers();

            System.out.println("Drivers in " + team.getName() + ":");
            for (Driver driver : driversInTeam) {
                System.out.println("\t" + driver.getName() + " (Value: $" + driver.getValue() + " million)");
            }
        }
    }


    @SuppressWarnings("methodlength")
    // EFFECTS: allows user to choose drivers to join their selected team
    private void addDriversToTeam() {
        List<Team> teams = league.getTeams(); // Use the teams from the loaded league
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

    // REQUIRES: team is already created
    // EFFECTS: view the teams' selected drivers
    private void viewDriversInTeam() {
        List<Team> teams = league.getTeams(); // Use the teams from the loaded league
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

    // EFFECTS: saves the league to file
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
    // EFFECTS: loads league from file
    private void loadLeague() {
        try {
            league = jsonReader.read();
            System.out.println("Loaded " + league.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    public League getLeague() {
        return league;
    }
}
