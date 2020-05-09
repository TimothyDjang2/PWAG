package pwag;

import pwag.imagehandling.ImageDictionary;
import pwag.world.Tile;
import pwag.world.WorldGen;

/**
 * Base class that runs all the loops and has the main method in it.
 */
public class Core {

    /*private static Tile[][] worldArr = {
        {new Tile(ImageDictionary.Tiles.TILE, false), new Tile(ImageDictionary.Tiles.TILE, false), new Tile(ImageDictionary.Tiles.TILE, false), new Tile(ImageDictionary.Tiles.COBBLESTONE, false)},
        {new Tile(ImageDictionary.Tiles.COBBLESTONE, false), new Tile(ImageDictionary.Tiles.TILE, false), new Tile(ImageDictionary.Tiles.TILE, false), new Tile(ImageDictionary.Tiles.TILE, false)},
        {new Tile(ImageDictionary.Tiles.TILE, false), new Tile(ImageDictionary.Tiles.COBBLESTONE, false), new Tile(ImageDictionary.Tiles.COBBLESTONE, false), new Tile(ImageDictionary.Tiles.COBBLESTONE, false)},
        {new Tile(ImageDictionary.Tiles.COBBLESTONE, false), new Tile(ImageDictionary.Tiles.COBBLESTONE, false), new Tile(ImageDictionary.Tiles.TILE, false), new Tile(ImageDictionary.Tiles.TILE, false)}
    };*/

    private static Tile[][] worldArr = new Tile[10][10];
    
    public static WorldGen world;

    private long lastFrameTime = System.currentTimeMillis();
    private long lastSecondUpdate = lastFrameTime;

    private long frames = 0;
    private long FPS;

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                worldArr[j][i] = new Tile(ImageDictionary.Tiles.TILE, false);
            }
        }
    
        world = new WorldGen(worldArr);

        new Core();
    }

    public Core() {

        long currentTime;

        while (true) {
            currentTime = System.currentTimeMillis();
            if ((currentTime - lastSecondUpdate) >= 1000) {
                //System.out.println(FPS);
                FPS = frames;
                frames = 0;
                lastSecondUpdate = currentTime;
            }
            if ((currentTime - lastFrameTime) >= 14) {
                Engine.getInstance().doFrame();
                frames++;
                lastFrameTime = currentTime;
            }
        }
    }
}