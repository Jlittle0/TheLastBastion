package Levels;

import entities.Enemy;

import java.util.ArrayList;

public class Wave {
    private boolean waveCompleted = false;
    private boolean isActive = false;
    private ArrayList<Enemy> enemies;

    public Wave(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void setWaveCompleted () {
        waveCompleted = true;
        isActive = false;
    }

    public void setActive() {
        isActive = true;
    }
}
