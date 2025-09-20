package com.github.mathlon26.ribble.ecs.system;

public class SystemManager {

    private SystemPool m_pool;

    public SystemManager() {

    }

    public void updateAll() {
        m_pool.forEach(System::update);
    }
}
