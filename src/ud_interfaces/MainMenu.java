package ud_interfaces;

import layout.Design;

import java.awt.*;
import java.util.Objects;

import javax.swing.*;

public class MainMenu extends JPanel{
    Image backgroundImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/template_v2.png"))).getImage();

    JLabel play, howToPlay, settings, exit;
    GridBagConstraints gbc = new GridBagConstraints();

    public MainMenu(){
        this.setLayout(new BorderLayout());

        displayTop();
        displayCenter();
        displayBottom();
    }

    public void displayTop(){
        Design.headerDesign(this, "menu.html");
    }
    
    // To do: refactor code
    public void displayCenter(){
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);

        centerPanel.setPreferredSize(new Dimension((int)(Design.screenWidth * 0.40), 20));
        // centerPanel.setBorder(BorderFactory.createMatteBorder(10, 0, 0, 0, Color.CYAN));
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
            // e.printStackTrace();
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

        // play.addMouseListener(mouseEVHandler);

        this.add(centerPanel, BorderLayout.CENTER);
    }

    public void displayBottom() {
        Design.footerDesign(this, "The Ultimate Dev Gameshow", "Points: 0");
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
