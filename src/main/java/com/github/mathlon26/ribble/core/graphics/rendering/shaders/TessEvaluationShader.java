package com.github.mathlon26.ribble.core.graphics.rendering.shaders;

public class TessEvaluationShader extends Shader{

    public TessEvaluationShader(String sourcePath, boolean isMultiShaderFile) {
        super(new ShaderSource(sourcePath, ShaderType.FRAGMENT, isMultiShaderFile));
    }

    public TessEvaluationShader(String sourcePath) {
        this(sourcePath, false);
    }
}