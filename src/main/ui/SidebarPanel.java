package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SidebarPanel extends JPanel {
    private FantasyUI fantasyUI;

    public SidebarPanel() {
        initialize();
    }

    public void setFantasyUI(FantasyUI fantasyUI) {
        this.fantasyUI = fantasyUI;
    }

    private void initialize() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(200, FantasyUI.HEIGHT));

        addHomeButton();
        addCreateTeamButton();
        addSaveButton();
        addLoadButton();
        addViewLeagueButton();
    }

    private void addHomeButton() {
        JButton homeButton = new JButton("Home");
        homeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fantasyUI != null) {
                    fantasyUI.showWelcomePanel();
                }
            }
        });
        add(homeButton);
    }

    private void addCreateTeamButton() {
        JButton createTeamButton = new JButton("Create Team");
        createTeamButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        createTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fantasyUI != null) {
                    fantasyUI.showCreateTeamPanel();
                }
            }
        });
        add(createTeamButton);
    }

    private void addSaveButton() {
        JButton saveButton = new JButton("Save");
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fantasyUI != null) {
                    fantasyUI.saveLeague();
                }
            }
        });
        add(saveButton);
    }

    private void addLoadButton() {
        JButton loadButton = new JButton("Load");
        loadButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fantasyUI != null) {
                    fantasyUI.loadLeague();
                }
            }
        });
        add(loadButton);
    }

    private void addViewLeagueButton() {
        JButton viewLeagueButton = new JButton("View League");
        viewLeagueButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewLeagueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fantasyUI != null) {
                    displayLeaguePanel();
                }
            }
        });
        add(viewLeagueButton);
    }

    private void displayLeaguePanel() {
        if (fantasyUI != null && fantasyUI.getLeague() != null) {
            LeaguePanel leaguePanel = new LeaguePanel(fantasyUI.getLeague());
            JFrame leagueFrame = new JFrame("League Information");
            leagueFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            leagueFrame.add(leaguePanel);
            leagueFrame.pack();
            leagueFrame.setLocationRelativeTo(null);
            leagueFrame.setVisible(true);
        }
    }
}
