// awt components
import java.awt.CardLayout;

// swing components
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Card extends JFrame {
    // Immutable dimension size
    private final int screenWidth = 500;
    private final int screenHeight = 500;

    private CardLayout cardLayout;
    private JPanel mainPanel;

    public Card() {
        this.setTitle("Ultimate Dev");
        this.setSize(screenWidth, screenHeight);

        this.cardLayout = new CardLayout();
        this.mainPanel = new JPanel(this.cardLayout); // mainCardPanel uses cardLayout



        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
}
