package ud_interfaces;

import events.MouseEVHandler;
import layout.Design;

import java.awt.*;
import java.util.Objects;

import javax.swing.*;

public class Menu extends JPanel{
    private JLabel play, howToPlay, settings, exit;
    private GridBagConstraints gbc = new GridBagConstraints();
    private CardLayout cardLayout;
    private JPanel container;
    private MouseEVHandler mouseEVHandler;

    Image backgroundImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/template_2.png"))).getImage();

    public Menu(CardLayout cardLayout, JPanel container){
    this.setLayout(new BorderLayout());
    this.cardLayout = cardLayout;
    this.container = container;
    this.mouseEVHandler = new MouseEVHandler(cardLayout, container);


    displayTop();
    displayCenter();
    displayBottom();

    }

    public void displayTop(){
        JPanel topPanel = new JPanel(new GridLayout(1,2));

        // Changed to a more dynamic panel placing
        topPanel.setPreferredSize(new Dimension(Design.screenWidth, (int)(Design.screenHeight * 0.15) ));
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        topPanel.setOpaque(false);

        Design.headerDesign(topPanel, "menu.html");
        this.add(topPanel, BorderLayout.NORTH);
    }

    public void displayCenter(){
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);

        centerPanel.setPreferredSize(new Dimension((int)(Design.screenWidth * 0.40), 20));
        //centerPanel.setBorder(BorderFactory.createMatteBorder(10, 0, 0, 0, Color.CYAN));
        //centerPanel.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2));

        play = new JLabel("Play");
        howToPlay = new JLabel("How to Play");
        settings = new JLabel("Settings");
        exit = new JLabel("Exit");

        try {
            play.setFont(Design.loadCustomFont(30));
            howToPlay.setFont(Design.loadCustomFont(30));
            settings.setFont(Design.loadCustomFont(30));
            exit.setFont(Design.loadCustomFont(30));

            play.setForeground(Color.MAGENTA);
            howToPlay.setForeground(Color.ORANGE);
            settings.setForeground(Color.GREEN);
            exit.setForeground(Color.RED);
        } catch (Exception e) {
            e.printStackTrace();
            play.setFont(new Font("SansSerif", Font.PLAIN, 30)); 
            howToPlay.setFont(new Font("SansSerif", Font.PLAIN, 30)); 
            settings.setFont(new Font("SansSerif", Font.PLAIN, 30)); 
            exit.setFont(new Font("SansSerif", Font.PLAIN, 30));
        }

        gbc.insets = new Insets(0, 0, 25, 500); 

        gbc.gridx = 0;
        gbc.gridy = 0;  
        centerPanel.add(play, gbc);

        gbc.insets = new Insets(10, 0, 25, 500);
        gbc.gridx = 0;
        gbc.gridy = 1;
        centerPanel.add(howToPlay, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        centerPanel.add(settings, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        centerPanel.add(exit, gbc);

        play.addMouseListener(mouseEVHandler);

        this.add(centerPanel, BorderLayout.CENTER);

    }

    public void displayBottom() {
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setPreferredSize(new Dimension((int)(Design.screenWidth * 0.40), 60));
        bottomPanel.setBorder(BorderFactory.createLineBorder(Color.PINK, 2));
        bottomPanel.setOpaque(false);

        // Add top padding to push the text upward
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        // Create footer panel
        JPanel footerPanel = new JPanel(new GridLayout(1, 2));
        footerPanel.setOpaque(false);

        JLabel fLeft = new JLabel("The Ultimate Dev Gameshow", JLabel.CENTER);
        JLabel fRight = new JLabel("Points: ", JLabel.CENTER);

        fLeft.setFont(Design.loadCustomFont(20));
        fLeft.setForeground(Color.WHITE);

        fRight.setFont(Design.loadCustomFont(20));
        fRight.setForeground(Color.WHITE);

        footerPanel.add(fLeft);
        footerPanel.add(fRight);

        // Add footer panel near the bottom
        bottomPanel.add(footerPanel, BorderLayout.CENTER);

        this.add(bottomPanel, BorderLayout.SOUTH);
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
