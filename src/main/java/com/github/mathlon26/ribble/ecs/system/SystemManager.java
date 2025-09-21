package com.github.mathlon26.ribble.ecs.system;

import java.util.HashSet;
import java.util.Set;

public class SystemManager {

    private Set<System> systemPool = new HashSet<>();

    public SystemManager() {

    }

    public void addSystem(System system)
    {
        if(system != null)
        {
            systemPool.add(system);
            system.start();
        }
    }


    public void startAll()
    {
        for(System system : systemPool)
        {
            system.start();
        }
    }

    public void updateAll() {
        for(System system : systemPool)
        {
            system.update();
        }
    }

    public void destroyAll()
    {
        for(System system : systemPool)
        {
            system.destroy();
        }
        // Remove all systems from the set
        systemPool = new HashSet<>();
    }

}
