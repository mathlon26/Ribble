package com.github.mathlon26.ribble.core.graphics.rendering.modeling;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class Mesh {
    private final Vertices vertices;
    private final Indices indices;

    private final int vaoId;
    private final int vboId;
    private final int iboId;
    private final int indexCount;

    public Mesh(Vertices vertices, Indices indices) {
        this.vertices = vertices;
        this.indices = indices;
        this.indexCount = indices.getLength();

        // Generate VAO
        vaoId = glGenVertexArrays();
        glBindVertexArray(vaoId);

        // Vertex buffer
        vboId = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboId);
        glBufferData(GL_ARRAY_BUFFER, vertices.toFloatArray(), GL_STATIC_DRAW);

        // Index buffer
        iboId = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, iboId);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indices.toIntArray(), GL_STATIC_DRAW);

        // Set up vertex attributes dynamically
        int stride = vertices.getStrideInBytes();
        for (Vertices.VertexAttribute attr : vertices.getAttributes()) {
            glVertexAttribPointer(attr.location(), attr.size(), GL_FLOAT, false, stride, (long) attr.offset() * Float.BYTES);
            glEnableVertexAttribArray(attr.location());
        }

        glBindVertexArray(0);
    }

    public void bind() {
        glBindVertexArray(vaoId);
    }

    public void unbind() {
        glBindVertexArray(0);
    }

    public int getIndexCount() {
        return indexCount;
    }

    public void delete() {
        glDeleteBuffers(vboId);
        glDeleteBuffers(iboId);
        glDeleteVertexArrays(vaoId);
    }
}
