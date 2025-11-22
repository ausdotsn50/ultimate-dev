package layout.design;

import ud_interfaces.Play;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class DesignResult {
    static Random rand = new Random();
    public static void showResult(JPanel centerPanel, GridBagConstraints gbc) {
        String[] correctPool = {
                "Correct! Keep the streak going!",
                "Nice one! You’re on a roll!",
                "Spot on! Let’s keep the momentum!",
                "You nailed it! Next question!",
        };
        String[] wrongPool = {
                "Not quite. You have 3 lives left—try again!",
                "Almost! Still 3 lives remaining.",
                "Incorrect, but you’re still in the game with 3 lives!",
                "Close miss. You’ve got 3 lives to turn it around."
        };

        String responseStr = "";
        if(DesignQuiz.isCorrect) {
             responseStr = correctPool[rand.nextInt(correctPool.length)];
        } else {
            responseStr = wrongPool[rand.nextInt(wrongPool.length)];
        }

        // Fix text options
        JLabel response = new JLabel(responseStr);
        // Correct
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(response, gbc);

        gbc.gridy = 1;
        JButton nextButton = new JButton("This is the next button");
        centerPanel.add(nextButton, gbc);

        // Incorrect
        DesignQuiz.isCorrect = false;
    }
}
