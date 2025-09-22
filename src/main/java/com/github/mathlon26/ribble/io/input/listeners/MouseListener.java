package com.github.mathlon26.ribble.io.input.listeners;


import com.github.mathlon26.ribble.io.input.devices.MouseButton;
import com.github.mathlon26.ribble.math.Vector2D;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class MouseListener {
    private static MouseListener instance;
    private Vector2D scrollOffset;
    private Vector2D mousePos;
    private Vector2D lastMousePos;
    private final boolean[] mouseButtonPressed = new boolean[MouseButton.MAX_BUTTONS];
    private boolean isDragging;

    public static MouseListener getInstance() {
        if (instance == null) {
            instance = new MouseListener();
        }

        return instance;
    }

    private MouseListener() {
        this.scrollOffset = new Vector2D();
        this.mousePos = new Vector2D();
        this.lastMousePos = new Vector2D();
    }

    public static void mousePosCallback(long window, double xPos, double yPos) {
        getInstance().lastMousePos = getInstance().mousePos;
        getInstance().mousePos = new Vector2D(xPos, yPos);
        for (int i = 0; i < MouseButton.MAX_BUTTONS; i++) {
            getInstance().isDragging |= instance.mouseButtonPressed[i];
        }

    }

    public static void mouseButtonCallback(long window, int button, int action, int mods) {
        if (button > MouseButton.MAX_BUTTONS) return;

        if (action == GLFW_PRESS) {
            getInstance().mouseButtonPressed[button] = true;
        } else if (action == GLFW_RELEASE) {
            getInstance().mouseButtonPressed[button] = false;
            getInstance().isDragging = false;
        }
    }

    public static void mouseScrollCallback(long window, double xOffset, double yOffset) {
        getInstance().scrollOffset = new Vector2D(xOffset, yOffset);
    }

    public static void endFrame() {
        getInstance().scrollOffset = new Vector2D();
        getInstance().lastMousePos = getInstance().mousePos;
    }


    public static Vector2D getMousePos() {
        return getInstance().mousePos;
    }

    public static double getMouseX() {
        return getMousePos().getX();
    }

    public static double getMouseY() {
        return getMousePos().getY();
    }


    public static Vector2D getMouseDxDy() {
        return getInstance().lastMousePos.sub(getMousePos());
    }

    public static double getMouseDx() {
        return getMouseDxDy().getX();
    }

    public static double getMouseDy() {
        return getMouseDxDy().getY();
    }

    public static Vector2D getScrollOffset() {
        return getInstance().scrollOffset;
    }

    public static double getScrollOffsetX() {
        return getScrollOffset().getX();
    }

    public static double getScrollOffsetY() {
        return getScrollOffset().getY();
    }

    public static boolean isDragging() {
        return getInstance().isDragging;
    }

    public static boolean mouseButtonDown(MouseButton button) {
        if (button.getCode() < MouseButton.MAX_BUTTONS) {
            return getInstance().mouseButtonPressed[button.getCode()];
        } else {
            return false;
        }
    }

    public static boolean mouseButtonDown(int glfwMouseButton) {
        return mouseButtonDown(MouseButton.fromCode(glfwMouseButton));
    }
}