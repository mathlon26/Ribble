package com.github.mathlon26.ribble.core;

import com.github.mathlon26.ribble.assets.AssetLoader;
import com.github.mathlon26.ribble.assets.ShaderAssetLoader;
import com.github.mathlon26.ribble.graphics.shader.ShaderType;
import com.github.mathlon26.ribble.managers.EntityManager;
import com.github.mathlon26.ribble.graphics.Renderer;
import com.github.mathlon26.ribble.graphics.Window;
import com.github.mathlon26.ribble.io.output.sys.Logger;
import com.github.mathlon26.ribble.managers.SceneManager;

import java.util.Map;

import static org.lwjgl.opengl.GL20.glUseProgram;

public class Engine {
    private static Engine m_instance;

    private Logger m_logger;
    private GameLoop m_gameLoop;
    private Window m_window;
    private Renderer m_renderer;

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

        m_logger.info("Started Ribble Game Engine | Engine::start");
        m_logger.info("Loaded settings | Config::init");

        m_window = new Window();
        m_renderer = new Renderer();
        m_gameLoop = new GameLoop(m_window);
        m_window.show();
        // after intialising OpenGL load all the assets that depend on opengl
        AssetLoader.getInstance().loadGLDependant();

        m_logger.info("Window created and shown | Window::show");

        SceneManager.getInstance().loadMainScene();


        m_gameLoop.start();
    }


    public void stop() {
        m_gameLoop.stop();
    }


    public void shutdown() {
        SceneManager.getInstance().destroyCurrentScene();

        if (m_window != null) {
            m_window.destroy();
        }
        if (m_logger != null) {
            Logger.getInstance().info("Game loop ended | GameLoop::cleanUp");
            Logger.getInstance().info("Ribble Game engine stopped | Engine::shutdown");
            m_logger.close();
        }
    }

    public void updateSystems(double deltaTime) {
        EntityManager.getInstance().update(deltaTime);
    }

    public void renderSystems() {
        m_renderer.render();
    }


    // Just for testing
    public Window getWindow() {
        return m_window;
    }
}
