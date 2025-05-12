package Levels;

import main.Game;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class LevelHandler {

    private Game game;
    private BufferedImage[] levelSprite;
    private ArrayList<Level> levels;
    private Level currentLevel = new Level(LoadSave.GetSpriteAtlas(LoadSave.Backgrounds.LVLMAP), null, 1.0f);
    private int lvlIndex = 0;
    private int lvlScale;

    public LevelHandler(Game game) {
        this.game = game;
        levels = new ArrayList<Level>();
        importOutsideSprites();
        buildLevel();
    }

    public void loadLevel(int index) {
        // Will read in a csv file that contains all the enemy information for the level.
        lvlIndex = index;
        Level newLevel = levels.get(lvlIndex);

//        game.getPlaying().getEnemyManager().loadEnemies(newLevel);
//        game.getPlaying().getPlayer().loadLvlData(newLevel.getLevelData());
//        game.getPlaying().setMaxLevelOffset(newLevel.getLvlOffset());
//        game.getPlaying().getObjectManager().loadObjects(newLevel);
    }

    private void buildLevel() {
//        BufferedImage mapData = LoadSave.GetLevelMap(lvlIndex);
//        BufferedImage lvlBg = LoadSave.GetLevelBg(lvlIndex);
//        Level currentLevel = new Level(mapData, lvlBg, lvlScale);
    }

    private void importOutsideSprites() {
        // Imports the tile assets for each level, currently only one image
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.Tiles.ENVIRONMENT_ATLAS);
        levelSprite = new BufferedImage[121];
        for (int j = 0; j < 11; j++)
            for (int i = 0; i < 11; i++) {
                int index = j * 11 + i;
                levelSprite[index] = img.getSubimage(i * 32, j * 32, 32, 32);
            }
        lvlScale = 0;
    }

    public void draw(Graphics g, int lvloffset) {
//        for (int i = 0; i < currentLevel.getLevelMapData().length; i++)
//            for (int j = 0; j < currentLevel.getLevelMapData()[0].length; j++) {
//                Color data = levels.get(lvlIndex).getSpriteIndex(i, j);
//                // Draws the base tiles using red value
//                g.drawImage(levelSprite[data.getRed()], Game.TILES_SIZE * i - lvloffset, Game.TILES_SIZE * j, Game.TILES_SIZE, Game.TILES_SIZE, null);
//                // Draws any decoration on tile using green value
//                g.drawImage(levelSprite[data.getGreen()], Game.TILES_SIZE * i - lvloffset, Game.TILES_SIZE * j, Game.TILES_SIZE, Game.TILES_SIZE, null);
//            }
//        currentLevel.drawGrid(g);
    }

    public void update() {
        // Empty for now
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public int getLevelAmount() {
        return levels.size();
    }


}
