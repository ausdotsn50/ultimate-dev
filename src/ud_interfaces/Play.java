package ud_interfaces;

import layout.Design;
import layout.DesignCategory;
import layout.constants.CustomButton;
import layout.constants.UDImages;

import javax.swing.*;
import java.awt.*;

public class Play extends UltDevScreen{
    // Temporary conditions for showing screens
    public static int categoryCnt = 7;

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
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        Design.centerDefault(this, centerPanel);

        if(cond1){
            DesignCategory.showCategories(this, centerPanel, gbc);
        }
        else if(cond2){

        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(UDImages.bgId1, 0, 0, getWidth(), getHeight(), this);
    }
}
