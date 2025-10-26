package ud_interfaces;

import layout.design.Design;
import layout.design.DesignCategory;
import layout.constants.UDImages;
import layout.design.DesignQuiz;

import javax.swing.*;
import java.awt.*;

public class Play extends UltDevScreen{
    // Temporary conditions for showing screens
    boolean categorySelect =  true;

    public static int categoryCnt = 7;
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

        int categoryIndex;
        if(categorySelect){
            DesignCategory.showCategories(centerPanel, gbc);
        }
        else {
            // Else if not selecting a category --> go to the interface you're going to go
            // Parsing the appropriate TOML file
            DesignQuiz.showQuiz(centerPanel, gbc);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(UDImages.bgId1, 0, 0, getWidth(), getHeight(), this);
    }
}
