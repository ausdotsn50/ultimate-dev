package ud_interfaces;

import com.moandjiezana.toml.Toml;
import layout.design.Design;
import layout.design.DesignCategory;
import layout.constants.UDImages;
import layout.design.DesignQuiz;
import layout.design.DesignResult;

import javax.swing.*;
import java.awt.*;

// Main file for the Play pages/interfaces
public class Play extends UltDevScreen{
    // Conditions that manipulate page display
    public static boolean categorySelect = true; // Category selection from introduction to comp mapping
    public static boolean showResult = false; // Shows if correct/incorrect response to question
    // public static boolean isRoundOver = false;

    // Life system
    public static int attemptsLeft = 3;
    // Point system
    public static int currPoints = 30;
    // Hint system
    public static int save = 1;
    public static int copy = 1;

    public static Toml toml; // parser
    public Play(){
        this.setLayout(new BorderLayout());

        displayTop(this, "play.html");
        displayCenter();
        displayBottom(this, "The Ultimate Dev Gameshow", null);
    }

    public void displayCenter(){
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        Design.centerDefault(this, centerPanel);
        // Switching panel shows logic here
        if(categorySelect && !showResult){
            DesignCategory.showCategories(centerPanel, gbc);
        }
        else if(!categorySelect && showResult){
            DesignResult.showResult(centerPanel, gbc);
        }
        else {
            DesignQuiz.showQuiz(this, centerPanel, toml); // Else if not selecting a category --> show quiz questions
        }
    }

    @Override // Overrides the existing footer from MainMenu
    public void displayBottom(JPanel mainPanel, String leftHd, String rightHd) {
        Design.footerDesign(mainPanel, "CoDev Calls: Copy [" + copy + "]" +
                " | Save [" + save + "]", "Points: " + currPoints); // Left and right footers
    }

    public void refreshCenter() {
        this.removeAll();              // clear everything
        displayTop(this, "play.html");
        displayCenter();               // rebuild center panel
        displayBottom(this, "The Ultimate Dev Gameshow", null);
        this.revalidate();             // re-layout components
        this.repaint();                // re-render graphics
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(UDImages.bgId1, 0, 0, getWidth(), getHeight(), this);
    }
}
