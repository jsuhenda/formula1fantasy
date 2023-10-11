package model;

import java.util.ArrayList;
import java.util.List;


// The Team class represents an F1 team in a Formula 1 fantasy league. Each team can have up to 5 drivers and
// must not exceed a maximum cost cap of $100m. The class provides methods for managing the team and
// calculating the total value of the team.
public class Team {
    private String name;
    private List<Driver> drivers;
    private static final double MAX_COST_CAP = 100.0;
    private static final int MAX_DRIVERS = 5;

    public Team(String name) {
        this.name = name;
        this.drivers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    // REQUIRES : The team must not be full (not exceed 5 drivers). The new driver's cost must not exceed the
    //            maximum cost cap.
    // MODIFIES : this
    // EFFECTS  : Adds the new driver to the team if requirements are met.
    public boolean addDriver(Driver driver) {
        double currentValue = calculateTotalCost();
        if (currentValue + driver.getValue() <= MAX_COST_CAP
                && drivers.size() < MAX_DRIVERS
                && (!drivers.contains(driver))) {
            drivers.add(driver);
            return true; // Driver added successfully
        } else {
            return false; // Team has reached cost cap or maximum number of drivers
        }
    }


    // MODIFIES : this
    // EFFECTS  : Removes a driver from the team
    public void removeDriver(Driver driver) {
        drivers.remove(driver);
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    // EFFECTS : returns the total value of the team
    public double calculateTotalCost() {
        double totalCost = 0;
        for (Driver d : drivers) {
            totalCost += d.getValue();
        }
        return totalCost;
    }

    // This method is to make console output the team name instead of the address.
    // EFFECTS : returns a string of team name and team value
    @Override
    public String toString() {
        return "\nTeam Name: " + name + "\nTeam Value: $" + calculateTotalCost();
    }
}