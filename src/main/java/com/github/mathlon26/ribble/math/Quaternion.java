package com.github.mathlon26.ribble.math;

/**
 * Simple quaternion class (x, y, z, w).
 * - Uses doubles for internal precision.
 * - w is the scalar part.
 */
public class Quaternion {
    public double x, y, z, w;

    /** Identity quaternion (no rotation). */
    public Quaternion() {
        this(0.0, 0.0, 0.0, 1.0);
    }

    public Quaternion(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    /** Copy constructor. */
    public Quaternion(Quaternion q) {
        this(q.x, q.y, q.z, q.w);
    }

    /**
     * Create quaternion from axis (must be non-zero) and angle (radians).
     */
    public static Quaternion fromAxisAngle(Vector3D axis, double angleRad) {
        double ax = axis.getX();
        double ay = axis.getY();
        double az = axis.getZ();
        double len = Math.sqrt(ax * ax + ay * ay + az * az);
        if (len == 0.0) {
            return new Quaternion(); // identity as fallback
        }
        double inv = 1.0 / len;
        ax *= inv;
        ay *= inv;
        az *= inv;

        double half = angleRad * 0.5;
        double s = Math.sin(half);
        double c = Math.cos(half);
        return new Quaternion(ax * s, ay * s, az * s, c).normalize();
    }

    /**
     * Create quaternion from Euler angles (in radians).
     * Order: yaw (Y), pitch (X), roll (Z) — typical FPS style.
     * This returns q = q_yaw * q_pitch * q_roll
     */
    public static Quaternion fromEuler(double pitch, double yaw, double roll) {
        double cy = Math.cos(yaw * 0.5);
        double sy = Math.sin(yaw * 0.5);
        double cp = Math.cos(pitch * 0.5);
        double sp = Math.sin(pitch * 0.5);
        double cr = Math.cos(roll * 0.5);
        double sr = Math.sin(roll * 0.5);

        double w = cr * cp * cy + sr * sp * sy;
        double x = sr * cp * cy - cr * sp * sy;
        double y = cr * sp * cy + sr * cp * sy;
        double z = cr * cp * sy - sr * sp * cy;

        return new Quaternion(x, y, z, w).normalize();
    }

    /**
     * Hamilton product: this * other
     */
    public Quaternion multiply(Quaternion other) {
        double nx =  w * other.x + x * other.w + y * other.z - z * other.y;
        double ny =  w * other.y - x * other.z + y * other.w + z * other.x;
        double nz =  w * other.z + x * other.y - y * other.x + z * other.w;
        double nw =  w * other.w - x * other.x - y * other.y - z * other.z;
        return new Quaternion(nx, ny, nz, nw);
    }

    /** Length (magnitude) */
    public double length() {
        return Math.sqrt(x * x + y * y + z * z + w * w);
    }

    /** Normalize to unit quaternion (in-place semantics returned as new instance) */
    public Quaternion normalize() {
        double len = length();
        if (len == 0.0) {
            // fallback to identity
            return new Quaternion(0.0, 0.0, 0.0, 1.0);
        }
        double inv = 1.0 / len;
        return new Quaternion(x * inv, y * inv, z * inv, w * inv);
    }

    /** Conjugate: (−v, w) */
    public Quaternion conjugate() {
        return new Quaternion(-x, -y, -z, w);
    }

    /** Inverse: conjugate / |q|^2 */
    public Quaternion inverse() {
        double norm2 = x * x + y * y + z * z + w * w;
        if (norm2 == 0.0) {
            return new Quaternion(); // identity fallback
        }
        double inv = 1.0 / norm2;
        return new Quaternion(-x * inv, -y * inv, -z * inv, w * inv);
    }

    /**
     * Rotate a Vector3D by this quaternion and return new Vector3D.
     * Assumes Vector3D has constructor Vector3D(double x, double y, double z).
     */
    public Vector3D rotate(Vector3D v) {
        // p = (v, 0)
        Quaternion p = new Quaternion(v.getX(), v.getY(), v.getZ(), 0.0);
        Quaternion inv = this.inverse();
        Quaternion res = this.multiply(p).multiply(inv);
        return new Vector3D(res.x, res.y, res.z);
    }

    /**
     * Produce a 3x3 rotation matrix (row-major) as a length-9 double array:
     * [ r00, r01, r02, r10, r11, r12, r20, r21, r22 ]
     * (This is convenient to plug into column-major 4x4 building, see Transform).
     */
    public double[] toRotationMatrix3D() {
        Quaternion q = this.normalize(); // use unit quaternion
        double xx = q.x * q.x;
        double yy = q.y * q.y;
        double zz = q.z * q.z;
        double xy = q.x * q.y;
        double xz = q.x * q.z;
        double yz = q.y * q.z;
        double wx = q.w * q.x;
        double wy = q.w * q.y;
        double wz = q.w * q.z;

        double[] m = new double[9];
        m[0] = 1.0 - 2.0 * (yy + zz); // r00
        m[1] = 2.0 * (xy - wz);       // r01
        m[2] = 2.0 * (xz + wy);       // r02

        m[3] = 2.0 * (xy + wz);       // r10
        m[4] = 1.0 - 2.0 * (xx + zz); // r11
        m[5] = 2.0 * (yz - wx);       // r12

        m[6] = 2.0 * (xz - wy);       // r20
        m[7] = 2.0 * (yz + wx);       // r21
        m[8] = 1.0 - 2.0 * (xx + yy); // r22

        return m;
    }

    @Override
    public String toString() {
        return "Quaternion{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", w=" + w +
                '}';
    }
}
