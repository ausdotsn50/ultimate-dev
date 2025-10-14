package layout;

import events.MouseEVHandler;

import javax.swing.*;
import java.awt.*;

public class DesignSplash {
    public static void titleDesign(JPanel mainPanel, String titleStr, String subStr, String instruc) {
        JPanel centerPanel = new JPanel(new BorderLayout()); // This is for the label title

        centerPanel.setPreferredSize(new Dimension(Design.screenWidth, (int)(Design.screenHeight * 0.70) ));
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

        // Button panel for customization purposes (instruction label)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);

        JLabel instruction = new JLabel(instruc, JLabel.CENTER);
        instruction.setPreferredSize(new Dimension((int)(Design.screenWidth * 0.40), 60));
        instruction.setFont(Design.loadCustomFont(20));
        instruction.setForeground(UDColors.udCyan);

        // add fade effect for instruction label
        Design.startFadeEffect(instruction);

        instruction.addMouseListener(Design.mouseEVHandler);
        buttonPanel.add(instruction);

        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        titlePanel.add(title, gbc);

        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.gridx = 0;
        gbc.gridy = 1;
        titlePanel.add(subtitle, gbc);

        gbc.insets = new Insets(75, 0, 0, 0);
        gbc.gridx = 0;
        gbc.gridy = 2;
        titlePanel.add(buttonPanel, gbc);

        centerPanel.add(titlePanel, BorderLayout.CENTER);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
    }
}
