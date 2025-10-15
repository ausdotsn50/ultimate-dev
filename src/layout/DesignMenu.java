package layout;

import javax.swing.*;
import java.awt.*;

public class DesignMenu {
    public static void optionsDesign(JPanel mainPanel) {
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setPreferredSize(new Dimension(Design.screenWidth, (int)(Design.screenHeight * 80) ));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
        // centerPanel.setOpaque(false);

        JPanel textPanel = new JPanel();
        textPanel.setOpaque(false);

        JLabel title = new JLabel("Design Menu");
        title.setFont(Design.loadCustomFont(50));
        title.setForeground(UDColors.udCyan);

        JLabel subtitle = new JLabel("Design Menu");
        subtitle.setFont(Design.loadCustomFont(20));
        subtitle.setForeground(UDColors.udCyan);

        textPanel.add(title);
        textPanel.add(subtitle);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        CustomButton play = new CustomButton("Play", 10, 10);
        play.setFont(Design.loadCustomFont(20));

        // CustomButton play = new CustomButton("Play", 10, 10);
        // play.setFont(Design.loadCustomFont(20));

        // CustomButton play = new CustomButton("Play", 10, 10);
        // play.setFont(Design.loadCustomFont(20));


        buttonPanel.add(play, gbc);

        centerPanel.add(textPanel, BorderLayout.NORTH);
        centerPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.add(centerPanel, BorderLayout.CENTER);
    }
}
