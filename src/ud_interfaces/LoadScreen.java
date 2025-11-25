package ud_interfaces;

import events.MenuKeyHandler;
import layout.design.Design;
import layout.design.DesignLoading;
import layout.constants.UDImages;

import java.awt.*;

import javax.swing.*;

public class LoadScreen extends UltDevScreen {
    MenuKeyHandler menuKeyHandler = new MenuKeyHandler();
    public LoadScreen(){
        this.setLayout(new BorderLayout());

        displayTop(this, "load.html");
        displayCenter();
        displayBottom(this, "The Ultimate Dev Gameshow", "(c) Group Pink");

        this.setFocusable(true);
        this.addKeyListener(menuKeyHandler);
    }

    @Override
    public void displayCenter() {
        JPanel centerPanel = new JPanel(new BorderLayout());

        Design.centerDefault(this, centerPanel);
        DesignLoading.titleDesign(centerPanel, "ULTIMATE DEV", "/* Think you've got what it takes? */", "Press 'Enter' to Start!");
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(UDImages.bgId1, 0, 0, getWidth(), getHeight(), this);
    }
}
