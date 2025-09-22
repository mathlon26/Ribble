package com.github.mathlon26.ribble.assets;

import com.github.mathlon26.ribble.core.datastructures.Pair;
import com.github.mathlon26.ribble.graphics.shader.ShaderType;
import com.github.mathlon26.ribble.io.output.sys.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static org.lwjgl.opengl.GL20.*;



public class ShaderAssetLoader {

    private Map<String, Integer> shaders;

    private List<Pair<String, Map<ShaderType, String>>> toLoad;
    public ShaderAssetLoader()
    {
        shaders = new HashMap<>();
        toLoad = new LinkedList<>();


    }

    /**
     *
     * @param assetName
     * @param resourcePaths A map
     */
    public void addShader(String assetName, Map<ShaderType, String> resourcePaths)
    {
        toLoad.add(new Pair<>(assetName, resourcePaths));

    }

    public void load()
    {
        for(Pair<String, Map<ShaderType, String>> entry : toLoad)
        {
            shaders.put(entry.first, createShaderProgramFromFiles(entry.second));
        }
    }


    /**
     *
     * @param assetName the name of the shader asset
     * @return the program ID of that shader
     */
    public int getShader(String assetName)
    {
        return shaders.getOrDefault(assetName, 0);
    }


    /**
     * Create a shader program from resource file paths.
     *
     * @param shaderFiles Map of ShaderType -> resource path (e.g. "shaders/basic.vert")
     * @return OpenGL program ID
     */
    private static int createShaderProgramFromFiles(Map<ShaderType, String> shaderFiles) {
        // Load each file into a string
        Map<ShaderType, String> shaderSources = shaderFiles.entrySet().stream()
                .collect(java.util.stream.Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> FileLoader.loadResourceAsString(entry.getValue())
                ));

        return createShaderProgram(shaderSources);
    }

    /**
     * Create a shader program from multiple shader sources.
     *
     * @param shaderSources A map of ShaderType -> GLSL source string.
     * @return The OpenGL shader program ID.
     */
    private static int createShaderProgram(Map<ShaderType, String> shaderSources) {
        int program = glCreateProgram();
        int[] shaders = new int[shaderSources.size()];
        int i = 0;

        // Compile each shader and attach
        for (Map.Entry<ShaderType, String> entry : shaderSources.entrySet()) {
            Logger.getInstance().debug("Compiling shader: " + entry.getKey().toString());
            ShaderType type = entry.getKey();
            String source = entry.getValue();

            int shader = glCreateShader(type.getGlType());
            glShaderSource(shader, source);
            glCompileShader(shader);

            // Check for compile errors
            int success = glGetShaderi(shader, GL_COMPILE_STATUS);
            if (success == GL_FALSE) {
                String log = glGetShaderInfoLog(shader);
                throw new RuntimeException("Error compiling " + type + " shader: " + log);
            }

            glAttachShader(program, shader);
            shaders[i++] = shader;
        }

        // Link program
        Logger.getInstance().debug("Linking shaders");
        glLinkProgram(program);

        int success = glGetProgrami(program, GL_LINK_STATUS);
        if (success == GL_FALSE) {
            String log = glGetProgramInfoLog(program);
            throw new RuntimeException("Error linking shader program: " + log);
        }

        // Cleanup
        for (int shader : shaders) {
            glDetachShader(program, shader);
            glDeleteShader(shader);
        }


        Logger.getInstance().debug("Created shaderProgram with ID: " + program);
        return program;
    }




}
