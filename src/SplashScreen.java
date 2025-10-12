import java.awt.*;
import javax.swing.*;

import java.util.Objects;

public class SplashScreen extends JPanel{
    Image backgroundImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("image/template.png"))).getImage();

    public SplashScreen(CardLayout cardLayout, JPanel container){
        this.setLayout(new BorderLayout());

        // Note on top and bottom panels
        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel bottomPanel = new JPanel();

        // Changed to a more dynamic panel placing
        topPanel.setPreferredSize(new Dimension(Design.screenWidth, (int)(Design.screenHeight * 0.75) ));
        topPanel.setBackground(Color.RED);
        bottomPanel.setPreferredSize(new Dimension(Design.screenWidth, (int)(Design.screenHeight * 0.25) ));
        bottomPanel.setBackground(Color.BLUE);

        JLabel enter = new JLabel("Press 'Enter' to Start!");
        enter.setFont(Design.loadCustomFont(20));
        enter.setForeground(Color.WHITE);

        bottomPanel.add(enter, BorderLayout.NORTH);

        this.add(topPanel, BorderLayout.NORTH);
        this.add(bottomPanel, BorderLayout.CENTER);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
