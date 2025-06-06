package main;

import gameStates.*;
import gameStates.Menu;
import ui.SoundControls;
import utilz.*;

import java.awt.*;

public class Game implements Runnable {
    // Private instance variables
    private GameViewer gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;

    private Playing playing;
    private Menu menu;
    private Options options;
    private Tutorial tutorial;
    private SoundControls soundControls;

    // Size of each "tile" used in the level maps in pixels
    public final static int TILES_DEFAULT_WIDTH = 32;
    public final static int TILES_DEFAULT_HEIGHT = 32;

    public final static float SCALE = 1.25f;

    // Desired number of visible tiles for game screen
    public final static int TILES_IN_WIDTH  = 26;
    public final static int TILES_IN_HEIGHT  = 14;

    public final static int TILES_SIZE = 32;

    // Found this on the internet to get the actual dimensions of the users screen.
    public final static GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    public final static GraphicsDevice gd = ge.getDefaultScreenDevice();
    public final static GraphicsConfiguration gc = gd.getDefaultConfiguration();
    public final static Rectangle screenBounds = gc.getBounds();

    public final static Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(gc);

    public final static int usableWidth = screenBounds.width - screenInsets.left - screenInsets.right;
    public final static int usableHeight = screenBounds.height - screenInsets.top - screenInsets.bottom;

    public final static int GAME_WIDTH =  usableWidth;
    public final static int GAME_HEIGHT = usableHeight;

    public Game() {
        initClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new GameViewer(gamePanel);
        gamePanel.setFocusable(true);
        gamePanel.requestFocus();
        startGameLoop();
    }

    private void initClasses() {
        soundControls = new SoundControls();
        menu = new Menu(this);
        options = new Options(this);
        playing = new Playing(this);
        tutorial = new Tutorial(this);
    }

    private void startGameLoop() {
        // Idea to run the game on a thread from StackOverview not sure if I'll need this but
        // better to have something and not need it than to need it and not be able to implement
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        // Updates the window based on the current state of the game and calls its own update
        switch (Gamestate.state) {
            case PLAYING:
                playing.update();
                break;
            case MENU:
                menu.update();
                break;
            case OPTIONS:
                options.update();
                break;
            case TUTORIAL:
                tutorial.update();
                break;
            case QUIT:
            default:
                System.exit(0);
                break;
        }
    }

    public void render(Graphics g) {
        // Same thing as update except redrawing
        switch (Gamestate.state) {
            case PLAYING:
                playing.draw(g);
                break;
            case MENU:
                menu.draw(g);
                break;
            case OPTIONS:
                options.draw(g);
                break;
            case TUTORIAL:
                tutorial.draw(g);
                break;
            default:
                break;
        }
    }

    @Override
    public void run() {
        // Find the time between each update and frame by using set FPS and UPS above
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate =  1000000000.0 / UPS_SET;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while(true) {
            long currentTime = System.nanoTime();

            // Sets the change in # of updates and frames between previous check
            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            // If the time between updates is greater than one update length, decrease by one and update
            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            // If the time between frames is greater than one time between each frame, repaint.
            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            // If more than a second has passed, reset frame and update count for next sec.
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                frames = 0;
                updates = 0;
            }
        }
    }




    public Menu getMenu() {
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }

    public Options getOptions() {
        return options;
    }

    public Tutorial getTutorial() {
        return tutorial;
    }

    public SoundControls getSoundControls() {
        return soundControls;
    }

    public void windowFocusLost() {
        if (Gamestate.state == Gamestate.PLAYING) {
            // Empty for now
        }

    }

}
