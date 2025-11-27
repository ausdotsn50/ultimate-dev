package layout.design;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Random;

import entities.CoDev;
import layout.constants.UDColors;
import ud_interfaces.Play;

public class DesignCoDev {
    public static Play playScreen; // bottom display purposes
    public static boolean isCorrect = false;
    public static CoDev codev = new CoDev(); // an entity

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
        infoPanel.setLayout(new GridLayout(2, 1)); // no timer row
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

            String answer = chooseAnswer(finalDev.accuracy);
            answerLabel.setText("CoDev's Answer: " + answer);

            finishWithAnswer(answer);
        });

        stopTimer.setRepeats(false);
        stopTimer.start();
    }

    private static void setScaledImage(Image img) {
        int IMG_WIDTH = 400;
        int IMG_HEIGHT = 400;
        Image scaled = img.getScaledInstance(IMG_WIDTH, IMG_HEIGHT, Image.SCALE_SMOOTH);
        devImageHolder.setIcon(new ImageIcon(scaled));
    }

    private static String chooseAnswer(double accuracy) {
        Random r = new Random();
        boolean correct = r.nextDouble() < accuracy;
        isCorrect = correct;

        if (correct) {
            return DesignQuiz.combinedChoices.get(0);
        } else {
            return DesignQuiz.combinedChoices.get(1 + r.nextInt(DesignQuiz.combinedChoices.size() - 1));
        }
    }

    private static void finishWithAnswer(String coDevAnswer) {
        DesignQuiz.isCorrect = Objects.equals(DesignQuiz.answer, coDevAnswer);
        Play.coDevActive = false;

        new javax.swing.Timer(5000, e -> {
            Play.showResult = true;
            DesignQuiz.playScreen.refreshCenter();
            ((javax.swing.Timer) e.getSource()).stop();
        }).start();
    }

}
