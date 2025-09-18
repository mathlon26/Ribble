package com.github.mathlon26.ribble_deprecated.core.graphics.rendering.shaders;

import static org.lwjgl.opengl.GL20.*;

public abstract class Shader {
    private final ShaderSource source;
    private int shaderId = 0;

    public Shader(ShaderSource source) {
        this.source = source;
    }

    /**
     * Compile the shader into an OpenGL shader object.
     */
    public void compile() {
        shaderId = glCreateShader(source.getShaderType().getGlType());
        glShaderSource(shaderId, source.getSourceCode());
        glCompileShader(shaderId);

        int status = glGetShaderi(shaderId, GL_COMPILE_STATUS);
        if (status == 0) {
            String log = glGetShaderInfoLog(shaderId);
            throw new RuntimeException("Failed to compile shader: " + log);
        }
    }

    public int getShaderId() {
        return shaderId;
    }

    public void delete() {
        if (shaderId != 0) {
            glDeleteShader(shaderId);
            shaderId = 0;
        }
    }
}

