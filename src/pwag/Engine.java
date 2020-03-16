package pwag;

import pwag.imagehandling.ImageDictionary;
import pwag.input.Input;
import pwag.renderables.Renderable;
import pwag.renderables.RenderableImage;
import pwag.renderables.entities.Player;
import pwag.util.MathUtils;
import pwag.world.WorldGen;

import java.util.ArrayList;

import pwag.input.InputMap;

/**
 * Does all the math and calculations and stuff.
 */
public class Engine {

    Player player;

    private Engine() {
        player = new Player();
    }

    public void doFrame() {
        
        doPlayerMovement();

        ArrayList<Renderable> renderList = new ArrayList<Renderable>();
        
        addWorldMap(renderList);
        renderList.add(player);
        Renderer.getInstance().renderFrame(renderList);
    }

    /**
     * Adds all the tiles the renderer is supposed to do for this frame.
     * @param renderList
     */
    private void addWorldMap(ArrayList<Renderable> renderList) {

        for (int y = 0; y < Constants.WINDOW.TILE_HEIGHT; y++) { // Iterates over all visible tiles
            for (int x = 0; x < Constants.WINDOW.TILE_WIDTH; x++) {
                int tileY = (int)(y + (Math.floor(player.getY() / 16) - (Constants.WINDOW.TILE_HEIGHT / 2))); // Gets the tile that's supposed to be displayed
                int tileX = (int)(x + (Math.floor(player.getX() / 16) - (Constants.WINDOW.TILE_WIDTH / 2)));

                if (tileX >= 0 && tileX < Core.world.width && tileY >= 0 && tileY < Core.world.height) {
                    renderList.add(new RenderableImage(Core.world.getTile(tileX, tileY).getTile(), (int)((x * 16) - ((player.getX() + ((Math.ceil(player.getX() / 16) + 16) * 16)) % 16)), (int)((y * 16) - ((player.getY() + ((Math.ceil(player.getY() / 16) + 16) * 16)) % 16))));
                    // Violent amounts of math. Draws tiles in the correct 16x16 region based on which visible tile we're currently drawing, then
                    // offsets them based on the player's pixel coordinates.
                    // The funky Math.ceil crap makes sure the transition from 1 to -1 still rolls over to an offset of 15. Rather than going from
                    // 1 mod 16 to -1 mod 16, it goes from 1 mod 16 to 16 mod 16. Jank but it works.
                }
            }
        }
    }

    private void doPlayerMovement() {
        if(Input.getInstance().getKeyPressed(InputMap.MOVE_UP)) { player.setY(player.getY() - 1); };
        if(Input.getInstance().getKeyPressed(InputMap.MOVE_LEFT)) { player.setX(player.getX() - 1); };
        if(Input.getInstance().getKeyPressed(InputMap.MOVE_DOWN)) { player.setY(player.getY() + 1); };
        if(Input.getInstance().getKeyPressed(InputMap.MOVE_RIGHT)) { player.setX(player.getX() + 1); };
    }

    private static Engine instance = null;
    public static Engine getInstance() { 
        if (instance == null) instance = new Engine();
        return instance;
    }
}