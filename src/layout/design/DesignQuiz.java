package layout.design;

import com.moandjiezana.toml.Toml;
import layout.constants.ChoicesButton;
import ud_interfaces.Play;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionListener;
import java.util.List;

public class DesignQuiz {
    // Utilities
    static Random rand = new Random();
    public static Timer timer;

    // Panels - question and choice
    static JPanel questionPanel;
    static JPanel choicesPanel;

    // TOML file parsing
    static boolean parsedTOML = false; // Might be used later on
    static List<Map<String, Object>> questions;
    static List<String> combinedChoices = new ArrayList<>();
    static String answer;

    // reference to the original PlayScreen
    public static Play playScreen; // reference to Play

    // Quiz navigation
    public static boolean isCorrect; // isCorrect -> manipulates DesignResult.showResult screen
    public static void showQuiz(Play play, JPanel centerPanel, GridBagConstraints gbc, Toml qDotTOML) {
        playScreen = play;

        // Question panel setup
        questionPanel = new JPanel();
        // questionPanel.setPreferredSize(new Dimension(Design.screenWidth, (int) (Design.screenHeight * 0.80 * 0.60)));
        questionPanel.setBackground(Color.red);
        questionPanel.setOpaque(false);

        // Choices panel setup
        choicesPanel = new JPanel();
        choicesPanel.setOpaque(false);

        // 2 row, 2 col button display layout
        choicesPanel.setLayout(new GridLayout(2, 2, 20, 20));

        // Parse questions table to ArrayList
        if(!parsedTOML) {
            questions = new ArrayList<>(qDotTOML.getList("questions"));
            parsedTOML = true;
        }

        if (questions.isEmpty()) {
            System.out.println("No questions available.");
            endQuizAndReturn();
            return; // STOP executing this method
        }

        // Timer logic
        ActionListener taskPerformer = evt -> displayQuestion();
        int delay = 5000;
        timer = new Timer(delay, taskPerformer);
        timer.setRepeats(true);

        // GridBag config
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        centerPanel.add(questionPanel, gbc);

        gbc.gridy = 1;
        centerPanel.add(choicesPanel, gbc);

        displayQuestion();
        timer.start();
    }

    public static void displayQuestion() {
        // questions empty w/ timer finished
        if(questions.isEmpty()) {
            if(timer != null) { timer.stop(); }
            System.out.println("This round has ended.");

            endQuizAndReturn(); clearPanel(); return;
        }

        // Extract random question
        int randIndex =  rand.nextInt(questions.size());
        Map<String, Object> randQ = questions.get(randIndex);
        Object questionObj = randQ.get("question");
        String questionString = (questionObj instanceof String) ? (String) questionObj : "";

        // Populate choices
        combinedChoices.clear(); // Clean before populating
        Object alternativesObj = randQ.get("alternatives");
        if (alternativesObj instanceof List<?> alts) {
            for (Object alt : alts) {
                combinedChoices.add(alt.toString());
            }
        }
        answer = randQ.get("answer").toString();
        combinedChoices.add(answer);
        java.util.Collections.shuffle(combinedChoices); // Shuffle combines choices

        // Load the font object using existing method
        int questionFSize = Design.subTitleSize; // int choicesFSize = Design.regularSize;
        Font myFont = Design.loadCustomFont(questionFSize);

        // JTextPane for the formatted question
        JTextPane questionPane = getJTextPane(myFont, questionFSize, questionString);

        // UI update: clear, display stuff, refresh, remove what needs be
        clearPanel();
        questions.remove(randQ);
        questionPanel.add(questionPane);
        displayChoices();
        refreshPanels();
    }

    private static JTextPane getJTextPane(Font myFont, int questionFSize, String questionString) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(myFont);
        String fontName = myFont.getFamily();

        JTextPane questionPane = new JTextPane();
        questionPane.setContentType("text/html");
        questionPane.setEditable(false);
        questionPane.setOpaque(false);
        questionPane.setFocusable(false);

        String formattedText = "<html><body style='font-family: \"" + fontName + "\"; color: white; font-size: " + questionFSize + "px;'>"
                + questionString
                + "</body></html>";
        questionPane.setText(formattedText);
        return questionPane;
    }

    public static void displayChoices() {
        for(String choiceText : combinedChoices) {
            ChoicesButton choiceBtn = new ChoicesButton(choiceText, 10, 10);
            choiceBtn.setPreferredSize(new Dimension(500, choiceBtn.getPreferredSize().height));

            // Lambda for button behavior
            choiceBtn.addActionListener(e -> {
                isCorrect = Objects.equals(answer, choiceBtn.getUnformattedText());
                timer.stop(); // Stop timer
                showCorrespondingResult();
            });
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

    private static void showCorrespondingResult() {
        Play.showResult = true;
        if(playScreen != null) { playScreen.refreshCenter(); }
    }

    // Helper method for screen switching
    private static void endQuizAndReturn() {
        if (timer != null) { timer.stop(); }
        Play.categorySelect = true;
        parsedTOML = false;
        if (questions != null) { questions.clear(); } if (playScreen != null) { playScreen.refreshCenter(); }
    }
}