package com.github.mathlon26.ribble;

import com.github.mathlon26.ribble.ecs.component.components.Camera2DComponent;
import com.github.mathlon26.ribble.ecs.component.components.Transform2DComponent;
import com.github.mathlon26.ribble.graphics.camera.Camera2D;
import com.github.mathlon26.ribble.io.output.sys.Logger;
import com.github.mathlon26.ribble.math.Transform2D;
import com.github.mathlon26.ribble.scene.Prefab;

public class CameraPrefab extends Prefab {
    @Override
    protected void componentSetup() {
        addComponent(
                new Camera2DComponent(
                        new Camera2D()
                )
        );

        addComponent(
                new Transform2DComponent(
                        new Transform2D()
                )
        );
    }

    @Override
    public void onLoad() {

    }

    @Override
    public void onDestroy() {

    }
}
