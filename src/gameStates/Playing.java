package gameStates;

import entities.EnemyManager;
import entities.Player;
import Levels.LevelHandler;
import main.Game;
import Towers.TowerManager;
import ui.GameCompletedOverlay;
import ui.GameOverOverlay;
import ui.LevelCompletedOverlay;
import ui.PauseOverlay;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.PlayerConstants.PLAYER_SCALE;


public class Playing extends State implements Statemethods {
    private Player player;
    private LevelHandler levelHandler;
    private EnemyManager enemyManager;
    private PauseOverlay pauseOverlay;
    private GameOverOverlay gameOverOverlay;
    private LevelCompletedOverlay levelCompletedOverlay;
    private GameCompletedOverlay gameCompletedOverlay;
    private boolean paused = false;
    private boolean firstClick;
    private int lastAttack;
    private long lastCheck;
    private BufferedImage backgroundImg;
    private boolean gameOver = false;
    private boolean lvlCompleted = false;
    private boolean gameCompleted = false;

    public Playing(Game game) {
        super(game);
        initClasses();
        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.PLAYING_BACKGROUND_IMG);
        calcLvlOffset();
        loadStartLevel();
    }

    public void loadNextLevel() {
        resetAll();
        levelHandler.loadNextLevel();
        player.setSpawn(levelHandler.getCurrentLevel().getPlayerSpawn());
    }

    private void loadStartLevel() {
        enemyManager.loadEnemies(levelHandler.getCurrentLevel());
        objectManager.loadObjects(levelHandler.getCurrentLevel());
    }

    private void calcLvlOffset() {
        maxLvlOffsetX = levelHandler.getCurrentLevel().getLvlOffset();
    }

    private void initClasses() {
        // Initializes all the classes etc. so that the constructor isn't so cluttered
        levelHandler = new LevelHandler(game);
        enemyManager = new EnemyManager(this);
        objectManager = new ObjectManager(this);
        player = new Player(200, 200, (int)(91 * Game.SCALE * PLAYER_SCALE), (int)(19 * Game.SCALE * PLAYER_SCALE), this);
        player.loadLvlData(levelHandler.getCurrentLevel().getLevelData());
        player.setSpawn(levelHandler.getCurrentLevel().getPlayerSpawn());
        pauseOverlay = new PauseOverlay(this);
        gameOverOverlay = new GameOverOverlay(this);
        gameCompletedOverlay = new GameCompletedOverlay(this);
        levelCompletedOverlay = new LevelCompletedOverlay(this);
    }

    public void update() {
        // Checks whether the state of the game has changed and what state should be updated
        // alongisde the standard playing state.
        if (paused) {
            pauseOverlay.update();
        } else if (lvlCompleted) {
            levelCompletedOverlay.update();
        } else if (gameOver){
//            gameOverOverlay.update();
        } else if (playerDying) {
            player.update();
        }else {
            levelHandler.update();
            towerManager.update(levelHandler.getCurrentLevel().getLevelData(), player);
            player.update();
            enemyManager.update(levelHandler.getCurrentLevel().getLevelData(), player);
        }
    }

    public void draw(Graphics g) {
        // Draws the background
        g.drawImage(backgroundImg, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);

        // Draws each individual part of the game in the order of level tiles, player, then enemies
        levelHandler.draw(g, xLvlOffset);
        objectManager.draw(g, xLvlOffset);
        enemyManager.draw(g, xLvlOffset);
        player.render(g, xLvlOffset);

        // Draw the paused or gameOver overlays depending on whether or not the game is paused and over
        if (paused) {
            g.setColor(new Color(0, 0, 0, 175));
            g.fillRect(0, 0, game.GAME_WIDTH, game.GAME_HEIGHT);
            pauseOverlay.draw(g);
        }
        else if (gameOver)
            gameOverOverlay.draw(g);
        else if (lvlCompleted)
            levelCompletedOverlay.draw(g);
        else if (gameCompleted)
            gameCompletedOverlay.draw(g);
    }

    public void resetAll() {
        // resets everything
        gameOver = false;
        paused = false;
        lvlCompleted = false;
        playerDying = false;
        player.resetAll();
        enemyManager.resetAllEnemies();
        objectManager.resetAllObjects();
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {

    }

    public void mouseDragged(MouseEvent e) {

    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }
}
