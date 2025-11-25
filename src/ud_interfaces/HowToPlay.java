package ud_interfaces;

import layout.design.Design;
import layout.design.DesignHowToPlay;
import layout.constants.UDImages;

import javax.swing.*;
import java.awt.*;

public class HowToPlay extends UltDevScreen{
    public HowToPlay(){
        this.setLayout(new BorderLayout());

        displayTop(this, "how_to_play.html");
        displayCenter();
        displayBottom(this, "The Ultimate Dev Gameshow", "1");
    }

    public void displayCenter(){
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        Design.centerDefault(this, centerPanel);
        DesignHowToPlay.displayIns(this, centerPanel, gbc);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(UDImages.bgId1, 0, 0, getWidth(), getHeight(), this);
    }
}
