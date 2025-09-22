package com.github.mathlon26.ribble;

import com.github.mathlon26.ribble.assets.AssetLoader;
import com.github.mathlon26.ribble.assets.ShaderAssetLoader;
import com.github.mathlon26.ribble.core.RibbleGame;
import com.github.mathlon26.ribble.graphics.shader.ShaderType;

import java.util.Map;

public class ExampleGame extends RibbleGame {
    public ExampleGame() {
        super();

        setMainScene(
                new ExampleScene()
        );

        AssetLoader.getInstance().loadShader("TestShader", Map.of(
                ShaderType.VERTEX, "shaders/default/shader.vert",
                ShaderType.FRAGMENT, "shaders/default/shader.frag"
        ));

        start();
    }
}
