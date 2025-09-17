package com.github.mathlon26.ribble.core.graphics.rendering.shaders;

public class TessControlShader extends Shader{

    public TessControlShader(String sourcePath, boolean isMultiShaderFile) {
        super(new ShaderSource(sourcePath, ShaderType.FRAGMENT, isMultiShaderFile));
    }

    public TessControlShader(String sourcePath) {
        this(sourcePath, false);
    }
}