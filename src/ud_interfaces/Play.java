package ud_interfaces;

import layout.Design;
import layout.constants.CustomButton;
import layout.constants.UDImages;

import javax.swing.*;
import java.awt.*;

public class Play extends UltDevScreen{
    // Temporary conditions for showing screens
    boolean cond1 =  true;
    boolean cond2 = false;

    int categoryCnt = 7;

    public Play(){
        this.setLayout(new BorderLayout());

        displayTop(this, "play.html");
        displayCenter();
        displayBottom(this, "The Ultimate Dev Gameshow", null);
    }

    // To do: Implement a changing displayCenter() for Play page
    public void displayCenter(){
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 20, 0, 20);
        gbc.gridy = 0;

        JPanel titlePanel = new JPanel(new GridBagLayout());

        Design.centerDefault(this, centerPanel);

        int fontsize = 20;
        if(cond1){
            for(int i = 0; i < categoryCnt; i++){
                gbc.gridx = i;
                // Replace with custom buttons
                CustomButton category = new CustomButton("Text", 10,10);
                category.setPreferredSize(new Dimension(250, fontsize * 2));

                category.setFont(Design.loadCustomFont(fontsize));
                centerPanel.add(category, gbc);
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
