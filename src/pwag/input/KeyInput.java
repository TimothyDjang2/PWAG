package pwag.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class KeyInput implements KeyListener {

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