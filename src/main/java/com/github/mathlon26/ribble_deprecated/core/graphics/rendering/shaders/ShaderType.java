package com.github.mathlon26.ribble_deprecated.core.graphics.rendering.shaders;


import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL32.GL_GEOMETRY_SHADER;
import static org.lwjgl.opengl.GL40.GL_TESS_CONTROL_SHADER;
import static org.lwjgl.opengl.GL40.GL_TESS_EVALUATION_SHADER;
import static org.lwjgl.opengl.GL43.GL_COMPUTE_SHADER;

import org.jetbrains.annotations.Nullable;

public enum ShaderType {
    VERTEX(GL_VERTEX_SHADER),
    FRAGMENT(GL_FRAGMENT_SHADER),
    GEOMETRY(GL_GEOMETRY_SHADER),
    TESS_CONTROL(GL_TESS_CONTROL_SHADER),
    TESS_EVALUATION(GL_TESS_EVALUATION_SHADER),
    COMPUTE(GL_COMPUTE_SHADER);

    private static String standardVertexKey = "Vertex";
    private static String standardFragmentKey = "Fragment";
    private static String standardGeometryKey = "Geometry";
    private static String standardTessControlKey = "TessControl";
    private static String standardTessEvaluationKey = "TessEvaluation";
    private static String standardComputeKey = "Compute";

    private final int glType;

    ShaderType(int glType) {
        this.glType = glType;
    }

    public static String getStandardVertexKey() {
        return standardVertexKey;
    }

    public static void setStandardVertexKey(String standardVertexKey) {
        ShaderType.standardVertexKey = standardVertexKey;
    }

    public static String getStandardFragmentKey() {
        return standardFragmentKey;
    }

    public static void setStandardFragmentKey(String standardFragmentKey) {
        ShaderType.standardFragmentKey = standardFragmentKey;
    }

    public static String getStandardGeometryKey() {
        return standardGeometryKey;
    }

    public static void setStandardGeometryKey(String standardGeometryKey) {
        ShaderType.standardGeometryKey = standardGeometryKey;
    }

    public static String getStandardTessControlKey() {
        return standardTessControlKey;
    }

    public static void setStandardTessControlKey(String standardTessControlKey) {
        ShaderType.standardTessControlKey = standardTessControlKey;
    }

    public static String getStandardTessEvaluationKey() {
        return standardTessEvaluationKey;
    }

    public static void setStandardTessEvaluationKey(String standardTessEvaluationKey) {
        ShaderType.standardTessEvaluationKey = standardTessEvaluationKey;
    }

    public static String getStandardComputeKey() {
        return standardComputeKey;
    }

    public static void setStandardComputeKey(String standardComputeKey) {
        ShaderType.standardComputeKey = standardComputeKey;
    }


    public int getGlType() {
        return glType;
    }

    @Nullable
    public String getStandardKey() {
        if (glType == VERTEX.getGlType()) {
            return standardVertexKey;
        } else if (glType == FRAGMENT.getGlType()) {
            return standardFragmentKey;
        } else if (glType == GEOMETRY.getGlType()) {
            return standardGeometryKey;
        } else if (glType == TESS_CONTROL.getGlType()) {
            return standardTessControlKey;
        } else if (glType == TESS_EVALUATION.getGlType()) {
            return standardTessEvaluationKey;
        } else if (glType == COMPUTE.getGlType()) {
            return standardComputeKey;
        }

        return null;
    }

}
