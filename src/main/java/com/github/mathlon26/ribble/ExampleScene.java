package com.github.mathlon26.ribble;

import com.github.mathlon26.ribble.scene.Scene;

public class ExampleScene extends Scene {
    public ExampleScene() {
        super();
    }

    @Override
    protected void prefabSetup() {
        addPrefab(
                new ExamplePrefab()
        );
    }

    @Override
    protected void systemSetup() {
        addSystem(
                new ExampleSystem()
        );
    }
}
