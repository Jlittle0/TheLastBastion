package utilz;

import main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HelperMethods {

    // Red value = Base Tile
    // Green value = Decorative Tile
    // Blue value = Tower
    public static int[][] GetLevelData(BufferedImage img) {
        int[][] lvlData = new int[img.getHeight()][img.getWidth()];

        for (int j = 0; j < img.getHeight(); j++)
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getRed();
                if (value >= 121)
                    value = 0;
                lvlData[j][i] = value;
            }
        return lvlData;
    }

    public static int[][] GetTowerData(BufferedImage img) {
        int[][] towerData = new int[img.getHeight()][img.getWidth()];

        for (int j = 0; j < img.getHeight(); j++)
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getBlue();
                if (value >= 121)
                    value = 0;
                towerData[j][i] = value;
            }
        return towerData;
    }

    public static Point GetPlayerSpawn(BufferedImage img) {
        for (int j = 0; j < img.getHeight(); j++)
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getGreen();
                // Magic number currently for testing purposes
                if (value == 100)
                    return new Point(i * Game.TILES_SIZE, j * Game.TILES_SIZE);
            }
        return new Point(1 * Game.TILES_SIZE, 1 * Game.TILES_SIZE);
    }
}
