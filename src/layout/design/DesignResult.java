package layout.design;

import layout.constants.CustomButton;
import layout.constants.UDColors;
import ud_interfaces.Play;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class DesignResult {
    static Random rand = new Random();
    public static Play playScreen;
    public static void showResult(Play play, JPanel centerPanel, GridBagConstraints gbc) {
        String[] correctPool = { "Correct! Keep the streak going!", "Nice one! You’re on a roll!",
                "Spot on! Let’s keep the momentum!", "You nailed it! Next question!",
        };
        String noun = (Play.attemptsLeft == 1) ? "attempt" : "attempts";
        String[] wrongPool = {
                "Not quite. You have " + Play.attemptsLeft + " " + noun + " left, try again!", "Almost! Still " + Play.attemptsLeft + " " + noun +  " remaining.",
                "Incorrect, but you’re still in the game with " + Play.attemptsLeft + " " + noun + "!", "Close miss. You’ve got " + Play.attemptsLeft + " " + noun + " to turn it around."
        };
        playScreen = play;
        playScreen.displayTop(playScreen, "result.html");
        playScreen.displayBottom(playScreen, "Points: " + Play.currPoints,
                "Remaining Round Qs: " + DesignQuiz.questions.size());
        boolean isFinished = DesignQuiz.questions.isEmpty();

        String responseStr = ""; Color txtColor = null; String buttonText = "";
        if(DesignQuiz.isCorrect) { // correct
            responseStr = correctPool[rand.nextInt(correctPool.length)];
            buttonText = "Next Question!";
            txtColor = UDColors.udCorrect;
        } else if(Play.attemptsLeft == 0) {
            gameOver(centerPanel, gbc);
            return;
        } else { // incorrect
            Play.attemptsLeft--;
            responseStr = wrongPool[rand.nextInt(wrongPool.length)];
            txtColor = UDColors.udIncorrect;
            System.out.println("incorrect. here are the attempts left: " + Play.attemptsLeft);
            buttonText = "Continue";
        }

        if(isFinished) {
            responseStr = "That's all for this round. Round COMPLETE!";
            buttonText = "Click to select new round"; txtColor = UDColors.udCyan;
        }

        JLabel response = new JLabel(responseStr);
        response.setFont(Design.loadCustomFont(Design.subTitleSize + 10));
        response.setForeground(txtColor);

        // Correct
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.insets = new Insets(20, 10, 20, 10);
        centerPanel.add(response, gbc);

        gbc.gridy = 1;
        int fontSize = Design.regularSize + 5;
        CustomButton nextBtn = new CustomButton(buttonText, 10, 10);
        nextBtn.setPreferredSize(new Dimension(400, fontSize  * 3));
        nextBtn.setFont(Design.loadCustomFont(fontSize));

        nextBtn.addActionListener(e -> {
            Play.showResult = false;
            DesignQuiz.playScreen.refreshCenter();
        });

        centerPanel.add(nextBtn, gbc);
        DesignQuiz.isCorrect = false;
    }

    public static void gameOver(JPanel centerPanel, GridBagConstraints gbc) {
        centerPanel.setLayout(new GridBagLayout());
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.weightx = 1; gbc.anchor = GridBagConstraints.WEST;

        int leftIndent = 200, botIndent = 20;
        gbc.insets = new Insets(0, leftIndent, botIndent, 0);
        JLabel gameOverMsg = new JLabel("Try Again? {");
        gameOverMsg.setForeground(UDColors.udIncorrect);
        gameOverMsg.setFont(Design.loadCustomFont(Design.titleSize + botIndent));
        centerPanel.add(gameOverMsg, gbc);

        gbc.gridy = 2;
        JLabel endBracket = new JLabel("}");
        endBracket.setFont(Design.loadCustomFont(Design.titleSize + botIndent));
        endBracket.setForeground(UDColors.udIncorrect);
        centerPanel.add(endBracket, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(0, leftIndent * 2, 0, 0);
        JLabel pointsLabel = new JLabel("< Points: " + Play.currPoints + " >");
        pointsLabel.setFont(Design.loadCustomFont(Design.subTitleSize + (botIndent / 2)));
        pointsLabel.setForeground(UDColors.udWhite);
        centerPanel.add(pointsLabel, gbc);

        gbc.gridy = 3;
        gbc.insets = new Insets(0,  0, 0, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        int fontSize = Design.regularSize + 5;
        CustomButton retryBtn = new CustomButton("Retry!", 10, 10);
        retryBtn.setPreferredSize(new Dimension(400, fontSize  * 3));
        retryBtn.setFont(Design.loadCustomFont(fontSize));
        retryBtn.addActionListener(e -> {
            // Add your configurations here
            playScreen.init(); // Other screen configurations from DesignQuiz
        });
        centerPanel.add(retryBtn, gbc);

        playScreen.displayBottom(playScreen, "The Ultimate Dev Gameshow", "1");
    }
}
