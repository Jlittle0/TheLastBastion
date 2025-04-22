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
    private int lvlIndex = 0;

    public LevelHandler() {
        this.game =game;
        importOutsideSprites();
        levels =new ArrayList<Level>();
        buildAllLevels();
    }

    public void loadLevel(int index) {
        // Will read in a csv file that contains all the enemy information for the level.
        lvlIndex = index;
        Level newLevel = levels.get(lvlIndex);

        game.getPlaying().getEnemyManager().loadEnemies(newLevel);
        game.getPlaying().getPlayer().loadLvlData(newLevel.getLevelData());
        game.getPlaying().setMaxLevelOffset(newLevel.getLvlOffset());
        game.getPlaying().getObjectManager().loadObjects(newLevel);
    }

    private void buildAllLevels() {
        BufferedImage[] allLevels = LoadSave.GetLevelMaps();
        for (BufferedImage img : allLevels)
            levels.add(new Level(img));
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
    }

    public void draw(Graphics g, int lvloffset) {
        for (int j = 0; j < Game.TILES_IN_HEIGHT; j++)
            for (int i = 0; i < levels.get(lvlIndex).getLevelData()[0].length; i++) {
                int index =  levels.get(lvlIndex).getSpriteIndex(i, j);
                g.drawImage(levelSprite[index], Game.TILES_SIZE * i - lvloffset, Game.TILES_SIZE * j, Game.TILES_SIZE, Game.TILES_SIZE, null);
            }
    }

    public void update() {
        // Empty for now
    }

    public Level getCurrentLevel() {
        return  levels.get(lvlIndex);
    }

    public int getLevelAmount() {
        return levels.size();
    }


}
