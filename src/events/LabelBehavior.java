package events;

import javax.swing.*;

public interface LabelBehavior {
    // Methods for JLabel
    public void onEnter(JLabel label);
    public void onExit(JLabel label);
}
