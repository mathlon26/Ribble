package com.github.mathlon26.ribble.core.graphics.rendering.modeling;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents vertex data with interleaved attributes (position, normals, texcoords, etc.)
 */
public class Vertices {
    private float[] data;
    private final List<VertexAttribute> attributes = new ArrayList<>();

    /**
     * Represents a single vertex attribute layout.
     *
     * @param location shader attribute location
     * @param size     number of floats (e.g., 3 for vec3)
     * @param offset   offset in floats within vertex
     */
    public record VertexAttribute(int location, int size, int offset) {}

    /**
     * Create a Vertices object with interleaved data.
     * @param data the interleaved vertex data
     */
    public Vertices(float... data) {
        this.data = data;
    }

    /**
     * Add a vertex attribute to describe layout in the interleaved array.
     * @param location shader attribute location
     * @param size number of floats (e.g., 3 for vec3)
     * @param offset offset in floats from start of vertex
     */
    public void addAttribute(int location, int size, int offset) {
        attributes.add(new VertexAttribute(location, size, offset));
    }

    /**
     * Get all attributes.
     */
    public List<VertexAttribute> getAttributes() {
        return attributes;
    }

    /**
     * Returns the stride in floats (sum of all attribute sizes per vertex).
     */
    public int getStrideInFloats() {
        return attributes.stream().mapToInt(attr -> attr.size).sum();
    }

    /**
     * Returns the stride in bytes.
     */
    public int getStrideInBytes() {
        return getStrideInFloats() * Float.BYTES;
    }

    /**
     * Return interleaved float array.
     */
    public float[] toFloatArray() {
        return data;
    }

    public void setData(float... data) {
        this.data = data;
    }

    public static Vertices from(float... data) {
        return new Vertices(data);
    }

    /**
     * Number of vertices, calculated from total floats and stride.
     */
    public int getVertexCount() {
        if (getStrideInFloats() == 0) return 0;
        return data.length / getStrideInFloats();
    }
}
