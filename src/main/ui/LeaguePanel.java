package ui;

import model.League;
import model.Team;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LeaguePanel extends JPanel {
    private JTextArea leagueInfoArea;

    public LeaguePanel(League league) {
        initialize(league);
    }

    private void initialize(League league) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.WHITE);
        JLabel titleLabel = new JLabel("League Information");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(titleLabel);

        add(headerPanel, BorderLayout.NORTH);

        // Initializing the JTextArea to display league info
        leagueInfoArea = new JTextArea();
        leagueInfoArea.setBackground(Color.WHITE);
        leagueInfoArea.setEditable(false);
        leagueInfoArea.setFont(new Font("Arial", Font.PLAIN, 18));

        JScrollPane scrollPane = new JScrollPane(leagueInfoArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(scrollPane, BorderLayout.CENTER);

        updateLeague(league); // Initial display of league information
    }

    public void updateLeague(League league) {
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
