import java.awt.Font;
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
}
