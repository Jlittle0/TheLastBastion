package Levels;

import main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static utilz.HelperMethods.*;

public class Level {
    private BufferedImage img;
    private int[][] lvlData;
    private int lvlTilesWide;
    private int maxTilesOffset;
    private int maxLvlOffsetX;
    private Point enemySpawn;
    private Point playerSpawn;

    public Level (BufferedImage img) {
        this.img = img;
        createLevelData();
        createEnemies();
        calculateOffsets();
        calculatePlayerSpawn();
    }

    private void acquireLevelData () {
        lvlData = GetLevelData(img);
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

    private void createLevelData() {
        lvlData = GetLevelData(img);
    }

    public Point getPlayerSpawn() {
        return playerSpawn;
    }

    public int getSpriteIndex(int x, int y) {
        return lvlData[y][x];
    }

    public int[][] getLevelData() {
        return lvlData;
    }

    public int getLvlOffset() {
        return maxLvlOffsetX;
    }




}
