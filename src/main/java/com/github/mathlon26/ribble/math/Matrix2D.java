package com.github.mathlon26.ribble.math;

import com.github.mathlon26.ribble.io.output.sys.ExceptionHandler;

public class Matrix2D {
    private final double[][] m_data;
    private final int m_width;
    public int getWidth()
    {
        return m_width;
    }
    private final int m_height;
    public int getHeight()
    {
        return m_height;
    }

    public Matrix2D()
    {
        this(0,0);
    }

    public Matrix2D(int size)
    {
        this(size, size);
    }

    public Matrix2D(int width, int height)
    {
        m_data = new double[height][width];
        m_width = width;
        m_height = height;
    }

    public static Matrix2D identity(int size) {
        Matrix2D result = new Matrix2D(size, size);
        for (int i = 0; i < size; i++) {
            result.setAt(i, i, 1.0);
        }
        return result;
    }

    public double getAt(int row, int col)
    {
        if(row < m_height && col < m_width)
        {
            return m_data[row][col];
        }else {
            ExceptionHandler.raise(IndexOutOfBoundsException.class, "Matrix is a " + m_width + " by " + m_height + " Matrix. But row: " + row + ", col: " + col + " was requested!");
            return 0; // Should never happen
        }
    }

    public void setAt(int row, int col, double value)
    {
        if(row < m_height && col < m_width)
        {
            m_data[row][col] = value;
        }else {
            ExceptionHandler.raise(IndexOutOfBoundsException.class, "Matrix is a " + m_width + " by " + m_height + " Matrix. But row: " + row + ", col: " + col + " was requested!");
        }
    }

    public void add(Matrix2D other)
    {
        if(m_width == other.m_width && m_height == other.m_height)
        {
            for(int i = 0; i < m_height; i ++)
            {
                for(int j = 0; j < m_width; j++)
                {
                    m_data[i][j] += other.m_data[i][j];
                }
            }
        }else{
            ExceptionHandler.raise(IllegalArgumentException.class, "Matrices must be the same size to perform addition!");
        }
    }

    public void subtract(Matrix2D other)
    {
        if(m_width == other.m_width && m_height == other.m_height)
        {
            for(int i = 0; i < m_height; i ++)
            {
                for(int j = 0; j < m_width; j++)
                {
                    m_data[i][j] -= other.m_data[i][j];
                }
            }
        }else{
            ExceptionHandler.raise(IllegalArgumentException.class, "Matrices must be the same size to perform addition!");
        }
    }

    public void multiply(double scalar)
    {
        for(int i = 0; i < m_height; i ++)
        {
            for(int j = 0; j < m_width; j++)
            {
                m_data[i][j] *= scalar;
            }
        }
    }

    public static Matrix2D multiply(Matrix2D mat1, Matrix2D mat2)
    {
        if(mat1.m_width != mat2.m_height)
        {
            ExceptionHandler.raise(IllegalArgumentException.class, "Width of matrix1 must be equal to Height of matrix2 to multiply!");
            return new Matrix2D();
        }


        int rows = mat1.m_height;
        int cols = mat2.m_width;
        int shared = mat1.m_width;

        Matrix2D result = new Matrix2D(rows, cols);

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


    public void divide(double scalar)
    {
        for(int i = 0; i < m_height; i ++)
        {
            for(int j = 0; j < m_width; j++)
            {
                m_data[i][j] /= scalar;
            }
        }
    }


}
