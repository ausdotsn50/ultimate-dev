package ud_interfaces;

import events.MouseEVHandler;
import layout.Design;

import java.awt.*;
import javax.swing.*;

import java.util.Objects;

public class SplashScreen extends JPanel{
    Image backgroundImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/template.png"))).getImage();
    MouseEVHandler mouseEVHandler = new MouseEVHandler();

    public SplashScreen(){
        this.setLayout(new BorderLayout());

        displayTop();
        displayCenter();
        displayBottom();
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
        toStart.setForeground(Color.WHITE);
        toStart.addMouseListener(mouseEVHandler);
        buttonPanel.add(toStart);

        // Footer panel below instruction label
        JPanel footerPanel = new JPanel(new GridLayout(1,2));

        JLabel fLeft = new JLabel("The Ultimate Dev Gameshow", JLabel.CENTER);
        JLabel fRight = new JLabel("Points: ",  JLabel.CENTER);

        fLeft.setFont(Design.loadCustomFont(20));
        fLeft.setForeground(Color.WHITE);

        fRight.setFont(Design.loadCustomFont(20));
        fRight.setForeground(Color.WHITE);

        footerPanel.add(fLeft);
        footerPanel.add(fRight);
        footerPanel.setOpaque(false);

        // Bottom panel components
        bottomPanel.add(buttonPanel, BorderLayout.NORTH);
        bottomPanel.add(footerPanel, BorderLayout.SOUTH);

        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    public void displayCenter() {
        JPanel centerPanel = new JPanel(new BorderLayout()); // This is for the label title

        centerPanel.setPreferredSize(new Dimension(Design.screenWidth, (int)(Design.screenHeight * 0.50) ));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
        centerPanel.setOpaque(false);

        Design.centerDesign(centerPanel, "ULTIMATE DEV");
        this.add(centerPanel, BorderLayout.CENTER);
    }

    public void displayTop() {
        // Note on top and bottom panels
        JPanel topPanel = new JPanel(new GridLayout(1,2));

        // Changed to a more dynamic panel placing
        topPanel.setPreferredSize(new Dimension(Design.screenWidth, (int)(Design.screenHeight * 0.15) ));
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        topPanel.setOpaque(false);

        Design.headerDesign(topPanel, "splash.html");
        this.add(topPanel, BorderLayout.NORTH);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
