package com.github.mathlon26.ribble_deprecated.math;

public class Vec3D<T extends Number> {
    private T x;
    private T y;
    private T z;

    // Main constructor
    public Vec3D(T x, T y, T z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // Default constructor zeros
    @SuppressWarnings("unchecked")
    public Vec3D() {
        this.x = (T) (Double) 0.0;
        this.y = (T) (Double) 0.0;
        this.z = (T) (Double) 0.0;
    }

    // Getters
    public T getX() { return x; }
    public T getY() { return y; }
    public T getZ() { return z; }

    // Setters
    public void setX(T x) { this.x = x; }
    public void setY(T y) { this.y = y; }
    public void setZ(T z) { this.z = z; }

    /** Add another vector to this one and return a new Vec3D<Double> */
    public Vec3D<Double> add(Vec3D<? extends Number> other) {
        return new Vec3D<>(
                this.x.doubleValue() + other.getX().doubleValue(),
                this.y.doubleValue() + other.getY().doubleValue(),
                this.z.doubleValue() + other.getZ().doubleValue()
        );
    }

    /** Subtract another vector from this one and return a new Vec3D<Double> */
    public Vec3D<Double> subtract(Vec3D<? extends Number> other) {
        return new Vec3D<>(
                this.x.doubleValue() - other.getX().doubleValue(),
                this.y.doubleValue() - other.getY().doubleValue(),
                this.z.doubleValue() - other.getZ().doubleValue()
        );
    }

    /** Multiply this vector by a scalar and return a new Vec3D<Double> */
    public Vec3D<Double> scale(double scalar) {
        return new Vec3D<>(
                this.x.doubleValue() * scalar,
                this.y.doubleValue() * scalar,
                this.z.doubleValue() * scalar
        );
    }

    /** Compute the magnitude (length) of the vector */
    public double magnitude() {
        return Math.sqrt(
                this.x.doubleValue() * this.x.doubleValue() +
                        this.y.doubleValue() * this.y.doubleValue() +
                        this.z.doubleValue() * this.z.doubleValue()
        );
    }

    /** Normalize the vector and return a new Vec3D<Double> (unit vector) */
    public Vec3D<Double> normalize() {
        double mag = magnitude();
        if (mag == 0) return new Vec3D<>(0.0, 0.0, 0.0);
        return scale(1.0 / mag);
    }

    /** Compute the dot product with another vector */
    public double dot(Vec3D<? extends Number> other) {
        return this.x.doubleValue() * other.getX().doubleValue() +
                this.y.doubleValue() * other.getY().doubleValue() +
                this.z.doubleValue() * other.getZ().doubleValue();
    }

    /** Compute the cross product with another vector and return a new Vec3D<Double> */
    public Vec3D<Double> cross(Vec3D<? extends Number> other) {
        return new Vec3D<>(
                this.y.doubleValue() * other.getZ().doubleValue() - this.z.doubleValue() * other.getY().doubleValue(),
                this.z.doubleValue() * other.getX().doubleValue() - this.x.doubleValue() * other.getZ().doubleValue(),
                this.x.doubleValue() * other.getY().doubleValue() - this.y.doubleValue() * other.getX().doubleValue()
        );
    }

    /** return a Vec3D with all values clamped to the given value **/
    public Vec3D<Double> clamp(Double min, Double max) {
        return new Vec3D<Double>(
                Math.max(min, Math.min(max, this.x.doubleValue())),
                Math.max(min, Math.min(max, this.y.doubleValue())),
                Math.max(min, Math.min(max, this.z.doubleValue()))
        );
    }

    @Override
    public String toString() {
        return "Vec3D{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
