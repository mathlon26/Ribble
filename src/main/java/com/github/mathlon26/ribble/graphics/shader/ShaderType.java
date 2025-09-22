package com.github.mathlon26.ribble.graphics.shader;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL32.*; // for geometry, tessellation, etc.
import static org.lwjgl.opengl.GL40.*;

public enum ShaderType {
    VERTEX(GL_VERTEX_SHADER),
    FRAGMENT(GL_FRAGMENT_SHADER),
    GEOMETRY(GL_GEOMETRY_SHADER),
    TESS_CONTROL(GL_TESS_CONTROL_SHADER),
    TESS_EVALUATION(GL_TESS_EVALUATION_SHADER);

    private final int glType;

    ShaderType(int glType) {
        this.glType = glType;
    }

    public int getGlType() {
        return glType;
    }
}
