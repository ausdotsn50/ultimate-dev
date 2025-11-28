package ud_interfaces;

import layout.design.Design;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class DesignUltDev {
    public static Play playScreen;

    public static void showUltDev(Play play, JPanel centerPanel, GridBagConstraints gbc) {
        playScreen = play;
        playScreen.displayTop(playScreen, "ult_dev.html");
        playScreen.displayBottom(playScreen, "The Ultimate Dev Gameshow", "1");

        Play.centerPanel.setLayout(new BorderLayout());

        JLabel ultimateDevImg;

        try {
            ImageIcon rawIcon = new ImageIcon(
                Objects.requireNonNull(
                    DesignUltDev.class.getResource("/image/wins.png")
                )
            );

            Image scaled = rawIcon.getImage().getScaledInstance(
                    Design.screenWidth, Design.screenHeight, Image.SCALE_DEFAULT);

            ultimateDevImg = new JLabel(new ImageIcon(scaled));

        } catch (Exception e) {
            System.out.println("Ultimate Dev image not found");
            // e.printStackTrace();
            ultimateDevImg = new JLabel("Image not found");
        }

        ultimateDevImg.setHorizontalAlignment(SwingConstants.CENTER);
        ultimateDevImg.setVerticalAlignment(SwingConstants.CENTER);

        Play.centerPanel.add(ultimateDevImg, BorderLayout.CENTER);
    }
}
