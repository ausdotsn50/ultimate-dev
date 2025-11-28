package utils;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class SoundUtils {
    public static boolean isSfxEnabled = true;
    private static Clip bgClip;
    private static Clip clickClip;

    public static void playClickSound() {
        // Gatekeeper: If SFX is disabled, do nothing.
        if (!isSfxEnabled) {
            return;
        }

        try {
            if (clickClip == null || !clickClip.isOpen()) {
                loadClickClip();
            }
            if (clickClip.isRunning()) {
                clickClip.stop();
            }
            clickClip.setFramePosition(0);
            clickClip.start();

        } catch (Exception e) {
            System.err.println("Error playing click sound: " + e.getMessage());
        }
    }

    // Helper method to load the click sound into memory
    private static void loadClickClip() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        URL url = SoundUtils.class.getResource("/sound/click.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(url));

        clickClip = AudioSystem.getClip();
        clickClip.open(audioStream);

        // Set Volume (-2.0f)
        FloatControl gainControl = (FloatControl) clickClip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-2.0f);
    }

    public static void bgMusic() {
        stopBgMusic(); // Ensure no overlap
        try {
            AudioInputStream bgStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(SoundUtils.class.getResource("/sound/bg.wav")));
            bgClip = AudioSystem.getClip();
            bgClip.open(bgStream);

            FloatControl gainControl = (FloatControl) bgClip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-15.0f);

            bgClip.loop(Clip.LOOP_CONTINUOUSLY); // Usually BG music loops
            bgClip.start();
        } catch (Exception e) {
            System.err.println();
        }
    }

    public static void stopBgMusic() {
        if (bgClip != null) {
            if (bgClip.isRunning()) bgClip.stop();
            bgClip.close();
        }
    }
}