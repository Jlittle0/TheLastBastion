package utilz;

import main.Game;

public class Constants {

    public static final int ANIMATION_SPEED = 35;

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
}
