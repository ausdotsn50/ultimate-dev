package layout;

import javax.swing.*;
import java.awt.*;

public class DesignSplash {
    public static void titleDesign(JPanel mainPanel, String titleStr, String subStr, String instruc) {
        JPanel centerPanel = new JPanel(new BorderLayout()); // This is for the label title

        centerPanel.setPreferredSize(new Dimension(Design.screenWidth, (int)(Design.screenHeight * 0.80) ));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
        centerPanel.setOpaque(false);

        // Title label
        JPanel titlePanel = new JPanel(new GridBagLayout());
        titlePanel.setOpaque(false);

        // Initial constraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;

        displayTitleText(titlePanel, gbc, titleStr);
        displaySubText(titlePanel, gbc, subStr);
        displayInstrucText(titlePanel, gbc, instruc);

        centerPanel.add(titlePanel, BorderLayout.CENTER);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
    }

    private static void displayTitleText(JPanel titlePanel, GridBagConstraints gbc, String titleStr) {
        JLabel title = new JLabel(titleStr,  JLabel.CENTER);
        title.setFont(Design.loadCustomFont(100));
        title.setForeground(UDColors.udCyan);

        titlePanel.add(title, gbc);
    }

    private static void displaySubText(JPanel titlePanel, GridBagConstraints gbc, String subStr) {
        JLabel subtitle = new JLabel(subStr, JLabel.CENTER);
        subtitle.setFont(Design.loadCustomFont(20));
        subtitle.setForeground(UDColors.udGray);

        gbc.gridy = 1;
        titlePanel.add(subtitle, gbc);
    }

    private static void displayInstrucText(JPanel titlePanel, GridBagConstraints gbc, String instruc) {
        // Button panel for customization purposes (instruction label)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);

        JLabel instruction = new JLabel(instruc, JLabel.CENTER);
        instruction.setBackground(UDColors.udWhite);
        instruction.setPreferredSize(new Dimension((int)(Design.screenWidth * 0.40), 60));
        instruction.setFont(Design.loadCustomFont(20));
        instruction.setForeground(UDColors.udCyan);

        // add fade effect for instruction label
        Design.startFadeEffect(instruction);

        instruction.addMouseListener(Design.mouseEVHandler);
        buttonPanel.add(instruction);

        gbc.insets.top = 75;
        gbc.gridy = 2;
        titlePanel.add(buttonPanel, gbc);
    }

}
