import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

public class Design {
    // 'static' - belongs to the class itself, not to any instance (object)
    static double res_factor = 0.60;

    // Immutable dimension size
    protected static int screenWidth = (int)(1920 * res_factor);
    protected static int screenHeight = (int)(1080 * res_factor);

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

    public static void topDesign(JPanel choicePanel, String leftStr) {
        JLabel left = new JLabel(leftStr);
        JLabel right = new JLabel("game.css");

        left.setFont(loadCustomFont(20));
        left.setForeground(Color.WHITE);

        right.setFont(loadCustomFont(20));
        right.setForeground(Color.WHITE);

        left.setHorizontalAlignment(SwingConstants.CENTER);
        right.setHorizontalAlignment(SwingConstants.CENTER);

        left.setVerticalAlignment(SwingConstants.TOP);
        right.setVerticalAlignment(SwingConstants.TOP);

        choicePanel.add(left);
        choicePanel.add(right);
    }

    public static void centerDesign(JPanel choicePanel, String titleStr) {
        // Title label
        JLabel title = new JLabel(titleStr);
        title.setFont(Design.loadCustomFont(60));
        title.setForeground(Color.WHITE);

        // Ensure text is centered *inside* the label(title)
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.CENTER);

        choicePanel.add(title, BorderLayout.CENTER);
    }




}
