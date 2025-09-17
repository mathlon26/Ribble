package com.github.mathlon26.ribble.math;

public class Vec2D<T extends Number> {
    private T x;
    private T y;

    // Main constructor
    public Vec2D(T x, T y) {
        this.x = x;
        this.y = y;
    }

    // Default constructor zeros
    @SuppressWarnings("unchecked")
    public Vec2D() {
        this.x = (T) (Double) 0.0;
        this.y = (T) (Double) 0.0;
    }

    public T getX() { return x; }
    public T getY() { return y; }

    public void setX(T x) { this.x = x; }
    public void setY(T y) { this.y = y; }

    /** Add another vector to this one and return a new Vec2D<Double> */
    public Vec2D<Double> add(Vec2D<? extends Number> other) {
        return new Vec2D<>(
                this.x.doubleValue() + other.getX().doubleValue(),
                this.y.doubleValue() + other.getY().doubleValue()
        );
    }

    /** Subtract another vector from this one and return a new Vec2D<Double> */
    public Vec2D<Double> subtract(Vec2D<? extends Number> other) {
        return new Vec2D<>(
                this.x.doubleValue() - other.getX().doubleValue(),
                this.y.doubleValue() - other.getY().doubleValue()
        );
    }

    /** Multiply this vector by a scalar and return a new Vec2D<Double> */
    public Vec2D<Double> scale(double scalar) {
        return new Vec2D<>(
                this.x.doubleValue() * scalar,
                this.y.doubleValue() * scalar
        );
    }

    /** Compute the magnitude (length) of the vector */
    public double magnitude() {
        return Math.sqrt(this.x.doubleValue() * this.x.doubleValue() +
                this.y.doubleValue() * this.y.doubleValue());
    }

    /** Normalize the vector and return a new Vec2D<Double> (unit vector) */
    public Vec2D<Double> normalize() {
        double mag = magnitude();
        if (mag == 0) return new Vec2D<>(0.0, 0.0);
        return scale(1.0 / mag);
    }

    /** return a Vec2D with all values clamped to the given value **/
    public Vec2D<Double> clamp(Double min, Double max) {
        return new Vec2D<Double>(
                Math.max(min, Math.min(max, this.x.doubleValue())),
                Math.max(min, Math.min(max, this.y.doubleValue()))
        );
    }

    @Override
    public String toString() {
        return "Vec2D{" + "x=" + x + ", y=" + y + '}';
    }

}
