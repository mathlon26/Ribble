package com.github.mathlon26.ribble_deprecated.core.graphics.rendering.shaders;

public class ComputeShader extends Shader{

    public ComputeShader(String sourcePath, boolean isMultiShaderFile) {
        super(new ShaderSource(sourcePath, ShaderType.FRAGMENT, isMultiShaderFile));
    }

    public ComputeShader(String sourcePath) {
        this(sourcePath, false);
    }
}