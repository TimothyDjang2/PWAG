package pwag;

import pwag.imagehandling.ImageDictionary;
import pwag.world.Tile;
import pwag.world.WorldGen;

public class Core {

    private static Tile[][] worldArr = {{new Tile(ImageDictionary.Tiles.COBBLESTONE), new Tile(ImageDictionary.Tiles.COBBLESTONE)},{new Tile(ImageDictionary.Tiles.COBBLESTONE), new Tile(ImageDictionary.Tiles.COBBLESTONE)}};
    public static WorldGen world = new WorldGen(worldArr);

    public static void main(String[] args) {
        new Core();
    }

    public Core() {
        Engine.getInstance().doFrame();
    }
}