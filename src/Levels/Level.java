package Levels;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static utilz.HelperMethods.*;

public class Level {
    private BufferedImage img;
    private int[][] lvlData;

    public Level (BufferedImage img) {
        this.img = img;
    }

    private void acquireLevelData () {
        lvlData = GetLevelData(img);
    }



}
