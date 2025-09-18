package com.github.mathlon26.ribble_deprecated.core.graphics.rendering.shaders;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL20.*;

public class ShaderProgram {
    private final int programId;
    private final List<Shader> shaderList;

    public ShaderProgram(Shader... shaders) {
        this.shaderList = new ArrayList<>();
        programId = glCreateProgram();
        addShaders(shaders);
        linkAndValidate();
    }

    public ShaderProgram(int id) {
        this.shaderList = new ArrayList<>();
        programId = id;
    }

    private void addShader(Shader shader) {
        if (shader.getShaderId() == 0) {
            shader.compile();
        }
        this.shaderList.add(shader);
        glAttachShader(programId, shader.getShaderId());
    }

    private void addShaders(Shader... shaders) {
        for (Shader shader : shaders) {
            addShader(shader);
        }
    }

    private void linkAndValidate() {
        link();

        glValidateProgram(programId);
        int validateStatus = glGetProgrami(programId, GL_VALIDATE_STATUS);
        if (validateStatus == 0) {
            String log = glGetProgramInfoLog(programId);
            System.err.println("Shader program validation warning: " + log);
        }
    }

    private void link() {
        glLinkProgram(programId);

        int status = glGetProgrami(programId, GL_LINK_STATUS);
        if (status == 0) {
            String log = glGetProgramInfoLog(programId);
            throw new RuntimeException("Failed to link shader program: " + log);
        }
    }

    public void use() {
        glUseProgram(programId);
    }

    public void unUse() {
        glUseProgram(0);
    }

    public void delete() {
        for (Shader shader : this.shaderList) {
            shader.delete();
        }
        glDeleteProgram(programId);
    }

    public int getId() {
        return programId;
    }

    public void setUniform(String key, int unit) {
    }
}
