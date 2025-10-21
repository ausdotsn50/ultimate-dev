package events;

import layout.CustomButton;

import javax.swing.*;

public interface MouseBehavior {
    // Working with labels
    public void onEnter(JLabel label);
    public void onExit(JLabel label);

    public void onClick(JButton button);
}
