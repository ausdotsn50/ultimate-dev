package layout;

import layout.constants.RoundedPanel;
import layout.constants.UDColors;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class DesignSettings {
    public static void centerDesign(JPanel mainPanel, JPanel centerPanel, GridBagConstraints mainGbc) {
        centerPanel.setLayout(new GridBagLayout());

        mainGbc.gridx = 0;

        // Hex color string replaced with constant color
        int botPad = 25;
        JPanel musicPanel = createSettingsCard("Background Music", "Enable or disable background music", UDColors.udGrayDark);
        mainGbc.gridy = 0;
        mainGbc.insets = new Insets(0, 0, botPad, 0);
        centerPanel.add(musicPanel, mainGbc);

        JPanel sfxPanel = createSettingsCard("Sound Effects", "Enable or disable sound effects", UDColors.udGrayDark);
        mainGbc.gridy = 1;
        mainGbc.insets = new Insets(0, 0, botPad, 0);
        centerPanel.add(sfxPanel, mainGbc);

        mainPanel.add(centerPanel, BorderLayout.CENTER);
    }

    // Creates settings card
    public static JPanel createSettingsCard(String title, String subtitle, Color color) {
        // Rounded panel object that is used in how to play, settings and play
        RoundedPanel panel = new RoundedPanel();
        panel.setPreferredSize(new Dimension(800, 100)); // set preferred size
        panel.setBackground(color);

        // Rounded panel is set to follow a GridBagLayout
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 25, 10, 25);
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel textPanel = new JPanel(new GridLayout(2, 1, 0, 5));
        textPanel.setOpaque(false);
        textPanel.setAlignmentX(Component.LEFT_ALIGNMENT); 

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(Design.loadCustomFont(20));
        titleLabel.setForeground(UDColors.udMint);

        JLabel subtitleLabel = new JLabel("// " + subtitle);
        subtitleLabel.setFont(new Font("Consolas", Font.PLAIN, 14));
        subtitleLabel.setForeground(UDColors.udGrayLight);

        textPanel.add(titleLabel);
        textPanel.add(subtitleLabel);
        panel.add(textPanel, gbc);

        // Toggle positioning
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        JToggleButton toggle = createSwitch();
        panel.add(toggle, gbc);

        // Returns JPanel object
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

                // Switch to color constants
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
}
