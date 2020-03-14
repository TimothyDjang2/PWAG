package pwag;

import pwag.renderables.Renderable;
import pwag.renderables.RenderableImage;
import java.util.ArrayList;

/**
 * Does all the math and calculations and stuff.
 */
public class Engine {

    public Engine() {

    }

    public void doFrame() {
        Renderer r = Core.renderer;
        ArrayList<Renderable> renderList = new ArrayList<Renderable>();
        renderList.add(new RenderableImage(ImagePaths.Tiles.cobblestone));
        r.renderFrame(renderList);
    }

}