package pwag;

import javax.swing.JFrame;

public class Core {

    JFrame window;
    RenderPanel renderpane;

    public static void main(String[] args) {
        new Core();
    }

    public Core() {
        
        renderpane = new RenderPanel();
        renderpane.setSize(480, 360);
        //renderpane.setDoubleBuffered(true);

        window = new JFrame("Thing");
        window.add(renderpane);
        window.setSize(480, 360);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.setVisible(true);
    }

}