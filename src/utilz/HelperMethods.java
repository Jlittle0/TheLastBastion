package utilz;

import Levels.Tile;
import main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;

import static Levels.Level.grid;
import static gameStates.Playing.TILES_HEIGHT;
import static gameStates.Playing.TILES_WIDTH;
import static utilz.Constants.Directions.*;

public class HelperMethods {
    // Red value = Base Tile
    // Green value = Decorative Tile
    // Blue value = Player Spawn
    public static Color[][] GetLevelData(BufferedImage img) {
        Color[][] lvlData = new Color[img.getWidth()][img.getHeight()];

        for (int i = 0; i < img.getWidth(); i++)
            for (int j = 0; j < img.getHeight(); j++) {
                Color color = new Color(img.getRGB(i, j));
                lvlData[i][j] = color;
            }
        return lvlData;
    }

    public static int[] GetCurrentTile(float x, float y, int[] prevTile) {
        // Reverse-engineer coordinates using x,y location
        Point start = new Point(675, 125);
        int row = Math.round((y - start.y) / TILES_HEIGHT + (x - start.x) / TILES_WIDTH);
        int col = Math.round((y - start.y) / TILES_HEIGHT - (x - start.x) / TILES_WIDTH);

        // If the x,y location isn't within the bounds of the current grid index, check neighbors.
        int[] index = checkNeighbors(new Point((int)x,(int)y), row, col, prevTile);

        return index;
    }

    private static int[] checkNeighbors(Point p, int row, int col, int[] prevTile) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return null;
            }
            int rows = grid.length;
            int cols = grid[0].length;

            // Check if starting position is valid
            if (row < 0 || row >= rows || col < 0 || col >= cols) {
                return null;
            }

            // Check if starting position has target value
            if (grid[row][col].isIn(p)) {
                return new int[]{row, col};
            }

            // Queue for BFS
            Queue<int[]> queue = new LinkedList<>();
            boolean[][] visited = new boolean[rows][cols];

            // Add starting position to queue
            queue.offer(new int[]{row, col});
            visited[row][col] = true;

            // Possible directions: up, right, down, left
            int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

            while (!queue.isEmpty()) {
                int[] current = queue.poll();
                row = current[0];
                col = current[1];

                // Check all four neighbors
                for (int[] dir : directions) {
                    int newRow = row + dir[0];
                    int newCol = col + dir[1];

                    // Check if new position is within bounds
                    if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                        // Check if position hasn't been visited
                        if (!visited[newRow][newCol]) {
                            // Check if we found the target value
                            if (grid[newRow][newCol].isIn(p) || grid[newRow][newCol].isIn(new Point(p.x +10, p.y))) {
                                return new int[]{newRow, newCol};
                            }
                            // Add to queue and mark as visited
                            queue.offer(new int[]{newRow, newCol});
                            visited[newRow][newCol] = true;
                        }
                    }
                }
            }

            // Target value not found
        System.out.println("error 3");
            return null;
    }

    public static int[] GetNextLocation (int[] pos, int walkDir) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return null;
        }
        int rows = grid.length;
        int cols = grid[0].length;

        int row = pos[0];
        int col = pos[1];
        // Check four neighboring tiles, return the coordinate of the one that makes sense.
        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        // Check all four neighbors
        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                if (grid[newRow][newCol].isPath()) {
                    // Check if previous or next
                    if (newRow > row && walkDir != NE)
                        return new int[]{newRow, newCol};
                    if (newRow < row && walkDir != SW)
                        return new int[]{newRow, newCol};
                    if (newCol > col && walkDir != NW)
                        return new int[]{newRow, newCol};
                    if (newCol < col && walkDir != SE)
                        return new int[]{newRow, newCol};
                }
            }
        }
        System.out.println();
        return null;
    }
}
