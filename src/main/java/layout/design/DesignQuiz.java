package layout.design;

import com.moandjiezana.toml.Toml;
import layout.constants.UDColors;
import misc.Question;
import misc.ReadCSV;

// Use swing timer instead of util timer
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.awt.event.ActionListener;
import java.net.URL;

public class DesignQuiz {
    static List<Map<String, Object>> questions;
    static Random rand = new Random();
    static Timer timer;
    static URL url = DesignQuiz.class.getClassLoader().getResource("questions/introduction.csv");    
    static ReadCSV readCSV = new ReadCSV(url);

    static JPanel questionPanel;
    public static void showQuiz(JPanel centerPanel, GridBagConstraints gbc, Toml qDotTOML) {
        questionPanel = new JPanel();
        questionPanel.setOpaque(false);
        
        questions = new ArrayList<>(qDotTOML.getList("questions"));
        List<Question> questionsCSV = readCSV.loadQuestions();
        Question question1 = questionsCSV.get(0);
        System.out.println(question1);
        ActionListener taskPerformer = evt -> displayQuestion(); // Defining the action that will be performed by the timer

        int delay = 5000;
        timer = new Timer(delay, taskPerformer); timer.setRepeats(true);

        displayQuestion(); // Displays the first question
        centerPanel.add(questionPanel, gbc);
        timer.start();
    }

    public static void displayQuestion() {
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

        // Check for String instance
        String questionString = "";
        if (questionObj instanceof String) {
            questionString = (String) questionObj;
        }

        // Label dedicated for question display
        JLabel questionLabel = new JLabel(questionString);
        questionLabel.setFont(Design.loadCustomFont(Design.regularSize));
        questionLabel.setForeground(UDColors.udWhite);
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER); // JLabel.CENTER

        // Update the panel after extracting question
        clearPanel();
        questionPanel.add(questionLabel);
        questions.remove(randQ); // Remove Map from the List
    }

    public static void clearPanel() {
        questionPanel.removeAll();
        questionPanel.revalidate();
        questionPanel.repaint();
    }
}