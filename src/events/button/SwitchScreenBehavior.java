package events.button;

import layout.Card;

import javax.swing.*;

import events.sound_utls.SoundUtils;

public class SwitchScreenBehavior implements ButtonBehavior {
    static SoundUtils sound = new SoundUtils();
    @Override
    public void onClick(JButton button) {
        final String play = "Play", how = "How To Play", setting = "Settings", quit = "Quit";

        switch (button.getText()) {
            case play:
                sound.clickSound();
                Card.screenChoice(play);
                Card.currentPage = play;
                break;
            case how:
                sound.clickSound();
                Card.screenChoice(how);
                Card.currentPage = how;
                break;
            case setting:
                sound.clickSound();
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
