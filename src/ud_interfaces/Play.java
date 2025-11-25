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
    public static boolean categorySelect = true; // Category selection from introduction to comp mapping + equiv to round over
    public static boolean showResult = false; // Shows if correct/incorrect response to question

    // Life system
    public static int attemptsLeft = 3;
    // Point system
    public static int currPoints = 0;
    // Hint system
    public static int save = 1;
    public static int copy = 1;

    public static Toml toml; // parser

    // Rounds system
    public static int roundCtr = 0;
    public static int[] addtlPoints = {
            0, 100, 150,
            250, 400, 600,
            850, 1150
    };
    public Play(){
        this.setLayout(new BorderLayout());

        displayTop(this, "play.html");
        displayCenter();
        // displayBottom(this, "The Ultimate Dev Gameshow", "2");
    }

    public void displayCenter(){
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        Design.centerDefault(this, centerPanel);
        if(categorySelect && !showResult) {
            currPoints += addtlPoints[roundCtr];
            if (roundCtr < 8) { roundCtr++; }
            else { roundCtr = 0; } // Reset for forced new game or quit
            DesignCategory.showCategories(centerPanel, gbc);
            displayBottom(this, "Points: " + currPoints, "2");
        }
        else if(!categorySelect && showResult) { // Adding display bottom overrides for each Play-DesignScreen
            DesignResult.showResult(centerPanel, gbc);
            displayBottom(this, "The Ultimate Dev Gameshow", "hello");
        }
        else {
            DesignQuiz.showQuiz(this, centerPanel, toml); // Else if not selecting a category --> show quiz questions
            displayBottom(this, "The Ultimate Dev Gameshow", "hi");
        }
    }


    public void refreshCenter() {
        this.removeAll();              // clear everything
        displayTop(this, "play.html");
        displayCenter();               // rebuild center panel
        // displayBottom(this, "The Ultimate Dev Gameshow", null);
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
