package layout.design;

import layout.constants.CustomButton;
import layout.constants.UDColors;
import ud_interfaces.Play;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class DesignResult {
    static Random rand = new Random();
    // Reference to play here
    public static void showResult(JPanel centerPanel, GridBagConstraints gbc) {
        String[] correctPool = {
                "Correct! Keep the streak going!",
                "Nice one! You’re on a roll!",
                "Spot on! Let’s keep the momentum!",
                "You nailed it! Next question!",
        };
        String noun = (Play.attemptsLeft == 1) ? "attempt" : "attempts";
        String[] wrongPool = {
                "Not quite. You have " + Play.attemptsLeft + " " + noun + " left, try again!",
                "Almost! Still " + Play.attemptsLeft + " " + noun +  " remaining.",
                "Incorrect, but you’re still in the game with " + Play.attemptsLeft + " " + noun + "!",
                "Close miss. You’ve got " + Play.attemptsLeft + " " + noun + " to turn it around."
        };

        String responseStr = "";
        Color txtColor;
        if(DesignQuiz.isCorrect) {
             responseStr = correctPool[rand.nextInt(correctPool.length)];
             txtColor = UDColors.udCorrect;
        } else {
            responseStr = wrongPool[rand.nextInt(wrongPool.length)];
            txtColor = UDColors.udIncorrect;
            Play.attemptsLeft--;
        }

        // Fix text options
        JLabel response = new JLabel(responseStr);
        response.setFont(Design.loadCustomFont(Design.subTitleSize + 10));
        response.setForeground(txtColor);

        // Correct
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.insets = new Insets(20, 10, 20, 10);
        centerPanel.add(response, gbc);

        gbc.gridy = 1;
        int fontSize = Design.regularSize + 5;
        CustomButton nextBtn = new CustomButton("Next Question!", 10, 10);
        nextBtn.setPreferredSize(new Dimension(500, fontSize  * 3));
        nextBtn.setFont(Design.loadCustomFont(fontSize));
        nextBtn.addActionListener(e -> {
            Play.showResult = false;
            DesignQuiz.playScreen.refreshCenter();
        });
        centerPanel.add(nextBtn, gbc);

        // Incorrect
        DesignQuiz.isCorrect = false;
    }
}
