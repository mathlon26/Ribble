package com.github.mathlon26.ribble.core.graphics.rendering.materials;

import com.github.mathlon26.ribble.math.Vec3D;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static com.jogamp.opengl.GL.GL_TEXTURE0;
import static org.lwjgl.opengl.GL12.*;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;
import static org.lwjgl.stb.STBImage.*;

/** 3D texture implementation. */
public class Texture3D extends Texture {

    private final Vec3D<Integer> dimensions;

    public Texture3D(String[] paths) {
        super();
        glBindTexture(GL_TEXTURE_3D, id);

        glTexParameteri(GL_TEXTURE_3D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_3D, GL_TEXTURE_WRAP_T, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_3D, GL_TEXTURE_WRAP_R, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_3D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_3D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

        int width = -1, height = -1, depth = paths.length;
        ByteBuffer[] slices = new ByteBuffer[depth];

        for (int i = 0; i < depth; i++) {
            try (MemoryStack stack = MemoryStack.stackPush()) {
                IntBuffer w = stack.mallocInt(1);
                IntBuffer h = stack.mallocInt(1);
                IntBuffer channels = stack.mallocInt(1);

                stbi_set_flip_vertically_on_load(true);
                slices[i] = stbi_load(paths[i], w, h, channels, 4);
                if (slices[i] == null) throw new RuntimeException("Failed to load texture slice: " + stbi_failure_reason());

                if (i == 0) {
                    width = w.get();
                    height = h.get();
                } else if (w.get() != width || h.get() != height) {
                    throw new RuntimeException("All slices must have the same dimensions");
                }
            }
        }

        this.dimensions = new Vec3D<>(width, height, depth);

        for (int i = 0; i < depth; i++) {
            glTexSubImage3D(GL_TEXTURE_3D, 0, 0, 0, i, width, height, 1, GL_RGBA, GL_UNSIGNED_BYTE, slices[i]);
            stbi_image_free(slices[i]);
        }

        glGenerateMipmap(GL_TEXTURE_3D);
    }

    @Override
    public void bind(int unit) {
        glActiveTexture(GL_TEXTURE0 + unit);
        glBindTexture(GL_TEXTURE_3D, id);
    }

    @Override
    public void unbind() {
        glBindTexture(GL_TEXTURE_3D, 0);
    }

    public Vec3D<Integer> getDimensions() {
        return dimensions;
    }
}
