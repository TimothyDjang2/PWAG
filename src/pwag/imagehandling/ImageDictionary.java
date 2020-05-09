package pwag.imagehandling;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * Holds bufferedImages in memory so there only ever needs to be one instance of them.
 */
public class ImageDictionary {

    public static final BufferedImage MISSING = loadImage(ImagePaths.missing);

    public static class Tiles {
        public static final BufferedImage
            COBBLESTONE = loadImage(ImagePaths.Tiles.cobblestone),
            TILE = loadImage(ImagePaths.Tiles.tile);
    }

    public static class Entities {
        public static final BufferedImage
            PLAYER = loadImage(ImagePaths.Entities.player),
            HITBOX = loadImage(ImagePaths.Entities.hitbox);
    }



    private static BufferedImage loadImage(String filePath) {
        File file = new File(filePath);
        BufferedImage img = null;
        try {
            img = ImageIO.read(file);
        } catch(Exception e) {
            try {
                file = new File(ImagePaths.missing);
                img = ImageIO.read(file);
            } catch (Exception f) {
                System.out.println("The missing file texture is somehow missing. Panic now.");
            }
        }
        return img;
    }

}