package com.github.mathlon26.ribble.core.graphics.rendering;

import com.github.mathlon26.ribble.core.graphics.rendering.modeling.Mesh;
import com.github.mathlon26.ribble.core.graphics.rendering.shaders.ShaderProgram;

import static org.lwjgl.opengl.GL11.*;

public class Renderable {
    protected final ShaderProgram shaderProgram;
    protected final Mesh mesh;
    protected final DrawMode drawMode;

    public Renderable(Mesh mesh, ShaderProgram shaderProgram, DrawMode drawMode) {
        this.mesh = mesh;
        this.shaderProgram = shaderProgram;
        this.drawMode = drawMode;
        this.shaderProgram.linkAndValidate();
    }

    /**
     * Draws the renderable. Subclasses can override to set uniforms, etc.
     */
    public void draw() {
        shaderProgram.use();
        mesh.bind();

        onDraw();

        glDrawElements(drawMode.getGlMode(), mesh.getIndexCount(), GL_UNSIGNED_INT, 0L);

        mesh.unbind();
    }

    /**
     * Called before drawing. Useful for setting shader uniforms.
     */
    protected void onDraw() {}

    public ShaderProgram getShaderProgram() {
        return shaderProgram;
    }

    public Mesh getMesh() {
        return mesh;
    }
}
