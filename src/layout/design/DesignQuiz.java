package layout.design;

import com.moandjiezana.toml.Toml;
import layout.constants.ChoicesButton;
import layout.constants.CustomButton;

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
        // questionPanel.setPreferredSize(new Dimension(Design.screenWidth, (int) (Design.screenHeight * 0.80 * 0.60)));
        questionPanel.setBackground(Color.red);
        questionPanel.setOpaque(true);

        // Choices panel setup
        choicesPanel = new JPanel();
        choicesPanel.setOpaque(false);

        // 2 row, 2 col button display layout
        choicesPanel.setLayout(new GridLayout(2, 2, 10, 10));

        // Parse questions table to ArrayList
        questions = new ArrayList<>(qDotTOML.getList("questions"));

        ActionListener taskPerformer = evt -> displayQuestion();
        int delay = 2000;
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

        // Load the font object using existing method
        int questionFSize = Design.subTitleSize; // int choicesFSize = Design.regularSize;
        Font myFont = Design.loadCustomFont(questionFSize);

        // 2. REGISTER the font with the OS/JVM so HTML can "see" it
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(myFont);

        // 3. Get the actual internal name (likely "Fira Code")
        String fontName = myFont.getFamily();

        // 4. Create the JTextPane
        JTextPane questionPane = new JTextPane();
        questionPane.setContentType("text/html");
        questionPane.setEditable(false);
        questionPane.setOpaque(false);
        questionPane.setFocusable(false);

        // 5. Inject the variable 'fontName' into the HTML CSS
        // Note the single quotes around the font name in CSS: font-family: 'Fira Code';
        // Assuming questionFSize is an int (e.g., 14)
        // System.out.print(fontName);
        String formattedText = "<html><body style='font-family: \"" + fontName + "\"; color: white; font-size: " + questionFSize + "px;'>"
                + questionString
                + "</body></html>";
        questionPane.setText(formattedText);

        /*
        JLabel questionLabel = new JLabel(questionString);
        questionLabel.setFont(Design.loadCustomFont(Design.regularSize));
        questionLabel.setForeground(UDColors.udWhite);
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
         */

        // UI update
        clearPanel();
        questionPanel.add(questionPane);
        displayChoices();
        refreshPanels();
        questions.remove(randQ);
    }

    public static void displayChoices() {
        for(String choiceText : combinedChoices) {
            ChoicesButton choiceBtn = new ChoicesButton(choiceText, 10, 10);
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