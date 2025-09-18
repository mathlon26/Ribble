package com.github.mathlon26.ribble.math.physics;

import com.github.mathlon26.ribble.math.Vector4D;

public class Color {
    private Vector4D rgba;

    // Constructors
    public Color(float r, float g, float b, float a) {
        this.rgba = new Vector4D(r, g, b, a);
    }

    public Color(float r, float g, float b) {
        this(r, g, b, 1.0f); // default alpha
    }

    public Color(float value) {
        this(value, value, value, 1.0f); // grayscale
    }

    public Color() {
        this(0f, 0f, 0f, 1f); // default black
    }

    // Getters
    public float getR() { return (float) rgba.getX(); }
    public float getG() { return (float) rgba.getY(); }
    public float getB() { return (float) rgba.getZ(); }
    public float getA() { return (float) rgba.getW(); }

    // Setters
    public void setR(float r) { rgba.setX(r); }
    public void setG(float g) { rgba.setY(g); }
    public void setB(float b) { rgba.setZ(b); }
    public void setA(float a) { rgba.setW(a); }

    public void set(float r, float g, float b, float a) { rgba.setValues(r, g, b, a); }
    public void set(float r, float g, float b) { set(r, g, b, 1f); }

    // Operations
    public Color add(Color other) {
        return new Color(rgba.add(other.rgba));
    }

    public void addInPlace(Color other) {
        rgba.addInPlace(other.rgba);
    }

    public Color mul(float scalar) {
        return new Color(rgba.mul(scalar));
    }

    public void mulInPlace(float scalar) {
        rgba.mulInPlace(scalar);
    }

    public void blendWith(Color c) {
        float r = (getR() + c.getR()) / 2f;
        float g = (getG() + c.getG()) / 2f;
        float b = (getB() + c.getB()) / 2f;
        float a = (getA() + c.getA()) / 2f;
        this.rgba = new Vector4D(r, g, b, a);
    }

    // Conversion
    public Vector4D toVector4D() {
        return rgba;
    }

    @Override
    public String toString() {
        return "Color{" + "r=" + getR() + ", g=" + getG() + ", b=" + getB() + ", a=" + getA() + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Color other)) return false;
        return rgba.equals(other.rgba);
    }

    // Constructor from Vector4D
    public Color(Vector4D vec) {
        this.rgba = vec;
    }

    public static Color Red() {
        return new Color(1f, 0f, 0f);
    }

    public static Color Orange() {
        return new Color(1f, 0.5f, 0f);
    }

    public static Color Yellow() {
        return new Color(1f, 1f, 0f);
    }

    public static Color Green() {
        return new Color(0f, 1f, 0f);
    }

    public static Color Blue() {
        return new Color(0f, 0f, 1f);
    }

    public static Color Indigo() {
        return new Color(0.29f, 0f, 0.51f); // approximate
    }

    public static Color Violet() {
        return new Color(0.56f, 0f, 1f); // approximate
    }

    public static Color White() {
        return new Color(1f, 1f, 1f);
    }

    public static Color Black() {
        return new Color(0f, 0f, 0f);
    }
}
