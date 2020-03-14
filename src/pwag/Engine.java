package pwag;

import pwag.imagehandling.ImageDictionary;
import pwag.renderables.Renderable;
import pwag.renderables.RenderableImage;
import java.util.ArrayList;

/**
 * Does all the math and calculations and stuff.
 */
public class Engine {

    private Engine() {

    }

    public void doFrame() {
        ArrayList<Renderable> renderList = new ArrayList<Renderable>();
        renderList.add(new RenderableImage(ImageDictionary.Tiles.COBBLESTONE));
        Renderer.getInstance().renderFrame(renderList);
    }

    private static Engine instance = null;
    public static Engine getInstance() { 
        if (instance == null) instance = new Engine();
        return instance;
    }
}