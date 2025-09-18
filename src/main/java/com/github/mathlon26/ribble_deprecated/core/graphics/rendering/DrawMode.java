package com.github.mathlon26.ribble_deprecated.core.graphics.rendering;

import static com.jogamp.opengl.GL.GL_POINTS;
import static com.jogamp.opengl.GL.GL_LINES;
import static com.jogamp.opengl.GL.GL_LINE_STRIP;
import static com.jogamp.opengl.GL.GL_LINE_LOOP;
import static com.jogamp.opengl.GL.GL_TRIANGLES;
import static com.jogamp.opengl.GL.GL_TRIANGLE_STRIP;
import static com.jogamp.opengl.GL.GL_TRIANGLE_FAN;
import static org.lwjgl.opengl.GL11.GL_QUADS;

public enum DrawMode {
    POINTS(GL_POINTS),
    LINES(GL_LINES),
    LINE_STRIP(GL_LINE_STRIP),
    LINE_LOOP(GL_LINE_LOOP),
    TRIANGLES(GL_TRIANGLES),
    TRIANGLE_STRIP(GL_TRIANGLE_STRIP),
    TRIANGLE_FAN(GL_TRIANGLE_FAN),
    QUADS(GL_QUADS);

    private final int glMode;

    DrawMode(int glMode) {
        this.glMode = glMode;
    }

    public int getGlMode() {
        return glMode;
    }
}


/*
GL_POINTS	Each vertex is rendered as a single point
GL_LINES	Each pair of vertices forms a line
GL_LINE_STRIP	Continuous line through all vertices
GL_LINE_LOOP	Like GL_LINE_STRIP, but last vertex connects to the first
GL_TRIANGLES	Every 3 vertices form a triangle
GL_TRIANGLE_STRIP	Creates a strip of connected triangles
GL_TRIANGLE_FAN	Fan-shaped triangles around the first vertex
GL_QUADS (deprecated in core)	Every 4 vertices form a quad
 */