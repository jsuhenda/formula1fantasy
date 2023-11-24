package ui;

import model.Driver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class DriversPanel extends JPanel implements ActionListener {
    private Map<String, Driver> driverImagePaths;
    private JPanel driversListPanel;
    private JButton addToTeamButton;
    private JButton driverButton;
    private FantasyUI fantasyUI;
    private int imageSize = 50;
    private Driver driver;
    private String imagePath;

    String[] imagePaths = {
            "data/ver.png", "data/per.png", "data/ham.png", "data/rus.png", "data/lec.png", "data/sai.png",
            "data/nor.png", "data/pia.png", "data/alo.png", "data/str.png", "data/gas.png", "data/oco.png",
            "data/tsu.png", "data/ric.png", "data/alb.png", "data/sar.png", "data/bot.png", "data/zho.png",
            "data/mag.png", "data/hul.png"
    };


    public DriversPanel() {
        driverImagePaths = new HashMap<>();
        driverImagePaths.put("data/ver.png", Driver.d1);
        driverImagePaths.put("data/per.png", Driver.d2);
        driverImagePaths.put("data/ham.png", Driver.d3);
        driverImagePaths.put("data/rus.png", Driver.d4);
        driverImagePaths.put("data/lec.png", Driver.d5);
        driverImagePaths.put("data/sai.png", Driver.d6);
        driverImagePaths.put("data/nor.png", Driver.d7);
        driverImagePaths.put("data/pia.png", Driver.d8);
        driverImagePaths.put("data/alo.png", Driver.d9);
        driverImagePaths.put("data/str.png", Driver.d10);
        driverImagePaths.put("data/gas.png", Driver.d11);
        driverImagePaths.put("data/oco.png", Driver.d12);
        driverImagePaths.put("data/tsu.png", Driver.d13);
        driverImagePaths.put("data/ric.png", Driver.d14);
        driverImagePaths.put("data/alb.png", Driver.d15);
        driverImagePaths.put("data/sar.png", Driver.d16);
        driverImagePaths.put("data/bot.png", Driver.d17);
        driverImagePaths.put("data/zho.png", Driver.d18);
        driverImagePaths.put("data/mag.png", Driver.d19);
        driverImagePaths.put("data/hul.png", Driver.d20);

        setLayout(new BorderLayout());
        scrollPane();
    }

//    private List<Driver> getDriverList() {
//        return driver.returnDriverList();
//    }

    private void scrollPane() {
        // Create a scroll pane to contain the list of drivers
        JScrollPane scrollPane = new JScrollPane();
        driversListPanel = new JPanel();
        driversListPanel.setLayout(new BoxLayout(driversListPanel, BoxLayout.Y_AXIS));
        scrollPane.setViewportView(driversListPanel);

        add(scrollPane, BorderLayout.CENTER);

        addToTeamButton = new JButton("Add to Team");
        addToTeamButton.addActionListener(this);
        add(addToTeamButton, BorderLayout.SOUTH);

        initializeDriverButtons();
    }

    private void initializeDriverButtons() {
        for (int i = 0; i < driverImagePaths.size(); i++) {

            imagePath = imagePaths[i];
            driver = driverImagePaths.get(imagePath);

            ImageIcon originalIcon = new ImageIcon(imagePath);
            Image image = originalIcon.getImage().getScaledInstance(imageSize, imageSize, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(image);

            driverButton = new JButton(driver.getName(), scaledIcon);
            driverButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            driverButton.setHorizontalTextPosition(SwingConstants.CENTER);

            driverButton.addActionListener(this);

            JPanel driverPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            driverPanel.add(driverButton);

            driversListPanel.add(driverPanel);
        }
    }

    public void setFantasyUI(FantasyUI fantasyUI) {
        this.fantasyUI = fantasyUI;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton curr = (JButton) e.getSource();
            for (Map.Entry<String, Driver> entry : driverImagePaths.entrySet()) {
                if (entry.getValue().getName().equals(curr.getText())) {
                    fantasyUI.addDriverToTeam(entry.getValue(), entry.getKey()); // Pass both name and image
                }
            }
        }

//        if (e.getSource() == driverButton) {
//            System.out.println("blehhhhhh");
//            fantasyUI.addDriverToTeam(driver, imagePath); // Pass both name and image
//        }
    }
}