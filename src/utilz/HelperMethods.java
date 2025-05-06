package utilz;

import main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HelperMethods {

    // Red value = Base Tile
    // Green value = Decorative Tile
    // Blue value = Player Spawn
    public static Color[][] GetLevelData(BufferedImage img) {
        Color[][] lvlData = new Color[img.getWidth()][img.getHeight()];

        for (int i = 0; i < img.getWidth(); i++)
            for (int j = 0; j < img.getHeight(); j++) {
                Color color = new Color(img.getRGB(i, j));
                lvlData[i][j] = color;
            }
        return lvlData;
    }

    public static Point GetPlayerSpawn(BufferedImage img) {
        for (int j = 0; j < img.getHeight(); j++)
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getBlue();
                // Magic number currently for testing purposes
                if (value == 100)
                    return new Point(i * Game.TILES_SIZE, j * Game.TILES_SIZE);
            }
        return new Point(1 * Game.TILES_SIZE, 1 * Game.TILES_SIZE);
    }

    public static Point GetNextLocation(Color[][] lvlData, Point p) {
        // Check four neighboring tiles, return the coordinate of the one that makes sense.

        return null;
    }
}
