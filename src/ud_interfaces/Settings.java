package ud_interfaces;

import layout.Design;

import javax.swing.*;
import java.awt.*;

public class Settings extends UltDevScreen{
    public Settings(){
        this.setLayout(new BorderLayout());

        displayTop(this, "settings.html");
        displayCenter();
        displayBottom(this, "The Ultimate Dev Gameshow", "Points: 0");
    }

    public void displayCenter(){
        JPanel centerPanel = new JPanel();

        Design.centerDefault(this, centerPanel);
        // DesignMenu.optionsDesign(this, centerPanel, gbc);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(Design.bgId1, 0, 0, getWidth(), getHeight(), this);
    }
}
