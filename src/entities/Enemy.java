package entities;

import gameStates.Playing;
import main.Game;
import utilz.Constants;

import java.awt.geom.Rectangle2D;

import static utilz.Constants.ANIMATION_SPEED;
import static utilz.Constants.EnemyConstants.*;
import static utilz.Constants.Directions.*;
import static utilz.HelperMethods.*;

public class Enemy extends Entity {
    protected int enemyType;
    protected boolean firstUpdate = true;
    protected int walkDir = SE;
    protected boolean active = true;

    public Enemy(float x, float y, int width, int height, int enemyType) {
        super(x, y, width, height);
        this.enemyType = enemyType;
        maxHealth = GetMaxHealth(enemyType);
        currentHealth = maxHealth;
        setWalkSpeed();
    }

    private void setWalkSpeed() {
        switch (enemyType) {
            case GOBLIN:
                walkSpeed = Game.SCALE * 0.2f;
                break;
            case ORC:
                walkSpeed = Game.SCALE * 0.1f;
                break;
            case WOLF:
                walkSpeed = Game.SCALE * 0.35f;
                break;
            default:
                walkSpeed = Game.SCALE * 0.35f;
        }
    }

    protected void move(int[][] lvlData) {
        // Moves the enemy
        float xSpeed = 0;
        float ySpeed = 0;
        if (walkDir == SW || walkDir == NW)
            xSpeed = -walkSpeed;
        else
            xSpeed = walkSpeed;
        if (walkDir == SW || walkDir == SE)
            ySpeed = -walkSpeed;
        else
            ySpeed = walkSpeed;
        findNextLocation();



        if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData))
            if (IsFloor(hitbox, xSpeed, lvlData)) {
                hitbox.x += xSpeed;
                return;
            }
        changeWalkDir();
    }

    protected void turnTowardsPlayer(Player player) {
        // Changes the direction of the enemy to face the player
        if (player.hitbox.x > hitbox.x) {
            walkDir = RIGHT;
        }
        else
            walkDir = LEFT;
    }

    protected boolean canSeePlayer(int[][] lvlData, Player player) {
        // Checks whether or not there's an object obstructing enemies line of vision to the player
        // which would prevent them from following the player.
        int playerTileY = (int)(player.getHitbox().y / Game.TILES_SIZE);
        if (playerTileY == tileY)
            if (isPlayerInRange(player)) {
                if (IsSightClear(lvlData, hitbox, player.hitbox, tileY))
                    return true;
            }
        return false;
    }

    // Viewing Range
    protected boolean isPlayerInRange(Player player) {
        // Checks if they player is in range for viewing and tracking the player
        int absValue =(int)Math.abs(player.hitbox.x - hitbox.x);
        return absValue <= attackDistance * 5;
    }

    // Attacking Range
    protected boolean isPlayerInAttackRange(Player player) {
        // Checks if the player is within the set attack range for each enemy
        int absValue =(int)Math.abs(player.hitbox.x - hitbox.x);
        return absValue <= attackDistance;
    }

    protected void newState(int state) {
        // Applies a new state to the enemy
        this.state = state;
        aniTick = 0;
        aniIndex = 0;
    }

    public void hurt(int amount) {
        // Hurts the enemy to simulate taking damage
        currentHealth -= amount;
        if (currentHealth <= 0) {
            currentHealth = 0;
            if (enemyType == SHOCKER)
                newState(Constants.BossConstants.DEAD);
            else
                newState(DEAD);
        } else
        if (enemyType == SHOCKER)
            newState(Constants.BossConstants.HIT);
        else
            newState(HIT);
    }

    protected void checkEnemyHit(Rectangle2D.Float attackBox, Player player) {
        if (attackBox.intersects(player.hitbox))
            player.changeHealth(-GetEnemyDamage(enemyType) * player.getPlaying().getDifficulty());
        attackChecked = true;
    }

    protected void updateAnimationTick() {
        if (enemyType != SHOCKER) {
            aniTick++;
            if (aniTick >= ANIMATION_SPEED) {
                aniTick = 0;
                aniIndex++;
                if (aniIndex >= GetSpriteAmount(enemyType, state)) {
                    aniIndex = 0;
                    switch (state) {
                        case ATTACK:
                        case HIT:
                            state = IDLE;
                            break;
                        case DEAD:
                            active = false;
                    }
                }
            }
        }
        // This is a massive waste but I wanted to make sure boss worked and the states didn't
        // allign in terms of the order of their sprite atlas' and I didn't want to spend time fixing
        else {
            aniTick++;
            if (aniTick >= ANIMATION_SPEED) {
                aniTick = 0;
                aniIndex++;
                if (aniIndex >= Constants.BossConstants.GetSpriteAmount(enemyType, state)) {
                    aniIndex = 0;
                    switch (state) {
                        case Constants.BossConstants.ATTACK_2:
                            if (walkDir == RIGHT)
                                hitbox.x += 40 * Game.SCALE * SHOCKER_SCALE;
                            else
                                hitbox.x -= 40 * Game.SCALE * SHOCKER_SCALE;
                        case Constants.BossConstants.ATTACK_1:
                        case Constants.BossConstants.ATTACK_3:
                        case Constants.BossConstants.HIT:
                            state = IDLE;
                            break;
                        case Constants.BossConstants.DEAD:
                            active = false;
                            break;

                    }
                }
            }
        }
    }

    protected void changeWalkDir() {
        if (walkDir == LEFT)
            walkDir = RIGHT;
        else
            walkDir = LEFT;
    }

    public void resetEnemy() {
        hitbox.x = x;
        hitbox.y = y;
        firstUpdate = true;
        currentHealth = maxHealth;
        newState(IDLE);
        active = true;
        airSpeed = 0;
    }

    public boolean isActive() {
        return active;
    }

}
