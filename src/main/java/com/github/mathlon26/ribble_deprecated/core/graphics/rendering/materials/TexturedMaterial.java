package com.github.mathlon26.ribble_deprecated.core.graphics.rendering.materials;

import com.github.mathlon26.ribble_deprecated.core.graphics.rendering.shaders.Shader;
import com.github.mathlon26.ribble_deprecated.core.graphics.rendering.shaders.ShaderProgram;

import java.util.HashMap;
import java.util.Map;

public class TexturedMaterial extends Material {
    private Map<String, Texture> textures = new HashMap<>();

    public TexturedMaterial(Map<String, Texture> nameTextureMap, ShaderProgram shaderProgram) {
        super(shaderProgram);
        this.textures = nameTextureMap;
    }

    public TexturedMaterial(Map<String, Texture> nameTextureMap, Shader... shaders) {
        this(nameTextureMap, new ShaderProgram(shaders));
    }

    public TexturedMaterial(Shader... shaders) {
        this(new HashMap<>(), new ShaderProgram(shaders));
    }

    public TexturedMaterial(ShaderProgram shaderProgram) {
        this(new HashMap<>(), shaderProgram);
    }

    public void addTexture(String name, Texture texture) {
        textures.put(name, texture);
    }

    public void removeTexture(String name) {
        textures.remove(name);
    }

    @Override
    public void bind() {
        super.bind();
        int unit = 0;
        for (Map.Entry<String, Texture> entry : textures.entrySet()) {
            entry.getValue().bind(unit);
            shader.setUniform(entry.getKey(), unit);
            unit++;
        }
    }

    @Override
    public void unbind() {
        super.unbind();
        for (Texture tex : textures.values()) tex.unbind();
    }
}
