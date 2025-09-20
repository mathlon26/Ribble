package com.github.mathlon26.ribble.graphics;

import com.github.mathlon26.ribble.core.Config;
import com.github.mathlon26.ribble.io.output.sys.ExceptionHandler;
import com.github.mathlon26.ribble.io.output.sys.Logger;
import com.github.mathlon26.ribble.math.Vector2D;
import com.github.mathlon26.ribble.math.physics.Color;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWFramebufferSizeCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Window {
    private Vector2D m_size;
    private String m_title;
    private Color m_bgColor;

    private long m_glfwWindowPointer;

    private GLFWErrorCallback m_glfwErrorCallback;
    private GLFWFramebufferSizeCallback m_frameBufferSizeCallback;

    public Window() {
        m_size = Config.get("windowSize", Vector2D.class);
        m_title = Config.get("windowTitle", String.class);
        m_bgColor = Config.get("windowBgColor", Color.class);
    }

    public int getHeight() {
        return (int) m_size.getY();
    }

    public int getWidth() { return (int) m_size.getX(); }

    public Vector2D getSize() { return m_size; }

    public String getTitle() {
        return m_title;
    }

    public long getGlfwWindow() {
        return m_glfwWindowPointer;
    }

    public Color getBackgroundColor() { return m_bgColor; }

    public void setTitle(String title) {
        m_title = title;
        if (m_glfwWindowPointer != 0) {
            glfwSetWindowTitle(m_glfwWindowPointer, title);
        }
    }

    public void setBackgroundColor(Color c) { m_bgColor = c; }

    public void show() {
        m_glfwErrorCallback = GLFWErrorCallback.createPrint(System.err).set();
        if (!glfwInit()) {
            ExceptionHandler.raise(IllegalStateException.class, "Unable to initialize GLFW.");
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

        m_glfwWindowPointer = glfwCreateWindow((int) m_size.getX(), (int) m_size.getY(), m_title, 0, 0);
        if (m_glfwWindowPointer == GLFW_PLATFORM_NULL) {
            ExceptionHandler.raise(IllegalStateException.class, "Failed to create the GLFW window.");
        }

        setCallbacks();

        glfwMakeContextCurrent(m_glfwWindowPointer);
        glfwSwapInterval(1);

        GL.createCapabilities();
        glfwShowWindow(m_glfwWindowPointer);



        glViewport(0, 0, (int)m_size.getX(), (int) m_size.getY());
    }

    public void destroy() {
        glfwFreeCallbacks(m_glfwWindowPointer);
        glfwDestroyWindow(m_glfwWindowPointer);
        glfwTerminate();
        if (m_glfwErrorCallback != null) m_glfwErrorCallback.free();
    }


    public boolean shouldClose() {
        return glfwWindowShouldClose(m_glfwWindowPointer);
    }

    public void pollEvents() {
        glfwPollEvents();
    }

    public void clear() {
        glClearColor(
                m_bgColor.getR(),
                m_bgColor.getG(),
                m_bgColor.getB(),
                m_bgColor.getB()
        );
        glClear(GL_COLOR_BUFFER_BIT);
    }

    public void swapBuffers() {
        glfwSwapBuffers(m_glfwWindowPointer);
    }


    private void setCallbacks() {
        m_frameBufferSizeCallback = new GLFWFramebufferSizeCallback() {
            @Override
            public void invoke(long windowPointer, int width, int height) {
                m_size = new Vector2D(width, height);
                glViewport(0, 0, width, height);
            }
        };
        glfwSetFramebufferSizeCallback(m_glfwWindowPointer, m_frameBufferSizeCallback);
    }
}
