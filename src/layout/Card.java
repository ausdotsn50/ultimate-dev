package layout;
import ud_interfaces.Menu;
import ud_interfaces.SplashScreen;

import java.awt.CardLayout;

// swing components
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Card extends JFrame {
    public Card() {
        this.setTitle("Ultimate Dev");
        this.setSize(Design.screenWidth, Design.screenHeight);
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
        JPanel container = new JPanel(cardLayout);

        // Panels
        SplashScreen splash = new SplashScreen(cardLayout, container);
        Menu menu = new Menu(cardLayout, container);
        container.add(splash, "Splash");
        container.add(menu, "Menu");
        
        // Start with Splash
        cardLayout.show(container, "Splash");
        

        return container;
    }
}
