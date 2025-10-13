package ud_interfaces;

import events.MouseEVHandler;
import layout.Design;

import java.awt.*;
import javax.swing.*;

public class Menu extends JPanel{
    private JLabel play, howToPlay, settings, exit;

    public Menu(){
    this.setLayout(new BorderLayout());

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
        JPanel topPanel = new JPanel(new GridBagLayout());
        topPanel.setOpaque(false);

        topPanel.setPreferredSize(new Dimension((int)(Design.screenWidth * 0.40), 60));
        
    }

    public void displayBottom(){

    }
}
