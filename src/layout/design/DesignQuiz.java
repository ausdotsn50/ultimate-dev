package layout.design;

import com.moandjiezana.toml.Toml;
import layout.constants.CustomButton;
import layout.constants.UDColors;

import javax.swing.*;
import java.awt.*;
import java.util.*;

import javax.swing.Timer;   // Use swing timer instead of util timer

import java.awt.event.ActionListener;
import java.util.List;

public class DesignQuiz {
    static Random rand = new Random();
    static Timer timer;

    static JPanel questionPanel;
    static JPanel choicesPanel;

    static List<Map<String, Object>> questions;
    static List<String> combinedChoices = new ArrayList<>();
    public static void showQuiz(JPanel centerPanel, Toml qDotTOML) {
        questionPanel = new JPanel(new BorderLayout()); questionPanel.setOpaque(false);
        choicesPanel = new JPanel(); choicesPanel.setOpaque(false);

        questions = new ArrayList<>(qDotTOML.getList("questions"));
        ActionListener taskPerformer = evt -> displayQuestion(); // Defining the action that will be performed by the timer

        // Question panel update for every 'delay' ms
        int delay = 2000;
        timer = new Timer(delay, taskPerformer); timer.setRepeats(true);

        displayQuestion(); // Displays the first question

        centerPanel.add(questionPanel);
        timer.start();
    }

    public static void displayQuestion() {
        combinedChoices.clear();
        // Add panel clear here

        // When the questions ArrayList that was parsed from TOML is empty
        if(questions.isEmpty()) {
            if(timer != null) { timer.stop(); }
            System.out.println("This round has ended.");
            clearPanel();
            return;
        }

        // Generate a random number within the current size of 'questions'
        int randIndex =  rand.nextInt(questions.size());
        Map<String, Object> randQ = questions.get(randIndex); // Extracting a Map
        Object questionObj = randQ.get("question"); // Get question object from map

        // Choices extracted from the Map
        Object answerObj = randQ.get("answer");
        Object alternativesObj = randQ.get("alternatives");

        // To combine all the choices, create a new ArrayList<>
        // List<String> combinedChoices = new ArrayList<>();
        if (answerObj instanceof String answer) combinedChoices.add(answer);
        if (alternativesObj instanceof List<?> alternativesList) { // Cast to a generic List, not a specific ArrayList
            for (Object item : alternativesList) { // Loop through the list and add each item
                if (item instanceof String) combinedChoices.add((String) item); // Only add the item if it is an instance of Str
            }
        }
        System.out.println(combinedChoices);

        // Check for String instance
        String questionString = "";
        if (questionObj instanceof String)  questionString = (String) questionObj;

        questions.remove(randQ); // Removes item from questions table after use

        // Label dedicated for question display
        JLabel questionLabel = new JLabel(questionString);
        questionLabel.setFont(Design.loadCustomFont(Design.regularSize));
        questionLabel.setForeground(UDColors.udWhite);
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER); // JLabel.CENTER

        // Update the panel after extracting question
        clearPanel();
        questionPanel.add(questionLabel);

        // Add the label to the top
        questionPanel.add(questionLabel, BorderLayout.NORTH);

        // This will create buttons and add them to choicesPanel
        displayChoices();

        // Add the choices panel (now full of new buttons) to the center
        questionPanel.add(choicesPanel, BorderLayout.CENTER);

        // --- 5. RE-VALIDATE THE PANEL ---
        // (Moved from clearPanel() to here, so we only do it once)
        questionPanel.revalidate();
        questionPanel.repaint();
    }

    public static void displayChoices() {
        for(int i = 0; i < combinedChoices.size() - 2; i++) {
            CustomButton choices = new CustomButton(combinedChoices.get(i), 10, 10);
            choicesPanel.add(choices);
        }
    }

    public static void clearPanel() {
        questionPanel.removeAll();
    }
}