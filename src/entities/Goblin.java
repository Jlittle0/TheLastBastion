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

    public Goblin(float x, float y) {
        super(x, y, GOBLIN_WIDTH, GOBLIN_HEIGHT, GOBLIN);
        // Change values
        initHitbox(22, 19);
    }

    public void update(int[][] lvlData, Player player) {
//        updateBehavior(lvlData, player);
        updateAnimationTick();
    }

//    private void updateBehavior(int[][] lvlData, Player player) {
//        if (firstUpdate)
//            firstUpdateCheck(lvlData);
//        if (inAir) {
//            updateInAir(lvlData);
//        } else {
//            // Patrol
//            switch (state) {
//                case IDLE:
//                    newState(RUNNING);
//                    break;
//                case RUNNING:
//                    if (canSeePlayer(lvlData, player)) {
//                        turnTowardsPlayer(player);
//                        if (isPlayerInAttackRange(player))
//                            newState(ATTACK);
//                    }
//                    move(lvlData);
//                    break;
//                case ATTACK:
//                    if (aniIndex == 0)
//                        attackChecked = false;
//                    if (aniIndex == 3 && !attackChecked)
//                        checkEnemyHit(attackBox, player);
//                    break;
//                case HIT:
//                    break;
//            }
//        }
//    }

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
