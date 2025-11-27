package events.button;

import layout.Card;

import javax.swing.*;

import events.sound_utls.SoundUtils;

public class SwitchScreenBehavior implements ButtonBehavior {
    @Override
    public void onClick(JButton button) {
        final String play = "Play", how = "How To Play", setting = "Settings", quit = "Quit";

        switch (button.getText()) {
            case play:
                Card.screenChoice(play);
                Card.currentPage = play;
                break;
            case how:
                Card.screenChoice(how);
                Card.currentPage = how;
                break;
            case setting:
                Card.screenChoice(setting);
                Card.currentPage = setting;
                break;
            case quit:
                System.exit(0);
            default:
                break;
        }
    }
}
