package ui;

import gameStates.Gamestate;
import gameStates.Playing;
import main.Game;

import java.awt.*;
import java.awt.event.MouseEvent;

import static utilz.Constants.TowerConstants.*;

public class TowerOverlay {
    private Playing playing;
    private TowerIcon[] towers;

    public TowerOverlay(Playing playing) {
        this.playing = playing;
        createTowerIcons();
    }

    public void createTowerIcons() {
        towers = new TowerIcon[3];
        int spacingX = 50 + TOWER_ICON_WIDTH;
        int towerX = (int)(Game.GAME_WIDTH / 2 - (TOWER_ICON_WIDTH  + spacingX) * 1.5) + 100;
        int towerY = 700;
        towers[0] = new TowerIcon(Game.GAME_WIDTH /2 - TOWER_ICON_WIDTH - 95, towerY, TOWER_ICON_WIDTH, TOWER_ICON_HEIGHT, 0);
        towers[1] = new TowerIcon(Game.GAME_WIDTH /2 - 30, towerY, TOWER_ICON_WIDTH, TOWER_ICON_HEIGHT, 1);
        towers[2] = new TowerIcon(Game.GAME_WIDTH /2 + 91, towerY, TOWER_ICON_WIDTH, TOWER_ICON_HEIGHT, 2);
    }

    public void update() {
        for (TowerIcon t : towers)
            t.update();
    }

    public void draw(Graphics g) {
        for (TowerIcon t : towers)
            t.draw(g);
    }

    public void mousePressed(MouseEvent e) {
        for (TowerIcon t : towers)
            if (isIn(e, t))
                t.setMousePressed(true);
        else {
            }
            // Disable overlay
    }

    public void mouseReleased(MouseEvent e) {
        // Basically empty for now

        for (TowerIcon t : towers)
            t.resetBools();
    }

    public void mouseMoved(MouseEvent e) {
        // Make sure all the mouseOver variables are reset
        for (TowerIcon t : towers)
            t.setMouseOver(false);

        // Checks whether the mouse is hovering over any of the buttons and if so set their
        // mouse over to true to allow them to change their index for the image.
        for (TowerIcon t : towers)
            if (isIn(e, t))
                t.setMouseOver(true);
    }


    private boolean isIn(MouseEvent e, TowerIcon b) {
        return b.getBounds().contains(e.getX(), e.getY());
    }
}
