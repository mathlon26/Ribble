package com.github.mathlon26.ribble.math;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a position, rotation and scale in 3D space.
 * Rotation is stored as a Quaternion.
 */
public class Transform2D {
    @Getter
    @Setter
    private Vector2D position;

    @Getter
    @Setter
    private float rotation;

    @Getter
    @Setter
    private Vector2D scale;

    public Transform2D() {
        this.position = new Vector2D(0.0, 0.0);
        this.rotation = 0; // identity quaternion
        this.scale = new Vector2D(1.0, 1.0);
    }

    public Transform2D(Vector2D position, float rotation, Vector2D scale) {
        this.position = position != null ? position : new Vector2D(0.0, 0.0);
        this.rotation = rotation;
        this.scale = scale != null ? scale : new Vector2D(1.0, 1.0);
    }

    public void translate(Vector3D delta) {
        if (delta == null) return;
        this.position = new Vector2D(
                this.position.getX() + delta.getX(),
                this.position.getY() + delta.getY()
        );
    }

    public void rotate(float delta) {
        this.rotation = rotation + delta;
    }

    public void scale(Vector2D factor) {
        if (factor == null) return;
        this.scale = new Vector2D(
                this.scale.getX() * factor.getX(),
                this.scale.getY() * factor.getY()
        );
    }

    @Override
    public String toString() {
        return "Transform{" +
                "position=" + position +
                ", rotation=" + rotation +
                ", scale=" + scale +
                '}';
    }
}
