package com.github.mathlon26.ribble.scene;

import com.github.mathlon26.ribble.ecs.EntityManager;
import com.github.mathlon26.ribble.ecs.system.SystemBase;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene {
    private final List<SystemBase> m_systems;
    private final List<Prefab> m_sceneObjects;

    public Scene() {
        m_systems = new ArrayList<>();
        m_sceneObjects = new ArrayList<>();
        prefabSetup();
        systemSetup();
    }

    protected abstract void prefabSetup();
    protected abstract void systemSetup();



    public List<SystemBase> getSystems() { return m_systems; }
    public List<Prefab> getPrefabs() { return m_sceneObjects; }

    protected <T extends SystemBase> void addSystem(T system) {
        m_systems.add(system);
    }

    protected <T extends Prefab> void addPrefab(T prefab) {
        m_sceneObjects.add(prefab);
    }
}
