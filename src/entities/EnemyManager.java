package entities;

import Levels.Level;
import Levels.Wave;
import gameStates.*;
import utilz.LoadSave;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static utilz.Constants.EnemyConstants.*;
import static utilz.Constants.EnemyConstants.Wolfs.*;

public class EnemyManager {
    private Playing playing;
    private BufferedImage[][] wolfArr;
    private ArrayList<Wave> waves = new ArrayList<Wave>();
    private ArrayList<Enemy> enemies;
    private int currentWaveIndex = 0;
    private Wave currentWave;
    private Level currentLevel;

    public EnemyManager(Playing playing) {
        this.playing = playing;
        currentLevel = playing.getCurrentLevel();
        loadWaves(currentLevel);
        loadEnemyImages();
        loadEnemies();
    }

    public void loadWaves(Level level) {
        waves = level.getWaves();
        currentWave = waves.get(currentWaveIndex);
    }

    public void loadEnemies() {
        enemies = currentWave.getEnemies();
    }

    private void loadEnemyImages() {
        // Get the index for this array by doing 4 * state (as there are 2) + direction (0-3)
        wolfArr = new BufferedImage[8][12];
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.Enemies.WOLF_SPRITE);
        for (int i = 0; i < wolfArr.length; i++)
            for (int j = 0; j < wolfArr[i].length; j++)
                wolfArr[i][j] = temp.getSubimage(j * WOLF_DEFAULT_WIDTH, i * WOLF_DEFAULT_HEIGHT, WOLF_DEFAULT_WIDTH, WOLF_DEFAULT_HEIGHT);
    }

    public void update() {
        boolean isAnyActive = false;

        for (Enemy e : enemies) {
            if (e.isActive()) {
                e.update();
                isAnyActive = true;
            }
            if (!isAnyActive)
                currentWave.setWaveCompleted();
        }
    }

    public void draw(Graphics g) {
        drawEnemies(g);
    }

    public void drawEnemies(Graphics g) {
        for (Enemy e : enemies)
            if (e.isActive()) {
                e.draw(g);
//                g.drawImage(wormArr[e.getEnemyState()][e.getAniIndex()], (int) e.getHitbox().x - WORM_DRAWOFFSET_X - xLvlOffset + w.flipX(), (int) w.getHitbox().y - WORM_DRAWOFFSET_Y, WORM_WIDTH * w.flipW(), WORM_HEIGHT, null);
            }
    }

}
