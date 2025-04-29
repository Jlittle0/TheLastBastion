package Levels;

import main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static main.Game.*;
import static utilz.HelperMethods.*;
import static gameStates.Playing.*;

public class Level {
    private BufferedImage img;
    private Color[][] lvlData;
    private int lvlTilesWide;
    private int maxTilesOffset;
    private int maxLvlOffsetX;
    private Point enemySpawn;
    private Point playerSpawn;
    private Tile[][] grid;
    private int startX, startY, tilesX, tilesY;

    public Level (BufferedImage img) {
        this.img = img;
        acquireLevelData();
        createGrid();
        createEnemies();
        calculateOffsets();
        calculatePlayerSpawn();
    }

    private void acquireLevelData () {
        lvlData = GetLevelData(img);
        tilesX = lvlData.length;
        tilesY = lvlData[0].length;
        startX = 0;
        startY = 0;
    }

    private void createGrid() {
        grid = new Tile[tilesX][tilesY];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = new Tile(startX + (i - j) * TILES_WIDTH/2, startY - (i + j) * TILES_HEIGHT/2, TILES_WIDTH, TILES_HEIGHT);
            }
        }
    }

    public void drawGrid(Graphics g) {
        for (Tile[] row: grid)
            for (Tile tile : row)
                tile.draw(g);
    }

    private void calculateOffsets() {
        lvlTilesWide = img.getWidth();
        maxTilesOffset = lvlTilesWide - Game.TILES_IN_WIDTH;
        maxLvlOffsetX = maxTilesOffset * Game.TILES_SIZE;
    }

    private void calculatePlayerSpawn() {
        playerSpawn = GetPlayerSpawn(img);
    }

    private void createEnemies() {
//        crabs = GetCrabs(img);
//        worms = GetWorms(img);
//        shockers = GetShockers(img);
    }

    public Point getPlayerSpawn() {
        return playerSpawn;
    }

    public Color getSpriteIndex(int x, int y) {
        return lvlData[x][y];
    }

    public Color[][] getLevelData() {
        return lvlData;
    }

    public int getLvlOffset() {
        return maxLvlOffsetX;
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
