package xenon;

import org.lwjgl.glfw.GLFW;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class MouseListener {
    private static MouseListener instance;

    public static MouseListener getInstance() {
        if (MouseListener.instance == null) {
            instance = new MouseListener();
        }
        return instance;
    }
    private double scrollX;
    private double scrollY;
    private double xPos;
    private double yPos;
    private double lastX;
    private double lastY;
    private boolean isDragging;
    private boolean mouseButtonPressed[] = new boolean[3];
    private MouseListener() {
        scrollX = 0;
        scrollY = 0;
        xPos = 0;
        yPos = 0;
        lastX = 0;
        lastY = 0;
    }
    public static void mousePosCallback(long window, double xpos, double ypos) {
        getInstance().lastX = xpos;
        getInstance().lastY = ypos;
        getInstance().xPos = xpos;
        getInstance().yPos = ypos;
        getInstance().isDragging =
                getInstance().mouseButtonPressed[0]
                || getInstance().mouseButtonPressed[1]
                ||  getInstance().mouseButtonPressed[2];
    }
    public static void mouseButtonCallback(long window, int button, int action, int mods) {
        if (action == GLFW_PRESS) {
            if (button < getInstance().mouseButtonPressed.length) {
                getInstance().mouseButtonPressed[button] = true;
            }
            getInstance().mouseButtonPressed[button] = true;
        } else if (action == GLFW_RELEASE) {
            if (button < getInstance().mouseButtonPressed.length) {
                getInstance().mouseButtonPressed[button] = false;
                getInstance().isDragging = false;
            }
        }

    }
    public static void mouseScrollCallback(long window, double xpos, double ypos) {
        getInstance().scrollX = xpos;
        getInstance().scrollY = ypos;
    }
    public static void endFrame() {
        getInstance().scrollX = 0;
        getInstance().scrollY = 0;
        getInstance().lastX = getInstance().xPos;
        getInstance().lastY = getInstance().yPos;
    }
    public static float getX() {
        return (float) getInstance().xPos;
    }
    public static float getY() {
        return (float) getInstance().yPos;
    }
    public static float getDx() {
        return (float)(getInstance().xPos - getInstance().lastX);
    }
    public static float getDy() {
        return (float)(getInstance().yPos - getInstance().lastY);
    }
    public static float getScrollX() {
        return (float)(getInstance().scrollX);
    }
    public static float getScrollY() {
        return (float)(getInstance().scrollY);
    }
    public static boolean isDragging() {
        return getInstance().isDragging;
    }
    public static boolean mouseButtonDown(int button) {
        if (button < getInstance().mouseButtonPressed.length) {
            return getInstance().mouseButtonPressed[button];
        }
        return false;
    }
}
