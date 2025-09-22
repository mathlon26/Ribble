package com.github.mathlon26.ribble.math;

/**
 * Represents a position, rotation and scale in 3D space.
 * Rotation is stored as a Quaternion.
 */
public class Transform3D {
    private Vector3D position;
    private Quaternion rotation;
    private Vector3D scale;

    public Transform3D() {
        this.position = new Vector3D(0.0, 0.0, 0.0);
        this.rotation = new Quaternion(); // identity quaternion
        this.scale = new Vector3D(1.0, 1.0, 1.0);
    }

    public Transform3D(Vector3D position, Quaternion rotation, Vector3D scale) {
        this.position = position != null ? position : new Vector3D(0.0, 0.0, 0.0);
        this.rotation = rotation != null ? rotation : new Quaternion();
        this.scale = scale != null ? scale : new Vector3D(1.0, 1.0, 1.0);
    }

    // position
    public Vector3D getPosition() {
        return position;
    }

    public void setPosition(Vector3D position) {
        this.position = position;
    }

    public void translate(Vector3D delta) {
        if (delta == null) return;
        this.position = new Vector3D(
                this.position.getX() + delta.getX(),
                this.position.getY() + delta.getY(),
                this.position.getZ() + delta.getZ()
        );
    }

    // rotation
    public Quaternion getRotation() {
        return rotation;
    }

    public void setRotation(Quaternion rotation) {
        this.rotation = rotation;
    }

    /**
     * Applies a rotation by multiplying this.rotation = delta * this.rotation.
     * This follows the convention where newRotation = delta × current.
     */
    public void rotate(Quaternion delta) {
        if (delta == null) return;
        this.rotation = delta.multiply(this.rotation).normalize();
    }

    // scale
    public Vector3D getScale() {
        return scale;
    }

    public void setScale(Vector3D scale) {
        this.scale = scale;
    }

    public void scale(Vector3D factor) {
        if (factor == null) return;
        this.scale = new Vector3D(
                this.scale.getX() * factor.getX(),
                this.scale.getY() * factor.getY(),
                this.scale.getZ() * factor.getZ()
        );
    }

    /**
     * Returns a 4x4 transformation matrix as a double[16] in column-major order:
     * indices follow OpenGL-style (m00,m10,m20,m30, m01,m11,m21,m31, ...).
     * The matrix encodes scale, rotation, then translation: M = T * R * S
     */
    public double[] toMatrix4D() {
        double[] m = new double[16];

        // rotation matrix 3x3 from quaternion
        double[] r = rotation.toRotationMatrix3D(); // returns length-9 array row-major
        // r is [r00, r01, r02, r10, r11, r12, r20, r21, r22]
        // apply scale
        double sx = scale.getX();
        double sy = scale.getY();
        double sz = scale.getZ();

        // column-major layout
        m[0] = r[0] * sx; // m00
        m[1] = r[3] * sx; // m10
        m[2] = r[6] * sx; // m20
        m[3] = 0.0;

        m[4] = r[1] * sy; // m01
        m[5] = r[4] * sy; // m11
        m[6] = r[7] * sy; // m21
        m[7] = 0.0;

        m[8] = r[2] * sz; // m02
        m[9] = r[5] * sz; // m12
        m[10] = r[8] * sz; // m22
        m[11] = 0.0;

        // translation
        m[12] = position.getX();
        m[13] = position.getY();
        m[14] = position.getZ();
        m[15] = 1.0;

        return m;
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
