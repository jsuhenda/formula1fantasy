package ui;

import model.League;
import model.Team;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LeaguePanel extends JPanel {
    private League league; // Store the league instance
    private JTextArea leagueInfoArea;

    public LeaguePanel(League league) {
        this.league = league; // Initialize league instance
        initialize();
    }

    private void initialize() {
        setLayout(new BorderLayout());
        setBackground(Color.RED);

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.WHITE);
        JLabel titleLabel = new JLabel("League Information");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(titleLabel);

        add(headerPanel, BorderLayout.NORTH);

        leagueInfoArea = new JTextArea();
        leagueInfoArea.setBackground(Color.RED);
        leagueInfoArea.setEditable(false);
        leagueInfoArea.setFont(new Font("Arial", Font.PLAIN, 18));

        JScrollPane scrollPane = new JScrollPane(leagueInfoArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(scrollPane, BorderLayout.CENTER);

        updateLeagueInfo(); // Initial display of league information
    }

    protected void updateLeagueInfo() {
        if (league != null) {
            StringBuilder leagueInfo = new StringBuilder();
            leagueInfo.append("League Name: ").append(league.getName()).append("\n\n");
            leagueInfo.append("Teams:\n");

            List<Team> teams = league.getTeams();
            for (Team team : teams) {
                leagueInfo.append("Team: ").append(team.getName()).append("\n\n");
            }

            leagueInfoArea.setText(leagueInfo.toString());
        }
    }

    public void setLeague(League league) {
        this.league = league; // Setter method for league instance
        updateLeagueInfo(); // Update league information when league is set
    }

    public JPanel getLeaguePanel() {
        return this; // Return the LeaguePanel itself
    }
}
