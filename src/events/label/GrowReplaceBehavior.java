package events.label;

import javax.swing.*;
import java.awt.*;

public class GrowReplaceBehavior implements LabelBehavior {
    public int sizeIncrease;

    public GrowReplaceBehavior(int sizeIncrease) {
        this.sizeIncrease = sizeIncrease;
    }

    @Override
    public void onEnter(JLabel label) {
        Font origFont = label.getFont();
        String hoverText = (String) label.getClientProperty("hoverText");

        if (hoverText != null)
            label.setText(hoverText);

        label.setFont(origFont.deriveFont((float) (origFont.getSize() + sizeIncrease)));
    }

    @Override
    public void onExit(JLabel label) {
        Font modFont = label.getFont();
        String exitText = (String) label.getClientProperty("exitText");

        if (exitText != null)
            label.setText(exitText);

        label.setFont(modFont.deriveFont((float) (modFont.getSize() - sizeIncrease)));
    }

    @Override
    public void onClick(JLabel label) {
        // Do NADA
    }
}
