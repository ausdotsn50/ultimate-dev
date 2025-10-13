package events;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class MouseEVHandler extends MouseAdapter {
    @Override
    public void mouseEntered(MouseEvent e) {
        JButton src = (JButton) e.getSource();
        Font ogFontSet = src.getFont();

        // Increase font size on hover
        src.setFont(ogFontSet.deriveFont((float) (ogFontSet.getSize() + 10)));
    }

    public void mouseExited(MouseEvent e) {
        JButton src = (JButton) e.getSource();
        Font modFontSet = src.getFont();

        // Revert to orig font settings
        src.setFont(modFontSet.deriveFont((float) (modFontSet.getSize() - 10)));
    }
}
