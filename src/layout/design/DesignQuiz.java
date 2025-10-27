package layout.design;

import com.moandjiezana.toml.Toml;
import layout.constants.UDColors;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class DesignQuiz {
    // Indices for manipulation
    // String mapped to an Array object(e.g. alternatives)
    static List<Map<String, Object>> questions;
    static Random rand = new Random();

    // From category page, designing quiz, parsing the TOML that fits the selected category
    public static void showQuiz(JPanel centerPanel, GridBagConstraints gbc, Toml qDotTOML) {
        JPanel questionPanel = new JPanel();
        questionPanel.setOpaque(false);
        gbc.gridx = 0;

        questions = new ArrayList<>(qDotTOML.getList("questions")); // Extracts questions table from TOML file
        while(!questions.isEmpty()) {
            int randIndex =  rand.nextInt(questions.size());

            Map<String, Object> randQ = questions.get(randIndex);
            Object questionObj = randQ.get("question");

            String questionString = "";
            if (questionObj instanceof String) {
                 questionString = (String) questionObj;
                // System.out.println(questionString);
            }

            JLabel questionLabel = new JLabel(questionString);
            questionLabel.setFont(Design.loadCustomFont(Design.regularSize));
            questionLabel.setForeground(UDColors.udWhite);

            questionPanel.add(questionLabel);

            questions.remove(randQ);
        }
        centerPanel.add(questionPanel, gbc);
    }
}
