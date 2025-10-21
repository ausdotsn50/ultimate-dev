package layout;

import events.MouseEVHandler;
import events.SwitchScreenBehavior;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DesignMenu {
    public static SwitchScreenBehavior switchScreen = new SwitchScreenBehavior();
    public static MouseEVHandler mouseEVHandler = new MouseEVHandler(switchScreen);

    public static void optionsDesign(JPanel mainPanel, JPanel centerPanel, GridBagConstraints mainGbc) {
        mainGbc.gridx = 0;
        mainGbc.gridy = 0;

        JPanel textPanel = new JPanel(new GridBagLayout());
        EmptyBorder border = new EmptyBorder(0, 0, 25, 0);
        textPanel.setBorder(border);

        GridBagConstraints gbcText = new GridBagConstraints();
        gbcText.insets = new Insets(0, 0, 20, 0);
        gbcText.gridx = 0;
        gbcText.gridy = 0;

        textPanel.setOpaque(false);

        addTitle(textPanel, gbcText);
        addSubtitle(textPanel, gbcText);

        centerPanel.add(textPanel, mainGbc);

        mainGbc.gridy = 1;
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcButton = new GridBagConstraints();

        gbcButton.insets = new Insets(0, 0, 20, 0);
        gbcButton.gridx = 0;
        gbcButton.gridy = 0;

        buttonPanel.setOpaque(false);

        addButton(buttonPanel, "Play", gbcButton, 0);
        addButton(buttonPanel, "How To Play", gbcButton, 1);
        addButton(buttonPanel, "Settings", gbcButton, 2);
        addButton(buttonPanel, "Quit", gbcButton, 3);

        centerPanel.add(buttonPanel, mainGbc);

        mainPanel.add(centerPanel, BorderLayout.CENTER);
    }

    private static void addTitle(JPanel textPanel, GridBagConstraints gbc) {
        JLabel title = new JLabel("{ Ultimate Dev }");
        title.setFont(Design.loadCustomFont(50));
        title.setForeground(UDColors.udWhite);

        textPanel.add(title, gbc);
    }

    private static void addSubtitle(JPanel textPanel, GridBagConstraints gbc) {
        JLabel subtitle = new JLabel("/* Select an option to continue */");
        subtitle.setFont(Design.loadCustomFont(20));
        subtitle.setForeground(UDColors.udGreen);

        gbc.gridy = 1;

        textPanel.add(subtitle, gbc);
    }

    private static void addButton(JPanel buttonPanel, String buttonStr, GridBagConstraints gbc, int gridYCoord) {
        int fontsize = 20;

        CustomButton button = new CustomButton(buttonStr, 10, 10);
        button.setPreferredSize(new Dimension(250, fontsize * 2));
        button.setFont(Design.loadCustomFont(fontsize));

        gbc.gridy = gridYCoord;

        buttonPanel.add(button, gbc);
        button.addMouseListener(mouseEVHandler);
    }

}
