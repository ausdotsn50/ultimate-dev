package ud_interfaces;

import layout.Design;
import layout.DesignSettings;
import layout.constants.UDImages;

import javax.swing.*;
import java.awt.*;

public class Settings extends UltDevScreen{
    public Settings(){
        this.setLayout(new BorderLayout());

        displayTop(this, "settings.html");
        displayCenter();
        displayBottom(this, "The Ultimate Dev Gameshow", null);
    }

    public void displayCenter(){
        // Choose your custom layout
        JPanel centerPanel = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();

        Design.centerDefault(this, centerPanel);
        DesignSettings.centerDesign(this, centerPanel, gbc);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(UDImages.bgId1, 0, 0, getWidth(), getHeight(), this);
    }
}
