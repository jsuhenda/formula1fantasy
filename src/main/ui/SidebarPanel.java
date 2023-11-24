package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SidebarPanel extends JPanel implements ActionListener {
    private FantasyUI fantasyUI;
    private JButton homeButton;
    private JButton teamButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton viewLeagueButton;

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

        makeButton();
    }

    private void makeButton() {
        // home button
        homeButton = new JButton("Home");
        homeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        homeButton.addActionListener(this);
        add(homeButton);

        // view teams button
        teamButton = new JButton("Create Team");
        teamButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        teamButton.addActionListener(this);
        add(teamButton);

        // save button
        saveButton = new JButton("Save");
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveButton.addActionListener(this);
        add(saveButton);

        // load button
        loadButton = new JButton("Load");
        loadButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loadButton.addActionListener(this);
        add(loadButton);

        // view league button
        viewLeagueButton = new JButton("View League");
        viewLeagueButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewLeagueButton.addActionListener(this);
        add(viewLeagueButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == homeButton) {
            fantasyUI.showHomePanel();
        } else if (e.getSource() == teamButton) {
            fantasyUI.showCreateTeamPanel();
        } else if (e.getSource() == saveButton) {
            fantasyUI.saveLeague();
        } else if (e.getSource() == loadButton) {
            fantasyUI.loadLeague();
        } else if (e.getSource() == viewLeagueButton) {
//            fantasyUI.showViewLeaguePanel();
        }

    }
}
