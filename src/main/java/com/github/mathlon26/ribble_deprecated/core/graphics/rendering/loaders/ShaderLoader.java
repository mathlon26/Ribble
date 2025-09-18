package com.github.mathlon26.ribble_deprecated.core.graphics.rendering.loaders;

import com.github.mathlon26.ribble_deprecated.core.graphics.rendering.shaders.*;
import com.github.mathlon26.ribble_deprecated.util.ResourceUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ShaderLoader {

    public static boolean isShaderFile(String name) {
        return name.endsWith(".vert") || name.endsWith(".frag") || name.endsWith(".geom");
    }

    public static ShaderProgram loadProgram(File shaderFile) {
        String base = ResourceUtils.stripExtension(shaderFile);
        File parent = shaderFile.getParentFile();

        List<Shader> shaders = new ArrayList<>();
        loadIfExists(shaders, parent, base + ".vert", ShaderType.VERTEX);
        loadIfExists(shaders, parent, base + ".frag", ShaderType.FRAGMENT);
        loadIfExists(shaders, parent, base + ".geom", ShaderType.GEOMETRY);

        return new ShaderProgram(shaders.toArray(new Shader[0]));
    }

    private static void loadIfExists(List<Shader> list, File parent, String fileName, ShaderType type) {
        File file = new File(parent, fileName);
        if (file.exists()) {
            ShaderSource src = new ShaderSource("assets/" + file.getName(), type, false);
            list.add(new Shader(src) {});
        }
    }
}
