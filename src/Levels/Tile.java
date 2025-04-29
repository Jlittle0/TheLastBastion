package Levels;

import java.awt.*;

public class Tile {
    private int x, y, width, height;
    private Polygon bounds;
    private boolean isOccupied;
    private boolean isBuildable;

    public Tile(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.isOccupied = false;
        this.isBuildable = true;
        createBounds();
    }

    public Tile(int x, int y, int width, int height, boolean occupied, boolean buildable) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.isOccupied = occupied;
        this.isBuildable = buildable;
        createBounds();
    }

    public void createBounds() {
        // Left, top, right, bottom corners of diamond where x,y is the center.
        int[] xVals = new int[]{x - width/2, x, x + width/2, x};
        int[] yVals = new int[]{y, y + height/2, y, y-height/2};
        bounds = new Polygon(xVals, yVals, 4);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean getOccupied() { return isOccupied};

    public void setOccupied(boolean occupied) {
        this.isOccupied = occupied;
    }

    public boolean getBuildable() {
        return isBuildable;
    }

    public void setBuildable(boolean buildable) {
        this.isBuildable = buildable;
    }

    public Polygon getBounds() {
        return bounds;
    }

    public void setBounds(Polygon bounds) {
        this.bounds = bounds;
    }

}
