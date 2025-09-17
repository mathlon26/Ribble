package com.github.mathlon26.ribble.core.graphics.shaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class ShaderSource {
    private static final String END_KEY = "#end";
    private static final String BEGIN_KEY = "#begin";

    private final ShaderType shaderType;
    private final String sourceCode;

    /**
     * Load a specific section of a shader from a classpath resource.
     */
    public ShaderSource(String resourcePath, ShaderType type, String keyValue) {
        this.shaderType = type;
        this.sourceCode = extractSection(loadResource(resourcePath), keyValue);
    }

    /**
     * Load an entire shader file or a section from a multi-shader file.
     */
    public ShaderSource(String resourcePath, ShaderType type, boolean isMultiShaderFile) {
        this.shaderType = type;
        String content = loadResource(resourcePath);

        if (isMultiShaderFile) {
            this.sourceCode = extractSection(content, type.getStandardKey());
        } else {
            this.sourceCode = content;
        }
    }

    public ShaderType getShaderType() {
        return shaderType;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    /**
     * Load the shader file from the classpath as a String.
     */
    private String loadResource(String resourcePath) {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
            if (is == null) {
                throw new RuntimeException("Shader resource not found: " + resourcePath);
            }
            return new BufferedReader(new InputStreamReader(is))
                    .lines()
                    .collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read shader resource: " + resourcePath, e);
        }
    }

    /**
     * Extract a section from the shader content (multi-shader file support).
     */
    private String extractSection(String content, String keyValue) {
        String[] lines = content.split("\n");
        String beginMarker = BEGIN_KEY + " " + keyValue;
        StringBuilder builder = new StringBuilder();
        boolean recording = false;

        for (String line : lines) {
            if (line.trim().equals(beginMarker)) {
                recording = true;
                continue;
            }
            if (line.trim().equals(END_KEY) && recording) {
                break;
            }
            if (recording) {
                builder.append(line).append("\n");
            }
        }

        if (builder.isEmpty()) {
            throw new IllegalArgumentException("Section " + keyValue + " not found in shader content");
        }

        return builder.toString();
    }
}
