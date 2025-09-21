package com.github.mathlon26.ribble.ecs.system;

import java.util.HashSet;
import java.util.Set;

public class SystemManager {

    private Set<SystemBase> systemBasePool = new HashSet<>();

    public SystemManager() {

    }

    public void addSystem(SystemBase systemBase) {
        if(systemBase != null) {
            systemBasePool.add(systemBase);
        }
    }


    public void startAll() {
        for(SystemBase systemBase : systemBasePool) {
            systemBase.start();
        }
    }

    public void updateAll(double deltatime) {
        for(SystemBase systemBase : systemBasePool) {
            systemBase.update(deltatime);
        }
    }

    public void destroyAll()
    {
        for(SystemBase systemBase : systemBasePool) {
            systemBase.destroy();
        }
        // Remove all systems from the set
        systemBasePool = new HashSet<>();
    }

}
