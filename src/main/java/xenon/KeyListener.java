package xenon;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class KeyListener {
    private static KeyListener instance;
    public static KeyListener getInstance() {
        if (instance == null) {
            instance = new KeyListener();
        }
        return instance;
    }
    private boolean KeyPressed[] = new boolean[350];

    private KeyListener() {}

    public static void keyCallback(long window, int key, int scancode, int action, int mods) {
        if (action == GLFW_PRESS) {
            getInstance().KeyPressed[key] = true;
        }  else if (action == GLFW_RELEASE) {
            getInstance().KeyPressed[key] = false;
        }
    }
    public static boolean isKeyPressed(int key) {
        return  getInstance().KeyPressed[key];
    }
}
