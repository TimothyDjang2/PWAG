package pwag;

import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;

public class RenderPanel extends JPanel {

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        super.paintComponent(g);
        
    }
}