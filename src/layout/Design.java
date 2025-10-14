package layout;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

public class Design {
    // prolly include bg image template here instead

    // 'static' - belongs to the class itself, not to any instance (object)
    static double res_factor = 0.60;

    // Immutable dimension size
    public static int screenWidth = (int)(1920 * res_factor);
    public static int screenHeight = (int)(1080 * res_factor);

    public static Font loadCustomFont(int fontSize) {
        try (InputStream is = Design.class.getResourceAsStream("/font/FiraCode.ttf")) {
            if (is == null) {
                throw new RuntimeException("Font file not found!");
            }

            Font customFont = Font.createFont(Font.TRUETYPE_FONT, is);
            return customFont.deriveFont(Font.PLAIN, fontSize);

        } catch (Exception e) {
            // Add catch statement
            return new Font("SansSerif", Font.PLAIN, fontSize);
        }
    }

    public static void headerDesign(JPanel choicePanel, String leftHd) {
        JLabel hLeft = new JLabel(leftHd);
        JLabel hRight = new JLabel("game.css");

        hLeft.setFont(loadCustomFont(20));
        hLeft.setForeground(Color.WHITE);

        hRight.setFont(loadCustomFont(20));
        hRight.setForeground(Color.WHITE);

        hLeft.setHorizontalAlignment(SwingConstants.CENTER);
        hRight.setHorizontalAlignment(SwingConstants.CENTER);

        hLeft.setVerticalAlignment(SwingConstants.TOP);
        hRight.setVerticalAlignment(SwingConstants.TOP);

        choicePanel.add(hLeft);
        choicePanel.add(hRight);
    }

    //
    public static void startFadeEffect(JLabel label) {
        final float[] alpha = {1.0f};
        final boolean[] fadingOut = {true};

        Timer timer = new Timer(100, e -> {
            // to fade out
            if (fadingOut[0]) {
                alpha[0] -= 0.05f;
                // Capped at 0.1f transparency
                if (alpha[0] <= 0.1f) {
                    alpha[0] = 0.1f;
                    fadingOut[0] = false;
                }
            } else { // to fade in
                alpha[0] += 0.05f;
                // Capped at original alpha level
                if (alpha[0] >= 1.0f) {
                    alpha[0] = 1.0f;
                    fadingOut[0] = true;
                }
            }

            // Apply alpha transparency
            Color base = new Color(0x00DAF6);
            label.setForeground(new Color(
                    base.getRed(),
                    base.getGreen(),
                    base.getBlue(),
                    (int) (alpha[0] * 255) // opacity
            ));
            label.repaint();
        });

        timer.setInitialDelay(0);
        timer.start();
    }

    public static void centerDesign(JPanel choicePanel, String titleStr) {
        // Title label
        JLabel title = new JLabel(titleStr,  JLabel.CENTER);
        title.setFont(Design.loadCustomFont(110));
        title.setForeground(new Color(0x00DAF6));

        startFadeEffect(title);

        choicePanel.add(title, BorderLayout.CENTER);
    }

    // create universal footer design

}
