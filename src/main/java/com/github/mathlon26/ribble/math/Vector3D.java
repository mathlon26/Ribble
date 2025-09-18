package com.github.mathlon26.ribble.math;

public class Vector3D {
    private double x;
    private double y;
    private double z;

    // Constructors
    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3D(double v) {
        this(v, v, v);
    }

    public Vector3D() {
        this(0);
    }

    // Getters and setters
    public double getX() { return x; }
    public double getY() { return y; }
    public double getZ() { return z; }

    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }
    public void setZ(double z) { this.z = z; }

    public void setValues(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void setValues(double v) {
        setValues(v, v, v);
    }

    // Addition
    public Vector3D add(Vector3D other) {
        return new Vector3D(this.x + other.x, this.y + other.y, this.z + other.z);
    }

    public Vector3D add(double v) {
        return new Vector3D(this.x + v, this.y + v, this.z + v);
    }

    public void addInPlace(Vector3D other) {
        this.x += other.x;
        this.y += other.y;
        this.z += other.z;
    }

    public void addInPlace(double x, double y, double z) {
        this.x += x;
        this.y += y;
        this.z += z;
    }

    public void addInPlace(double v) {
        addInPlace(v, v, v);
    }

    // Subtraction
    public Vector3D sub(Vector3D other) {
        return new Vector3D(this.x - other.x, this.y - other.y, this.z - other.z);
    }

    public Vector3D sub(double v) {
        return new Vector3D(this.x - v, this.y - v, this.z - v);
    }

    public void subInPlace(Vector3D other) {
        this.x -= other.x;
        this.y -= other.y;
        this.z -= other.z;
    }

    public void subInPlace(double x, double y, double z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;
    }

    public void subInPlace(double v) {
        subInPlace(v, v, v);
    }

    // Multiplication
    public Vector3D mul(double scalar) {
        return new Vector3D(this.x * scalar, this.y * scalar, this.z * scalar);
    }

    public void mulInPlace(double scalar) {
        this.x *= scalar;
        this.y *= scalar;
        this.z *= scalar;
    }

    // Division
    public Vector3D div(double scalar) {
        return new Vector3D(this.x / scalar, this.y / scalar, this.z / scalar);
    }

    public void divInPlace(double scalar) {
        this.x /= scalar;
        this.y /= scalar;
        this.z /= scalar;
    }

    // Magnitude and normalization
    public double magnitude() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public double magnitudeSquared() {
        return x * x + y * y + z * z;
    }

    public Vector3D normalized() {
        double mag = magnitude();
        if (mag == 0) return new Vector3D(0);
        return new Vector3D(x / mag, y / mag, z / mag);
    }

    public void normalizeInPlace() {
        double mag = magnitude();
        if (mag != 0) {
            x /= mag;
            y /= mag;
            z /= mag;
        }
    }

    // Dot product
    public double dot(Vector3D other) {
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }

    // Cross product
    public Vector3D cross(Vector3D other) {
        return new Vector3D(
                this.y * other.z - this.z * other.y,
                this.z * other.x - this.x * other.z,
                this.x * other.y - this.y * other.x
        );
    }

    // Distance
    public double distance(Vector3D other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        double dz = this.z - other.z;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    public double distanceSquared(Vector3D other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        double dz = this.z - other.z;
        return dx * dx + dy * dy + dz * dz;
    }

    // Override toString
    @Override
    public String toString() {
        return "Vector3D{" + "x=" + x + ", y=" + y + ", z=" + z + '}';
    }

    // Equality check
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Vector3D other)) return false;
        return Double.compare(x, other.x) == 0 &&
                Double.compare(y, other.y) == 0 &&
                Double.compare(z, other.z) == 0;
    }
}
