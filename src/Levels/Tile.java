package Levels;

import java.awt.*;

public class Tile {
    private int x, y, width, height;
    private Polygon bounds;
    private boolean isOccupied, isBuildable, isPath, isEnd;
    private static float scale;

    public Tile(int x, int y, int width, int height, float scale) {
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.width = (int)(width * scale);
        this.height = (int)(height * scale);
        this.isOccupied = false;
        this.isBuildable = false;
        isPath = false;
        createBounds();
    }

    public Tile(int x, int y, int width, int height, float scale, boolean occupied, boolean buildable) {
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.width = (int)(width * scale);
        this.height = (int)(height * scale);
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

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.drawPolygon(bounds);
    }

    public boolean isIn(Point p) {
        return bounds.contains(p);
    }

    public boolean isPath() {
        return isPath;
    }

    public void setPath(boolean isPath) {
        this.isPath = isPath;
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

    public boolean getOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        this.isOccupied = occupied;
    }

    public boolean getBuildable() {
        return isBuildable;
    }

    public void setBuildable(boolean buildable) {
        this.isBuildable = buildable;
    }

    public boolean getEnd() {
        return isEnd;
    }

    public void setEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }

    public Polygon getBounds() {
        return bounds;
    }

    public void setBounds(Polygon bounds) {
        this.bounds = bounds;
    }

}
