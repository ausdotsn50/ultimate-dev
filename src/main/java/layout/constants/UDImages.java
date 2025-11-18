package layout.constants;

import layout.design.Design;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class UDImages {
    // prolly include bg image template here instead
    public static Image bgId1;
    public static Image bgId2;

    static {
        try {
            bgId1 = ImageIO.read(Objects.requireNonNull(Design.class.getResource("/image/template_v1.png")));
            bgId2 = ImageIO.read(Objects.requireNonNull(Design.class.getResource("/image/template_v2.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
