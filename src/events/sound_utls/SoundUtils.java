package events.sound_utls;

import javax.sound.sampled.*;
import java.io.IOException;

public class SoundUtils {
    public void clickSound(){
    try {
           AudioInputStream bgStream = AudioSystem.getAudioInputStream(getClass().getResource("/sound/click.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(bgStream);

            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-2.0f); // lowered by 2 db

            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void bgMusic() {
        try {
            AudioInputStream bgStream = AudioSystem.getAudioInputStream(getClass().getResource("/sound/bg.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(bgStream);

            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-15.0f); // lowered by 15 db

            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}