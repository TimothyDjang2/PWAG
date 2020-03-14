package pwag;

public class Core {

    public static Renderer renderer;
    public static Engine engine;

    public static void main(String[] args) {
        new Core();
    }

    public Core() {
        renderer = new Renderer();
        engine = new Engine();

        engine.doFrame();
    }
}