import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class SplashScreen extends JPanel{
    private Image backgroundImage;
    private JLabel enter;
    private JPanel topPanel, bottomPanel;

    public SplashScreen(CardLayout cardLayout, JPanel container){
        this.setLayout(new BorderLayout());
        backgroundImage = new ImageIcon(getClass().getResource("Images/Title.png")).getImage();

        topPanel = new JPanel(new BorderLayout());
        bottomPanel = new JPanel();

        topPanel.setOpaque(false);
        bottomPanel.setOpaque(false);

        topPanel.setPreferredSize(new Dimension(1920, 500));
        bottomPanel.setPreferredSize(new Dimension(1920, 400));
        
        enter = new JLabel("Press 'Enter' to Start!");
        enter.setFont(Design.loadCustomFont(20));
        enter.setForeground(Color.WHITE);

        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "startGame");
        this.getActionMap().put("startGame", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(container, "Menu");
            }
        });

        bottomPanel.add(enter, BorderLayout.NORTH);

        this.add(topPanel, BorderLayout.NORTH);
        this.add(bottomPanel, BorderLayout.CENTER);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);    
    }
}
