package events.label;

import layout.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.WeakHashMap;

public record GrowSwitchBehavior(int sizeIncrease) implements LabelBehavior {
    static final WeakHashMap<JLabel, BufferedImage> originalImages = new WeakHashMap<>();
    // Cache for resized images - keyed by "label_width_height"
    static final WeakHashMap<JLabel, BufferedImage> hoverImages = new WeakHashMap<>();
    static final WeakHashMap<JLabel, ImageIcon> normalIcons = new WeakHashMap<>();

    @Override
    public void onEnter(JLabel label) {
        // Cache the normal icon if not already cached
        if (!normalIcons.containsKey(label)) {
            normalIcons.put(label, (ImageIcon) label.getIcon());
        }

        // Check if hover image is cached
        BufferedImage hoverImg = hoverImages.get(label);
        if (hoverImg == null) {
            BufferedImage original = getOriginalImage(label);
            int newWidth = original.getWidth() + sizeIncrease;
            int newHeight = original.getHeight() + sizeIncrease;
            hoverImg = resizeImage(original, newWidth, newHeight);
            hoverImages.put(label, hoverImg);
        }
        label.setIcon(new ImageIcon(hoverImg));
    }

    @Override
    public void onExit(JLabel label) {
        // Restore cached normal icon
        ImageIcon normalIcon = normalIcons.get(label);
        if (normalIcon != null) {
            label.setIcon(normalIcon);
        } else {
            // Fallback: resize back to original
            BufferedImage original = getOriginalImage(label);
            label.setIcon(new ImageIcon(original));
        }
    }

    @Override
    public void onClick(JLabel label) {
        String path = (String) label.getClientProperty("path");
        switch (path) {
            case "/Main_Menu.png", "/How_To_Play.png", "/Settings.png", "/Surrender.png":
                String screen = path.replace(".png", "").replace("_", " ")
                        .replace("/", "");
                Card.screenChoice(screen);
                Card.currentPage = screen;
                break;
            case "/Quit.png":
                System.exit(0);
            default:
                break;
        }
    }

    // Method gets called onEnter and onExit
    private BufferedImage getOriginalImage(JLabel label) {
        // Take from the WeakHashMap
        BufferedImage img = originalImages.get(label);
        if (img == null) {
            ImageIcon icon = (ImageIcon) label.getIcon(); // get icon
            img = toBufferedImage(icon.getImage()); // get img, convert to buffered img
            originalImages.put(label, img); // put in weak hashmap
        }
        return img;
    }

    // Method for resizing image without pixel degradation
    private BufferedImage resizeImage(BufferedImage original, int width, int height) {
        // The new canvas
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);  // requires Alpha for PNG transparency settings

        // Creates a Graphics2D, which can be used to draw into this BufferedImage.
        // Returns: a Graphics2D, used for drawing into this image.
        // Creating a paintbrush that knows where exactly to draw
        Graphics2D g2 = resized.createGraphics();

        // High-quality rendering settings
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // This code - draws the original image to the 'new and scaled' canvas
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();

        return resized;
    }

    private BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }
        BufferedImage bimage = new BufferedImage(
                img.getWidth(null),
                img.getHeight(null),
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = bimage.createGraphics();
        g2.drawImage(img, 0, 0, null);
        g2.dispose();
        return bimage;
    }
}
