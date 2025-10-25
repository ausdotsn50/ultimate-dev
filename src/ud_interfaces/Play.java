package ud_interfaces;

import layout.Design;
import layout.DesignMenu;
import layout.DesignSettings;
import layout.constants.RoundedPanel;
import layout.constants.UDImages;

import javax.swing.*;
import java.awt.*;

public class Play extends UltDevScreen{
    // Temporary conditions for showing screens
    boolean cond1 =  true;
    boolean cond2 = false;

    public Play(){
        this.setLayout(new BorderLayout());

        displayTop(this, "play.html");
        displayCenter();
        displayBottom(this, "The Ultimate Dev Gameshow", null);
    }

    // To do: Implement a changing displayCenter() for Play page
    public void displayCenter(){
        JPanel centerPanel = new JPanel(new GridLayout(3, 2));

        Design.centerDefault(this, centerPanel);

        if(cond1){
            for(int i = 0; i < 7; i++){
                RoundedPanel rp = new RoundedPanel(20);
                centerPanel.add(rp);
            }
        }
        else if(cond2){

        }
        // DesignMenu.optionsDesign(this, centerPanel, gbc);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(UDImages.bgId1, 0, 0, getWidth(), getHeight(), this);
    }
}
