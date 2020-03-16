package pwag.renderables.entities;

import pwag.Constants;
import pwag.imagehandling.ImageDictionary;
import pwag.renderables.Renderable;
import java.awt.Graphics;

public class Player implements Renderable {

    private double x = 0, y = 0;

    public Player() {

    }

    /**
     * Coordinates are measured in pixels for the player. [0, 0] would mean the player is in the very top-left corner of the map.
     * [16, 16] would mean the player is in the very top-left corner of the tile at World[1][1].
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

    @Override
    public void draw(Graphics g) {
        g.drawImage(ImageDictionary.Entities.PLAYER, (Constants.WINDOW.WIDTH / 2) - 8, (Constants.WINDOW.HEIGHT / 2) - 8, null);
    }

}