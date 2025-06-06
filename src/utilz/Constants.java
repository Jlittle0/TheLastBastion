package utilz;

import main.Game;

public class Constants {

    public static final int ANIMATION_SPEED = 20;

    public static class UI {
        public static class Buttons {
            // Specifications for current buttons on menu
            public static final int MB_DEFAULT_WIDTH = 140;
            public static final int MB_DEFAULT_HEIGHT = 56;
            public static final int MB_WIDTH = (int)(MB_DEFAULT_WIDTH *  Game.SCALE);
            public static final int MB_HEIGHT = (int)(MB_DEFAULT_HEIGHT * Game.SCALE);
        }

        public static class OptionsButtons {
            public static final int OB_DEFAULT_WIDTH = 114;
            public static final int OB_DEFAULT_HEIGHT = 41;
            public static final int OB_WIDTH = (int)(OB_DEFAULT_WIDTH * Game.SCALE + 2);
            public static final int OB_HEIGHT = (int)(OB_DEFAULT_HEIGHT * Game.SCALE);
        }

        public static class PauseButtons {
            // Specifications for each sound button (42 pixels both width and height)
            public static final int SB_DEFAULT_SIZE = 42;
            public static final int SB_SIZE = (int)(SB_DEFAULT_SIZE * Game.SCALE);
        }

        public static class UrmButtons {
            // Specifications for the unpause, redo, and menu buttons (56 pixels both w and h)
            public static final int URM_DEFAULT_SIZE = 56;
            public static final int URM_SIZE = (int)(URM_DEFAULT_SIZE * Game.SCALE);
        }

        public static class VolumeButtons {
            // Dimensions for the volume button ontop of slider
            public static final int VOLUME_DEFAULT_WIDTH = 28;
            public static final int VOLUME_DEFAULT_HEIGHT = 44;
            // Height is the same for the slider as it is for the button so not specified again
            public static final int SLIDER_DEFAULT_WIDTH = 215;

            public static final int VOLUME_WIDTH = (int)(VOLUME_DEFAULT_WIDTH * Game.SCALE);
            public static final int VOLUME_HEIGHT = (int)(VOLUME_DEFAULT_HEIGHT * Game.SCALE);
            public static final int SLIDER_WIDTH = (int)(SLIDER_DEFAULT_WIDTH * Game.SCALE);
        }
    }

    public static class TowerConstants {

        public static final int TOWER_DEFAULT_WIDTH = 750;
        public static final int TOWER_DEFAULT_HEIGHT = 1063;

        public static final int TOWER_ICON_WIDTH = 60;
        public static final int TOWER_ICON_HEIGHT = 90;

    }

    public static class Directions {
        public static final int SW = 0;
        public static final int SE = 1;
        public static final int NW = 2;
        public static final int NE = 3;
    }

    public static class EnemyConstants {
        // Constants for enemy type
        public static final int GOBLIN = 0;
        public static final int ORC = 1;
        public static final int WOLF = 2;
        public static final int WOLF_ALPHA = 3;
        public static final int BADGER = 4;

        public static final int RUNNING = 0;
        public static final int DEAD = 1;
        public static final int HIT = 2;

        public static class Goblins {
            public static final float GOBLIN_SCALE = 1.0f;

            public static final int GOBLIN_DEFAULT_WIDTH = 10;
            public static final int GOBLIN_DEFAULT_HEIGHT = 10;
            public static final int GOBLIN_WIDTH = (int) (GOBLIN_DEFAULT_WIDTH * GOBLIN_SCALE * Game.SCALE);
            public static final int GOBLIN_HEIGHT = (int) (GOBLIN_DEFAULT_HEIGHT * GOBLIN_SCALE * Game.SCALE);

            public static final int GOBLIN_DRAWOFFSET_X = (int) (0 * Game.SCALE);
            public static final int GOBLIN_DRAWOFFSET_Y = (int) (0 * Game.SCALE);
        }

        public static class Orcs {
            public static final float ORC_SCALE = 1.0f;
            public static final int ORC = 1;

            public static final int ORC_DEFAULT_WIDTH = 10;
            public static final int ORC_DEFAULT_HEIGHT = 10;
            public static final int ORC_WIDTH = (int) (ORC_DEFAULT_WIDTH * ORC_SCALE * Game.SCALE);
            public static final int ORC_HEIGHT = (int) (ORC_DEFAULT_HEIGHT * ORC_SCALE * Game.SCALE);

            public static final int ORC_DRAWOFFSET_X = (int) (0 * Game.SCALE);
            public static final int ORC_DRAWOFFSET_Y = (int) (0 * Game.SCALE);
        }

        public static class Wolfs {
            public static final float WOLF_SCALE = 1.0f;


            public static final int WOLF_DEFAULT_WIDTH = 64;
            public static final int WOLF_DEFAULT_HEIGHT = 64;
            public static final int WOLF_WIDTH = (int) (WOLF_DEFAULT_WIDTH * WOLF_SCALE * Game.SCALE);
            public static final int WOLF_HEIGHT = (int) (WOLF_DEFAULT_HEIGHT * WOLF_SCALE * Game.SCALE);

            public static final int WOLF_DRAWOFFSET_X = (int) (18 * Game.SCALE * WOLF_SCALE);
            public static final int WOLF_DRAWOFFSET_Y = (int) (18 * Game.SCALE * WOLF_SCALE);
        }

        public static class Badgers {
            public static final float BADGER_SCALE = 0.5f;


            public static final int BADGER_DEFAULT_WIDTH = 64;
            public static final int BADGER_DEFAULT_HEIGHT = 64;
            public static final int BADGER_WIDTH = (int) (BADGER_DEFAULT_WIDTH * BADGER_SCALE * Game.SCALE);
            public static final int BADGER_HEIGHT = (int) (BADGER_DEFAULT_HEIGHT * BADGER_SCALE * Game.SCALE);

            public static final int BADGER_DRAWOFFSET_X = (int) (0 * Game.SCALE);
            public static final int BADGER_DRAWOFFSET_Y = (int) (0 * Game.SCALE);
        }

        // Returns the max health of an enemy
        public static int GetMaxHealth(int enemyType) {
            switch(enemyType) {
                case GOBLIN:
                    return 10;
                case ORC:
                    return 50;
                default:
                    return 1;
            }
        }

        public static int GetSpriteAmount(int enemyType, int enemyState) {
            switch (enemyType) {
                case GOBLIN:
                    switch (enemyState) {
                        case RUNNING:
                            return 6;
                        case HIT:
                            return 4;
                        case DEAD:
                            return 5;
                    }
                case ORC:
                    switch (enemyState) {
                        case RUNNING:
                            return 5;
                        case HIT:
                            return 3;
                        case DEAD:
                            return 0;
                    }
                case WOLF:
                    switch (enemyState) {
                        case RUNNING:
                            return 8;
                        case DEAD:
                            return 12;
                    }
            }
            return 0;
        }
    }

    public static class WaveConstants {
        public static int WAVE_VALS = 10;
    }
}
