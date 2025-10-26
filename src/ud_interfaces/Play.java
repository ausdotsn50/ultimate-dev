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

        Design.centerDefault(this, centerPanel);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(UDImages.bgId1, 0, 0, getWidth(), getHeight(), this);
    }
}
