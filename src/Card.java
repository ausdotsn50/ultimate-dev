// awt components
import java.awt.CardLayout;

// swing components
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Card extends JFrame {
    // Immutable dimension size
    protected final int screenWidth = 1920;
    protected final int screenHeight = 1080;

    public Card() {
        this.setTitle("Ultimate Dev");
        this.setSize(screenWidth, screenHeight);
        this.setResizable(false);

        JPanel container = getJPanel(); // container as main JPanel

        this.add(container);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    // To do: Add threading
    private static JPanel getJPanel() {
        CardLayout cardLayout = new CardLayout();
        JPanel container = new JPanel(cardLayout); // container uses instance of CardLayout

        // Panels
        MainMenu menu = new MainMenu(cardLayout, container);
        container.add(menu, "Menu");

        // Main Panel
        cardLayout.show(container, "Splash");
        return container;
    }
}
