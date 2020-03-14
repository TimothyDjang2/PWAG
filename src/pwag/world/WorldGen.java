package pwag.world;

/**
 * Does all the fantastic numbers, math, AND science to generate random dungeon levels.
 * Also does fantastic math and science to generate specific pre-determined areas.
 */
public class WorldGen {

    private Tile[][] worldArr;

    public WorldGen(Tile[][] worldArr) {
        this.worldArr = worldArr;
    }

    public Tile getTile(int x, int y) {
        return worldArr[x][y];
    }

}