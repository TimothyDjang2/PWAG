package pwag;

import pwag.imagehandling.ImageDictionary;
import pwag.world.Tile;
import pwag.world.WorldGen;

public class Core {

    private static Tile[][] worldArr = {
        {new Tile(ImageDictionary.Tiles.COBBLESTONE), new Tile(ImageDictionary.Tiles.COBBLESTONE), new Tile(ImageDictionary.Tiles.COBBLESTONE), new Tile(ImageDictionary.Tiles.COBBLESTONE)},
        {new Tile(ImageDictionary.Tiles.COBBLESTONE), new Tile(ImageDictionary.Tiles.COBBLESTONE), new Tile(ImageDictionary.Tiles.COBBLESTONE), new Tile(ImageDictionary.Tiles.COBBLESTONE)},
        {new Tile(ImageDictionary.Tiles.COBBLESTONE), new Tile(ImageDictionary.Tiles.COBBLESTONE), new Tile(ImageDictionary.Tiles.COBBLESTONE), new Tile(ImageDictionary.Tiles.COBBLESTONE)},
        {new Tile(ImageDictionary.Tiles.COBBLESTONE), new Tile(ImageDictionary.Tiles.COBBLESTONE), new Tile(ImageDictionary.Tiles.COBBLESTONE), new Tile(ImageDictionary.Tiles.COBBLESTONE)}
    };
    public static WorldGen world = new WorldGen(worldArr);

    private long lastFrameTime = System.currentTimeMillis();
    private long lastSecondUpdate = lastFrameTime;

    private long frames = 0;
    private long FPS;

    public static void main(String[] args) {
        new Core();
    }

    public Core() {

        System.out.println(-1 % -16);
        System.out.println(-1 % 16);

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