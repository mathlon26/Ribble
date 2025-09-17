package com.github.mathlon26.ribble.core.graphics.rendering.shaders;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL20.*;

public class ShaderProgram {
    private final int programId;
    private List<Shader> shaderList;

    public ShaderProgram(Shader... shaders) {
        this.shaderList = new ArrayList<>();
        programId = glCreateProgram();
        addShaders(shaders);
    }

    public void addShader(Shader shader) {
        if (shader.getShaderId() == 0) {
            shader.compile();
        }
        this.shaderList.add(shader);
        glAttachShader(programId, shader.getShaderId());
    }

    public void addShaders(Shader... shaders) {
        for (Shader shader : shaders) {
            addShader(shader);
        }
    }

    public void linkAndValidate() {
        link(); // existing link method

        glValidateProgram(programId);
        int validateStatus = glGetProgrami(programId, GL_VALIDATE_STATUS);
        if (validateStatus == 0) {
            String log = glGetProgramInfoLog(programId);
            System.err.println("Shader program validation warning: " + log);
        }
    }

    public void use() {
        glUseProgram(programId);
    }

    public void delete() {
        for (Shader shader : this.shaderList) {
            shader.delete();
        }
        glDeleteProgram(programId);
    }

    private void link() {
        glLinkProgram(programId);

        int status = glGetProgrami(programId, GL_LINK_STATUS);
        if (status == 0) {
            String log = glGetProgramInfoLog(programId);
            throw new RuntimeException("Failed to link shader program: " + log);
        }
    }
}
