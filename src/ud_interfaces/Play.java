package ud_interfaces;

import com.moandjiezana.toml.Toml;
import layout.design.Design;
import layout.design.DesignCategory;
import layout.constants.UDImages;
import layout.design.DesignQuiz;
import layout.design.DesignResult;

import javax.swing.*;
import java.awt.*;

public class Play extends UltDevScreen{
    // Temporary conditions for showing screens
    // public static int categoryCnt = 7;
    public static boolean categorySelect = true;
    public static Toml toml;

    public static boolean showResult = false;
    public static int attemptsLeft = 3;

    // Might move this
    public static int currPoints = 30;
    public static int save = 1;
    public static int copy = 1;
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
        if(categorySelect && !showResult){
            DesignCategory.showCategories(centerPanel, gbc);
        }
        else if(!categorySelect && showResult){
            DesignResult.showResult(centerPanel, gbc);
        }
        else {
            // Else if not selecting a category --> show quiz questions
            // Parsing the appropriate TOML file

            // Addition: recommended to pass a reference of Play
            DesignQuiz.showQuiz(this, centerPanel, gbc, toml);
        }
    }

    @Override
    public void displayBottom(JPanel mainPanel, String leftHd, String rightHd) {
        Design.footerDesign(mainPanel, "CoDev Calls: Copy [" + copy + "]" +
                " | Save [" + save + "]", "Points: " + currPoints);
    }

    // Override displayBottom


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(UDImages.bgId1, 0, 0, getWidth(), getHeight(), this);
    }

    public void refreshCenter() {
        this.removeAll();              // clear everything
        displayTop(this, "play.html");
        displayCenter();               // rebuild center panel
        displayBottom(this, "The Ultimate Dev Gameshow", null);
        this.revalidate();             // re-layout components
        this.repaint();                // re-render graphics
    }

}
