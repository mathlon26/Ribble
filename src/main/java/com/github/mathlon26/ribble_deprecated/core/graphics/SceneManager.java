package com.github.mathlon26.ribble_deprecated.core.graphics;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class SceneManager {
    private static SceneManager instance;
    private final Map<Class<? extends Scene>, Supplier<? extends Scene>> sceneConstructors = new HashMap<>();
    private Scene currentScene;

    public static SceneManager getInstance() {
        if (instance == null) {
            instance = new SceneManager();
        }

        return instance;
    }

    private SceneManager() {

    }

    public <T extends Scene> void addScene(Class<T> sceneClass, Supplier<T> constructor) {
        sceneConstructors.put(sceneClass, constructor);
    }

    public <T extends Scene> void loadScene(Class<T> sceneClass) {
        Supplier<? extends Scene> constructor = sceneConstructors.get(sceneClass);
        if (constructor != null) {
            currentScene = constructor.get();
        } else {
            throw new IllegalArgumentException("Scene not registered: " + sceneClass.getName());
        }
    }

    @SuppressWarnings("unchecked")
    public <T extends Scene> T getCurrentScene() {
        return (T) currentScene;
    }

    public void updateCurrentScene(double dt) {
        if (currentScene != null) currentScene.update(dt);
    }

    public void renderCurrentScene() {
        if (currentScene != null) currentScene.render();
    }
}
