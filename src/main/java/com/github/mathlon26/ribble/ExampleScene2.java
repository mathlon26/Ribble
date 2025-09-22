package com.github.mathlon26.ribble;

import com.github.mathlon26.ribble.core.Engine;
import com.github.mathlon26.ribble.io.output.sys.Logger;
import com.github.mathlon26.ribble.managers.SceneManager;
import com.github.mathlon26.ribble.math.physics.Color;
import com.github.mathlon26.ribble.scene.Scene;

public class ExampleScene2 extends Scene {
    public ExampleScene2() {
        super();
    }

    @Override
    protected void prefabSetup() {
        addPrefab(
                new CameraPrefab()
        );
    }

    @Override
    protected void systemSetup() {

    }

    @Override
    public void onLoad() {
        Logger.getInstance().info("ExampleScene 2 loaded");
        Engine.getInstance().getWindow().setBackgroundColor(
                Color.White()
        );
    }

    @Override
    public void onDestroy() {
        Logger.getInstance().info("ExampleScene 2 destroyed");
    }
}
