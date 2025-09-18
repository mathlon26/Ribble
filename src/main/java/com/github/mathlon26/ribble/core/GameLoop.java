package com.github.mathlon26.ribble.core;

import com.github.mathlon26.ribble.core.time.GameTime;
import com.github.mathlon26.ribble.graphics.Window;

public class GameLoop {

    private final Engine m_engine;
    private final Window m_window;
    private boolean m_running;
    private int targetFPS;

    public GameLoop(Window window, int targetFPS) {
        m_engine = Engine.getInstance();
        m_window = window;
        setTargetFPS(targetFPS);
        m_running = false;
    }

    public GameLoop(Window window) {
        this(window, Ribble.FPS_60);
    }

    public void setTargetFPS(int fps) {
        if (fps <= 0) throw new IllegalArgumentException("FPS must be positive.");
        this.targetFPS = fps;
    }

    public int getTargetFPS() {
        return targetFPS;
    }

    public void start() {
        m_running = true;
        GameTime.init();

        double accumulator = 0;

        while (m_running && !m_window.shouldClose()) {
            m_window.pollEvents();

            GameTime.update();
            accumulator += GameTime.getDeltaTime();

            final double frameTime = 1.0 / targetFPS;

            while (accumulator >= frameTime) {
                update();
                accumulator -= frameTime;
            }

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
