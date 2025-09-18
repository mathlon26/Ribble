package com.github.mathlon26.ribble_deprecated.core.graphics.rendering.materials;

import com.github.mathlon26.ribble_deprecated.math.Vec2D;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static com.jogamp.opengl.GL.GL_TEXTURE0;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;
import static org.lwjgl.stb.STBImage.*;

/** 2D texture implementation. */
public class Texture2D extends Texture {

    private final Vec2D<Integer> dimensions;

    public Texture2D(String path) {
        super();
        glBindTexture(GL_TEXTURE_2D, id);

        // Texture parameters
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

        ByteBuffer image;
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);
            IntBuffer channels = stack.mallocInt(1);

            stbi_set_flip_vertically_on_load(true);
            image = stbi_load(path, w, h, channels, 4);
            if (image == null) throw new RuntimeException("Failed to load texture: " + stbi_failure_reason());

            this.dimensions = new Vec2D<>(w.get(), h.get());
        }

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, dimensions.getX(), dimensions.getY(), 0, GL_RGBA, GL_UNSIGNED_BYTE, image);
        glGenerateMipmap(GL_TEXTURE_2D);
        stbi_image_free(image);
    }

    @Override
    public void bind(int unit) {
        glActiveTexture(GL_TEXTURE0 + unit);
        glBindTexture(GL_TEXTURE_2D, id);
    }

    @Override
    public void unbind() {
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public Vec2D<Integer> getDimensions() {
        return dimensions;
    }
}
