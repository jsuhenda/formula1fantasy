
package ui;

import model.Driver;
import model.Event;
import model.EventLog;
import model.League;
import model.Team;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class FantasyUI extends JFrame {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;

    private JPanel masterPanel;
    private JPanel contentPanel;
    private JPanel createTeamPanel;
    private JPanel homePanel;
    private LeaguePanel leaguePanel;

    private static final String JSON_STORE = "./data/league.json";
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;
    private League league;

    public FantasyUI() {

        league = new League("F1 Fantasy");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        initializeFrame();
        initializeUI();
        initializeSidebar();
        add(masterPanel);
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

        //SOURCE: https://stackoverflow.com/questions/60516720/java-how-to-print-message-when-a-jframe-is-closed
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                EventLog eventLog = EventLog.getInstance();
                for (Event event : eventLog) {
                    System.out.println(event.toString() + "\n");
                }
                System.exit(0);
            }
        });
    }

    // initializes UI
    private void initializeUI() {
        masterPanel = new JPanel();
        masterPanel.setLayout(new BorderLayout());
        masterPanel.setBackground(Color.RED);

        contentPanel = new JPanel(new CardLayout());
        initializeHomePanel();
        initializeCreateTeamPanel();
        initializeLeaguePanel();

        masterPanel.add(contentPanel, BorderLayout.CENTER);
        contentPanel.add(homePanel, "homePanel");
        contentPanel.add(createTeamPanel, "createTeamPanel");
        contentPanel.add(leaguePanel, "leaguePanel");
    }


    // initializes sidebar panel
    private void initializeSidebar() {
        SidebarPanel sidebarPanel = new SidebarPanel();
        masterPanel.add(sidebarPanel, BorderLayout.WEST);
        sidebarPanel.setFantasyUI(this);
    }

    // initializes welcome label
    private JLabel initializeWelcomeLabel() {
        JLabel welcomeLabel = new JLabel("Welcome to Formula One Fantasy!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 40));
        welcomeLabel.setForeground(Color.WHITE);
        return welcomeLabel;
    }

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

    private void initializeHomePanel() {
        homePanel = new JPanel();
        homePanel.setBackground(Color.RED);
        homePanel.add(initializeWelcomeLabel());
        homePanel.add(createTeamButton());
    }

    // switches to the home panel
    protected void showHomePanel() {
        CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
        cardLayout.show(contentPanel, "homePanel");
    }

    // switches panel and displays the createTeam panel
    protected void showCreateTeamPanel() {
        CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
        cardLayout.show(contentPanel, "createTeamPanel");
    }

    private void initializeCreateTeamPanel() {
        createTeamPanel = new JPanel();
        createTeamPanel.setBackground(Color.RED);
        createTeamPanel.setLayout(new BoxLayout(createTeamPanel, BoxLayout.Y_AXIS));
        JTextField inputField = initializeInputField();
        JButton confirmButton = createConfirmButton(inputField);
        createTeamPanel.add(inputField);
        createTeamPanel.add(confirmButton);
    }

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

    // handles when the user clicks the confirm button
    private void handleConfirm(String teamName) {
        if (!teamName.isEmpty()) {
            Team newTeam = new Team((teamName));
            league.addTeam(newTeam);
            setupTeamPanel(newTeam);
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a team name!");
        }
    }

    private JPanel setupTeamPanel(Team team) {

        JPanel teamPanel = createTeamPanelWithLayout();
        JLabel teamNameLabel = createTeamNameLabel(team.getName());
        JPanel driversListPanel = createDriversListPanel();
        JLabel totalValueLabel = createTotalValueLabel(team);

//        makeDriverButton(team);
        JButton addDriversButton = new JButton("Add Drivers");
        addDriversButton.addActionListener(e -> createDriversPanel(team, driversListPanel, totalValueLabel));

        JButton removeDriversButton = new JButton("Remove Drivers");
        removeDriversButton.addActionListener(e -> removeDriverFromTeam(team, driversListPanel, totalValueLabel));

        teamPanel.add(teamNameLabel);
        teamPanel.add(totalValueLabel);
        teamPanel.add(driversListPanel);
        teamPanel.add(addDriversButton);
        teamPanel.add(removeDriversButton);

        createTeamPanel.add(teamPanel);
        createTeamPanel.validate();
        createTeamPanel.repaint();
        return driversListPanel;
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

    private JLabel createTotalValueLabel(Team team) {
        JLabel tvLabel = new JLabel();
        updateTotalValueLabel(team, tvLabel);
        tvLabel.setFont(new Font("Arial", Font.BOLD, 24));
        tvLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        return tvLabel;
    }

    // Method to update the total value label
    public void updateTotalValueLabel(Team team, JLabel totalValueLabel) {

        double totalValue = team.calculateTotalCost();
//        System.out.println("Updating total value: " + totalValue);
        totalValue = Math.round(totalValue * 10.0) / 10.0; // Round to one decimal place
        totalValueLabel.setText("Total Team Value: $" + String.format("%.1f", totalValue) + "m");
    }

    private JPanel createDriversListPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        return panel;
    }

    private void createDriversPanel(Team team, JPanel driversListPanel, JLabel totalValueLabel) {
        DriversPanel driversPanel = new DriversPanel();
        driversPanel.setFantasyUI(this); // Pass the reference to the DriversPanel
        driversPanel.setTeam(team);
        driversPanel.setDriverTeamListPanel(driversListPanel);
        driversPanel.setTotalValueLabel(totalValueLabel);
        JFrame newFrame = new JFrame();
        newFrame.setSize(200, 800);
        newFrame.add(driversPanel);
        newFrame.validate();
        newFrame.repaint();
        newFrame.setVisible(true);
    }

    public void addDriverToTeam(Driver driver, String driverImage, Team team, JPanel driversListPanel, JLabel tvLabel) {
        boolean added = team.addDriver(driver);

        if (added) {
            addDriverPanelToTeam(driver, driverImage, team, driversListPanel, tvLabel);
            JOptionPane.showMessageDialog(this, "Successfully added " + driver.getName() + " to your team!");
        } else {
            handleFailedDriverAddition(driver, team);
        }
    }

    private void addDriverPanelToTeam(Driver driver, String driverImage, Team team, JPanel driversListPanel,
                                      JLabel tvLabel) {
        int imageSize = 50;
        JLabel driverLabel = new JLabel(driver.getName() + " ($" + driver.getValue() + "m)");
        ImageIcon driverIcon = new ImageIcon(driverImage);
        Image image = driverIcon.getImage().getScaledInstance(imageSize, imageSize, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(image);
        driverLabel.setIcon(scaledIcon);
        driverLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        driversListPanel.add(driverLabel);
        updateTotalValueLabel(team, tvLabel);
        driversListPanel.validate();
        driversListPanel.repaint();
        createTeamPanel.validate();
        createTeamPanel.repaint();
    }

    private void handleFailedDriverAddition(Driver driver, Team team) {
        if (team.getDrivers().size() >= Team.MAX_DRIVERS) {
            JOptionPane.showMessageDialog(this,
                    "You have exceeded the maximum limit of drivers!");
        } else if (team.getDrivers().contains(driver)) {
            JOptionPane.showMessageDialog(this,
                    "You have already added " + driver.getName() + " to your team!");
        } else {
            JOptionPane.showMessageDialog(this,
                    "Adding " + driver.getName() + " exceeds the budget!");
        }
    }

    public void removeDriverFromTeam(Team team, JPanel driversListPanel, JLabel tvLabel) {

        if (!team.getDrivers().isEmpty()) {
            JFrame removeDriversFrame = new JFrame("Remove Drivers");
            removeDriversFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            removeDriversFrame.setLayout(new BorderLayout());

            JPanel removeDriversPanel = new JPanel();
            removeDriversPanel.setLayout(new BoxLayout(removeDriversPanel, BoxLayout.Y_AXIS));

            for (Driver d : team.getDrivers()) {
                JButton removeDriverButton = new JButton("Remove " + d.getName());
                removeDriverButton.addActionListener(removeDriverEvent ->
                        removeDriverAndLabel(d, removeDriverButton,
                                removeDriversPanel, team, driversListPanel, tvLabel));
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

    private void removeDriverAndLabel(Driver driver, JButton button, JPanel panel, Team team,
                                      JPanel driversListPanel, JLabel tvLabel) {

        for (Component component : driversListPanel.getComponents()) {
            if (component instanceof JLabel) {
                JLabel driverLabel = (JLabel) component;
                if (driverLabel.getText().contains(driver.getName())) {
//                    System.out.println("Team: " + team.getName());
                    driversListPanel.remove(driverLabel);
                    team.removeDriver(driver);
                    updateTotalValueLabel(team, tvLabel);
                    driversListPanel.revalidate();
                    driversListPanel.repaint();
                    JOptionPane.showMessageDialog(this,
                            "Successfully removed " + driver.getName() + " from your team!");
                    panel.remove(button);
                    panel.validate();
                    panel.repaint();
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
            leaguePanel.setLeague(league);
            updateDriverPanel();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // switches to the home panel
    protected void showLeaguePanel() {
        CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
        cardLayout.show(contentPanel, "leaguePanel");
    }

    private void initializeLeaguePanel() {
        leaguePanel = new LeaguePanel(league);
        leaguePanel.setBackground(Color.RED);
    }

    private void updateDriverPanel() {
        List<Team> teams = league.getTeams();

        for (Team t : teams) {
            JPanel driversLIstPanel = setupTeamPanel(t);
            List<Driver> drivers = t.getDrivers();
            for (Driver d : drivers) {
                String imgPath = "data/" + d.getName().split(" ")[1].substring(0, 3).toLowerCase() + ".png";
                addDriverPanelToTeam(d, imgPath, t, driversLIstPanel, new JLabel());
            }
        }

    }



}

