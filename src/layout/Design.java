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

    public static void headerDesign(JPanel mainPanel, String leftHd) {
        JPanel headerPanel = new JPanel(new GridLayout(1,2));

        // Changed to a more dynamic panel placing
        headerPanel.setPreferredSize(new Dimension(Design.screenWidth, (int)(Design.screenHeight * 0.15) ));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        headerPanel.setOpaque(false);

        JLabel hLeft = new JLabel(leftHd);
        JLabel hRight = new JLabel("game.css");

        hLeft.setFont(loadCustomFont(20));
        hLeft.setForeground(UDColors.udWhite);

        hRight.setFont(loadCustomFont(20));
        hRight.setForeground(UDColors.udWhite);

        hLeft.setHorizontalAlignment(SwingConstants.CENTER);
        hRight.setHorizontalAlignment(SwingConstants.CENTER);

        hLeft.setVerticalAlignment(SwingConstants.TOP);
        hRight.setVerticalAlignment(SwingConstants.TOP);

        headerPanel.add(hLeft);
        headerPanel.add(hRight);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
    }

    public static void footerDesign(JPanel choicePanel, String leftFt, String rightFt) {
        JPanel footerPanel = new JPanel(new GridLayout(1,2));

        JLabel fLeft = new JLabel(leftFt, JLabel.CENTER);
        JLabel fRight = new JLabel(rightFt,  JLabel.CENTER);

        fLeft.setFont(Design.loadCustomFont(20));
        fLeft.setForeground(Color.WHITE);

        fRight.setFont(Design.loadCustomFont(20));
        fRight.setForeground(Color.WHITE);

        footerPanel.add(fLeft);
        footerPanel.add(fRight);
        footerPanel.setOpaque(false);

        // Bottom panel components
        choicePanel.add(footerPanel, BorderLayout.SOUTH);
    }

    // fade fx
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
            Color base = UDColors.udCyan;
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

    // create universal footer design
    public static void footerDesign(JPanel panel) {

    }

}
