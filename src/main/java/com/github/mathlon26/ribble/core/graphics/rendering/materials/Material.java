package com.github.mathlon26.ribble.core.graphics.rendering.materials;

import com.github.mathlon26.ribble.core.graphics.rendering.shaders.Shader;
import com.github.mathlon26.ribble.core.graphics.rendering.shaders.ShaderProgram;

import java.util.Map;

public class Material {
    protected final ShaderProgram shader;

    public Material(ShaderProgram shader) {
        this.shader = shader;
    }

    public Material(Shader... shaders) {
        this(new ShaderProgram(shaders));
    }

    public void bind() {
        shader.use();
    }

    public void unbind() {
        shader.unUse();
    }

    public ShaderProgram getShader() { return shader; }
}
