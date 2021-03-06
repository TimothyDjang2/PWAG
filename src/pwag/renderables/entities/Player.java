package pwag.renderables.entities;

import pwag.Constants;
import pwag.imagehandling.ImageDictionary;
import pwag.renderables.Renderable;
import java.awt.Graphics;

public class Player implements Renderable {

    private double 
        x = 0, 
        y = 0, 
        xVel = 0, 
        yVel = 0,
        maxSpeed = 2.5,
        acceleration = 0.25;

    /**
     * Pretty much just a data storage class. Might move to Engine to improve performance.  
    */
    public Player() {

    }

    public Player(double x, double y) {
        setPosition(x, y);
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