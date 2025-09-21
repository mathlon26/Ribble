package com.github.mathlon26.ribble.core;

import com.github.mathlon26.ribble.managers.SceneManager;
import com.github.mathlon26.ribble.scene.Scene;

public class RibbleGame {
    private final Engine m_engine;

    public RibbleGame() {
        m_engine = Engine.getInstance();
    }

    public void setMainScene(Scene scene) {
        SceneManager.getInstance().setMainScene(scene);
    }

    protected void start() {
        m_engine.start();
    }

    protected void stop() {
        m_engine.stop();
    }
}
