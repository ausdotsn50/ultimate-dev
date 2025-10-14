package ud_interfaces;

import events.KeyEVHandler;
import events.MouseEVHandler;
import layout.Design;
import layout.DesignSplash;

import java.awt.*;

import javax.swing.*;

import java.util.Objects;

public class SplashScreen extends JPanel{
    Image backgroundImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/template.png"))).getImage();
    MouseEVHandler mouseEVHandler = new MouseEVHandler();
    KeyEVHandler keyEVHandler = new KeyEVHandler();

    public SplashScreen(){
        this.setLayout(new BorderLayout());

        displayTop();
        displayCenter();
        displayBottom();

        this.setFocusable(true);
        this.addKeyListener(keyEVHandler);
    }

    public void displayBottom(){
        JPanel bottomPanel = new JPanel(new BorderLayout());

        bottomPanel.setPreferredSize(new Dimension(Design.screenWidth, (int)(Design.screenHeight * 0.35) ));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        bottomPanel.setOpaque(false);

        // Button panel for customization purposes
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);

        // Instruction label
        JLabel toStart = new JLabel("Press 'Enter' to Start!", JLabel.CENTER);
        toStart.setPreferredSize(new Dimension((int)(Design.screenWidth * 0.40), 60));
        toStart.setFont(Design.loadCustomFont(20));
        toStart.setForeground(new Color(0x00DAF6));

        Design.startFadeEffect(toStart);

        toStart.addMouseListener(mouseEVHandler);
        buttonPanel.add(toStart);

        // Footer panel below instruction label
        Design.footerDesign(bottomPanel, "The Ultimate Dev Gameshow", "(c) 2025 Group Pink");

        // Bottom panel add button and footer in (footerDesign)
        bottomPanel.add(buttonPanel, BorderLayout.NORTH);

        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    public void displayCenter() {
        DesignSplash.titleDesign(this, "ULTIMATE DEV", "/* Think you've got what it takes? */");
    }

    public void displayTop() {
        Design.headerDesign(this, "splash.html");
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
