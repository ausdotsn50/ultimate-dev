// awt components
import java.awt.CardLayout;

// swing components
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Card extends JFrame {
    // Immutable dimension size
    private final int screenWidth = 1920;
    private final int screenHeight = 1080;

    private CardLayout cardLayout;
    private JPanel container;

    private MainMenu menu;
    private SplashScreen splashScreen;
    private HowToPlay howToPlay;

    public Card() {
        this.setTitle("Ultimate Dev");
        this.setSize(screenWidth, screenHeight);
        this.setResizable(false);

        this.cardLayout = new CardLayout();
        this.container = new JPanel(this.cardLayout); // mainCardPanel uses cardLayout

        menu = new MainMenu(cardLayout, container);
        container.add(menu, "Menu");

        splashScreen = new SplashScreen(cardLayout, container);

        howToPlay = new HowToPlay(cardLayout, container);

        container.add(howToPlay, "HowToPlay");
        container.add(splashScreen, "Splash");
        cardLayout.show(container, "Splash");
        
        this.add(container);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
