package com.github.mathlon26.ribble.math;

public class Vector2D {
    private double x;
    private double y;

    // Constructors
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D(double v) {
        this(v, v);
    }

    public Vector2D() {
        this(0);
    }

    // Getters and setters
    public double getX() { return x; }
    public double getY() { return y; }

    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }

    public void setValues(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setValues(double v) {
        setValues(v, v);
    }

    // Addition
    public Vector2D add(Vector2D other) {
        return new Vector2D(this.x + other.x, this.y + other.y);
    }

    public Vector2D add(double v) {
        return new Vector2D(this.x + v, this.y + v);
    }

    public void addInPlace(Vector2D other) {
        this.x += other.x;
        this.y += other.y;
    }

    public void addInPlace(double x, double y) {
        this.x += x;
        this.y += y;
    }

    public void addInPlace(double v) {
        this.x += v;
        this.y += v;
    }

    // Subtraction
    public Vector2D sub(Vector2D other) {
        return new Vector2D(this.x - other.x, this.y - other.y);
    }

    public Vector2D sub(double v) {
        return new Vector2D(this.x - v, this.y - v);
    }

    public void subInPlace(Vector2D other) {
        this.x -= other.x;
        this.y -= other.y;
    }

    public void subInPlace(double x, double y) {
        this.x -= x;
        this.y -= y;
    }

    public void subInPlace(double v) {
        this.x -= v;
        this.y -= v;
    }

    // Multiplication
    public Vector2D mul(double scalar) {
        return new Vector2D(this.x * scalar, this.y * scalar);
    }

    public void mulInPlace(double scalar) {
        this.x *= scalar;
        this.y *= scalar;
    }

    // Division
    public Vector2D div(double scalar) {
        return new Vector2D(this.x / scalar, this.y / scalar);
    }

    public void divInPlace(double scalar) {
        this.x /= scalar;
        this.y /= scalar;
    }

    // Magnitude and normalization
    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    public double magnitudeSquared() {
        return x * x + y * y;
    }

    public Vector2D normalized() {
        double mag = magnitude();
        if (mag == 0) return new Vector2D(0);
        return new Vector2D(x / mag, y / mag);
    }

    public void normalizeInPlace() {
        double mag = magnitude();
        if (mag != 0) {
            x /= mag;
            y /= mag;
        }
    }

    // Dot product
    public double dot(Vector2D other) {
        return this.x * other.x + this.y * other.y;
    }

    // Distance
    public double distance(Vector2D other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double distanceSquared(Vector2D other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return dx * dx + dy * dy;
    }

    // Override toString
    @Override
    public String toString() {
        return "Vector2D{" + "x=" + x + ", y=" + y + '}';
    }

    // Equality check
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Vector2D other)) return false;
        return Double.compare(x, other.x) == 0 && Double.compare(y, other.y) == 0;
    }
}
