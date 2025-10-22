package events;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseEVHandler extends MouseAdapter {
    private final ButtonBehavior buttonBehavior;
    private final LabelBehavior labelBehavior;

    public MouseEVHandler(ButtonBehavior buttonBehavior, LabelBehavior labelBehavior) {
        this.buttonBehavior = buttonBehavior;
        this.labelBehavior = labelBehavior;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // 'Event filtering' by using 'instanceof keyword'
        if(e.getSource() instanceof JButton) {
            buttonBehavior.onClick((JButton) e.getSource());
        }
        else if(e.getSource() instanceof JLabel) {
            labelBehavior.onClick((JLabel) e.getSource());
        }
    }

    public void mouseEntered(MouseEvent e) {
        if(e.getSource() instanceof JLabel) {
            labelBehavior.onEnter((JLabel) e.getSource());
        }
    }

    public void mouseExited(MouseEvent e) {
        if(e.getSource() instanceof JLabel) {
            labelBehavior.onExit((JLabel) e.getSource());
        }
    }
}
