package layout;

import layout.constants.CustomButton;
import layout.constants.UDColors;
import ud_interfaces.Play;

import javax.swing.*;
import java.awt.*;

public class DesignCategory {
    static String[] categories = {
            "Introduction to the Paradigms", "Procedural Programming",
            "Functional Programming", "Object-Oriented Programming",
            "Imperative vs Declarative", "Event-Driven Programming",
            "Component Mappings between Paradigms",

    };
    public static void showCategories(JPanel mainPanel, JPanel centerPanel, GridBagConstraints gbc) {
        int tbInsets = 20, lrInsets = 30;

        gbc.gridx = 0; gbc.gridy = 0;
        gbc.insets = new Insets(tbInsets / 2, 0, tbInsets / 2, 0);

        // Dedicated panel for the text
        JPanel textPanel = new JPanel();
        textPanel.setOpaque(false);

        JLabel titleLabel = new JLabel("{ Choose a category }");
        titleLabel.setFont(Design.loadCustomFont(Design.titleSize));
        titleLabel.setForeground(UDColors.udWhite);

        textPanel.add(titleLabel);
        centerPanel.add(textPanel, gbc);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false);

        GridBagConstraints buttonGbc = new GridBagConstraints(); // gbc for buttons
        buttonGbc.insets = new Insets(tbInsets, 0, tbInsets, lrInsets);
        buttonGbc.gridx = 0;
        buttonGbc.gridy = 0;

        int fontsize = Design.regularSize;
        for (int i = 0; i < Play.categoryCnt; i++) {
            buttonGbc.gridx += 1;

            if(i%3==0){
                buttonGbc.gridx = 0;
                buttonGbc.gridy += 1;
            }

            // Replace with custom buttons
            CustomButton category = new CustomButton(categories[i], 10, 10);
            category.setPreferredSize(new Dimension(250, fontsize * 3));

            category.setFont(Design.loadCustomFont(fontsize));
            buttonPanel.add(category, buttonGbc);
        }

        gbc.gridy = 1;
        centerPanel.add(buttonPanel, gbc);

    }
}
