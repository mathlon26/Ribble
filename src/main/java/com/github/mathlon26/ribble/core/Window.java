package com.github.mathlon26.ribble.core;

import com.github.mathlon26.ribble.event.listener.KeyListener;
import com.github.mathlon26.ribble.event.listener.MouseListener;
import com.github.mathlon26.ribble.math.Vec2D;
import com.github.mathlon26.ribble.math.Vec4D;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Window {

    private int width;
    private int height;
    private String title;
    private long glfwWindow;
    private Vec4D<Double> backGroundColor;

    private GLFWErrorCallback errorCallback;
    private GLFWCursorPosCallback cursorPosCallback;
    private GLFWMouseButtonCallback mouseButtonCallback;
    private GLFWScrollCallback scrollCallback;
    private GLFWKeyCallback keyCallback;


    public Window() {
        this.width = 1920;
        this.height = 1080;
        this.title = "Game Window";
        this.backGroundColor = new Vec4D<>(0.0, 0.0, 0.0, 1.0);

    }

    public String getTitle() {
        return title;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public long getGlfwWindow() {
        return glfwWindow;
    }

    public Vec4D<Double> getBackGroundColor() {
        return backGroundColor;
    }

    public void changeBackGroundColor(Vec4D<Double> dColor) {
        setBackGroundColor(backGroundColor.add(dColor));
        backGroundColor = backGroundColor.clamp(0.0, 1.0);
    }


    public void setWindowSize(Vec2D<Integer> dimensions) {
        this.width = dimensions.getX();
        this.height = dimensions.getY();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setBackGroundColor(Vec4D<Double> newColor) {
        this.backGroundColor = newColor;
    }

    public void show() {
        this.errorCallback = GLFWErrorCallback.createPrint(System.err).set();
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW.");
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

        glfwWindow = glfwCreateWindow(this.width, this.height, this.title, 0, 0);
        if (glfwWindow == GLFW_PLATFORM_NULL) {
            throw new IllegalStateException("Failed to create the GLFW window.");
        }

        setCallbacks();

        glfwMakeContextCurrent(glfwWindow);
        glfwSwapInterval(1);
        glfwShowWindow(glfwWindow);

        GL.createCapabilities();
        glViewport(0, 0, width, height);
    }

    public void close() {
        glfwFreeCallbacks(glfwWindow);
        glfwDestroyWindow(glfwWindow);
        glfwFreeCallbacks(glfwWindow);
        glfwTerminate();
        if (errorCallback != null) errorCallback.free();
    }


    public Boolean shouldClose() {
        return glfwWindowShouldClose(glfwWindow);
    }

    public void pollEvents() {
        glfwPollEvents();
    }

    public void clearBg() {
        glClearColor(
                backGroundColor.getX().floatValue(),
                backGroundColor.getY().floatValue(),
                backGroundColor.getZ().floatValue(),
                backGroundColor.getW().floatValue()
        );
        glClear(GL_COLOR_BUFFER_BIT);

    }

    public void swapBuffers() {
        glfwSwapBuffers(glfwWindow);
    }

    private void setCallbacks() {
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
                KeyListener.keyCallback(window, key, scancode, action, mods);
            }
        };
        glfwSetKeyCallback(glfwWindow, keyCallback);
    }
}
