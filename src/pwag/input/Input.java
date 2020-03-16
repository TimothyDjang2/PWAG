package pwag.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class Input implements KeyListener {

    private Map<Integer, Boolean> keyMap;

    private Input() {
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

    private static Input instance;
    public static Input getInstance() {
        if (instance == null) { instance = new Input(); }
        return instance;
    }
    
}