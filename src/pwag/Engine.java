package pwag;

import pwag.imagehandling.ImageDictionary;
import pwag.renderables.Renderable;
import pwag.renderables.RenderableImage;
import pwag.renderables.entities.Player;
import pwag.world.WorldGen;

import java.util.ArrayList;

/**
 * Does all the math and calculations and stuff.
 */
public class Engine {

    Player player;

    private Engine() {
        player = new Player();
    }

    public void doFrame() {
        ArrayList<Renderable> renderList = new ArrayList<Renderable>();
        
        for (int y = 0; y < Core.world.height; y++) {
            for (int x = 0; x < Core.world.height; x++) {
                renderList.add(new RenderableImage(Core.world.getTile(x, y).getTile(), x * 16, y * 16));
            }
        }

        renderList.add(player);

        Renderer.getInstance().renderFrame(renderList);
    }

    private static Engine instance = null;
    public static Engine getInstance() { 
        if (instance == null) instance = new Engine();
        return instance;
    }
}