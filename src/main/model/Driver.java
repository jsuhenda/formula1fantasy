package model;

import org.json.JSONObject;
import persistence.Writable;

// The class driver represents the drivers in a team. Drivers have a price to recruit to the team.
// The driver's value is in millions of dollars.
public class Driver implements Writable {
    public static final Driver d1 = new Driver("Max Verstappen", 32.8);
    public static final Driver d2 = new Driver("Sergio Perez", 20.4);
    public static final Driver d3 = new Driver("Lewis Hamilton", 30.0);
    public static final Driver d4 = new Driver("George Russell", 22.6);
    public static final Driver d5 = new Driver("Charles Leclerc", 23.3);
    public static final Driver d6 = new Driver("Carlos Sainz", 19.8);
    public static final Driver d7 = new Driver("Lando Norris", 16.7);
    public static final Driver d8 = new Driver("Oscar Piastri", 14.2);
    public static final Driver d9 = new Driver("Fernando Alonso", 13.0);
    public static final Driver d10 = new Driver("Lance Stroll", 10.0);
    public static final Driver d11 = new Driver("Pierre Gasly", 12.5);
    public static final Driver d12 = new Driver("Esteban Ocon", 11.4);
    public static final Driver d13 = new Driver("Yuki Tsunoda", 8.2);
    public static final Driver d14 = new Driver("Daniel Ricciardo", 8.0);
    public static final Driver d15 = new Driver("Alex Albon", 9.8);
    public static final Driver d16 = new Driver("Logan Sargeant", 4.5);
    public static final Driver d17 = new Driver("Valtteri Bottas", 7.6);
    public static final Driver d18 = new Driver("Zhou Guanyu", 5.5);
    public static final Driver d19 = new Driver("Kevin Magnussen", 5.5);
    public static final Driver d20 = new Driver("Nico Hulkenberg", 6.7);

    private final String name;
    private final double value; //The cost to recruit the driver

    // EFFECTS : constructs a driver of given name and value
    public Driver(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    // EFFECTS: returns a string representation of a driver's name and its value in millions ($)
    @Override
    public String toString() {
        return "Driver: " + name + "\nValue: $" + value + " million";
    }

    // EFFECTS: returns this Driver as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("value", value);
        return json;
    }

}
