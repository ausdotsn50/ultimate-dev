package layout;

import events.GrowReplaceBehavior;
import events.MouseEVHandler;
import layout.constants.UDColors;

import javax.swing.*;
import java.awt.*;

public class DesignLoading {
    static MouseEVHandler mouseEVHandler = new MouseEVHandler(null, new GrowReplaceBehavior(10));

    public static void titleDesign(JPanel centerPanel, String titleStr, String subStr, String instruc) {
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
    }

    private static void displayTitleText(JPanel titlePanel, GridBagConstraints gbc, String titleStr) {
        JLabel title = new JLabel(titleStr,  JLabel.CENTER);
        title.setFont(Design.loadCustomFont(Design.mainTitleSize));
        title.setForeground(UDColors.udCyan);

        titlePanel.add(title, gbc);
    }


    private static void displaySubText(JPanel titlePanel, GridBagConstraints gbc, String subStr) {
        JLabel subtitle = new JLabel(subStr, JLabel.CENTER);
        subtitle.setFont(Design.loadCustomFont(Design.subTitleSize));
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
        instruction.setFont(Design.loadCustomFont(Design.subTitleSize));
        instruction.setForeground(UDColors.udCyan);

        // Adding client property (hidden key-value pairing) to tailor to GrowReplaceBehavior
        instruction.putClientProperty("hoverText", "Nothing to click here");
        instruction.putClientProperty("exitText", "Press 'Enter' to Start!");

        // add fade effect for instruction label
        Design.startFadeEffect(instruction);

        // To do: Add a specific handler here
        instruction.addMouseListener(mouseEVHandler);
        buttonPanel.add(instruction);

        gbc.insets.top = 75;
        gbc.gridy = 2;
        titlePanel.add(buttonPanel, gbc);
    }

}
