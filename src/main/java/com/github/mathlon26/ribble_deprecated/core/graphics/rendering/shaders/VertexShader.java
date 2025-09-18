package com.github.mathlon26.ribble_deprecated.core.graphics.rendering.shaders;

public class VertexShader extends Shader{

    public VertexShader(String sourcePath, boolean isMultiShaderFile) {
        super(new ShaderSource(sourcePath, ShaderType.VERTEX, isMultiShaderFile));
    }

    public VertexShader(String sourcePath) {
        this(sourcePath, false);
    }
}
