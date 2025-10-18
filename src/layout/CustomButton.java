package layout;

import javax.swing.*;
import java.awt.*;

public class CustomButton extends JButton {
    private int arcWidth;
    private int arcHeight;

    public CustomButton(String text, int arcWidth, int arcHeight) {
        super(text); // inherited from JButton
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;

        // setContentAreaFilled(false);
        setFocusPainted(false);
        // setBorderPainted(false);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Background color
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);

        // Paint button text
        super.paintComponent(g);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground().darker());
        g2.setStroke(new BasicStroke(4));

        g2.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, arcWidth, arcHeight);
        g2.dispose();
    }

    public void getArcWidth(int arcWidth) {
        this.arcWidth = arcWidth;
    }

    public void getArcHeight(int arcHeight) {
        this.arcHeight = arcHeight;
    }
}
