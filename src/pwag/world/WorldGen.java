package pwag.world;


/**
 * Does all the fantastic numbers, math, AND science to generate random dungeon levels.
 * Also does fantastic math and science to generate specific pre-determined areas.
 */
public class WorldGen {

    private Tile[][] worldArr;
    public int width, height;

    public WorldGen(Tile[][] worldArr) {
        this.worldArr = worldArr;
        height = worldArr.length;
        width = worldArr[0].length;
    }

    public Tile getTile(int x, int y) {
        return worldArr[x][y];
    }

    public boolean doesTileExist(int x, int y) {
        if (x < 0 || x > width - 1 || y < 0 || y > height - 1) { return false; }
        if (getTile(0, 0) == null) { return false; }
        return true;
    }

}