package utilz;

import Levels.Wave;
import entities.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

import static utilz.Constants.WaveConstants.WAVE_VALS;

public class LoadSave {

    public static final String TOWERS = "towers.png";

    public static class Backgrounds {
        public static final String MENU_BACKGROUND = "title_menu.png";
        public static final String PLAYING_BACKGROUND = "playing_background.png";
        public static final String GAME_WON = "congratulations.png";
        public static final String LVLMAP = "lvlMaps/lvlMap.png";
    }

    public static class Icon {
        public static final String HEART = "heart.png";
        public static final String GOLD = "Gold.png";
        public static final String CRYSTAL = "Crystal.png";
    }

    public static class Tiles {
        public static final String ENVIRONMENT_ATLAS = "environment_atlas.png";
    }

    public static class Enemies {
        public static final String WOLF_SPRITE = "wolf_sprite.png";
    }

    public static class Buttons {
        public static final String MENU_BUTTONS = "menu_buttons.png";
    }

    public static BufferedImage GetSpriteAtlas(String fileName) {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }

    public static BufferedImage GetLevelMap(int index) {
            Image temp = null;
            try {
                temp = ImageIO.read(new File("resources/lvlsMaps/" + (index) + ".png"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            BufferedImage img = (BufferedImage)temp;
        return img;
    }

    public static BufferedImage GetLevelBg(int index) {
        Image temp = null;
        try {
            temp = ImageIO.read(new File("resources/lvlsBgs/" + (index) + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedImage img = (BufferedImage)temp;
        return img;
    }

    public static ArrayList<Wave> GetLevelWaves(int index) {
        String file = "resources/levels/" + (index) + "/";
        String line;
        BufferedReader br;
        ArrayList<ArrayList<String>> levelData = new ArrayList<>();
            try {
                int numFiles = new File("resources/levels/" + (index) + "/").list().length;
                for (int i = 0; i < numFiles; i++) {
                    ArrayList<String> data = new ArrayList<>();
                    br = new BufferedReader(new FileReader(file + "" + i + ".csv"));
                    while ((line = br.readLine()) != null) {
                        data.add(line);
                    }
                    levelData.add(data);
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        return CreateWaves(levelData);
    }

    public static ArrayList<Wave> CreateWaves(ArrayList<ArrayList<String>> levelData) {
        ArrayList<Wave> waves = new ArrayList<>();
        for (ArrayList<String> s : levelData) {
            ArrayList<Enemy> enemies = new ArrayList<>();
            for (String a : s) {
                switch (a) {
                    case "goblin":
                        enemies.add(new Goblin());
                        break;
                    case "orc":
                        enemies.add(new Orc());
                        break;
                    case "wolf":
                        enemies.add(new Wolf());
                        break;
                    case "Badger":
                        enemies.add(new Badger());
                        break;
                }
                waves.add(new Wave(enemies));
            }
        }
        return waves;
    }

    /*  Wave Class Parameters (atm)
    Enemy Type


    - Enemies + number of each
    - Time delay between each enemy spawning
    - Wave #
    * */

    // Method to read in a CSV file

    /*
    Level Data - Format
    {Enemy spawn location (X,Y), Path end location (X,Y), # of waves, duration in seconds}

    Format:
    Filename: Wave #
    {Enemy Name, Total # spawned, Interval between each spawn, Delay in seconds b4 start}

    Will attempt to create an enraged wave or mutated wave that causes the enemy stats to
    spike and result in the user needing to build in preparaton for it.



     */
}

