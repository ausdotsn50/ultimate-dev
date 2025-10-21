package events;

import layout.Card;
import layout.CustomButton;

import javax.swing.*;

public class SwitchScreenBehavior implements MouseBehavior {
    @Override
    public void onEnter(JLabel label) {

    }

    @Override
    public void onExit(JLabel label) {

    }

    @Override
    public void onClick(JButton button) {
        final String play = "Play", how = "How To Play", setting = "Settings", quit = "Quit";

        switch (button.getText()) {
            case play:
                Card.screenChoice(play);
                break;
            case how:
                Card.screenChoice(how);
                break;
            case setting:
                Card.screenChoice(setting);
                break;
            case quit:
                Card.screenChoice(quit);
                System.exit(0);
            default:
                break;
        }
    }

}
