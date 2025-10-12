import java.awt.*;
import javax.swing.*;

import java.util.Objects;

public class SplashScreen extends JPanel{
    Image backgroundImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("image/template.png"))).getImage();

    public SplashScreen(CardLayout cardLayout, JPanel container){
        this.setLayout(new BorderLayout());

        // Note on top and bottom panels
        JPanel topPanel = new JPanel(new GridLayout(1,2));
        JPanel centerPanel = new JPanel(new BorderLayout()); // This is for the label title
        JPanel bottomPanel = new JPanel();

        // Changed to a more dynamic panel placing
        topPanel.setPreferredSize(new Dimension(Design.screenWidth, (int)(Design.screenHeight * 0.15) ));
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        centerPanel.setPreferredSize(new Dimension(Design.screenWidth, (int)(Design.screenHeight * 0.50) ));
        bottomPanel.setPreferredSize(new Dimension(Design.screenWidth, (int)(Design.screenHeight * 0.35) ));

        topPanel.setOpaque(false);
        centerPanel.setOpaque(false);
        bottomPanel.setOpaque(false);

        Design.topDesign(topPanel, "splash.html");
        Design.centerDesign(centerPanel, "ULTIMATE DEV");

        // Instruction label
        JLabel toStart = new JLabel("Press 'Enter' to Start!");
        toStart.setFont(Design.loadCustomFont(20));
        toStart.setForeground(Color.WHITE);

        bottomPanel.add(toStart, BorderLayout.NORTH);


        this.add(topPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
