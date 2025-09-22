package com.github.mathlon26.ribble.assets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class FileLoader {

    /**
     * Load a resource file from a classpath as a String.
     * @param resourcePath Path inside resources (e.g. "shaders/basic.vert")
     * @return File contents as a String
     */
    public static String loadResourceAsString(String resourcePath) {
        try (InputStream is = FileLoader.class.getClassLoader().getResourceAsStream(resourcePath)) {
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
}
