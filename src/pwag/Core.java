package pwag;

import java.util.ArrayList;
import java.io.File;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Canvas;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.MouseInfo;

/**
 * Base class that runs all the loops and has the main method in it.
 */
class Core {

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

        //Unit tests for MathUtil functions
        //System.out.println(MathUtils.getDecimal(1.234));
        //System.out.println(MathUtils.getDecimal(-1.234));

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                worldArr[j][i] = new Tile(ImageDictionary.Tiles.TILE, false);
            }
        }

        //worldArr[4][4] = new Tile(ImageDictionary.Tiles.COBBLESTONE, true);
    
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

/**
 * Calculates all collisions, handles all key inputs, and figures out which stuff needs to render.
 * Once that's done, feeds the renderer an array of Renderables to draw.
 */
class Engine {

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

/**
 * Makes the window exist and draws pretty pictures on it. The engine feeds it an array of renderable stuff to draw.
 */
class Renderer {

    private JFrame window;
    private Canvas renderpane;
    private BufferStrategy buffer;
    private Graphics graphics = null;

    private Renderer() {

        renderpane = new Canvas();
        renderpane.setSize(Constants.WINDOW.WIDTH, Constants.WINDOW.HEIGHT);
        renderpane.setBackground(Color.BLACK);
        renderpane.setIgnoreRepaint(true);

        window = new JFrame("Thing");
        window.add(renderpane);
        window.setSize(Constants.WINDOW.WIDTH, Constants.WINDOW.HEIGHT);
        window.setLocation((int)((Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2) - (Constants.WINDOW.WIDTH / 2)), (int)((Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2) - (Constants.WINDOW.HEIGHT / 2)));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setIgnoreRepaint(true);
        renderpane.addMouseListener(MouseInput.getInstance());
        renderpane.addKeyListener(KeyInput.getInstance());
        window.setFocusTraversalKeysEnabled(false);

        //window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //window.setUndecorated(true);
        window.setVisible(true);

        renderpane.createBufferStrategy(2);
        buffer = renderpane.getBufferStrategy();
    }

    public void renderFrame(ArrayList<Renderable> renderList) {
        graphics = buffer.getDrawGraphics();
        graphics.clearRect(0, 0, renderpane.getWidth(), renderpane.getHeight());

        for (Renderable renderable : renderList) {
            renderable.draw(graphics);
        }

        if (!buffer.contentsLost()) buffer.show();

        if (graphics != null) {
            graphics.dispose();
        }
    }

    private static Renderer instance = null;
    public static Renderer getInstance() { 
        if (instance == null) instance = new Renderer();
        return instance;
    }

}

/**
 * Stores numbers.
 */
class Constants {

    public static final class WINDOW {
        public static final int
            WIDTH = 480, //Toolkit.getDefaultToolkit().getScreenSize().width,
            HEIGHT = 360, //Toolkit.getDefaultToolkit().getScreenSize().height,
            TILE_WIDTH = 30, // amount of tiles to render in the window.
            TILE_HEIGHT = 22;
    }

    public static final class RENDERING {
        public static final int
            TILE_OFFSET_X = 0, // How many (px) to offset tile rendering so that tiles line up with the player.
            TILE_OFFSET_Y = 4, // How many (px) to offset tile rendering in the y direction.
            TILE_SIZE = 16, // How big (px) to render tiles at scale 1x. Since tiles are square, there's only one number here.
            PLAYER_SIZE = 16; // How big (px) to render the player. Not really sure where I should put this yet. Makes the hitbox go.
    }

}

/**
 * Data storage type for worldmaps. Contains a reference to the image this tile is supposed to display.
 */
class Tile {

    private BufferedImage tile;
    private boolean isWall; // Can the player walk through this tile, or not?

    public Tile(BufferedImage tile, boolean isWall) {
        this.tile = tile;
        this.isWall = isWall;
    }

    public BufferedImage getTile() {
        return tile;
    }

    public boolean isWall() {
        return isWall;
    }

    public void setWall(boolean state) {
        isWall = state;
    }

}

/**
 * In theory, does all the fantastic numbers, math, AND science to generate random dungeon levels.
 * Also in theory does fantastic math and science to generate specific pre-determined areas.
 * In practice, this does none of that stuff yet.
 */
class WorldGen {

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

    //TODO figure out a good seperation between the worldgen class and the world itself.
    public boolean doesTileExist(int x, int y) {
        if (x < 0 || x > width - 1 || y < 0 || y > height - 1) { return false; }
        if (getTile(0, 0) == null) { return false; }
        return true;
    }

}

/**
 * Useful math functions I desire.
 */
class MathUtils {

    /**
     * Makes sure the input number is within a certain domain.
     */
    public static double clamp(double a, double min, double max) {
        if (a < min) return min;
        if (a > max) return max;
        return a;
    }

    /**
     * Returns 0 unless the value is outside a specified bound.
     */
    public static double inverseClamp(double a, double boundMin, double boundMax) {
        if (a <= boundMax && a >= boundMin) return 0;
        return a;
    }

    /**
     * Gets the sign of a number.
     */
    public static double getSign(double a) {
        if (a == 0) return 1;
        return a / Math.abs(a);
    }

    /**
     * Returns the decimal component of a double.
     * An input of 1.234 would return 0.234.
     */
    public static double getDecimal(double a) {
        return a - smartFloor(a);
    }

    /**
     * Makes it so that the floor function doesn't return -2 when I do floor(-1.6).
     */
    public static double smartFloor(double a) {
        if (a < 0) return Math.ceil(a);
        return Math.floor(a);
    }
}

interface Renderable {

    public void draw(Graphics g);

}

class RenderableImage implements Renderable {

    private BufferedImage img;
    private int x = 0, y = 0;

    public RenderableImage(BufferedImage img) {
        this.img = img;
    }

    public RenderableImage(BufferedImage img, int x, int y) {
        this.img = img;
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Graphics g) {
            g.drawImage(img, x, y, null);

    }

}

class Player implements Renderable {

    private double 
        x = 0, 
        y = 0, 
        xVel = 0, 
        yVel = 0,
        maxSpeed = 0.3,
        acceleration = 0.05;

    /**
     * Pretty much just a data storage class. Might move to Engine to improve performance.  
    */
    public Player() {

    }

    public Player(double x, double y) {
        setPosition(x, y);
    }

    /**
     * Coordinates are measured in percent of tile crossed for the player. [0, 0] would mean the player is in the very top-left corner of the map.
     * [1, 1] would mean the player is in the very top-left corner of the tile at World[1][1]. Based off of the center of the player, so 0.5, 0.5 puts
     * you in the center of the first tile.
     */
    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setX(double x) {
        setPosition(x, this.y);
    }

    public void setY(double y) {
        setPosition(this.x, y);
    }

    public double getX() {
        return x;
    }
    
    public double getY() {
        return y;
    }

    public void setVelocity(double xVel, double yVel) {
        this.xVel = xVel;
        this.yVel = yVel;
    }

    public void setXVel(double xVel) {
        setVelocity(xVel, this.yVel);
    }

    public void setYVel(double yVel) {
        setVelocity(this.xVel, yVel);
    }

    public double getXVel() {
        return xVel;
    }
    
    public double getYVel() {
        return yVel;
    }

    //**
    /* Update the player's x and y position based on its velocity
    */ 
    public void updatePosition() {
        x += xVel;
        y += yVel;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double speed) {
        maxSpeed = speed;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(double accel) {
        acceleration = accel;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(ImageDictionary.Entities.HITBOX, (Constants.WINDOW.WIDTH / 2) - 8, (Constants.WINDOW.HEIGHT / 2) - 8, null);
    }

}

class InputMap {

    public static final int
        MOVE_UP = KeyEvent.VK_W,
        MOVE_LEFT = KeyEvent.VK_A,
        MOVE_DOWN = KeyEvent.VK_S,
        MOVE_RIGHT = KeyEvent.VK_D;
}   

class KeyInput implements KeyListener {

    private Map<Integer, Boolean> keyMap;

    private KeyInput() {
        keyMap = new HashMap<>();
    }

    @Override
    public void keyPressed(KeyEvent event) {
        keyMap.put(event.getKeyCode(), true);
        //System.out.println("Key " + event.getKeyCode() + " pressed");
    }

    @Override
    public void keyReleased(KeyEvent event) {
        keyMap.put(event.getKeyCode(), false);
        //System.out.println("Key " + event.getKeyCode() + " released");
    }

    public boolean getKeyPressed(int key) {
        if (keyMap.get(key) == null) return false;
        return keyMap.get(key);
    }

    @Override
    public void keyTyped(KeyEvent event) { }

    private static KeyInput instance;
    public static KeyInput getInstance() {
        if (instance == null) { instance = new KeyInput(); }
        return instance;
    }
    
}

/**
 * Holds bufferedImages in memory so there only ever needs to be one instance of them.
 */
class ImageDictionary {

    public static final BufferedImage MISSING = loadImage(ImagePaths.missing);

    public static class Tiles {
        public static final BufferedImage
            COBBLESTONE = loadImage(ImagePaths.Tiles.cobblestone),
            TILE = loadImage(ImagePaths.Tiles.tile);
    }

    public static class Entities {
        public static final BufferedImage
            PLAYER = loadImage(ImagePaths.Entities.player),
            HITBOX = loadImage(ImagePaths.Entities.hitbox);
    }



    private static BufferedImage loadImage(String filePath) {
        File file = new File(filePath);
        BufferedImage img = null;
        try {
            img = ImageIO.read(file);
        } catch(Exception e) {
            try {
                file = new File(ImagePaths.missing);
                img = ImageIO.read(file);
            } catch (Exception f) {
                System.out.println("The missing file texture is somehow missing. Panic now.");
            }
        }
        return img;
    }

}

/**
 * Dictionary so I don't have to type out individual filepaths every time.
 */
class ImagePaths{
    
    public static final String
        missing = "./image/missing.png";

    public static class Entities {
        public static final String
            player = "./image/player/priest.png",
            hitbox = "./image/player/hitboxTst.png";
    }

    public static class Tiles {

        public static final String
            cobblestone = "./image/tiles/cobblestone.png",
            tile = "./image/tiles/tile.png";

    }

}

/**
 * Unused for now.
 */
class MouseInput implements MouseListener {

    private boolean mouseDown = false;

    private MouseInput() {}

    public void mousePressed(MouseEvent event) {
        mouseDown = true;
    }
    public void mouseReleased(MouseEvent event) { mouseDown = false; }

    public void mouseClicked(MouseEvent event) {} 
    public void mouseEntered(MouseEvent event) {}
    public void mouseExited(MouseEvent event) {}

    public double getX() { return MouseInfo.getPointerInfo().getLocation().getX(); }
    public double getY() { return MouseInfo.getPointerInfo().getLocation().getY(); }
    public boolean getMouseDown() { return mouseDown; }

    private static MouseInput instance;
    public static MouseInput getInstance() {
        if (instance == null) instance = new MouseInput();
        return instance;
    }
}