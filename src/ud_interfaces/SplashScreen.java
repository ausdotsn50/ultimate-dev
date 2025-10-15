package ud_interfaces;

import events.KeyEVHandler;
import events.MouseEVHandler;
import layout.DesignSplash;

import java.awt.*;

import javax.swing.*;

import java.util.Objects;

public class SplashScreen extends UltDevScreen {
    Image backgroundImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/template_v1.png"))).getImage();
    MouseEVHandler mouseEVHandler = new MouseEVHandler();
    KeyEVHandler keyEVHandler = new KeyEVHandler();

    public SplashScreen(){
        this.setLayout(new BorderLayout());

        displayTop(this, "splash.html");
        displayCenter();
        displayBottom(this, "The Ultimate Dev Gameshow", "(c) Group Pink 2025");

        this.setFocusable(true);
        this.addKeyListener(keyEVHandler);
    }

    public void displayCenter() {
        DesignSplash.titleDesign(this, "ULTIMATE DEV", "/* Think you've got what it takes? */", "Press 'Enter' to Start!");
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
