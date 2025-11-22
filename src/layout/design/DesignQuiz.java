package layout.design;

import com.moandjiezana.toml.Toml;
import layout.constants.CustomButton;
import layout.constants.UDColors;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.awt.event.ActionListener;

public class DesignQuiz {
    static Random rand = new Random();
    static Timer timer;

    static JPanel questionPanel;
    static JPanel choicesPanel;

    static List<Map<String, Object>> questions;
    static List<String> combinedChoices = new ArrayList<>();

    public static void showQuiz(JPanel centerPanel, GridBagConstraints gbc, Toml qDotTOML) {
        // Question panel setup
        questionPanel = new JPanel();
        questionPanel.setOpaque(false);

        // Choices panel setup
        choicesPanel = new JPanel();
        choicesPanel.setOpaque(false);

        // 2 row, 2 col button display layout
        choicesPanel.setLayout(new GridLayout(2, 2, 10, 10));

        // Parse questions table to ArrayList
        questions = new ArrayList<>(qDotTOML.getList("questions"));

        ActionListener taskPerformer = evt -> displayQuestion();
        int delay = 5000;
        timer = new Timer(delay, taskPerformer);
        timer.setRepeats(true);

        // GridBag config
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(questionPanel, gbc);

        gbc.gridy = 1;
        centerPanel.add(choicesPanel, gbc);

        displayQuestion();
        timer.start();
    }

    public static void displayQuestion() {
        if(questions.isEmpty()) {
            if(timer != null) { timer.stop(); }
            System.out.println("This round has ended.");
            clearPanel();
            return;
        }

        int randIndex =  rand.nextInt(questions.size());
        Map<String, Object> randQ = questions.get(randIndex);
        Object questionObj = randQ.get("question");

        // Populate choices
        combinedChoices.clear();

        Object alternativesObj = randQ.get("alternatives");
        if (alternativesObj instanceof List<?> alts) {
            for (Object alt : alts) {
                combinedChoices.add(alt.toString());
            }
        }
        combinedChoices.add(randQ.get("answer").toString());
        java.util.Collections.shuffle(combinedChoices);

        String questionString = (questionObj instanceof String) ? (String) questionObj : "";

        JLabel questionLabel = new JLabel(questionString);
        questionLabel.setFont(Design.loadCustomFont(Design.regularSize));
        questionLabel.setForeground(UDColors.udWhite);
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // UI update
        clearPanel();
        questionPanel.add(questionLabel);
        displayChoices();
        refreshPanels();
        questions.remove(randQ);
    }

    public static void displayChoices() {
        for(String choiceText : combinedChoices) {
            CustomButton choiceBtn = new CustomButton(choiceText, 10, 10);
            choicesPanel.add(choiceBtn);
        }
    }

    // Clears both panels
    public static void clearPanel() {
        questionPanel.removeAll();
        choicesPanel.removeAll();
    }

    // Helper to revalidate and repaint both panels
    public static void refreshPanels() {
        questionPanel.revalidate();
        questionPanel.repaint();
        choicesPanel.revalidate();
        choicesPanel.repaint();
    }
}