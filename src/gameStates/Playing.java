package gameStates;

import Levels.Level;
import entities.EnemyManager;
import Levels.LevelHandler;
import main.Game;
import ui.*;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static utilz.LoadSave.Icon.*;


public class Playing extends State implements Statemethods {
//    private Player player;
    private LevelHandler levelHandler;
    private EnemyManager enemyManager;
    private PauseOverlay pauseOverlay;
    private GameOverOverlay gameOverOverlay;
    private LevelCompletedOverlay levelCompletedOverlay;
    private GameCompletedOverlay gameCompletedOverlay;
    private TowerOverlay towerOverlay;
    private boolean paused = false;
    private boolean firstClick;
    private int lastAttack;
    private long lastCheck;
    private BufferedImage backgroundImg;
    private boolean gameOver = false;
    private boolean lvlCompleted = false;
    private boolean gameCompleted = false;
    private int scale;

    public static int TILES_WIDTH, TILES_HEIGHT;

    public Playing(Game game) {
        super(game);
        loadBackground();
        initClasses();
        loadLevel();
    }

    private void loadBackground() {
        // Move all this stuff into the LevelHandler class later.
        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.Backgrounds.PLAYING_BACKGROUND);
        TILES_WIDTH = 32 * Game.GAME_WIDTH / backgroundImg.getWidth() + 1;
        TILES_HEIGHT = 16 * Game.GAME_HEIGHT / backgroundImg.getHeight() + 1;
    }

    private void loadLevel() {
        resetAll();
//        player.setSpawn(levelHandler.getCurrentLevel().getPlayerSpawn());
    }

    private void initClasses() {
        // Initializes all the classes etc. so that the constructor isn't so cluttered
        levelHandler = new LevelHandler(game);
        enemyManager = new EnemyManager(this);
        towerOverlay = new TowerOverlay(this);
//        player = new Player(200, 200, (int)(91 * Game.SCALE * PLAYER_SCALE), (int)(19 * Game.SCALE * PLAYER_SCALE), this);
//        player.loadLvlData(levelHandler.getCurrentLevel().getLevelData());
//        pauseOverlay = new PauseOverlay(this);
//        gameOverOverlay = new GameOverOverlay(this);
//        gameCompletedOverlay = new GameCompletedOverlay(this);
//        levelCompletedOverlay = new LevelCompletedOverlay(this);
    }

    public void update() {
        // Checks whether the state of the game has changed and what state should be updated
        // alongisde the standard playing state.
        if (paused) {
            pauseOverlay.update();
        } else if (lvlCompleted) {
            levelCompletedOverlay.update();
        } else if (gameOver){
            gameOverOverlay.update();
        } else {
            levelHandler.update();
            enemyManager.update();
            towerOverlay.update();
        }
    }

    public void draw(Graphics g) {
        // Draws the background
        g.drawImage(backgroundImg, 0, 0, (int)(Game.GAME_WIDTH), (int)(Game.GAME_HEIGHT), null);

        // Draws each individual part of the game in the order of level tiles, player, then enemies
        levelHandler.draw(g, 0);
        enemyManager.draw(g);

        g.setColor(new Color(0, 0, 0, 77));
        int squareSize = 100;
        g.fillRect(Game.GAME_WIDTH/2 - squareSize/2, 700, squareSize, squareSize);
        g.fillRect((int)(Game.GAME_WIDTH/2 - squareSize * 1.75), 700, squareSize, squareSize);
        g.fillRect((int)(Game.GAME_WIDTH/2 + squareSize * .75), 700, squareSize, squareSize);

        towerOverlay.draw(g);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.setColor(Color.WHITE);
        g.drawString("Wave 1", Game.GAME_WIDTH / 2 - 50, 50);

        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawImage(LoadSave.GetSpriteAtlas(HEART), 40, 25, 26, 24, null);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.setColor(Color.RED);
        g.drawString("50", 70, 42);
        g.drawImage(LoadSave.GetSpriteAtlas(GOLD), 40, 55, 26, 24, null);
        g.setColor(Color.YELLOW);
        g.drawString("200", 70, 72);
        g.drawImage(LoadSave.GetSpriteAtlas(CRYSTAL), 40, 85, 30, 30, null);
        g.setColor(Color.PINK);
        g.drawString("0", 70, 110);

        // Draws the tower icons
//        player.render(g);

        // Draw the paused or gameOver overlays depending on whether or not the game is paused and over
//        if (paused) {
//            g.setColor(new Color(0, 0, 0, 175));
//            g.fillRect(0, 0, game.GAME_WIDTH, game.GAME_HEIGHT);
//            pauseOverlay.draw(g);
//        }
//        else if (gameOver)
//            gameOverOverlay.draw(g);
//        else if (lvlCompleted)
//            levelCompletedOverlay.draw(g);
//        else if (gameCompleted)
//            gameCompletedOverlay.draw(g);
    }

    public void resetAll() {
        // resets everything
//        gameOver = false;
//        paused = false;
//        lvlCompleted = false;
//        playerDying = false;
//        player.resetAll();
//        enemyManager.resetAllEnemies();
//        objectManager.resetAllObjects();
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

    public Level getCurrentLevel() {
        return levelHandler.getCurrentLevel();
    }

    // For placing towers, what I think the best option would be is to have a boolean value
    // called isPlacing and whenever that value is true, have the draw method include
    // the grid as well as an image of the tower that snaps to each of the grid spots
    // based on the location of the mouse on the board. There will also be a little message
    // above the tower selection bar that says "press esc to leave tower placement"
    // which will then turn the boolean into a false value.
}
