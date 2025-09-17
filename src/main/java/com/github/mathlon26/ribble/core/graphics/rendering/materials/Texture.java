package com.github.mathlon26.ribble.core.graphics.rendering.materials;

import static org.lwjgl.opengl.GL11.*;


/**
 * Base abstract class for textures.
 */
public abstract class Texture {
    protected final int id;

    protected Texture() {
        this.id = glGenTextures();
    }

    /** Bind texture to a texture unit. */
    public abstract void bind(int unit);

    /** Unbind texture. */
    public abstract void unbind();

    public int getId() {
        return id;
    }

    public void delete() {
        glDeleteTextures(id);
    }
}
