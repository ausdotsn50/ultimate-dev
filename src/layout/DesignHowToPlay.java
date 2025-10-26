package layout;

import layout.constants.RoundedPanel;
import layout.constants.UDColors;

import javax.swing.*;
import java.awt.*;

public class DesignHowToPlay {
    public static void displayIns(JPanel mainPanel, JPanel centerPanel, GridBagConstraints gbc) {
        gbc.gridx = 0;

        Color cardBg = UDColors.udGrayDark;
        Color headerColor = UDColors.udMint;
        Color textColor = UDColors.udWhite;

        int gap = 25;
        JPanel cardsPanel = new JPanel(new GridLayout(1, 3, gap, gap));
        cardsPanel.setOpaque(false);
        cardsPanel.setPreferredSize(new Dimension(Design.screenWidth, (int)(Design.screenHeight * 0.40)));
        // cardsPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));

        // Adding elements
        cardsPanel.add(createInfoCard("7 Topics",
                "The game covers 7 main topics from the Surveys and Programming Paradigms course.",
                cardBg, textColor, headerColor));

        cardsPanel.add(createInfoCard("140 Questions",
                "Complete all 7 rounds with a total of 140 questions to win the game.",
                cardBg, textColor, headerColor));

        cardsPanel.add(createInfoCard("Win Points",
                "Answer correctly to earn points and prove you're the ultimate developer.",
                cardBg, textColor, headerColor));

        JPanel rulesPanel = new JPanel();
        rulesPanel.setOpaque(false);
        rulesPanel.setPreferredSize(new Dimension(Design.screenWidth, (int)(Design.screenHeight * 0.40)));

        rulesPanel.add(createRulesCard(cardBg, textColor, headerColor));

        gbc.gridy = 0;
        gbc.insets = new Insets(gap, gap, gap, gap);
        centerPanel.add(cardsPanel, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(gap, gap, gap, gap);
        centerPanel.add(rulesPanel, gbc);
    }

    private static JPanel createInfoCard(String title, String description, Color bgColor, Color textColor, Color accentColor) {
        RoundedPanel card = new RoundedPanel();
        card.setBackground(bgColor);
        card.setLayout(new GridBagLayout());

        int tbInsets = 5, lrInsets = 15;
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(tbInsets, lrInsets, tbInsets, lrInsets);
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Title
        gbc.gridy = 0;
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(Design.loadCustomFont(Design.subTitleSize));
        titleLabel.setForeground(accentColor);
        card.add(titleLabel, gbc);

        // Description
        gbc.gridy = 1;
        JTextArea desc = new JTextArea(description);
        desc.setLineWrap(true);
        desc.setWrapStyleWord(true);
        desc.setFocusable(false);
        desc.setEditable(false);
        desc.setOpaque(false);
        desc.setForeground(textColor);
        desc.setFont(new Font("Consolas", Font.PLAIN, 14));

        card.add(desc, gbc);

        // Explicit and intentional method rather than relying on swing magic of getPreferredSize();
        card.doLayout();

        return card;
    }


    private static JPanel createRulesCard(Color bgColor, Color textColor, Color titleColor) {
        RoundedPanel card = new RoundedPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(bgColor);

        JLabel title = new JLabel("Game Rules");
        title.setFont(Design.loadCustomFont(Design.subTitleSize));
        title.setForeground(titleColor);
        title.setBorder(BorderFactory.createEmptyBorder(15, 20, 5, 0)); // To recheck

        JTextArea rulesText = getJTextArea(textColor);

        card.add(title, BorderLayout.NORTH);
        card.add(rulesText, BorderLayout.CENTER);

        card.doLayout();

        return card;
    }

    private static JTextArea getJTextArea(Color textColor) {
        JTextArea rulesText = new JTextArea(
                "- Answer each question within the time limit\n" +
                "- Earn points for correct answers\n" +
                "- Progress through all 7 rounds to complete the game\n"
        );
        rulesText.setFont(new Font("Consolas", Font.PLAIN, 14));
        rulesText.setForeground(textColor);
        rulesText.setOpaque(false);
        rulesText.setFocusable(false);
        rulesText.setEditable(false);
        rulesText.setBorder(BorderFactory.createEmptyBorder(5, 25, 15, 25));
        return rulesText;
    }
}
