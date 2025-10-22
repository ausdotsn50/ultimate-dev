package ud_interfaces;

import layout.Design;
import layout.DesignMenu;
import layout.UDImages;

import java.awt.*;
import java.util.Objects;

import javax.swing.*;

public class MainMenu extends UltDevScreen {
    public MainMenu(){
        this.setLayout(new BorderLayout());

        displayTop(this, "main_menu.html");
        displayCenter();
        displayBottom(this, "The Ultimate Dev Gameshow", "Points: 0");
    }

    public void displayCenter(){
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        Design.centerDefault(this, centerPanel);
        DesignMenu.optionsDesign(this, centerPanel, gbc);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(UDImages.bgId2, 0, 0, getWidth(), getHeight(), this);
    }
}
