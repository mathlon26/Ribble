package com.github.mathlon26.ribble.managers;

import com.github.mathlon26.ribble.managers.EntityManager;
import com.github.mathlon26.ribble.ecs.component.Component;
import com.github.mathlon26.ribble.ecs.entity.Entity;
import com.github.mathlon26.ribble.ecs.system.SystemBase;
import com.github.mathlon26.ribble.scene.Prefab;
import com.github.mathlon26.ribble.scene.Scene;

import java.util.Collection;

public class SceneManager {
    private static SceneManager s_instance;
    private final EntityManager m_entityManager = EntityManager.getInstance();
    private Scene m_mainScene = null;
    private Scene m_currentScene = null;

    public static SceneManager getInstance() {
        if (s_instance == null) {
            s_instance = new SceneManager();
        }

        return s_instance;
    }

    private SceneManager() {}

    public void setMainScene(Scene scene) { m_mainScene = scene; }
    public Scene getMainScene() { return m_mainScene; }


    public void loadMainScene() {
        loadScene(m_mainScene);
    }

    public void reloadCurrentScene() {
        loadScene(m_currentScene);
    }

    public void loadScene(Scene scene) {
        destroyCurrentScene();
        m_currentScene = scene;
        loadCurrentScene();
    }

    public void destroyCurrentScene() {

        if (m_currentScene != null) {
            m_currentScene.onDestroy();
            for (Prefab prefab : m_currentScene.getPrefabs())
                prefab.onDestroy();
        }
        m_entityManager.destroyCurrentSystems();
        m_entityManager.destroyCurrentComponents();
        m_entityManager.destroyCurrentEntities();
        m_currentScene = null;
    }

    private void loadCurrentScene() {

        Collection<SystemBase> systems = m_currentScene.getSystems();
        for (SystemBase s : systems)
            m_entityManager.addSystem(s);

        Collection<Prefab> prefabs = m_currentScene.getPrefabs();
        for (Prefab prefab : prefabs) {
            Entity entity = m_entityManager.createEntity();
            for (Component component : prefab.getComponents())
                m_entityManager.addComponentToEntity(component, entity);
            prefab.setEntity(entity);

            prefab.onLoad();

        }

        m_currentScene.onLoad();
    }

}
