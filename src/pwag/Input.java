package pwag;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Input {
    
    public Input() {
        Renderer.getInstance().getCanvas().addKeyListener(new Keychecker());
    }

    class Keychecker extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent event) {
   
            char ch = event.getKeyChar();
   
            System.out.println(event.getKeyChar());

        }
    }
}