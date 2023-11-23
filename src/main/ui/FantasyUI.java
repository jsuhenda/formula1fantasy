
package ui;

import model.Driver;
import model.League;
import model.Team;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FantasyUI extends JFrame {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;

    private JPanel masterPanel;
    private JPanel contentPanel;
    private JPanel teamPanel;
    private JPanel createTeamPanel;
    private JPanel welcomePanel;
    private JLabel teamNameLabel;
    private JPanel driversListPanel;
    private DriversPanel driversPanel;
    private SidebarPanel sidebarPanel;
    private LeaguePanel leaguePanel;
    private Set<String> selectedDrivers = new HashSet<>();

    private static final String JSON_STORE = "./data/league.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private League league;
    private List<Team> createdTeams = new ArrayList<>();


    public FantasyUI() {

        league = new League("F1 Fantasy");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        initializeFrame();
        initializeUI();
        initializeSidebar();
        setVisible(true);
    }

    // initializes JFrame
    private void initializeFrame() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Formula One Fantasy");
        setSize(WIDTH, HEIGHT);
        setBackground(new Color(243, 23, 23));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 5));
    }

    // initializes UI
    private void initializeUI() {
        masterPanel = new JPanel();
        masterPanel.setLayout(new BorderLayout());
        masterPanel.setBackground(Color.RED);

        contentPanel = new JPanel(new CardLayout());
        welcomePanel = new JPanel();
        welcomePanel.setBackground(Color.RED);
        JLabel welcomeLabel = initializeWelcomeLabel();
        JButton createTeamButton = createTeamButton();
        welcomePanel.add(welcomeLabel, BorderLayout.NORTH);
        welcomePanel.add(createTeamButton);
        JButton createLeagueButton = createLeagueButton();
        welcomePanel.add(createLeagueButton);

        createTeamPanel = new JPanel();
        createTeamPanel.setBackground(Color.RED);
        createTeamPanel.setLayout(new BoxLayout(createTeamPanel, BoxLayout.Y_AXIS));
        JTextField inputField = initializeInputField();
        JButton confirmButton = createConfirmButton(inputField);
        createTeamPanel.add(inputField);
        createTeamPanel.add(confirmButton);

        masterPanel.add(contentPanel, BorderLayout.CENTER);
        contentPanel.add(welcomePanel, "welcomePanel");
        contentPanel.add(createTeamPanel, "createTeamPanel");
        add(masterPanel);
    }

    // initializes sidebar panel
    private JPanel initializeSidebar() {
        sidebarPanel = new SidebarPanel();
        masterPanel.add(sidebarPanel, BorderLayout.WEST);
        sidebarPanel.setFantasyUI(this);
        return sidebarPanel;
    }

    // initializes welcome label
    private JLabel initializeWelcomeLabel() {
        JLabel welcomeLabel = new JLabel("Welcome to Formula One Fantasy!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 40));
        welcomeLabel.setForeground(Color.WHITE);
        return welcomeLabel;
    }

    // creates a button to create team
    private JButton createTeamButton() {
        JButton createTeamButton = new JButton("Create Team");
        createTeamButton.setMaximumSize(new Dimension(200, 50));
        ImageIcon plusIcon = new ImageIcon(new ImageIcon("data/plusIcon.png").getImage()
                .getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        createTeamButton.setIcon(plusIcon);
        createTeamButton.setFont(new Font("Arial", Font.BOLD, 20));
        createTeamButton.setMargin(new Insets(10, 10, 10, 10));
        createTeamButton.addActionListener(e -> showCreateTeamPanel());
        return createTeamButton;
    }

    // creates a text field to enter team name
    private JTextField initializeInputField() {
        JTextField inputField = new JTextField(20);
        inputField.setMaximumSize(new Dimension(500, 30));
        inputField.setAlignmentX(Component.CENTER_ALIGNMENT);
        return inputField;
    }

    // creates a confirm button
    private JButton createConfirmButton(JTextField inputField) {
        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(e -> handleConfirm(inputField.getText()));
        confirmButton.setFont(new Font("Arial", Font.BOLD, 16));
        return confirmButton;
    }

    // switches panel and displays the create team panel
    protected void showCreateTeamPanel() {
        CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
        cardLayout.show(contentPanel, "createTeamPanel");
    }

    // handles when the user clicks the confirm button
    private void handleConfirm(String teamName) {
        if (!teamName.isEmpty()) {
            Team newTeam = new Team(teamName);
            createdTeams.add(newTeam);
            setupTeamPanel(teamName);
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a team name!");
        }
    }

    // switches to the welcome panel (home page)
    protected void showWelcomePanel() {
        CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
        cardLayout.show(contentPanel, "welcomePanel");
        if (leaguePanel != null) {
            leaguePanel.setVisible(false);
        }
    }

    @SuppressWarnings("checkstyle:MethodLength")
    private void setupTeamPanel(String teamName) {
        if (teamName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a team name!");
            return;
        }

        teamPanel = createTeamPanelWithLayout();
        teamNameLabel = createTeamNameLabel(teamName);
        driversListPanel = createDriversListPanel();

        selectedDrivers.clear();

        JButton addDriversButton = createAddDriversButton();
        JButton removeDriversButton = createRemoveDriversButton();

        teamPanel.add(teamNameLabel);
        teamPanel.add(driversListPanel);
        teamPanel.add(addDriversButton);
        teamPanel.add(removeDriversButton);

        createTeamPanel.add(teamPanel);
        createTeamPanel.validate();
        createTeamPanel.repaint();
    }


    private JPanel createTeamPanelWithLayout() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.RED);
        return panel;
    }

    private JLabel createTeamNameLabel(String teamName) {
        JLabel label = new JLabel("Team Name: " + teamName);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private JPanel createDriversListPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        return panel;
    }

    private JButton createAddDriversButton() {
        JButton button = new JButton("Add Drivers");
        button.addActionListener(driverEvent -> {
            if (driversPanel == null) {
                driversPanel = new DriversPanel();
                driversPanel.setFantasyUIReference(this);
            }
            showDriversDialog();
        });
        return button;
    }


    // show the driver's dialog when choosing drivers
    private void showDriversDialog() {
        if (driversPanel == null) {
            driversPanel = new DriversPanel();
            driversPanel.setFantasyUIReference(this);
        }

        JDialog driversDialog = new JDialog(this, "Select Drivers", Dialog.ModalityType.APPLICATION_MODAL);
        driversDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        driversDialog.setLayout(new BorderLayout());
        driversDialog.add(driversPanel, BorderLayout.CENTER);
        driversDialog.pack();
        driversDialog.setLocationRelativeTo(this);
        driversDialog.setVisible(true);
    }

    // method to add driver to team, adds only if size of team is less than maximum driver size of 5, and
    // driver is not already in team
    public void addDriverToTeam(String driverName, ImageIcon driverImage) {
        if (selectedDrivers.size() >= 5) {
            JOptionPane.showMessageDialog(this, "You've reached the maximum limit of 5 drivers.");
            return;
        }

        if (selectedDrivers.contains(driverName)) {
            JOptionPane.showMessageDialog(this, "You've already added " + driverName + " to your team.");
            return;
        }

        JLabel driverLabel = new JLabel(driverName);
        driverLabel.setIcon(driverImage);
        driverLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        driversListPanel.add(driverLabel);
        driversListPanel.revalidate();
        driversListPanel.repaint();

        selectedDrivers.add(driverName);

        JOptionPane.showMessageDialog(this, "Successfully added " + driverName + " to your team!");
    }

    private JButton createRemoveDriversButton() {
        JButton button = new JButton("Remove Driver");
        button.addActionListener(removeDriverEvent -> showRemoveDriversDialog());
        return button;
    }

    private void showRemoveDriversDialog() {
        if (!selectedDrivers.isEmpty()) {
            JFrame removeDriversFrame = new JFrame("Remove Drivers");
            removeDriversFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            removeDriversFrame.setLayout(new BorderLayout());

            JPanel removeDriversPanel = new JPanel();
            removeDriversPanel.setLayout(new BoxLayout(removeDriversPanel, BoxLayout.Y_AXIS));

            for (String driverName : selectedDrivers) {
                JButton removeDriverButton = new JButton("Remove " + driverName);
                removeDriverButton.addActionListener(removeDriverEvent -> removeDriverFromTeam(driverName));
                removeDriversPanel.add(removeDriverButton);
            }

            removeDriversFrame.add(removeDriversPanel, BorderLayout.CENTER);
            removeDriversFrame.pack();
            removeDriversFrame.setLocationRelativeTo(this);
            removeDriversFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "No drivers to remove!");
        }
    }

    private void removeDriverFromTeam(String driverName) {
        for (Component component : driversListPanel.getComponents()) {
            if (component instanceof JLabel) {
                JLabel driverLabel = (JLabel) component;
                if (driverLabel.getText().equals(driverName)) {
                    driversListPanel.remove(driverLabel);
                    selectedDrivers.remove(driverName);
                    driversListPanel.revalidate();
                    driversListPanel.repaint();
                    JOptionPane.showMessageDialog(this, "Successfully removed " + driverName + " from your team!");
                    break;
                }
            }
        }
    }


    // EFFECTS: saves the league to file
    protected void saveLeague() {
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
    protected void loadLeague() {
        try {
            league = jsonReader.read();
            System.out.println("Loaded " + league.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    private void refreshUIWithLoadedLeague() {
        // Clear the teamPanel and driversListPanel before re-adding teams and drivers
        if (teamPanel != null && driversListPanel != null) {
            teamPanel.removeAll();
            driversListPanel.removeAll();
        }

        // Display teams and drivers from the loaded league
        displayTeamsAndDrivers();

        // Check if the teamPanel is not null and not added to createTeamPanel, then add it
        if (createTeamPanel != null && teamPanel != null && !isComponentInPanel(createTeamPanel, teamPanel)) {
            createTeamPanel.add(teamPanel);
        }

        // Update the UI after re-adding teams and drivers
        if (createTeamPanel != null) {
            createTeamPanel.revalidate();
            createTeamPanel.repaint();
        }
    }

    // Helper method to check if a component is already added to a panel
    private boolean isComponentInPanel(Container container, Component component) {
        Component[] components = container.getComponents();
        for (Component c : components) {
            if (c.equals(component)) {
                return true;
            }
        }
        return false;
    }

    private void displayTeamsAndDrivers() {
        // Get teams and their drivers from the loaded league
        List<Team> teams = league.getTeams();

        for (Team team : teams) {
            JLabel teamLabel = new JLabel("Team Name: " + team.getName());
            teamLabel.setFont(new Font("Arial", Font.BOLD, 24));
            teamLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            teamPanel.add(teamLabel);

            List<Driver> driversInTeam = team.getDrivers();
            for (Driver driver : driversInTeam) {
                JLabel driverLabel = new JLabel(driver.getName());
                // Set the driver's image - replace this line with code to add driver's image
                driverLabel.setFont(new Font("Arial", Font.PLAIN, 18));
                driversListPanel.add(driverLabel);
            }
        }

        // Update the UI after re-adding teams and drivers
        teamPanel.revalidate();
        driversListPanel.revalidate();
        teamPanel.repaint();
        driversListPanel.repaint();
    }

    // Create a button to create a league
    // creates a button to create team
    private JButton createLeagueButton() {
        JButton createLeagueButton = new JButton("Create League");
        createLeagueButton.setMaximumSize(new Dimension(200, 50));
        ImageIcon plusIcon = new ImageIcon(new ImageIcon("data/plusIcon.png").getImage()
                .getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        createLeagueButton.setIcon(plusIcon);
        createLeagueButton.setFont(new Font("Arial", Font.BOLD, 20));
        createLeagueButton.setMargin(new Insets(10, 10, 10, 10));
        createLeagueButton.addActionListener(e -> showCreateLeagueDialog());
        return createLeagueButton;
    }

    // Show a dialog to input league name and add stored teams to the league
    private void showCreateLeagueDialog() {
        JTextField leagueNameField = new JTextField(20);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Enter League Name:"));
        panel.add(leagueNameField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Create League",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String leagueName = leagueNameField.getText();
            if (!leagueName.isEmpty()) {
                createLeagueAndAddTeams(leagueName, createdTeams); // Pass the stored teams to create the league
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a league name!");
            }
        }
    }

    private void createLeagueAndAddTeams(String leagueName, List<Team> teams) {
        League newLeague = new League(leagueName);

        for (Team team : teams) {
            newLeague.addTeam(team);
        }

        this.league = newLeague;

        // Optionally, you can save the league after creation
        saveLeague();

        System.out.println("League created: " + leagueName);
        System.out.println("Teams added to the league: " + teams.size());
    }

    public League getLeague() {
        return league;
    }
}
