package com.github.mathlon26.ribble;

import com.github.mathlon26.ribble.ecs.component.components.Transform3DComponent;
import com.github.mathlon26.ribble.math.Transform3D;
import com.github.mathlon26.ribble.scene.Prefab;

public class ExamplePrefab extends Prefab {

    public ExamplePrefab() {
        super();
    }

    @Override
    protected void componentSetup() {
        addComponent(
                new Transform3DComponent(
                        new Transform3D()
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
