package Levels;

import entities.Enemy;
import main.Game;
import org.w3c.dom.css.RGBColor;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static main.Game.*;
import static utilz.HelperMethods.*;
import static gameStates.Playing.*;
import static utilz.LoadSave.GetLevelWaves;

public class Level {
    private BufferedImage mapData;
    private BufferedImage bg;
    private Color[][] lvlMapData;
    private int lvlTilesWide;
    private int maxTilesOffset;
    private int maxLvlOffsetX;
    private Point enemySpawn;
    private Point playerSpawn;
    public static Tile[][] grid;
    private int startX, startY, tilesX, tilesY;
    private static float lvlScale;
    private ArrayList<Wave> waves;

    public Level (BufferedImage mapData, BufferedImage bg, float lvlScale) {
        this.mapData = mapData;
        this.bg = bg;
        this.lvlScale = lvlScale;
        acquireLevelData();
        createGrid();
        setGridBooleans();
    }

    private void acquireLevelData () {
        lvlMapData = GetLevelData(mapData);
        tilesX = lvlMapData.length;
        tilesY = lvlMapData[0].length;
//        tilesX = 20;
//        tilesY = 20;

        // Read in CSV file containing data for specific level.
        waves = GetLevelWaves(1);
//        waves = createWaves(waveData);
        startX = 675;
        startY = 125;
    }

    private void createGrid() {
        grid = new Tile[tilesX][tilesY];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[j][i] = new Tile(startX + (i - j) * TILES_WIDTH/2, startY + (i + j) * TILES_HEIGHT/2, TILES_WIDTH, TILES_HEIGHT, lvlScale);
            }
        }
    }

    private void setGridBooleans() {
        for (int i = 0; i < lvlMapData.length; i++)
            for (int j = 0; j < lvlMapData[0].length; j++) {
                 if (lvlMapData[i][j].getGreen() == 255)
                    grid[j][i].setBuildable(true);
                else if (lvlMapData[i][j].getBlue() == 255)
                    grid[j][i].setPath(true);
                else if (lvlMapData[i][j].getRGB() == 0)
                    grid[j][i].setEnd(true);
            }
    }

    public void drawGrid(Graphics g) {
        for (Tile[] row: grid)
            for (Tile tile : row)
                tile.draw(g);
    }

    public void printGrid() {
        System.out.println(grid[0][17].isPath());
    }

    public Point getPlayerSpawn() {
        return playerSpawn;
    }

    public Color getSpriteIndex(int x, int y) {
        return lvlMapData[x][y];
    }

    public Color[][] getLevelMapData() {
        return lvlMapData;
    }

    public int getLvlOffset() {
        return maxLvlOffsetX;
    }

    public BufferedImage getBgImg() {
        return bg;
    }

    public ArrayList<Enemy> getEnemies() {
        return null;
    }

    public ArrayList<Wave> getWaves() {
        return waves;
    }

    public Tile[][] getGrid() {
        return grid;
    }

    public Point getStartPos() {
        return new Point(startX, startY);
    }


    // What I need now is to have each level have a starting point for the grid, containing
    // an X value, Y Value, and then size of the array or # of tiles. Likely would be stored
    // alongside a bitmap.


    /*
    Level Data File
    Contains:
    - X and Y coordinate for top of grid, enemy spawn, and potentially player spawn.
    - A list of individual "waves" that are read in as such and contain things like
    - Enemy name (matches image file), number of enemies, wave length (in seconds)

    This level class must also have a queue to store enemies in for both drawing priority and
    targetting purposes.

    */

}
