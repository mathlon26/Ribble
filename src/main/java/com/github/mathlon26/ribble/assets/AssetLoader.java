package com.github.mathlon26.ribble.assets;

import com.github.mathlon26.ribble.graphics.shader.ShaderType;

import java.util.Map;

public class AssetLoader {

    public AssetLoader() {}

    private static AssetLoader s_instance;


    private ShaderAssetLoader shaderAssetLoader = new ShaderAssetLoader();

    public static AssetLoader getInstance(){
        if (s_instance == null){
            s_instance = new AssetLoader();
        }
        return s_instance;
    }

    public void loadShader(String assetName, Map<ShaderType, String> resourcePaths)
    {
        shaderAssetLoader.addShader(assetName, resourcePaths);
    }
    public int getShader(String assetName)
    {
        return shaderAssetLoader.getShader(assetName);
    }


    public void loadGLDependant()
    {
        shaderAssetLoader.load();
    }
}
