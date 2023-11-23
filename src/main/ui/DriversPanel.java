package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class DriversPanel extends JPanel implements ActionListener {
    private Map<String, String> driverImagePaths;
    private JPanel driversListPanel;
    private JButton addToTeamButton;
    private FantasyUI fantasyUI;

    @SuppressWarnings("checkstyle:MethodLength")
    public DriversPanel() {

        driverImagePaths = new HashMap<>();
        driverImagePaths.put("Max Verstappen", "data/ver.png");
        driverImagePaths.put("Sergio Perez", "data/per.png");
        driverImagePaths.put("Lewis Hamilton", "data/ham.png");
        driverImagePaths.put("George Russell", "data/rus.png");
        driverImagePaths.put("Charles Leclerc", "data/lec.png");
        driverImagePaths.put("Carlos Sainz", "data/sai.png");
        driverImagePaths.put("Lando Norris", "data/nor.png");
        driverImagePaths.put("Oscar Piastri", "data/pia.png");
        driverImagePaths.put("Fernando Alonso", "data/alo.png");
        driverImagePaths.put("Lance Stroll", "data/str.png");
        driverImagePaths.put("Pierre Gasly", "data/gas.png");
        driverImagePaths.put("Esteban Ocon", "data/oco.png");
        driverImagePaths.put("Yuki Tsunoda", "data/tsu.png");
        driverImagePaths.put("Daniel Ricciardo", "data/ric.png");
        driverImagePaths.put("Alex Albon", "data/alb.png");
        driverImagePaths.put("Logan Sargeant", "data/sar.png");
        driverImagePaths.put("Valterri Bottas", "data/bot.png");
        driverImagePaths.put("Zhou Guanyu", "data/zho.png");
        driverImagePaths.put("Kevin Magnussen", "data/mag.png");
        driverImagePaths.put("Nico Hulkenberg", "data/hul.png");


        setLayout(new BorderLayout());

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


    @SuppressWarnings("checkstyle:MethodLength")
    private void initializeDriverButtons() {
        String[] driverNames = {
                "Max Verstappen", "Sergio Perez", "Lewis Hamilton", "George Russell", "Charles Leclerc", "Carlos Sainz",
                "Lando Norris", "Oscar Piastri", "Fernando Alonso", "Lance Stroll", "Pierre Gasly", "Esteban Ocon",
                "Yuki Tsunoda", "Daniel Ricciardo", "Alex Albon", "Logan Sargeant", "Valterri Bottas", "Zhou Guanyu",
                "Kevin Magnussen", "Nico Hulkenberg"
        };
        String[] imagePaths = {
                "data/ver.png", "data/per.png", "data/ham.png", "data/rus.png", "data/lec.png", "data/sai.png",
                "data/nor.png", "data/pia.png", "data/alo.png", "data/str.png", "data/gas.png", "data/oco.png",
                "data/tsu.png", "data/ric.png", "data/alb.png", "data/sar.png", "data/bot.png", "data/zho.png",
                "data/mag.png", "data/hul.png"
        };

        int imageSize = 50; // Adjust this size according to your preference

        for (int i = 0; i < driverNames.length; i++) {
            String driverName = driverNames[i];
            String imagePath = imagePaths[i];

            ImageIcon originalIcon = new ImageIcon(imagePath);
            Image image = originalIcon.getImage().getScaledInstance(imageSize, imageSize, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(image);

            JButton driverButton = new JButton(driverName, scaledIcon);
            driverButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            driverButton.setHorizontalTextPosition(SwingConstants.CENTER);

            driverButton.addActionListener(this);

            JPanel driverPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            driverPanel.add(driverButton);

            driversListPanel.add(driverPanel);
        }
    }

    public void setFantasyUIReference(FantasyUI fantasyUI) {
        this.fantasyUI = fantasyUI;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton clickedButton = (JButton) e.getSource();
            if (!clickedButton.getText().equals("Add to Team")) {
                String selectedDriver = clickedButton.getText();
                String imagePath = driverImagePaths.get(selectedDriver);
                ImageIcon driverImage = new ImageIcon(new ImageIcon(imagePath).getImage()
                        .getScaledInstance(50, 50, Image.SCALE_SMOOTH)); // Adjust image size

                fantasyUI.addDriverToTeam(selectedDriver, driverImage); // Pass both name and image
            }
        }
    }
}