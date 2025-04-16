package utilz;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {

    public static class Testing {
        public static final String test = "test.png";
    }

    public static class Backgrounds {
        public static final String MENU_BACKGROUND = "menu_background.jpg";
    }

    public static class Tiles {
        public static final String ENVIRONMENT_ATLAS = "environment_atlas.png";
    }

    public static class Buttons {
        public static final String MENU_BUTTONS = "menu_buttons.png";
    }

    public static BufferedImage GetSpriteAtlas(String fileName) {
        // Method idea taken from stackOverflow and it basically just makes sure the image
        // with the specified fileName actually exists and whether or not it should be returned
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }

    public static BufferedImage[] GetLevelMaps() {
        // Gets the maps for every (manually countered) level and returns them in an array
        // of BufferedImages. Might try to make this work regardless of the size so that the
        // 4 isn't just a magic number but not currently sure how to do that.
        int numLevels = 4;
        BufferedImage[] imgs = new BufferedImage[numLevels];
        for (int i = 0; i < numLevels; i++) {
            Image temp = null;
            try {
                temp = ImageIO.read(new File("resources/lvls/" + (i + 1) + ".png"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            imgs[i] = (BufferedImage) temp;
        }
        return imgs;
    }
}

