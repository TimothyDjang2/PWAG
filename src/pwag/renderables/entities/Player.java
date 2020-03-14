package pwag.renderables.entities;

import pwag.Constants;
import pwag.imagehandling.ImageDictionary;
import pwag.renderables.Renderable;
import java.awt.Graphics;

public class Player implements Renderable {

    double x = 0, y = 0;

    public Player() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(ImageDictionary.Entities.PLAYER, Constants.WINDOW.WIDTH - 8, Constants.WINDOW.HEIGHT - 8, null);
    }

}