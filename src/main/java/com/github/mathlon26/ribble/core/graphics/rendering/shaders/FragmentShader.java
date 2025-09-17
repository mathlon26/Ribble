package com.github.mathlon26.ribble.core.graphics.rendering.shaders;

public class FragmentShader extends Shader{

    public FragmentShader(String sourcePath, boolean isMultiShaderFile) {
        super(new ShaderSource(sourcePath, ShaderType.FRAGMENT, isMultiShaderFile));
    }

    public FragmentShader(String sourcePath) {
        this(sourcePath, false);
    }
}