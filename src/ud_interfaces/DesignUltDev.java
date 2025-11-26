package ud_interfaces;

import javax.swing.*;
import java.awt.*;

public class DesignUltDev {
    public static Play playScreen;
    public static void showUltDev(Play play, JPanel centerPanel, GridBagConstraints gbc) {
        // change the top design
        playScreen = play;
        playScreen.displayTop(playScreen, "ult_dev.html");
        playScreen.displayBottom(playScreen, "The Ultimate Dev Gameshow", "1");
    }
}
