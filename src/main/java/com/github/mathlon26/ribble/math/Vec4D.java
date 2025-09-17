package com.github.mathlon26.ribble.math;

public class Vec4D<T extends Number> {
    private T x;
    private T y;
    private T z;
    private T w;

    // Main constructor
    public Vec4D(T x, T y, T z, T w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    // Default constructor zeros
    @SuppressWarnings("unchecked")
    public Vec4D() {
        this.x = (T) (Double) 0.0;
        this.y = (T) (Double) 0.0;
        this.z = (T) (Double) 0.0;
        this.w = (T) (Double) 0.0;
    }

    // Getters
    public T getX() { return x; }
    public T getY() { return y; }
    public T getZ() { return z; }
    public T getW() { return w; }

    // Setters
    public void setX(T x) { this.x = x; }
    public void setY(T y) { this.y = y; }
    public void setZ(T z) { this.z = z; }
    public void setW(T w) { this.w = w; }

    /** Add another vector to this one and return a new Vec4D<Double> */
    public Vec4D<Double> add(Vec4D<? extends Number> other) {
        return new Vec4D<>(
                this.x.doubleValue() + other.getX().doubleValue(),
                this.y.doubleValue() + other.getY().doubleValue(),
                this.z.doubleValue() + other.getZ().doubleValue(),
                this.w.doubleValue() + other.getW().doubleValue()
        );
    }

    /** Subtract another vector from this one and return a new Vec4D<Double> */
    public Vec4D<Double> subtract(Vec4D<? extends Number> other) {
        return new Vec4D<>(
                this.x.doubleValue() - other.getX().doubleValue(),
                this.y.doubleValue() - other.getY().doubleValue(),
                this.z.doubleValue() - other.getZ().doubleValue(),
                this.w.doubleValue() - other.getW().doubleValue()
        );
    }

    /** Multiply this vector by a scalar and return a new Vec4D<Double> */
    public Vec4D<Double> scale(double scalar) {
        return new Vec4D<>(
                this.x.doubleValue() * scalar,
                this.y.doubleValue() * scalar,
                this.z.doubleValue() * scalar,
                this.w.doubleValue() * scalar
        );
    }

    /** Compute the magnitude (length) of the vector */
    public double magnitude() {
        return Math.sqrt(
                this.x.doubleValue() * this.x.doubleValue() +
                        this.y.doubleValue() * this.y.doubleValue() +
                        this.z.doubleValue() * this.z.doubleValue() +
                        this.w.doubleValue() * this.w.doubleValue()
        );
    }

    /** Normalize the vector and return a new Vec4D<Double> (unit vector) */
    public Vec4D<Double> normalize() {
        double mag = magnitude();
        if (mag == 0) return new Vec4D<>(0.0, 0.0, 0.0, 0.0);
        return scale(1.0 / mag);
    }

    /** return a Vec4D with all values clamped to the given value **/
    public Vec4D<Double> clamp(Double min, Double max) {
        return new Vec4D<Double>(
                Math.max(min, Math.min(max, this.x.doubleValue())),
                Math.max(min, Math.min(max, this.y.doubleValue())),
                Math.max(min, Math.min(max, this.z.doubleValue())),
                Math.max(min, Math.min(max, this.w.doubleValue()))
        );
    }

    @Override
    public String toString() {
        return "Vec4D{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", w=" + w +
                '}';
    }
}
