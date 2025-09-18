package com.github.mathlon26.ribble_deprecated.event.listener;

import java.util.Arrays;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class KeyListener {
    private static KeyListener instance;
    private final boolean[] keyPressed = new boolean[KeyBoardKey.MAX_KEYS];
    private final boolean[] keyJustPressed = new boolean[KeyBoardKey.MAX_KEYS];

    private KeyListener() { }

    public static KeyListener getInstance() {
        if (instance == null) {
            instance = new KeyListener();
        }
        return instance;
    }

    public static void keyCallback(long window, int key, int scancode, int action, int mods) {
        if (key < 0 || key >= KeyBoardKey.MAX_KEYS) return;

        if (action == GLFW_PRESS) {
            if (!getInstance().keyPressed[key]) {
                getInstance().keyJustPressed[key] = true; // mark as just pressed
            }
            getInstance().keyPressed[key] = true;
        } else if (action == GLFW_RELEASE) {
            getInstance().keyPressed[key] = false;
        }
    }

    public static boolean isKeyDown(KeyBoardKey keyCode) {
        int code = keyCode.getCode();
        return code < KeyBoardKey.MAX_KEYS && getInstance().keyPressed[code];
    }

    public static boolean isKeyDown(int glfwKeyCode) {
        return isKeyDown(KeyBoardKey.fromCode(glfwKeyCode));
    }

    public static boolean isKeyPressed(KeyBoardKey keyCode) {
        int code = keyCode.getCode();
        if (code >= KeyBoardKey.MAX_KEYS) return false;
        return getInstance().keyJustPressed[code];
    }

    public static boolean isKeyPressed(int glfwKeyCode) {
        return isKeyPressed(KeyBoardKey.fromCode(glfwKeyCode));
    }

    public static void endFrame() {
        KeyListener listener = getInstance();
        Arrays.fill(listener.keyJustPressed, false);
    }
}
