package events;

import layout.CustomButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class MouseEVHandler extends MouseAdapter {
    @Override
    public void mouseEntered(MouseEvent e) {
        JLabel src = (JLabel) e.getSource();
        Font origFont = src.getFont();

        // Increase font size on hover
        src.setText("Nothing to click here");
        src.setFont(origFont.deriveFont((float) (origFont.getSize() + 10)));
    }

    public void mouseExited(MouseEvent e) {
        JLabel src = (JLabel) e.getSource();
        Font modFont = src.getFont();

        // Revert to orig text settings
        src.setText("Press 'Enter' to Start");
        src.setFont(modFont.deriveFont((float) (modFont.getSize() - 10)));

    }
}
