package ui;

import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilz.Constants.TowerConstants.*;
import static utilz.Constants.UI.UrmButtons.URM_DEFAULT_SIZE;
import static utilz.Constants.UI.UrmButtons.URM_SIZE;

public class TowerIcon extends Button {
    private BufferedImage[] imgs;
    private int rowIndex, index;
    private boolean mouseOver, mousePressed;

    public TowerIcon (int x, int y, int width, int height, int rowIndex) {
        super(x, y, width, height);
        this.rowIndex = rowIndex;
        loadImgs();
    }

    private void loadImgs() {
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.TOWERS);
        imgs = new BufferedImage[3];
        for (int i = 0; i < imgs.length; i++)
            imgs[i] = temp.getSubimage(i * TOWER_DEFAULT_WIDTH, 0, TOWER_DEFAULT_WIDTH, TOWER_DEFAULT_HEIGHT);
    }

    public void update() {
        index = rowIndex;
    }

    public void draw(Graphics g) {
        g.drawImage(imgs[index], x, y, TOWER_ICON_WIDTH, TOWER_ICON_HEIGHT, null);
    }

    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }
}
