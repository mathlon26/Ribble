package com.github.mathlon26.ribble_deprecated.core.graphics.rendering.loaders;

import com.github.mathlon26.ribble_deprecated.core.graphics.rendering.materials.*;
import com.github.mathlon26.ribble_deprecated.core.graphics.rendering.shaders.ShaderProgram;
import com.github.mathlon26.ribble_deprecated.util.ResourceUtils;

import java.io.File;
import java.util.*;

/**
 * Orchestrates loading of all assets from resources path.
 */
public class AssetLoader {

    private final Map<String, Texture2D> textures2D = new HashMap<>();
    private final Map<String, Texture3D> textures3D = new HashMap<>();
    private final Map<String, ShaderProgram> shaders = new HashMap<>();
    private final Map<String, Material> materials = new HashMap<>();

    public void loadAll(String resourcesPath) {
        File root = ResourceUtils.getResourceFile(resourcesPath);
        assert root != null;
        scanDirectory(root);
    }

    private void scanDirectory(File dir) {
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            if (file.isDirectory()) scanDirectory(file);
            else classifyAndLoad(file);
        }
    }

    private void classifyAndLoad(File file) {
        String name = file.getName().toLowerCase();

        if (TextureLoader.is2DTexture(name)) {
            textures2D.put(ResourceUtils.stripExtension(file), TextureLoader.load2D(file));
        } else if (TextureLoader.is3DTexture(name)) {
            textures3D.put(ResourceUtils.stripExtension(file), TextureLoader.load3D(file));
        } else if (ShaderLoader.isShaderFile(name)) {
            ShaderProgram prog = ShaderLoader.loadProgram(file);
            String base = ResourceUtils.stripExtension(file);
            shaders.put(base, prog);
            materials.put(base, new Material(prog));
        }
    }

    // === Public Getters ===
    public Texture2D getTexture2D(String name) { return textures2D.get(name); }
    public Texture3D getTexture3D(String name) { return textures3D.get(name); }
    public ShaderProgram getShader(String name) { return shaders.get(name); }
    public Material getMaterial(String name) { return materials.get(name); }
}
