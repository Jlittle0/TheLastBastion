package entities;

import gameStates.Playing;
import main.Game;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static utilz.Constants.EnemyConstants.*;
import static utilz.Constants.Directions.*;
import static utilz.Constants.EnemyConstants.Goblins.*;

public class Goblin extends Enemy {
    private int attackBoxOffsetX;

    public Goblin() {
        super(0, 0, GOBLIN_WIDTH, GOBLIN_HEIGHT, GOBLIN);
        initHitbox(0, 0);
    }

    public Goblin(float x, float y) {
        super(x, y, GOBLIN_WIDTH, GOBLIN_HEIGHT, GOBLIN);
        // Change values
        initHitbox(22, 19);
    }

    public void update() {
//        updateBehavior(lvlData, player);
        updateAnimationTick();
    }

    public int flipX() {
        // Flips the direction of the crab in the X direction for movement
        if (walkDir == SW)
            return width;
        return 0;
    }

    public int flipW() {
        // Flips the width of the crab so that it appears to be moving the other direction
        if (walkDir == SE)
            return -1;
        return 1;
    }
}
