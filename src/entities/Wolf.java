package entities;

import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilz.Constants.Directions.*;
import static utilz.Constants.EnemyConstants.*;
import static utilz.Constants.EnemyConstants.Wolfs.*;

public class Wolf extends Enemy {
    private BufferedImage[][] wolfArr;

    public Wolf() {
        super(START.x, START.y, WOLF_WIDTH, WOLF_HEIGHT, WOLF);
        initHitbox(64, 64);
        loadEnemyImages();
    }

    private void loadEnemyImages() {
        // Get the index for this array by doing 4 * state (as there are 2) + direction (0-3)
        wolfArr = new BufferedImage[8][12];
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.Enemies.WOLF_SPRITE);
        for (int i = 0; i < wolfArr.length; i++)
            for (int j = 0; j < wolfArr[i].length; j++)
                wolfArr[i][j] = temp.getSubimage(j * WOLF_DEFAULT_WIDTH, i * WOLF_DEFAULT_HEIGHT, WOLF_DEFAULT_WIDTH, WOLF_DEFAULT_HEIGHT);
    }

    @Override
    public void update() {
        location = new Point((int)x, (int)y);
        updateBehavior();
        updateAnimationTick();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(wolfArr[this.getEnemyState() * 4 + walkDir][this.getAniIndex()], (int)(x - (WOLF_WIDTH - WOLF_DRAWOFFSET_X) / 2), (int)(y - (WOLF_HEIGHT - WOLF_DRAWOFFSET_Y) / 2), WOLF_WIDTH, WOLF_HEIGHT, null);
//        g.drawImage(wolfArr[this.getEnemyState() * 4 + walkDir][this.getAniIndex()], (int) this.getHitbox().x - WOLF_DRAWOFFSET_X, (int) this.getHitbox().y - WOLF_DRAWOFFSET_Y, WOLF_WIDTH, WOLF_HEIGHT, null);
    }

        private void updateBehavior() {
            switch (state) {
                case RUNNING:
                    move();
                    break;
                case HIT:
                    break;
            }
        }
}

