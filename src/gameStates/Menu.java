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

import static utilz.Constants.UI.Buttons.*;

public class Menu extends State implements Statemethods {

    private MenuButton[] buttons = new MenuButton[4];
    private BufferedImage backgroundImg;
    private int menuX, menuY, menuWidth, menuHeight;

    public Menu (Game game) {
        super(game);
        loadButtons();
        loadBackground();
    }

    private void loadBackground() {
        backgroundImg= LoadSave.GetSpriteAtlas(LoadSave.Backgrounds.MENU_BACKGROUND);
    }

    private void loadButtons() {
        // Manually setting all the buttons
        buttons[0] = new MenuButton((int)(Game.GAME_WIDTH / 2 - MB_DEFAULT_WIDTH * Game.SCALE), (int)(250 * Game.SCALE), 0, Gamestate.PLAYING);
        buttons[1] = new MenuButton(Game.GAME_WIDTH / 32 * 18, (int)(250 * Game.SCALE), 1, Gamestate.OPTIONS);
        buttons[2] = new MenuButton(Game.GAME_WIDTH / 32 * 14, (int)(320 * Game.SCALE), 3, Gamestate.TUTORIAL);
        buttons[3] = new MenuButton(Game.GAME_WIDTH / 32 * 18, (int)(320 * Game.SCALE), 2, Gamestate.QUIT);
    }

    public void update() {
        for (MenuButton mb : buttons) {
            mb.update();
        }
    }

    public void draw(Graphics g) {
        g.drawImage(backgroundImg, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);

        for (MenuButton mb : buttons) {
            mb.draw(g);
        }
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        for (MenuButton mb : buttons) {
            if (isIn(e, mb)) {
                mb.setMousePressed(true);
                break;
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
        for (MenuButton mb : buttons) {
            if (isIn(e, mb)) {
                if (mb.isMousePressed()) {
                    mb.applyGamestate();
                }
                break;
            }
        }
        resetButtons();
    }

    public void mouseMoved(MouseEvent e) {
        for (MenuButton mb : buttons)
            mb.setMouseOver(false);

        for (MenuButton mb : buttons)
            if (isIn(e, mb)) {
                mb.setMouseOver(true);
                break;
            }
    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }

    private void resetButtons() {
        for (MenuButton mb : buttons)
            mb.resetBools();
    }
}
