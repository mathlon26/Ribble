package com.github.mathlon26.ribble.ecs.system;


import com.github.mathlon26.ribble.io.output.sys.ExceptionHandler;

public class SystemManager {
    private SystemPool m_pool;

    public SystemManager() {

    }

    public void updateAll() {
        m_pool.forEach(System::update);
    }
}
