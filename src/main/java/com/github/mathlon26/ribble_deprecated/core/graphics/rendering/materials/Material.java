package com.github.mathlon26.ribble_deprecated.core.graphics.rendering.materials;

import com.github.mathlon26.ribble_deprecated.core.graphics.rendering.shaders.Shader;
import com.github.mathlon26.ribble_deprecated.core.graphics.rendering.shaders.ShaderProgram;

public class Material {
    protected final ShaderProgram shader;

    public Material(int shaderProgramId) {
        this.shader = new ShaderProgram(shaderProgramId);
    }

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
