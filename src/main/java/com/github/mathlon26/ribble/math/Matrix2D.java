package com.github.mathlon26.ribble.math;

import com.github.mathlon26.ribble.ecs.component.components.Transform2DComponent;
import com.github.mathlon26.ribble.io.output.sys.ExceptionHandler;

public class Matrix2D {
    private final double[][] m_data;
    private final int m_width;
    private final int m_height;

    public int getWidth() { return m_width; }
    public int getHeight() { return m_height; }

    public Matrix2D() {
        this(0,0);
    }

    public Matrix2D(int size) {
        this(size, size);
    }

    public Matrix2D(int width, int height) {
        m_data = new double[height][width];
        m_width = width;
        m_height = height;
    }

    public Matrix2D(int width, int height, double... values) {
        m_data = new double[height][width];
        m_width = width;
        m_height = height;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int index = y * width + x;
                if (index < values.length) {
                    m_data[y][x] = values[index];
                } else {
                    m_data[y][x] = 0.0;
                }
            }
        }
    }

    public static Matrix2D identity(int size) {
        Matrix2D result = new Matrix2D(size, size);
        for (int i = 0; i < size; i++) {
            result.setAt(i, i, 1.0);
        }
        return result;
    }

    public double getAt(int row, int col) {
        if(row < m_height && col < m_width) {
            return m_data[row][col];
        } else {
            ExceptionHandler.raise(IndexOutOfBoundsException.class,
                    "Matrix is " + m_width + "x" + m_height + " but row=" + row + ", col=" + col + " requested!");
            return 0; // unreachable
        }
    }

    public void setAt(int row, int col, double value) {
        if(row < m_height && col < m_width) {
            m_data[row][col] = value;
        } else {
            ExceptionHandler.raise(IndexOutOfBoundsException.class,
                    "Matrix is " + m_width + "x" + m_height + " but row=" + row + ", col=" + col + " requested!");
        }
    }

    public void add(Matrix2D other) {
        if(m_width == other.m_width && m_height == other.m_height) {
            for(int i = 0; i < m_height; i++) {
                for(int j = 0; j < m_width; j++) {
                    m_data[i][j] += other.m_data[i][j];
                }
            }
        } else {
            ExceptionHandler.raise(IllegalArgumentException.class, "Matrices must be the same size for addition!");
        }
    }

    public void subtract(Matrix2D other) {
        if(m_width == other.m_width && m_height == other.m_height) {
            for(int i = 0; i < m_height; i++) {
                for(int j = 0; j < m_width; j++) {
                    m_data[i][j] -= other.m_data[i][j];
                }
            }
        } else {
            ExceptionHandler.raise(IllegalArgumentException.class, "Matrices must be the same size for subtraction!");
        }
    }

    public void multiply(double scalar) {
        for(int i = 0; i < m_height; i++) {
            for(int j = 0; j < m_width; j++) {
                m_data[i][j] *= scalar;
            }
        }
    }

    public static Matrix2D multiply(Matrix2D mat1, Matrix2D mat2) {
        if(mat1.m_width != mat2.m_height) {
            ExceptionHandler.raise(IllegalArgumentException.class,
                    "Width of mat1 must equal height of mat2 to multiply!");
            return new Matrix2D();
        }

        int rows = mat1.m_height;
        int cols = mat2.m_width;
        int shared = mat1.m_width;

        Matrix2D result = new Matrix2D(cols, rows);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double sum = 0.0;
                for (int k = 0; k < shared; k++) {
                    sum += mat1.getAt(i, k) * mat2.getAt(k, j);
                }
                result.setAt(i, j, sum);
            }
        }

        return result;
    }

    public void divide(double scalar) {
        for(int i = 0; i < m_height; i++) {
            for(int j = 0; j < m_width; j++) {
                m_data[i][j] /= scalar;
            }
        }
    }

    public float[] toFloatArray() {
        float[] result = new float[m_width * m_height];
        int index = 0;
        for (int col = 0; col < m_width; col++) {
            for (int row = 0; row < m_height; row++) {
                result[index++] = (float) m_data[row][col];
            }
        }
        return result;
    }

    // -------------------------------
    // Transform helpers (for 2D)
    // -------------------------------

    public static Matrix2D translation(double tx, double ty) {
        Matrix2D mat = identity(3);
        mat.setAt(0, 2, tx);
        mat.setAt(1, 2, ty);
        return mat;
    }

    public static Matrix2D rotation(double radians) {
        Matrix2D mat = identity(3);
        double cos = Math.cos(radians);
        double sin = Math.sin(radians);

        mat.setAt(0, 0, cos);
        mat.setAt(0, 1, -sin);
        mat.setAt(1, 0, sin);
        mat.setAt(1, 1, cos);

        return mat;
    }

    public static Matrix2D scale(double sx, double sy) {
        Matrix2D mat = identity(3);
        mat.setAt(0, 0, sx);
        mat.setAt(1, 1, sy);
        return mat;
    }

    // -------------------------------
    // Orthographic projection
    // -------------------------------
    public static Matrix2D orthographic(double left, double right, double bottom, double top) {
        // 3x3 version (2D only, normalized to -1..1)
        Matrix2D ortho = identity(3);
        ortho.setAt(0, 0, 2.0 / (right - left));
        ortho.setAt(1, 1, 2.0 / (top - bottom));
        ortho.setAt(0, 2, -(right + left) / (right - left));
        ortho.setAt(1, 2, -(top + bottom) / (top - bottom));
        return ortho;
    }

    public static Matrix2D orthographic4x4(double left, double right, double bottom, double top, double near, double far) {
        // 4x4 version (for compatibility with typical GL shaders)
        Matrix2D ortho = identity(4);
        ortho.setAt(0, 0, 2.0 / (right - left));
        ortho.setAt(1, 1, 2.0 / (top - bottom));
        ortho.setAt(2, 2, -2.0 / (far - near));
        ortho.setAt(0, 3, -(right + left) / (right - left));
        ortho.setAt(1, 3, -(top + bottom) / (top - bottom));
        ortho.setAt(2, 3, -(far + near) / (far - near));
        return ortho;
    }


}
