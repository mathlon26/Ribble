package com.github.mathlon26.ribble.core;

import com.github.mathlon26.ribble.core.time.GameTime;
import com.github.mathlon26.ribble.graphics.Window;
import com.github.mathlon26.ribble.io.input.listeners.GLFWListener;
import com.github.mathlon26.ribble.io.output.sys.Logger;
import com.github.mathlon26.ribble.managers.SceneManager;
import com.github.mathlon26.ribble.scene.Scene;

public class GameLoop {

    private final Engine m_engine;
    private final Window m_window;
    private boolean m_running;
    private int m_targetFPS;

    public GameLoop(Window window) {
        m_engine = Engine.getInstance();
        m_window = window;
        m_targetFPS = Config.get("targetFPS", Integer.class);
        m_running = false;
    }


    public int getTargetFPS() {
        return m_targetFPS;
    }

    public void start() {
        m_running = true;
        Logger.getInstance().info("Game loop started by engine | GameLoop::start");
        GameTime.init();

        double accumulator = 0;

        while (m_running && !m_window.shouldClose()) {
            m_window.pollEvents();

            GameTime.update();
            accumulator += GameTime.getDeltaTime();

            final double frameTime = GameTime.getFrameTime();
            while (accumulator >= frameTime) {
                update();
                accumulator -= frameTime;
            }

            GLFWListener.endFrame();
            render();
        }


        cleanup();
    }

    private void update() {
        m_engine.updateSystems(GameTime.getDeltaTime());
    }

    private void render() {
        m_window.clear();
        m_engine.renderSystems();
        m_window.swapBuffers();
    }

    private void cleanup() {
        m_engine.shutdown();
    }

    public void stop() {
        m_running = false;
    }
}
