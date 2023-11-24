
package ui;

import model.Driver;
import model.Team;
import model.League;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FantasyUI extends JFrame implements ActionListener {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;

    private JPanel masterPanel;
    private JPanel contentPanel;
    private JPanel createTeamPanel;
    private JPanel homePanel;
    private JLabel teamNameLabel;
    private JPanel teamPanel;
    private JPanel driversListPanel;
    private DriversPanel driversPanel;
    private SidebarPanel sidebarPanel;
    //    private LeaguePanel leaguePanel;
    private Set<String> selectedDrivers = new HashSet<>();
    private List<Driver> drivers;

    private static final String JSON_STORE = "./data/league.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private League league;
    private Team team;

    private JButton createTeamButton;
    private JButton addDriversButton;
    private JButton removeDriversButton;


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
    }

    // initializes UI
    private void initializeUI() {
        masterPanel = new JPanel();
        masterPanel.setLayout(new BorderLayout());
        masterPanel.setBackground(Color.RED);

        contentPanel = new JPanel(new CardLayout());
        initalizeHomePanel();
        intializeCreateTeamPanel();

        masterPanel.add(contentPanel, BorderLayout.CENTER);
        contentPanel.add(homePanel, "homePanel");
        contentPanel.add(createTeamPanel, "createTeamPanel");
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

    private JButton createTeamButton() {
        createTeamButton = new JButton("Create Team");
        createTeamButton.setMaximumSize(new Dimension(200, 50));
        ImageIcon plusIcon = new ImageIcon(new ImageIcon("data/plusIcon.png").getImage()
                .getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        createTeamButton.setIcon(plusIcon);
        createTeamButton.setFont(new Font("Arial", Font.BOLD, 20));
        createTeamButton.setMargin(new Insets(10, 10, 10, 10));
        createTeamButton.addActionListener(e -> showCreateTeamPanel());
        return createTeamButton;
    }

    private JPanel initalizeHomePanel() {
        homePanel = new JPanel();
        homePanel.setBackground(Color.RED);
        homePanel.add(initializeWelcomeLabel());
        homePanel.add(createTeamButton());
        return homePanel;
    }

    // switches to the home panel
    protected void showHomePanel() {
        CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
        cardLayout.show(contentPanel, "homePanel");
    }

    // switches panel and displays the create team panel
    protected void showCreateTeamPanel() {
        CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
        cardLayout.show(contentPanel, "createTeamPanel");
    }

    private JPanel intializeCreateTeamPanel() {
        createTeamPanel = new JPanel();
        createTeamPanel.setBackground(Color.RED);
        createTeamPanel.setLayout(new BoxLayout(createTeamPanel, BoxLayout.Y_AXIS));
        JTextField inputField = initializeInputField();
        JButton confirmButton = createConfirmButton(inputField);
        createTeamPanel.add(inputField);
        createTeamPanel.add(confirmButton);
        return createTeamPanel;
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
            team = new Team((teamName));
            league.addTeam(team);
            setupTeamPanel(teamName);
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a team name!");
        }
    }

    private void setupTeamPanel(String teamName) {

        teamPanel = createTeamPanelWithLayout();
        teamNameLabel = createTeamNameLabel(teamName);
        driversListPanel = createDriversListPanel();

        selectedDrivers.clear();

        makeButton();

        teamPanel.add(teamNameLabel);
        teamPanel.add(driversListPanel);
        teamPanel.add(addDriversButton);
        teamPanel.add(removeDriversButton);

        createTeamPanel.add(teamPanel);
        createTeamPanel.validate();
        createTeamPanel.repaint();

//        createTeamPanel.add(teamNameLabel);
//        createTeamPanel.add(driversListPanel);
//        createTeamPanel.add(addDriversButton);
//        createTeamPanel.add(removeDriversButton);
//
//        createTeamPanel.validate();
//        createTeamPanel.repaint();
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

    private void makeButton() {
        addDriversButton = new JButton("Add Drivers");
        addDriversButton.addActionListener(this);

        removeDriversButton = new JButton("Remove Drivers");
        removeDriversButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addDriversButton) {
            driversPanel = new DriversPanel();
            driversPanel.setFantasyUI(this); // Pass the reference to the DriversPanel
            JFrame newFrame = new JFrame();
            newFrame.setSize(200, 800);
            newFrame.add(driversPanel);
            newFrame.validate();
            newFrame.repaint();
            newFrame.setVisible(true);
        } else if (e.getSource() == removeDriversButton) {
            // removeDriver method!!!
        }
    }

    public void addDriverToTeam(Driver driver, String driverImage) {
        team.addDriver(driver);

        JLabel driverLabel = new JLabel(driver.getName());
        ImageIcon driverIcon = new ImageIcon(driverImage);
        driverLabel.setIcon(driverIcon);
        driverLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        driversListPanel.add(driverLabel);
        driversListPanel.validate();
        driversListPanel.repaint();


        JOptionPane.showMessageDialog(this, "Successfully added " + driver.getName() + " to your team!");
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

}

