package layout;

import javax.swing.*;
import java.awt.*;

public class DesignSplash {
    public static void titleDesign(JPanel mainPanel, String titleStr, String subStr) {
        JPanel centerPanel = new JPanel(new BorderLayout()); // This is for the label title

        centerPanel.setPreferredSize(new Dimension(Design.screenWidth, (int)(Design.screenHeight * 0.50) ));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
        centerPanel.setOpaque(false);

        // Title label
        JPanel titlePanel = new JPanel(new GridBagLayout());
        titlePanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel title = new JLabel(titleStr,  JLabel.CENTER);
        title.setFont(Design.loadCustomFont(100));
        title.setForeground(UDColors.udCyan);

        JLabel subtitle = new JLabel(subStr, JLabel.CENTER);
        subtitle.setFont(Design.loadCustomFont(20));
        subtitle.setForeground(UDColors.udGray);

        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        titlePanel.add(title, gbc);

        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.gridx = 0;
        gbc.gridy = 1;
        titlePanel.add(subtitle, gbc);

        // startFadeEffect(subtitle);

        centerPanel.add(titlePanel, BorderLayout.CENTER);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
    }
}
