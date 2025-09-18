package com.github.mathlon26.ribble.math;

public class Vector4D {
    private double x;
    private double y;
    private double z;
    private double w;

    // Constructors
    public Vector4D(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vector4D(double v) {
        this(v, v, v, v);
    }

    public Vector4D() {
        this(0);
    }

    // Getters and setters
    public double getX() { return x; }
    public double getY() { return y; }
    public double getZ() { return z; }
    public double getW() { return w; }

    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }
    public void setZ(double z) { this.z = z; }
    public void setW(double w) { this.w = w; }

    public void setValues(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public void setValues(double v) {
        setValues(v, v, v, v);
    }

    // Addition
    public Vector4D add(Vector4D other) {
        return new Vector4D(this.x + other.x, this.y + other.y, this.z + other.z, this.w + other.w);
    }

    public Vector4D add(double v) {
        return new Vector4D(this.x + v, this.y + v, this.z + v, this.w + v);
    }

    public void addInPlace(Vector4D other) {
        this.x += other.x;
        this.y += other.y;
        this.z += other.z;
        this.w += other.w;
    }

    public void addInPlace(double x, double y, double z, double w) {
        this.x += x;
        this.y += y;
        this.z += z;
        this.w += w;
    }

    public void addInPlace(double v) {
        addInPlace(v, v, v, v);
    }

    // Subtraction
    public Vector4D sub(Vector4D other) {
        return new Vector4D(this.x - other.x, this.y - other.y, this.z - other.z, this.w - other.w);
    }

    public Vector4D sub(double v) {
        return new Vector4D(this.x - v, this.y - v, this.z - v, this.w - v);
    }

    public void subInPlace(Vector4D other) {
        this.x -= other.x;
        this.y -= other.y;
        this.z -= other.z;
        this.w -= other.w;
    }

    public void subInPlace(double x, double y, double z, double w) {
        this.x -= x;
        this.y -= y;
        this.z -= z;
        this.w -= w;
    }

    public void subInPlace(double v) {
        subInPlace(v, v, v, v);
    }

    // Multiplication
    public Vector4D mul(double scalar) {
        return new Vector4D(this.x * scalar, this.y * scalar, this.z * scalar, this.w * scalar);
    }

    public void mulInPlace(double scalar) {
        this.x *= scalar;
        this.y *= scalar;
        this.z *= scalar;
        this.w *= scalar;
    }

    // Division
    public Vector4D div(double scalar) {
        return new Vector4D(this.x / scalar, this.y / scalar, this.z / scalar, this.w / scalar);
    }

    public void divInPlace(double scalar) {
        this.x /= scalar;
        this.y /= scalar;
        this.z /= scalar;
        this.w /= scalar;
    }

    // Magnitude and normalization
    public double magnitude() {
        return Math.sqrt(x * x + y * y + z * z + w * w);
    }

    public double magnitudeSquared() {
        return x * x + y * y + z * z + w * w;
    }

    public Vector4D normalized() {
        double mag = magnitude();
        if (mag == 0) return new Vector4D(0);
        return new Vector4D(x / mag, y / mag, z / mag, w / mag);
    }

    public void normalizeInPlace() {
        double mag = magnitude();
        if (mag != 0) {
            x /= mag;
            y /= mag;
            z /= mag;
            w /= mag;
        }
    }

    // Dot product
    public double dot(Vector4D other) {
        return this.x * other.x + this.y * other.y + this.z * other.z + this.w * other.w;
    }

    // Distance
    public double distance(Vector4D other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        double dz = this.z - other.z;
        double dw = this.w - other.w;
        return Math.sqrt(dx * dx + dy * dy + dz * dz + dw * dw);
    }

    public double distanceSquared(Vector4D other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        double dz = this.z - other.z;
        double dw = this.w - other.w;
        return dx * dx + dy * dy + dz * dz + dw * dw;
    }

    // Override toString
    @Override
    public String toString() {
        return "Vector4D{" + "x=" + x + ", y=" + y + ", z=" + z + ", w=" + w + '}';
    }

    // Equality check
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Vector4D other)) return false;
        return Double.compare(x, other.x) == 0 &&
                Double.compare(y, other.y) == 0 &&
                Double.compare(z, other.z) == 0 &&
                Double.compare(w, other.w) == 0;
    }
}
