package layout.design;

import java.awt.*;
import javax.swing.*;
/*
 * Represents a loading developer component with an image and accuracy attribute.
 */
public class LoadDev extends JLabel {
    Image bgImage;
    public double accuracy;
    public LoadDev(String imagePath, double accuracy) {
        this.accuracy = accuracy;

        try {
            bgImage = new ImageIcon(getClass().getResource(imagePath)).getImage();
        } catch (Exception e) {
            System.out.println("No such image: " + imagePath);
        }
    }

    public Image getImage() {
    return bgImage;
}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (bgImage != null) {
            g2d.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
            g2d.dispose();
        }
    }
}
