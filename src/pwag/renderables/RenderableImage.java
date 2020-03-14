package pwag.renderables;

import java.awt.Graphics;

import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class RenderableImage implements Renderable {

    private BufferedImage img;
    private int x = 0, y = 0;

    public RenderableImage(String imagePath) {
            File imgFile = new File(imagePath);
            try {
                img = ImageIO.read(imgFile);
            } catch(Exception e) {
                try {
                    img = ImageIO.read(new File("./image/missing.png"));
                } catch (Exception f) {
                    System.out.println("The missing file texture is somehow missing. Panic now.");
                }
            }
    }

    @Override
    public void draw(Graphics g) {
            g.drawImage(img, x, y, null);

    }

}