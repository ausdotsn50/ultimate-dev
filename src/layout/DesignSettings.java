package layout;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class DesignSettings {

    public static void centerDesign(JPanel mainPanel, JPanel centerPanel, GridBagConstraints mainGbc) {
        
        centerPanel.setLayout(new GridBagLayout());

        mainGbc.gridx = 0;
        mainGbc.anchor = GridBagConstraints.CENTER;
        mainGbc.fill = GridBagConstraints.NONE;

        JPanel musicPanel = createSettingsCard("Background Music", "Enable or disable background music", "#2d2d30");
        GridBagConstraints gbcMusic = new GridBagConstraints();
        gbcMusic.gridx = 0;
        gbcMusic.gridy = 0;
        gbcMusic.insets = new Insets(0, 0, 25, 0); 
        centerPanel.add(musicPanel, gbcMusic);

        JPanel sfxPanel = createSettingsCard("Sound Effects", "Enable or disable sound effects", "#2d2d30");
        GridBagConstraints gbcSfx = new GridBagConstraints();
        gbcSfx.gridx = 0;
        gbcSfx.gridy = 1;
        gbcSfx.insets = new Insets(0, 0, 40, 0); 
        centerPanel.add(sfxPanel, gbcSfx);

        mainPanel.add(centerPanel, BorderLayout.CENTER);
    }

    private static JPanel createSettingsCard(String title, String subtitle, String hexColor) {
        Color bgColor = Color.decode(hexColor);

        RoundedPanel panel = new RoundedPanel(20);
        panel.setPreferredSize(new Dimension(800, 100));
        panel.setBackground(bgColor);
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 25, 10, 25);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST; 
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel textPanel = new JPanel(new GridLayout(2, 1, 0, 5));
        textPanel.setOpaque(false);
        textPanel.setAlignmentX(Component.LEFT_ALIGNMENT); 

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(Design.loadCustomFont(20));
        titleLabel.setForeground(new Color(200, 255, 230));

        JLabel subtitleLabel = new JLabel("// " + subtitle);
        subtitleLabel.setFont(new Font("Consolas", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(120, 120, 130));

        textPanel.add(titleLabel);
        textPanel.add(subtitleLabel);
        panel.add(textPanel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        JToggleButton toggle = createSwitch();
        panel.add(toggle, gbc);

        return panel;
    }

    private static JToggleButton createSwitch() {
        JToggleButton toggle = new JToggleButton();
        toggle.setPreferredSize(new Dimension(45, 25));
        toggle.setFocusPainted(false);
        toggle.setBorderPainted(false);
        toggle.setContentAreaFilled(false);

        toggle.setUI(new javax.swing.plaf.basic.BasicToggleButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                JToggleButton b = (JToggleButton) c;

                int w = b.getWidth();
                int h = b.getHeight();

                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(b.isSelected() ? new Color(0, 200, 130) : new Color(20, 20, 30));
                g2.fillRoundRect(0, 0, w, h, h, h);

                int knobSize = h - 6;
                int knobX = b.isSelected() ? w - knobSize - 3 : 3;
                g2.setColor(Color.WHITE);
                g2.fillOval(knobX, 3, knobSize, knobSize);

                g2.dispose();
            }
        });
        return toggle;
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
            g2.setColor(new Color(60, 60, 65)); // border color
            g2.draw(round);
            g2.dispose();
            super.paintComponent(g);
        }
    }
}
