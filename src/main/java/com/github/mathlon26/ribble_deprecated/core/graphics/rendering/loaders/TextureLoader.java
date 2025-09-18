package com.github.mathlon26.ribble_deprecated.core.graphics.rendering.loaders;

import com.github.mathlon26.ribble_deprecated.core.graphics.rendering.materials.Texture2D;
import com.github.mathlon26.ribble_deprecated.core.graphics.rendering.materials.Texture3D;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class TextureLoader {

    public static boolean is2DTexture(String name) {
        return name.endsWith(".png") || name.endsWith(".jpg") || name.endsWith(".jpeg");
    }

    public static boolean is3DTexture(String name) {
        return name.endsWith(".t3d");
    }

    public static Texture2D load2D(File file) {
        return new Texture2D(file.getAbsolutePath());
    }

    public static Texture3D load3D(File file) {
        try {
            List<String> slicePaths = Files.readAllLines(file.toPath());
            return new Texture3D(slicePaths.toArray(new String[0]));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load 3D texture: " + file.getName(), e);
        }
    }
}
