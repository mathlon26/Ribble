package com.github.mathlon26.ribble.core;

public class RibbleGame {
    private final Engine m_engine;

    public RibbleGame() {
        m_engine = Engine.getInstance();
    }

    protected void start() {
        m_engine.start();
    }

    protected void stop() {
        m_engine.stop();
    }
}
