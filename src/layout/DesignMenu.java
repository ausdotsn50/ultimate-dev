package layout;

import events.MouseEVHandler;
import events.SwitchScreenBehavior;
import layout.constants.CustomButton;
import layout.constants.UDColors;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DesignMenu {
    static MouseEVHandler mouseEVHandler = new MouseEVHandler(new SwitchScreenBehavior(), null);
    static String[] buttons = {
            "Play",
            "How To Play",
            "Settings",
            "Quit",
    };

    public static void optionsDesign(JPanel centerPanel, GridBagConstraints mainGbc) {
        mainGbc.gridx = 0;
        mainGbc.gridy = 0;

        int topPad = 25, botPad = topPad - 5;
        JPanel textPanel = new JPanel(new GridBagLayout());
        EmptyBorder border = new EmptyBorder(topPad, 0, botPad, 0);
        textPanel.setBorder(border);

        GridBagConstraints gbcText = new GridBagConstraints();
        gbcText.insets = new Insets(0, 0, botPad, 0);
        gbcText.gridx = 0;
        gbcText.gridy = 0;

        textPanel.setOpaque(false);

        addTitle(textPanel, gbcText);
        addSubtitle(textPanel, gbcText);

        centerPanel.add(textPanel, mainGbc);

        mainGbc.gridy = 1;
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcButton = new GridBagConstraints();

        gbcButton.insets = new Insets(0, 0, botPad, 0);
        gbcButton.gridx = 0;
        gbcButton.gridy = 0;

        buttonPanel.setOpaque(false);

        // replace w/ loop
        for (int i = 0; i < buttons.length; i++) {
            addButton(buttonPanel, buttons[i], gbcButton, i);
        }

        centerPanel.add(buttonPanel, mainGbc);
    }

    private static void addTitle(JPanel textPanel, GridBagConstraints gbc) {
        JLabel title = new JLabel("{ Ultimate Dev }");
        title.setFont(Design.loadCustomFont(Design.titleSize));
        title.setForeground(UDColors.udWhite);

        textPanel.add(title, gbc);
    }

    private static void addSubtitle(JPanel textPanel, GridBagConstraints gbc) {
        JLabel subtitle = new JLabel("/* Select an option to continue */");
        subtitle.setFont(Design.loadCustomFont(Design.subTitleSize));
        subtitle.setForeground(UDColors.udGreen);

        gbc.gridy = 1;

        textPanel.add(subtitle, gbc);
    }

    private static void addButton(JPanel buttonPanel, String buttonStr, GridBagConstraints gbc, int gridYCoord) {
        int fontSize = Design.subTitleSize;

        // Main menu buttons customizations
        CustomButton button = new CustomButton(buttonStr, 10, 10);
        button.setPreferredSize(new Dimension(250, fontSize * 2));
        button.setFont(Design.loadCustomFont(fontSize));

        gbc.gridy = gridYCoord;
        buttonPanel.add(button, gbc);

        /*
            Each button in MainMenu is given the CustomButton look
            and SwitchScreen behavior
         */
        button.addMouseListener(mouseEVHandler);
    }
}
