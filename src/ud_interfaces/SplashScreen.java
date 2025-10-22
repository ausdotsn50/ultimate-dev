package ud_interfaces;

import events.KeyEVHandler;
import layout.Design;
import layout.DesignSplash;
import layout.UDImages;

import java.awt.*;

import javax.swing.*;

public class SplashScreen extends UltDevScreen {
    KeyEVHandler keyEVHandler = new KeyEVHandler();
    public SplashScreen(){
        this.setLayout(new BorderLayout());

        displayTop(this, "splash.html");
        displayCenter();
        displayBottom(this, "The Ultimate Dev Gameshow", "(c) Group Pink");

        this.setFocusable(true);
        this.addKeyListener(keyEVHandler);
    }

    @Override
    public void displayCenter() {
        JPanel centerPanel = new JPanel(new BorderLayout());

        Design.centerDefault(this, centerPanel);
        DesignSplash.titleDesign(centerPanel, "ULTIMATE DEV", "/* Think you've got what it takes? */", "Press 'Enter' to Start!");
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(UDImages.bgId1, 0, 0, getWidth(), getHeight(), this);
    }
}
