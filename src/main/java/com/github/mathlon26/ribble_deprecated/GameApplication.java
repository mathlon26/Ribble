package com.github.mathlon26.ribble_deprecated;

import com.github.mathlon26.ribble_deprecated.core.Window;
import com.github.mathlon26.ribble_deprecated.core.editor.LevelEditorScene;
import com.github.mathlon26.ribble_deprecated.core.graphics.SceneManager;
import com.github.mathlon26.ribble_deprecated.core.graphics.rendering.*;
import com.github.mathlon26.ribble_deprecated.core.graphics.rendering.modeling.Indices;
import com.github.mathlon26.ribble_deprecated.core.graphics.rendering.modeling.Mesh;
import com.github.mathlon26.ribble_deprecated.core.graphics.rendering.modeling.Vertices;
import com.github.mathlon26.ribble_deprecated.core.graphics.rendering.shaders.FragmentShader;
import com.github.mathlon26.ribble_deprecated.core.graphics.rendering.shaders.ShaderProgram;
import com.github.mathlon26.ribble_deprecated.core.graphics.rendering.shaders.VertexShader;
import com.github.mathlon26.ribble_deprecated.event.listener.KeyListener;
import com.github.mathlon26.ribble_deprecated.event.listener.MouseListener;
import com.github.mathlon26.ribble_deprecated.util.Time;

public class GameApplication {

    private Boolean isRunning;
    private static final Window mainWindow = new Window();

    public GameApplication() {
        this.isRunning = true;
    }

    public static Window getMainWindow() {
        return mainWindow;
    }


    public void start() {
        SceneManager.getInstance().addScene(LevelEditorScene.class, LevelEditorScene::new);
        SceneManager.getInstance().loadScene(LevelEditorScene.class);

        mainWindow.show();

        startApplicationLoop();
    }


    public void stop() {
        isRunning = false;
    }

    private void startApplicationLoop() {
        double beginTime = Time.getElapsedTime();
        double endTime;
        double dt = -1.0;

        float[] rectVertices = {
                // positions        // colors (RGBA)
                -0.5f, -0.5f, 0.0f,  1f, 0f, 0f, 1f, // bottom-left, red
                0.5f, -0.5f, 0.0f,   0f, 1f, 0f, 1f, // bottom-right, green
                0.5f,  0.5f, 0.0f,   0f, 0f, 1f, 1f, // top-right, blue
                -0.5f,  0.5f, 0.0f,  1f, 1f, 1f, 1f  // top-left, white
        };

        int[] rectIndices = {
                0, 1, 2,  // first triangle
                2, 3, 0   // second triangle
        };

        Vertices vertices = new Vertices(rectVertices);
        vertices.addAttribute(0, 3, 0); // position
        vertices.addAttribute(1, 4, 3); // color (offset = 3 floats)

        Indices indices = new Indices(rectIndices);

        Mesh rectangleMesh = new Mesh(vertices, indices);
        rectangleMesh.bind();

        String shaderPath = "shaders/default.glsl";
        VertexShader vertexShader = new VertexShader(shaderPath, true);
        FragmentShader fragmentShader = new FragmentShader(shaderPath, true);

        ShaderProgram shaderProgram = new ShaderProgram(vertexShader, fragmentShader);

        Renderable rectangle = new Renderable(rectangleMesh, shaderProgram, DrawMode.TRIANGLES);

        while (!mainWindow.shouldClose() && this.isRunning) {
            mainWindow.pollEvents();

            // Clear screen first
            mainWindow.clearBg(); // or separate clear() method if you want swap later

            if (dt >= 0) {
                SceneManager.getInstance().updateCurrentScene(dt);
                SceneManager.getInstance().renderCurrentScene();
            }

            rectangle.draw(); // draw rectangle after clearing

            MouseListener.endFrame();
            KeyListener.endFrame();

            // Swap buffers at the end
            mainWindow.swapBuffers();
        }

        mainWindow.close();
    }
}
