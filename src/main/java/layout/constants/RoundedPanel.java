package layout.constants;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedPanel extends JPanel {
    public RoundedPanel() {
        super();
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int cornerRadius = 20;
        Shape round = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        g2.setColor(getBackground());
        g2.fill(round);
        g2.setColor(new Color(60, 60, 65)); // border color
        g2.draw(round);
        g2.dispose();
        super.paintComponent(g);
    }
}
