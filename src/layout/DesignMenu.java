package layout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DesignMenu {
    public static void optionsDesign(JPanel mainPanel, JPanel centerPanel, GridBagConstraints mainGbc) {
        mainGbc.gridx = 0;
        mainGbc.gridy = 0;

        JPanel textPanel = new JPanel(new GridBagLayout());
        EmptyBorder border = new EmptyBorder(0, 0, 50, 0);
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

        addPlayButton(buttonPanel, gbcButton);
        addHowButton(buttonPanel, gbcButton);
        addSettingsButton(buttonPanel, gbcButton);

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

    private static void addPlayButton(JPanel buttonPanel, GridBagConstraints gbc) {
        CustomButton play = new CustomButton("Play", 10, 10);
        play.setFont(Design.loadCustomFont(20));

        buttonPanel.add(play, gbc);
    }

    private static void addHowButton(JPanel buttonPanel, GridBagConstraints gbc) {
        CustomButton how = new CustomButton("How to Play", 10, 10);
        how.setFont(Design.loadCustomFont(20));

        gbc.gridy = 1;

        buttonPanel.add(how, gbc);
    }

    private static void addSettingsButton(JPanel buttonPanel, GridBagConstraints gbc) {
        CustomButton settings = new CustomButton("Settings", 10, 10);
        settings.setFont(Design.loadCustomFont(20));

        gbc.gridy = 2;

        buttonPanel.add(settings, gbc);
    }
}
