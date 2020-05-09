package pwag.world;

import java.awt.image.BufferedImage;

/**
 * Data storage type for worldmaps. Contains a reference to the image this tile is supposed to display.
 */
public class Tile {

    private BufferedImage tile;
    private boolean isWall; // Can the player walk through this tile, or not?

    public Tile(BufferedImage tile, boolean isWall) {
        this.tile = tile;
        this.isWall = isWall;
    }

    public BufferedImage getTile() {
        return tile;
    }

    public boolean isWall() {
        return isWall;
    }

    public void setWall(boolean state) {
        isWall = state;
    }

}