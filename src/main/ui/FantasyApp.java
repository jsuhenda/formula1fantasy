package ui;

// import java.util.Scanner;

import model.Driver;
import model.League;
import model.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FantasyApp {
    List<League> leagues;
    List<Team> teams;

    public FantasyApp() {
        runFantasy();
    }

    @SuppressWarnings("methodlength")
    public void runFantasy() {
        leagues = new ArrayList<>();
        teams = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1 - Create a league");
            System.out.println("2 - Create a team");
            System.out.println("3 - View created leagues");
            System.out.println("4 - View created teams");
            System.out.println("5 - View created teams' drivers");
            System.out.println("6 - Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter the name of the league: ");
                    scanner.nextLine();  // Consume the newline character
                    String leagueName = scanner.nextLine();  // Read the league name
                    League newLeague = new League(leagueName);
                    leagues.add(newLeague);
                    System.out.println("League created: " + newLeague.getName());
                    break;

                case 2:
                    System.out.println("Enter the name of the team: ");
                    scanner.nextLine();
                    String teamName = scanner.nextLine();
                    Team newTeam = new Team(teamName);
                    teams.add(newTeam);
                    System.out.println("Team created: " + newTeam.getName());
                    break;

                case 3:
                    System.out.println("List of created leagues:");
                    for (int i = 0; i < leagues.size(); i++) {
                        League league = leagues.get(i);
                        System.out.println(i + ". " + league.getName());
                    }

                    System.out.println("Select a league to add a team to (enter the league number):");
                    int leagueIndex = scanner.nextInt();
                    if (leagueIndex >= 0 && leagueIndex < leagues.size()) {
                        League selectedLeague = leagues.get(leagueIndex);

                        while (true) {
                            System.out.println("Select a team to add to the league (enter the team number):");
                            for (int i = 0; i < teams.size(); i++) {
                                Team team = teams.get(i);
                                System.out.println(i + ". " + team.getName());
                            }

                            System.out.println("Enter 'e' to exit without adding a team.");
                            String teamInput = scanner.next();

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
                    break;

                case 4:
                    System.out.println("List of created teams:");
                    for (int i = 0; i < teams.size(); i++) {
                        Team team = teams.get(i);
                        System.out.println(i + ". " + team.getName());
                    }

                    System.out.println("Select a team to add drivers to (enter the team number):");
                    int teamIndex = scanner.nextInt();
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
                            String userInput = scanner.next();
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
                            String addAnother = scanner.next();
                            if (!addAnother.equals("yes")) {
                                addMoreDrivers = false;
                            }
                        }
                    } else {
                        System.out.println("Invalid team number.");
                    }
                    break;

                case 5:
                    System.out.println("Select a team to view drivers (enter the team number):");
                    for (int i = 0; i < teams.size(); i++) {
                        Team team = teams.get(i);
                        System.out.println(i + ". " + team.getName());
                    }

                    int teamViewIndex = scanner.nextInt();

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
                    break;

                default:
                    System.out.println("Invalid choice. Please select a valid option.");


                case 6:
                    System.out.println("Exiting the application.");
                    scanner.close();
                    return;

            }
        }
    }
}
