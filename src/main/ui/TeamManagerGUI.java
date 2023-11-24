//package ui;
//
//import model.Driver;
//import model.Team;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class TeamManagerGUI extends JFrame {
//    private Team team; // Assuming you have a Team object
//    private JComboBox<String> driverComboBox;
//
//    public TeamManagerGUI(Team team) {
//        this.team = team;
//
//        // Assuming you have a list of driver names as constants
//        String[] driverNames = {
//                "Driver1", "Driver2", "Driver3", /* Add all 20 driver names here */
//        };
//
//        driverComboBox = new JComboBox<>(driverNames);
//
//        JButton addDriverButton = new JButton("Add Driver");
//        addDriverButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String selectedDriverName = (String) driverComboBox.getSelectedItem();
//                // Search for the selected driver from the predefined list of constants
//                Team.Driver newDriver = getDriverByName(selectedDriverName);
//
//                if (newDriver != null && team.addDriver(newDriver)) {
//                    JOptionPane.showMessageDialog(null, "Driver added successfully!");
//                } else {
//                    JOptionPane.showMessageDialog(null, "Cannot add the driver. Team is full or cost cap reached.");
//                }
//            }
//        });
//
//        JPanel panel = new JPanel();
//        panel.add(driverComboBox);
//        panel.add(addDriverButton);
//        add(panel);
//
//        setTitle("Team Manager");
//        setSize(300, 200);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//        setVisible(true);
//    }
//
//    public static void main(String[] args) {
//        Team myTeam = new Team("MyTeam");
//        SwingUtilities.invokeLater(() -> new TeamManagerGUI(myTeam));
//    }
//}
//
