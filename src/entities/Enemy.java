package entities;

import gameStates.Playing;
import main.Game;
import utilz.Constants;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static gameStates.Playing.TILES_HEIGHT;
import static gameStates.Playing.TILES_WIDTH;
import static main.Game.TILES_SIZE;
import static utilz.Constants.ANIMATION_SPEED;
import static utilz.Constants.EnemyConstants.*;
import static utilz.Constants.Directions.*;
import static utilz.HelperMethods.*;

public class Enemy extends Entity {
    protected int enemyType;
    protected boolean firstUpdate = true;
    protected int walkDir = SE;
    protected boolean active = true;
    protected Point location;
    protected int[] prevTile = new int[]{0,0};
    // Move to playing class.
    protected static final Point START = new Point(175, 385);
    protected static final Point END = new Point(0,0);


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
                walkSpeed = 0.5f * Game.SCALE;
                break;
            case ORC:
                walkSpeed = Game.SCALE * 0.5f;
                break;
            case WOLF:
                walkSpeed = 1.0f * Game.SCALE;
                break;
            default:
                walkSpeed = Game.SCALE * 0.35f;
        }
    }

    protected void move() {
        int aniFrames = ANIMATION_SPEED * GetSpriteAmount(enemyType, state);

        int nextTile = findNextTile();
        changeWalkDir(nextTile);

        float xSpeed = (walkSpeed * TILES_WIDTH/2) / ((aniFrames) + 0.0f);
        float ySpeed = (walkSpeed * TILES_HEIGHT/2) / ((aniFrames) + 0.0f);

        switch (walkDir) {
            case SW:
                xSpeed = -xSpeed;
                break;
            case SE:
                break;
            case NW:
                xSpeed = -xSpeed;
            case NE:
                ySpeed = -ySpeed;
                break;
        }

        x += xSpeed ;
        y += ySpeed;
    }

    private int findNextTile() {
        int direction = walkDir;
        Point startPos = new Point(675, 122);
        int[] currentTile = GetCurrentTile(x, y, prevTile);
        int[] nextTile = GetNextLocation(currentTile, walkDir);
        if (prevTile != nextTile || prevTile != currentTile)
            direction = getNextDirection(currentTile, nextTile, walkDir);
//        System.out.println("Previous - row: " + prevTile[0] + " col: " + prevTile[1]);
//        System.out.println("Current - row: " + currentTile[0] + " col: " + currentTile[1]);
//        System.out.println("Next - row: " + nextTile[0] + " col: " + nextTile[1]);
        prevTile = currentTile;
        return direction;
    }

    private int getNextDirection(int[] currentTile, int[] nextTile, int direction) {
        if (nextTile[0] > currentTile[0])
            return SW;
        if (nextTile[0] < currentTile[0])
            return NE;
        if (nextTile[1] > currentTile[1])
            return SE;
        if (nextTile[1] < currentTile[1])
            return NW;
        return direction;
    }

    protected boolean isAtEnd() {
        if (this.getLocation() == END)
            return true;
        return false;
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
            newState(DEAD);
        } else
            newState(HIT);
    }

    protected void updateAnimationTick() {
        aniTick++;
        if (aniTick >= ANIMATION_SPEED) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(enemyType, state)) {
                aniIndex = 0;
                switch (state) {
                    case HIT:
                        state = RUNNING;
                        break;
                    case DEAD:
                        active = false;
                }
            }
        }
    }

    protected void changeWalkDir(int nextTileDir) {
        walkDir = nextTileDir;
    }

    public boolean isActive() {
        return active;
    }

    public Point getLocation() {
        return location;
    }

    public void update() {}

    public void draw(Graphics g) {
    }

}
