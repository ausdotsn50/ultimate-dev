package layout.design;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Random;

import entities.CoDev;
import ud_interfaces.Play;

public class DesignCoDev {
    public static Play playScreen; // bottom display purposes
    public static boolean isCorrect = false;
    public static CoDev codev = new CoDev(); // an entity

    // Reusable Random instance
    private static final Random rand = new Random();

    // Cache for scaled images (same size always: 400x400)
    private static final java.util.Map<Image, ImageIcon> scaledImageCache = new java.util.WeakHashMap<>();
    private static final int IMG_WIDTH = 400;
    private static final int IMG_HEIGHT = 400;

    private static JLabel devImageHolder;
    private static JLabel accuracyLabel;
    private static JLabel answerLabel;
    private static Timer flashTimer;
    private static Timer stopTimer;

    public static void showCoDev(Play play, JPanel centerPanel) {
        playScreen = play;
        playScreen.displayTop(playScreen, "co_dev.html");
        playScreen.displayBottom(playScreen, "Points: " + Play.currPoints, "Copy [" + Play.copy +"]");

        centerPanel.setLayout(new BorderLayout());
        centerPanel.setOpaque(false);

        devImageHolder = new JLabel();
        devImageHolder.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(2, 1)); 
        infoPanel.setOpaque(false);

        accuracyLabel = new JLabel("Accuracy: ---", SwingConstants.CENTER);
        accuracyLabel.setFont(Design.loadCustomFont(Design.subTitleSize + 10));
        accuracyLabel.setForeground(Color.WHITE);

        answerLabel = new JLabel("CoDev's Answer: ---", SwingConstants.CENTER);
        answerLabel.setFont(Design.loadCustomFont(Design.subTitleSize + 10));
        answerLabel.setForeground(Color.WHITE);

        infoPanel.add(accuracyLabel);
        infoPanel.add(answerLabel);

        centerPanel.add(devImageHolder, BorderLayout.CENTER);
        centerPanel.add(infoPanel, BorderLayout.NORTH);

        startCoDevRandomizer();
    }

    public static void startCoDevRandomizer() {
        if (DesignQuiz.combinedChoices.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No choices loaded!");
            return;
        }

        flashTimer = new Timer(120, e -> {
            LoadDev randDev = codev.getRandomDev();
            setScaledImage(randDev.bgImage);
            accuracyLabel.setText("Accuracy: " + (int)(randDev.accuracy * 100) + "%");
        });

        flashTimer.start();

        stopTimer = new Timer(5000, e -> {
            flashTimer.stop();
            stopTimer.stop();

            LoadDev finalDev = codev.getRandomDev();
            setScaledImage(finalDev.bgImage);
            accuracyLabel.setText("Accuracy: " + (int)(finalDev.accuracy * 100) + "%");

            // parses answer
            int cDevAnsIndex = chooseAnswer(finalDev.accuracy);
            answerLabel.setText("CoDev's Answer: " + DesignQuiz.letters[DesignQuiz.answerIndex].charAt(0));

            finishWithAnswer(cDevAnsIndex);
        });

        stopTimer.setRepeats(false);
        stopTimer.start();
    }

    private static void setScaledImage(Image img) {
        // Check cache first
        ImageIcon cachedIcon = scaledImageCache.get(img);
        if (cachedIcon == null) {
            Image scaled = img.getScaledInstance(IMG_WIDTH, IMG_HEIGHT, Image.SCALE_SMOOTH);
            cachedIcon = new ImageIcon(scaled);
            scaledImageCache.put(img, cachedIcon);
        }
        devImageHolder.setIcon(cachedIcon);
    }

    private static int chooseAnswer(double accuracy) {
        boolean willAnswerCorrectly = rand.nextDouble() < accuracy;

        if (willAnswerCorrectly) {
            return DesignQuiz.answerIndex;
        } else {
            int wrongIndex;
            do {
                wrongIndex = rand.nextInt(4);
            } while (wrongIndex == DesignQuiz.answerIndex);
            return wrongIndex;
        }
    }

    private static void finishWithAnswer(int index) {
        DesignQuiz.isCorrect = Objects.equals(DesignQuiz.answerIndex, index);
        Play.coDevActive = false;

        new javax.swing.Timer(5000, e -> {
            Play.showResult = true;
            DesignQuiz.playScreen.refreshCenter();
            ((javax.swing.Timer) e.getSource()).stop();
        }).start();
    }

}
