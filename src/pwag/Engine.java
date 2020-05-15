package pwag;

import pwag.input.KeyInput;
import pwag.input.MouseInput;
import pwag.renderables.Renderable;
import pwag.renderables.RenderableImage;
import pwag.renderables.entities.Player;
import pwag.util.MathUtils;

import java.util.ArrayList;

import pwag.imagehandling.ImageDictionary;
import pwag.input.InputMap;

/**
 * Calculates all collisions, handles all key inputs, and figures out which stuff needs to render.
 * Once that's done, feeds the renderer an array of Renderables to draw.
 */
public class Engine {

    Player player;

    private Engine() {
        player = new Player(0.5,0.5);
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

    //TODO figure out how to avoid floating-point errors so there's no weird lines on the map.
    private void addWorldMap(ArrayList<Renderable> renderList) {

        for (int y = 0; y < Constants.WINDOW.TILE_HEIGHT; y++) { // Iterates over all visible tiles
            for (int x = 0; x < Constants.WINDOW.TILE_WIDTH; x++) {
                int tileY = (int)(y + MathUtils.smartFloor(player.getY()) - (Constants.WINDOW.TILE_HEIGHT / 2)); // Gets the tile that's supposed to be displayed
                int tileX = (int)(x + MathUtils.smartFloor(player.getX()) - (Constants.WINDOW.TILE_WIDTH / 2));

                //System.out.print("Did tile [" + tileX + ", " + tileY + "] ,");

                if (tileX >= 0 && tileX < Core.world.width && tileY >= 0 && tileY < Core.world.height) {
                    renderList.add(new RenderableImage(Core.world.getTile(tileY, tileX).getTile(), (int)((x * 16) - (MathUtils.getDecimal(player.getX()) * 16) + Constants.RENDERING.TILE_OFFSET_X), (int)((y * 16) - (MathUtils.getDecimal(player.getY()) * 16) + Constants.RENDERING.TILE_OFFSET_Y)));
                }
            }
        }

        //System.out.println("");
    }

    private void doPlayerMovement() {
        if (KeyInput.getInstance().getKeyPressed(InputMap.MOVE_UP)) { player.setYVel(MathUtils.clamp(player.getYVel() - player.getAcceleration(), -player.getMaxSpeed(), player.getMaxSpeed())); }
        if (KeyInput.getInstance().getKeyPressed(InputMap.MOVE_DOWN)) { player.setYVel(MathUtils.clamp(player.getYVel() + player.getAcceleration(), -player.getMaxSpeed(), player.getMaxSpeed())); } 
        if (!KeyInput.getInstance().getKeyPressed(InputMap.MOVE_UP) && !KeyInput.getInstance().getKeyPressed(InputMap.MOVE_DOWN) && player.getYVel() != 0) { player.setYVel(MathUtils.inverseClamp(player.getYVel() - ((Math.abs(player.getYVel()) / player.getYVel()) * player.getAcceleration() ), -player.getAcceleration(), player.getAcceleration())); }

        if (KeyInput.getInstance().getKeyPressed(InputMap.MOVE_LEFT)) { player.setXVel(MathUtils.clamp(player.getXVel() - player.getAcceleration(), -player.getMaxSpeed(), player.getMaxSpeed())); }
        if (KeyInput.getInstance().getKeyPressed(InputMap.MOVE_RIGHT)) { player.setXVel(MathUtils.clamp(player.getXVel() + player.getAcceleration(), -player.getMaxSpeed(), player.getMaxSpeed())); }
        if (!KeyInput.getInstance().getKeyPressed(InputMap.MOVE_LEFT) && !KeyInput.getInstance().getKeyPressed(InputMap.MOVE_RIGHT) && player.getXVel() != 0) { player.setXVel(MathUtils.inverseClamp(player.getXVel() - ((Math.abs(player.getXVel()) / player.getXVel()) * player.getAcceleration() ), -player.getAcceleration(), player.getAcceleration())); }

        //TODO: make the hitbox actually based on a square, rather than the center of the player.
        // x and y of the tile to check collision for.
        int xCollision = (int)(Math.floor(player.getX() + player.getXVel() + (0.5 * MathUtils.getSign(player.getXVel()))));
        int yCollision = (int)(Math.floor(player.getY() + player.getYVel() + (0.5 * MathUtils.getSign(player.getYVel()))));

        if (!Core.world.doesTileExist(xCollision, (int)(Math.floor(player.getY()))) || Core.world.getTile(xCollision, (int)(Math.floor(player.getY()))).isWall()) {
            player.setXVel(0);
        }

        if (!Core.world.doesTileExist((int)(Math.floor(player.getX())), yCollision)  || Core.world.getTile((int)(Math.floor(player.getX())), yCollision).isWall()) {
            player.setYVel(0);
        }
        
        player.updatePosition();
    }

    private static Engine instance = null;
    public static Engine getInstance() { 
        if (instance == null) instance = new Engine();
        return instance;
    }
}