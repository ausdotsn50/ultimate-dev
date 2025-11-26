package layout.design;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Random;

import entities.CoDev;
import layout.constants.UDColors;
import ud_interfaces.Play;

public class DesignCoDev {

    public static Play playScreen;
    public static CoDev codev = new CoDev();

    private JPanel mainPanel;
    private JLabel devImageHolder;
    private JLabel accuracyLabel;
    private JLabel answerLabel;
    private Timer flashTimer;
    private Timer stopTimer;

    public static boolean isCorrect = false;
    static Random rand = new Random();

    private final int IMG_WIDTH = 400;
    private final int IMG_HEIGHT = 400;

    public DesignCoDev() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setOpaque(false);

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

        mainPanel.add(devImageHolder, BorderLayout.CENTER);
        mainPanel.add(infoPanel, BorderLayout.NORTH);
    }

    public JPanel getPanel() {
        return mainPanel;
    }

    public void startCoDevRandomizer() {
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

    private void setScaledImage(Image img) {
        Image scaled = img.getScaledInstance(IMG_WIDTH, IMG_HEIGHT, Image.SCALE_SMOOTH);
        devImageHolder.setIcon(new ImageIcon(scaled));
    }

    private String chooseAnswer(double accuracy) {
        Random r = new Random();
        boolean correct = r.nextDouble() < accuracy;
        isCorrect = correct;

        if (correct) {
            return DesignQuiz.combinedChoices.get(0);
        } else {
            return DesignQuiz.combinedChoices.get(1 + r.nextInt(DesignQuiz.combinedChoices.size() - 1));
        }
    }

    private void finishWithAnswer(String coDevAnswer) {

        boolean coDevIsCorrect = Objects.equals(DesignQuiz.answer, coDevAnswer);

        DesignQuiz.isCorrect = coDevIsCorrect;
        DesignQuiz.coDevActive = false;

        new javax.swing.Timer(5000, e -> {
            Play.showResult = true;
            DesignQuiz.playScreen.refreshCenter();
            ((javax.swing.Timer) e.getSource()).stop();
        }).start();
    }

}
