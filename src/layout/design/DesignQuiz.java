package layout.design;

import com.moandjiezana.toml.Toml;

import events.CoDevEVHandler;
import layout.constants.ChoicesButton;
import layout.constants.UDColors;
import ud_interfaces.Play;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.*;
import java.util.List;

import static ud_interfaces.Play.centerPanel;

public class DesignQuiz {
    // Utilities
    static Random rand = new Random();
    public static Timer timer;
    static int currentSeconds;

    // Panels - timer, question, and choice
    static JPanel timerPanel;
    static JLabel timerLabel;
    static JPanel questionPanel;
    static JPanel choicesPanel;

    // TOML file parsing
    public static boolean parsedTOML = false; // Might be used later on
    public static List<Map<String, Object>> questions;
    static List<String> combinedChoices = new ArrayList<>();
    static String answer;

    static boolean isCorrect = false; // isCorrect -> manipulates DesignResult.showResult screen
    public static Play playScreen; // reference to Play
    public static void showQuiz(Play play, JPanel centerPanel, Toml qDotTOML) {
        playScreen = play;
        playScreen.displayTop(playScreen, "question.html");
        playScreen.displayBottom(playScreen, "Points: " + Play.currPoints, "Copy [" + Play.copy +"]");

        centerPanel.setLayout(new BorderLayout());
        JPanel itemPanel = new JPanel(new GridBagLayout()); itemPanel.setOpaque(false);
        GridBagConstraints itemGbc = new GridBagConstraints();

        timerPanel = new JPanel(); timerPanel.setOpaque(false);
        timerPanel.setBorder(new EmptyBorder(20,0,0,0));

        timerLabel = new JLabel();
        timerLabel.setFont(Design.loadCustomFont(Design.subTitleSize + 10));
        timerLabel.setForeground(UDColors.udCorrect);
        timerPanel.add(timerLabel);

        // Question panel setup
        questionPanel = new JPanel(); questionPanel.setOpaque(false);

        // Choices panel setup
        choicesPanel = new JPanel(); choicesPanel.setOpaque(false);
        choicesPanel.setLayout(new GridLayout(2, 2, 20, 20));

        // Parse questions table to ArrayList
        if(!parsedTOML) { questions = new ArrayList<>(qDotTOML.getList("questions")); parsedTOML = true; }

        if (questions.isEmpty()) { // Failed TOML parse?
            System.out.println("No questions available.");
            endQuizAndReturn();
            return; // STOP executing this method
        }

        itemGbc.insets = new Insets(10, 10, 10, 10);
        itemGbc.gridx = 0; itemGbc.gridy = 0;
        itemPanel.add(questionPanel, itemGbc);
        itemGbc.gridy = 1;
        itemPanel.add(choicesPanel, itemGbc);

        centerPanel.add(timerPanel, BorderLayout.NORTH); centerPanel.add(itemPanel, BorderLayout.CENTER);
        displayQuestion();
        timer.start();

        centerPanel.addKeyListener(new CoDevEVHandler());
        centerPanel.setFocusable(true);
        centerPanel.requestFocusInWindow();
    }

    // Separate method to update just the text of the existing label
    private static void updateTimerLabel() {
        String secondsFormatted = (currentSeconds < 10 ? "0" : "") + currentSeconds + " }";
        timerLabel.setText("{ 00:" + secondsFormatted);
    }

    public static void displayQuestion() {
        if(questions.isEmpty()) { // Questions empty, timer finished
            if(timer != null) { timer.stop(); }
            endQuizAndReturn(); clearPanel(); return;
        }

        // The revised timer logic
        currentSeconds = 10; updateTimerLabel();
        timer = new Timer(1000, e -> { // 1000ms tick
            currentSeconds--; updateTimerLabel();
            if(currentSeconds <= 3) { timerLabel.setForeground(UDColors.udIncorrect); }
            if (currentSeconds < 0) {
                ((Timer)e.getSource()).stop();
                isCorrect = false; // Timeout: Mark incorrect
                showCorrespondingResult();
                timerLabel.setForeground(UDColors.udCorrect);
            }
        });
        timer.setRepeats(true); timer.start();

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

        // Load the font object-oriented.toml using existing method
        int questionFSize = Design.subTitleSize - 3; // int choicesFSize = Design.regularSize;
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
            choiceBtn.setPreferredSize(new Dimension(400, choiceBtn.getPreferredSize().height));

            // Lambda for button behavior
            choiceBtn.addActionListener(e -> {
                if (Play.coDevActive) return;
                isCorrect = Objects.equals(answer, choiceBtn.getUnformattedText());
                timer.stop(); // Stop timer
                showCorrespondingResult();
            });
            choicesPanel.add(choiceBtn);
            choiceBtn.setFocusable(false);
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

    public static void useCoDev() {
        if(Play.copy > 0){
            Play.coDevActive = true;
            if (timer != null) timer.stop();
            if(playScreen != null) { playScreen.refreshCenter(); }
            // DesignCoDev.showCoDev(playScreen, centerPanel);
        }
    }


    // Helper method for screen switching
    public static void endQuizAndReturn() {
        if (timer != null) { timer.stop(); }
        Play.categorySelect = true;
        parsedTOML = false;
        if (questions != null) { questions.clear(); } if (playScreen != null) { playScreen.refreshCenter(); }
    }
}