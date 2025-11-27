package layout.design;

import layout.constants.RoundedPanel;
import layout.constants.UDColors;

import javax.swing.*;
import java.awt.*;

public class DesignHowToPlay {
    public static void displayIns(JPanel centerPanel, GridBagConstraints gbc) {
        gbc.gridx = 0;

        Color cardBg = UDColors.udGrayDark;
        Color headerColor = UDColors.udMint;
        Color textColor = UDColors.udWhite;

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);
        infoPanel.setPreferredSize(new Dimension((int)(Design.screenWidth * 0.9), (int)(Design.screenHeight * 0.5)));

        RoundedPanel card = new RoundedPanel();
        card.setBackground(cardBg);
        card.setLayout(new GridBagLayout());
        card.setAlignmentX(Component.CENTER_ALIGNMENT);

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 20, 10, 20);
        c.weightx = 1.0;

        int y = 0;

        JTextArea descriptionText = createTextArea(
                "Ultimate Dev is a quiz-style gameshow designed to determine who truly deserves the title of Ultimate Developer.",
                textColor
        );
        c.gridy = y++;
        card.add(descriptionText, c);

        JLabel topicsLabel = new JLabel("7 Topics");
        topicsLabel.setFont(Design.loadCustomFont(Design.subTitleSize - 2));
        topicsLabel.setForeground(headerColor);
        c.gridy = y++;
        card.add(topicsLabel, c);

        JTextArea topicsText = createTextArea(
                "The game covers 7 main topics on Surveys of Programming Paradigms.",
                textColor
        );
        c.gridy = y++;
        card.add(topicsText, c);

        JLabel questionsLabel = new JLabel("145 Questions");
        questionsLabel.setFont(Design.loadCustomFont(Design.subTitleSize - 2));
        questionsLabel.setForeground(headerColor);
        c.gridy = y++;
        card.add(questionsLabel, c);

        JTextArea questionsText = createTextArea(
                "Complete all 7 rounds with a total of 145 questions to win the game.",
                textColor
        );
        c.gridy = y++;
        card.add(questionsText, c);

        JLabel pointsLabel = new JLabel("Win Points");
        pointsLabel.setFont(Design.loadCustomFont(Design.subTitleSize - 2));
        pointsLabel.setForeground(headerColor);
        c.gridy = y++;
        card.add(pointsLabel, c);

        JTextArea pointsText = createTextArea(
                "Answer correctly to earn points and prove you're the ultimate developer.",
                textColor
        );
        c.gridy = y++;
        card.add(pointsText, c);
        infoPanel.add(card);
        centerPanel.add(infoPanel, gbc);
    }

    private static JTextArea createTextArea(String text, Color color) {
        JTextArea area = new JTextArea(text);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setFocusable(false);
        area.setEditable(false);
        area.setOpaque(false);
        area.setForeground(color);
        area.setFont(new Font("Consolas", Font.PLAIN, 14));
        return area;
    }
}
