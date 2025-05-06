package utilz;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {

    public static class Backgrounds {
        public static final String MENU_BACKGROUND = "title_menu.png";
        public static final String PLAYING_BACKGROUND = "playing_background.png";
        public static final String GAME_WON = "congratulations.png";
    }

    public static class Tiles {
        public static final String ENVIRONMENT_ATLAS = "environment_atlas.png";
    }

    public static class Buttons {
        public static final String MENU_BUTTONS = "menu_buttons.png";
    }

    public static BufferedImage GetSpriteAtlas(String fileName) {
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

    public static BufferedImage GetLevelMap(int index) {
            Image temp = null;
            try {
                temp = ImageIO.read(new File("resources/lvlsMaps/" + (index) + ".png"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            BufferedImage img = (BufferedImage)temp;
        return img;
    }

    public static BufferedImage GetLevelBg(int index) {
        Image temp = null;
        try {
            temp = ImageIO.read(new File("resources/lvlsBgs/" + (index) + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedImage img = (BufferedImage)temp;
        return img;
    }

    // Method to read in a CSV file

    /*
    Level Data - Format
    {Enemy spawn location (X,Y), Path end location (X,Y), # of waves, duration in seconds}

    Format:
    Filename: Wave #
    {Enemy Name, Total # spawned, Interval between each spawn, Delay in seconds b4 start}

    Will attempt to create an enraged wave or mutated wave that causes the enemy stats to
    spike and result in the user needing to build in preparaton for it.



     */
}

