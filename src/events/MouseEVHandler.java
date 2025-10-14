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
        String text = src.getText();

        if (text.equals("Play")) return;

        // Increase font size on hover
        src.setText("Nothing to click here");
        src.setFont(origFont.deriveFont((float) (origFont.getSize() + 10)));
    }

    public void mouseExited(MouseEvent e) {
        JLabel src = (JLabel) e.getSource();
        Font modFont = src.getFont();
        String text = src.getText();

        if (text.equals("Play")) return;

        // Revert to orig text settings
        src.setText("Press 'Enter' to Start");
        src.setFont(modFont.deriveFont((float) (modFont.getSize() - 10)));

    }

    /*
    @Override
    public void mouseClicked(MouseEvent e) {
        JLabel clickedLabel = (JLabel) e.getSource();
        String text = clickedLabel.getText();

        switch (text) {
            case "Play":
                cardLayout.show(container, "Splash"); //for testing only
                break;
            case "Settings":
                //cardLayout.show(container, "Settings");
                break;
            case "How to Play":
                //cardLayout.show(container, "HowToPlay");
                break;
            case "Exit":
                System.exit(0);
                break;
            default:
                System.out.println("Clicked: " + text);
                break;
        }
    }
     */
}
