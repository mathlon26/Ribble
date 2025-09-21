package com.github.mathlon26.ribble;

import com.github.mathlon26.ribble.ecs.component.TransformComponent;
import com.github.mathlon26.ribble.math.Transform;
import com.github.mathlon26.ribble.scene.Prefab;

public class ExamplePrefab extends Prefab {

    public ExamplePrefab() {
        super();
    }

    @Override
    protected void componentSetup() {
        addComponent(
                new TransformComponent(
                        new Transform()
                )
        );

        addComponent(
                new ExampleComponent()
        );
    }

    @Override
    public void onLoad() {

    }

    @Override
    public void onDestroy() {

    }
}
