import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
//import org.w3c.dom.events.MouseEvent;
import java.awt.event.MouseEvent;


public class MainMenu extends JPanel{
    // private Image backgroundImage;
    private JPanel topPanel, bottomPanel;
    private JLabel play, howToPlay, settings, exit;
    private GridBagConstraints gbc = new GridBagConstraints();
    private CardLayout cardLayout;
    private JPanel container;

    public MainMenu(CardLayout cardLayout, JPanel container){
        this.setLayout(new BorderLayout());
        this.cardLayout = cardLayout;
        this.container = container;
        // backgroundImage = new ImageIcon(getClass().getResource("Images/Menu.png")).getImage();

        topPanel = new JPanel(new GridBagLayout());
        bottomPanel = new JPanel(new GridBagLayout());

        topPanel.setOpaque(false);
        bottomPanel.setOpaque(false);

        topPanel.setPreferredSize(new Dimension(1920, 620));
        bottomPanel.setPreferredSize(new Dimension(1920, 300));

        displayTopPanel();
        displayBottomPanel();

        this.add(topPanel, BorderLayout.NORTH);
        this.add(bottomPanel, BorderLayout.CENTER);
    }

    public void displayTopPanel(){
        play = new JLabel("Play");
        howToPlay = new JLabel("How to Play");
        settings = new JLabel("Settings");
        exit = new JLabel("Exit");

        try {
            play.setFont(Design.loadCustomFont(30));
            howToPlay.setFont(Design.loadCustomFont(30));
            settings.setFont(Design.loadCustomFont(30));
            exit.setFont(Design.loadCustomFont(30));

            play.setForeground(Color.CYAN);
            howToPlay.setForeground(Color.ORANGE);
            settings.setForeground(Color.GREEN);
            exit.setForeground(Color.RED);
        } catch (Exception e) {
            e.printStackTrace();
            play.setFont(new Font("SansSerif", Font.PLAIN, 30));
            howToPlay.setFont(new Font("SansSerif", Font.PLAIN, 30));
            settings.setFont(new Font("SansSerif", Font.PLAIN, 30));
            exit.setFont(new Font("SansSerif", Font.PLAIN, 30));
        }

        gbc.insets = new Insets(125, 0, 25, 550);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        topPanel.add(play, gbc);

        gbc.insets = new Insets(10, 0, 25, 550);
        gbc.gridx = 0;
        gbc.gridy = 1;
        topPanel.add(howToPlay, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        topPanel.add(settings, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        topPanel.add(exit, gbc);

        play.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        settings.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        howToPlay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        exit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        play.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            }
        });

        howToPlay.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(container, "HowToPlay");
            }
        });

        settings.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            }
        });

        exit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            }
        });
    }

    public void displayBottomPanel(){

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        // g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
