package ud_interfaces;

import com.moandjiezana.toml.Toml;
import events.sound_utls.SoundUtils;
import layout.design.*;
import layout.constants.UDImages;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Play extends UltDevScreen{
    public static boolean categorySelect; // Category selection from introduction to comp mapping + equiv to round over
    public static boolean showResult; // Shows if correct/incorrect response to question

    // Life system
    public static int attemptsLeft;
    // Point system
    public static int currPoints;
    public static int[] addtlPoints = { 0, 100, 150, 250, 400, 600, 850, 1150 };
    // Hint system
    public static int copy;
    public static boolean coDevActive = false;

    // Rounds system
    public static int roundCtr;
    public static Toml toml; // parser
    public static JPanel centerPanel;
    public Play(){
        init();
        this.setLayout(new BorderLayout());
        displayTop(this, "play.html");
        displayCenter();
    }

    public void displayCenter(){
        centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        Design.centerDefault(this, centerPanel);

        if(DesignCategory.categories.isEmpty() && attemptsLeft != 0 && categorySelect){
            currPoints += addtlPoints[roundCtr];
            DesignUltDev.showUltDev(this, centerPanel, gbc); // Show ultimate dev screen
            System.out.println(currPoints);
        } else if(categorySelect && !showResult) {
            currPoints += addtlPoints[roundCtr];
            if(roundCtr > 0) {
                attemptsLeft += 1;
                copy += 1;
            }
            if (roundCtr < 8) { roundCtr++; }
            else { roundCtr = 0; } // Reset for forced new game or quit
            //DesignUltDev.showUltDev(this, centerPanel, gbc); -- @Elizah if you want to design the ult dev screen, do it here
            DesignCategory.showCategories(this, centerPanel, gbc);
        } else if(!categorySelect && showResult) { // Adding display bottom overrides for each Play-DesignScreen
            DesignResult.showResult(this, centerPanel, gbc);
        } else if (coDevActive){
            DesignCoDev.showCoDev(this, centerPanel);
        }
        else {
            DesignQuiz.showQuiz(this, centerPanel, toml); // Else if not selecting a category --> show quiz questions
        }
    }

    public void refreshCenter() {
        this.removeAll();              // clear everything
        displayTop(this, "play.html");
        displayCenter();               // rebuild center panel
        this.revalidate();             // re-layout components
        this.repaint();                // re-render graphics
    }

    // todo: modify this into a cleaner init function
    public void init() {
        SoundUtils.stopBgMusic();
        SoundUtils.bgMusic();
        categorySelect = true; showResult = false;
        attemptsLeft = 100; currPoints = 0;
        copy = 2;
        roundCtr = 0;
        DesignCategory.categories = new ArrayList<>(Arrays.asList( // Repopulate the array
                "Introduction to the Paradigms", "Procedural Programming",
                "Functional Programming", "Object-Oriented Programming",
                "Imperative vs Declarative", "Event-Driven Programming",
                "Component Mappings between Paradigms"
        )); DesignQuiz.endQuizAndReturn();
    }

    public JPanel getCenterPanel() {
        return centerPanel;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(UDImages.bgId1, 0, 0, getWidth(), getHeight(), this);
    }
}
