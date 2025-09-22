package com.github.mathlon26.ribble.math.geometry;

public class Rectangle {
    private float x;      // left
    private float y;      // top
    private float width;
    private float height;

    public Rectangle(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getRight() {
        return x + width;
    }

    public float getBottom() {
        return y + height;
    }

    @Override
    public String toString() {
        return "Rectangle[x=" + x + ", y=" + y + ", w=" + width + ", h=" + height + "]";
    }
}
