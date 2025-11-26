package layout.design;

import events.MouseEVHandler;
import events.button.GoToCategBehavior;
import layout.constants.CustomButton;
import layout.constants.UDColors;
import ud_interfaces.Play;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DesignCategory {
    public static ArrayList<String> categories;
    public static Play playScreen;
    static MouseEVHandler mouseEVHandler = new MouseEVHandler(new GoToCategBehavior(), null);
    public static void showCategories(Play play, JPanel centerPanel, GridBagConstraints gbc) {
        playScreen = play;
        playScreen.displayBottom(playScreen, "Points: " + Play.currPoints, "2");

        int tbInsets = 20;

        gbc.gridx = 0; gbc.gridy = 0;
        gbc.insets = new Insets(tbInsets / 2, 0, tbInsets / 2, 0);

        // Dedicated panel for the text
        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);

        addTitle(titlePanel);
        centerPanel.add(titlePanel, gbc);

        GridBagConstraints buttonGbc = new GridBagConstraints(); // gbc for buttons
        buttonGbc.insets = new Insets(tbInsets, tbInsets, tbInsets, tbInsets);
        buttonGbc.gridx = 0; buttonGbc.gridy = 0;

        gbc.gridy = 1;
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false);

        addCategoryButtons(buttonPanel, buttonGbc);
        centerPanel.add(buttonPanel, gbc);
    }

    public static void addTitle(JPanel titlePanel) {
        JLabel titleLabel = new JLabel("{ Choose a category }");
        titleLabel.setFont(Design.loadCustomFont(Design.titleSize));
        titleLabel.setForeground(UDColors.udWhite);

        titlePanel.add(titleLabel);
    }

    public static void addCategoryButtons(JPanel buttonPanel, GridBagConstraints buttonGbc) {
        int fontSize = Design.regularSize + 2;
        for (int i = 0; i < categories.size(); i++) {
            buttonGbc.gridx += 1;

            if(i % 3 == 0){
                if(i == 6) { buttonGbc.gridx = 1; }
                else { buttonGbc.gridx = 0; }
                buttonGbc.gridy += 1;
            }

            // Replace with custom buttons
            CustomButton category = new CustomButton(categories.get(i), 10, 10);
            category.setPreferredSize(new Dimension(320, fontSize  * 3));
            category.doLayout();
            category.setFont(Design.loadCustomFont(fontSize - 3));
            category.addMouseListener(mouseEVHandler);

            buttonPanel.add(category, buttonGbc);
        }
    }
}
