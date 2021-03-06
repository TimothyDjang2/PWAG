package pwag;

public class Constants {

    public static final class WINDOW {
        public static final int
            WIDTH = 480, //Toolkit.getDefaultToolkit().getScreenSize().width,
            HEIGHT = 360, //Toolkit.getDefaultToolkit().getScreenSize().height,
            TILE_WIDTH = 30, // amount of tiles to render in the window.
            TILE_HEIGHT = 22;
    }

    public static final class RENDERING {
        public static final int
            TILE_SIZE = 16, // How big (px) to render tiles at scale 1x. Since tiles are square, there's only one number here.
            PLAYER_SIZE = 16; // How big (px) to render the player. Not really sure where I should put this yet. Makes the hitbox go.
    }

}