package com.github.mathlon26.ribble.core;

import com.github.mathlon26.ribble.core.time.GameTime;
import com.github.mathlon26.ribble.graphics.Window;
import com.github.mathlon26.ribble.io.output.sys.Logger;

public class Engine {
    private static Engine m_instance;

    private Logger m_logger;
    private GameLoop m_gameLoop;
    private Window m_window;

    private Engine() {}

    public static Engine getInstance() {
        if (m_instance == null) {
            m_instance = new Engine();
        }
        return m_instance;
    }

    public void start() {
        Config.init();
        m_logger = Logger.getInstance(Config.get("logPath", String.class));
        m_logger.info("Starting Ribble Game Engine");
        m_window = new Window();
        m_gameLoop = new GameLoop(m_window);

        m_window.show();
        m_gameLoop.start();

    }

    public void stop() {
        m_gameLoop.stop();
    }


    public void shutdown() {
        if (m_window != null) {
            m_window.destroy();
        }
        if (m_logger != null) {
            m_logger.close();
        }
    }

    public void updateSystems(double deltaTime) {
    }

    public void renderSystems() {
    }
}
