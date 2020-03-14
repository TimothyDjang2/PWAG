package pwag.renderables;

import java.awt.Graphics;

import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class RenderableImage implements Renderable {

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