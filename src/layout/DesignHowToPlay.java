package layout;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class DesignHowToPlay {

    public static void displayIns(JPanel mainPanel, JPanel centerPanel, GridBagConstraints mainGbc) {
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.setOpaque(false);

        Color cardBg = Color.decode("#2d2d30");
        Color headerColor = new Color(200, 255, 230);
        Color textColor = new Color(255, 255, 255);

        JPanel cardsPanel = new JPanel(new GridLayout(1, 3, 25, 0));
        cardsPanel.setOpaque(false);
        cardsPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));

        cardsPanel.add(createInfoCard("7 Topics",
                "The game covers 7 main topics from the Surveys and Programming Paradigms course.",
                cardBg, textColor, headerColor));

        cardsPanel.add(createInfoCard("140 Questions",
                "Complete all 7 rounds with a total of 140 questions to win the game.",
                cardBg, textColor, headerColor));

        cardsPanel.add(createInfoCard("Win Points",
                "Answer correctly to earn points and prove you're the ultimate developer.",
                cardBg, textColor, headerColor));

        JPanel rulesPanel = createRulesCard(cardBg, textColor, headerColor);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 15, 0);
        centerPanel.add(cardsPanel, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 30, 0);
        centerPanel.add(rulesPanel, gbc);

        mainPanel.setBackground(cardBg);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
    }

    private static JPanel createInfoCard(String title, String description, Color bgColor, Color textColor, Color accentColor) {
        RoundedPanel card = new RoundedPanel(20);
        card.setBackground(bgColor);
        card.setLayout(new GridBagLayout());
        card.setPreferredSize(new Dimension(350, 180));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 15, 5, 15);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(Design.loadCustomFont(20));
        titleLabel.setForeground(accentColor);
        card.add(titleLabel, gbc);

        gbc.gridy = 1;
        JTextArea desc = new JTextArea(description);
        desc.setLineWrap(true);
        desc.setWrapStyleWord(true);
        desc.setEditable(false);
        desc.setOpaque(false);
        desc.setForeground(textColor);
        desc.setFont(new Font("Consolas", Font.PLAIN, 14));
        desc.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        card.add(desc, gbc);

        return card;
    }

    private static JPanel createRulesCard(Color bgColor, Color textColor, Color titleColor) {
        RoundedPanel panel = new RoundedPanel(20);
        panel.setLayout(new BorderLayout());
        panel.setBackground(bgColor);
        panel.setPreferredSize(new Dimension(1150, 180));

        JLabel title = new JLabel("Game Rules");
        title.setFont(Design.loadCustomFont(20));
        title.setForeground(titleColor);
        title.setBorder(BorderFactory.createEmptyBorder(15, 20, 5, 0));

        JTextArea rulesText = new JTextArea(
                "- Answer each question within the time limit\n" +
                "- Earn points for correct answers\n" +
                "- Progress through all 7 rounds to complete the game\n"
        );
        rulesText.setFont(new Font("Consolas", Font.PLAIN, 14));
        rulesText.setForeground(textColor);
        rulesText.setOpaque(false);
        rulesText.setEditable(false);
        rulesText.setBorder(BorderFactory.createEmptyBorder(5, 25, 15, 25));

        panel.add(title, BorderLayout.NORTH);
        panel.add(rulesText, BorderLayout.CENTER);

        return panel;
    }

    static class RoundedPanel extends JPanel {
        private final int cornerRadius;

        public RoundedPanel(int radius) {
            super();
            this.cornerRadius = radius;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Shape round = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
            g2.setColor(getBackground());
            g2.fill(round);
            g2.dispose();
            super.paintComponent(g);
        }
    }
}
