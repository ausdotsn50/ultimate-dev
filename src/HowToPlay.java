import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HowToPlay extends JPanel {
    private JPanel topPanel, bottomPanel;
    private JLabel back;
    private Image backgroundImage;
    private GridBagConstraints gbc = new GridBagConstraints();
    
    public HowToPlay(CardLayout cardLayout, JPanel container) {
        this.setLayout(new BorderLayout());

        backgroundImage = new ImageIcon(getClass().getResource("Images/howtoplay.png")).getImage();

        topPanel = new JPanel();
        bottomPanel = new JPanel(new GridBagLayout());

        topPanel.setPreferredSize(new java.awt.Dimension(1920, 620));
        bottomPanel.setPreferredSize(new java.awt.Dimension(1920, 300));

        displayTopPanel();
        displayBottomPanel();

        this.add(topPanel, BorderLayout.NORTH);
        this.add(bottomPanel, BorderLayout.CENTER);
    }

    public void displayTopPanel(){
        topPanel.setOpaque(false);

    }

    public void displayBottomPanel(){
        bottomPanel.setOpaque(false);
        back = new JLabel("Back");
        gbc.gridx = 0;
        gbc.gridy = 0;

        bottomPanel.add(back);
        back.setFont(new Font("Fira Code", Font.PLAIN, 20));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);    
    
    }
}
