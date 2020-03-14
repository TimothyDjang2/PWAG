package pwag;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import pwag.renderables.Renderable;

import java.awt.image.BufferStrategy;

import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

/**
 * Makes the window exist and draws crap on it. The engine feeds it an array of renderable stuff to draw.
 */
public class Renderer {

    private JFrame window;
    private Canvas renderpane;
    private BufferStrategy buffer;
    private Graphics graphics = null;

    public Renderer() {
        renderpane = new Canvas();
        renderpane.setSize(480, 360);
        renderpane.setBackground(Color.BLACK);
        renderpane.setIgnoreRepaint(true);

        window = new JFrame("Thing");
        window.add(renderpane);
        window.setSize(480, 360);
        window.setLocation((int)((Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2) - (480 / 2)), (int)((Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2) - (360 / 2)));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setIgnoreRepaint(true);

        window.setVisible(true);

        renderpane.createBufferStrategy(2);
        buffer = renderpane.getBufferStrategy();
    }

    public void renderFrame(ArrayList<Renderable> renderList) {
        graphics = buffer.getDrawGraphics();

        for (Renderable renderable : renderList) {
            renderable.draw(graphics);
        }

        if(!buffer.contentsLost()) buffer.show();
    }

}