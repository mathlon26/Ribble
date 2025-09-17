package com.github.mathlon26.ribble.core.graphics.shaders;

public class GeometryShader extends Shader{

    public GeometryShader(String sourcePath, boolean isMultiShaderFile) {
        super(new ShaderSource(sourcePath, ShaderType.FRAGMENT, isMultiShaderFile));
    }

    public GeometryShader(String sourcePath) {
        this(sourcePath, false);
    }
}