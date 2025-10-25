package layout;

import ud_interfaces.*;

import java.awt.*;

// swing components
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Card extends JFrame {
    static CardLayout cardLayout = new CardLayout();
    static JPanel container = new JPanel(cardLayout);

    public static String currentPage = "";

    public Card() {
        this.setTitle("Ultimate Dev");
        this.setSize(Design.screenWidth, Design.screenHeight);
        this.setResizable(false);

        addPanels();

        this.add(container);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    // To do: Add threading
    private static void addPanels() {
        // Panels
        // Note: first added - first one to shows
        LoadScreen loadScreen = new LoadScreen();
        container.add(loadScreen, "Load Screen");

        MainMenu mainMenu = new MainMenu();
        container.add(mainMenu, "Main Menu");

        Play play = new Play();
        container.add(play, "Play");

        HowToPlay howToPlay = new HowToPlay();
        container.add(howToPlay, "How To Play");

        Settings settings = new Settings();
        container.add(settings, "Settings");

        cardLayout.show(container, "Settings");
    }

    public static void screenChoice(String cardPage) {
        System.out.println("Trying to switch from " + currentPage + " to " + cardPage);

        if(cardPage == null || cardPage.isEmpty() || cardPage.equals(currentPage)) {
            // return early
            return;
        }
        cardLayout.show(container, cardPage);
    }
}
