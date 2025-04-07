package gameStates;

import gameStates.State;
import gameStates.Statemethods;
import main.Game;
import ui.MenuButton;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Menu extends State implements Statemethods {

    private MenuButton[] buttons = new MenuButton[4];
    private BufferedImage backgroundImg;
    private int menuX, menuY, menuWidth, menuHeight;

    public Menu (Game game) {
        super(game);
//        loadButtons();
        loadBackground();
    }

    private void loadBackground() {
        backgroundImg= LoadSave.GetSpriteAtlas(LoadSave.Backgrounds.MENU_BACKGROUND);
    }

    private void loadButtons() {
        // Manually setting all the buttons
        buttons[0] = new MenuButton(Game.GAME_WIDTH / 32 * 12, (int)(250 * Game.SCALE), 0, Gamestate.PLAYING);
        buttons[1] = new MenuButton(Game.GAME_WIDTH / 32 * 18, (int)(250 * Game.SCALE), 1, Gamestate.OPTIONS);
        buttons[2] = new MenuButton(Game.GAME_WIDTH / 32 * 12, (int)(320 * Game.SCALE), 3, Gamestate.TUTORIAL);
        buttons[3] = new MenuButton(Game.GAME_WIDTH / 32 * 18, (int)(320 * Game.SCALE), 2, Gamestate.QUIT);
    }

    public void update() {

    }

    public void draw(Graphics g) {

    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {

    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }
}
