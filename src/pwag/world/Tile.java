package pwag.world;

import java.awt.image.BufferedImage;

/**
 * Data storage type for worldmaps. Contains a reference to the image this tile is supposed to display.
 */
public class Tile {

    private BufferedImage tile;

    public Tile(BufferedImage tile) {
        this.tile = tile;
    }

    public BufferedImage getTile() {
        return tile;
    }

}