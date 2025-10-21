package events;

import javax.swing.*;
import java.awt.*;

public class GrowReplaceBehavior implements LabelBehavior {
    public int fontIncrease;

    public GrowReplaceBehavior(int fontIncrease) {
        this.fontIncrease = fontIncrease;
    }

    @Override
    public void onEnter(JLabel label) {
        Font origFont = label.getFont();
        String hoverText = (String) label.getClientProperty("hoverText");

        if (hoverText != null)
            label.setText(hoverText);

        label.setFont(origFont.deriveFont((float) (origFont.getSize() + fontIncrease)));
    }

    @Override
    public void onExit(JLabel label) {
        Font modFont = label.getFont();
        String exitText = (String) label.getClientProperty("exitText");

        if (exitText != null)
            label.setText(exitText);

        label.setFont(modFont.deriveFont((float) (modFont.getSize() - fontIncrease)));
    }
}
