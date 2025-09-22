package com.github.mathlon26.ribble.io.input.listeners;

import com.github.mathlon26.ribble.core.Engine;
import org.lwjgl.glfw.*;

import java.awt.event.KeyListener;

import static org.lwjgl.glfw.GLFW.*;

public class GLFWListener {
    private static GLFWListener s_instance;
    private GLFWCursorPosCallback cursorPosCallback;
    private GLFWMouseButtonCallback mouseButtonCallback;
    private GLFWScrollCallback scrollCallback;
    private GLFWKeyCallback keyCallback;
    private GLFWWindowSizeCallback windowSizeCallback;

    public static GLFWListener getInstance() {
        if (s_instance == null) {
            s_instance = new GLFWListener();
        }

        return s_instance;
    }

    public static void init() {
        s_instance.setCallbacks();
    }

    public static void endFrame() {
        s_instance.setCallbacks();
    }

    private void callEndFrameOnListeners() {
        KeyboardListener.endFrame();
        MouseListener.endFrame();
    }

    private void setCallbacks() {
        long glfwWindow = Engine.getInstance().getWindow().getGlfwWindow();

        cursorPosCallback = new GLFWCursorPosCallback() {
            @Override
            public void invoke(long window, double xPos, double yPos) {
                MouseListener.mousePosCallback(window, xPos, yPos);
            }
        };
        glfwSetCursorPosCallback(glfwWindow, cursorPosCallback);

        mouseButtonCallback = new GLFWMouseButtonCallback() {
            @Override
            public void invoke(long window, int button, int action, int mods) {
                MouseListener.mouseButtonCallback(window, button, action, mods);
            }
        };
        glfwSetMouseButtonCallback(glfwWindow, mouseButtonCallback);

        scrollCallback = new GLFWScrollCallback() {
            @Override
            public void invoke(long window, double xOffset, double yOffset) {
                MouseListener.mouseScrollCallback(window, xOffset, yOffset);
            }
        };
        glfwSetScrollCallback(glfwWindow, scrollCallback);

        keyCallback = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                KeyboardListener.keyCallback(window, key, scancode, action, mods);
            }
        };
        glfwSetKeyCallback(glfwWindow, keyCallback);

        windowSizeCallback = new GLFWWindowSizeCallback() {
            @Override
            public void invoke(long window, int width, int height) {
                Engine.getInstance().getWindow().setSize(width, height);
            }
        };
        glfwSetWindowSizeCallback(glfwWindow, windowSizeCallback);
    }
}
