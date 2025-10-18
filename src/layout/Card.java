package layout;

import ud_interfaces.MainMenu;
import ud_interfaces.SplashScreen;

import java.awt.CardLayout;

// swing components
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Card extends JFrame {
    static CardLayout cardLayout = new CardLayout();
    static JPanel container = new JPanel(cardLayout);

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
        SplashScreen splashScreen = new SplashScreen();
        container.add(splashScreen, "SplashScreen");

        MainMenu mainMenu = new MainMenu();
        container.add(mainMenu, "MainMenu");

        // Start with Splash
        cardLayout.show(container, "MainMenu");
    }

    public static void showMainMenu() {
        cardLayout.show(container, "MainMenu");
        System.out.println("Showing main menu...");
    }

    public static void showSplashScreen() {
        cardLayout.show(container, "SplashScreen");
        System.out.println("Showing splash screen...");
    }

    public static void menuQuit() {
        System.exit(0);
    }

}
