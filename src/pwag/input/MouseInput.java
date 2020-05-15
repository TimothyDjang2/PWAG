package pwag.input;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.MouseInfo;

/**
 * Unused for now.
 */
public class MouseInput implements MouseListener {

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