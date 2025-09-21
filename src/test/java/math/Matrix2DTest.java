package math;

import com.github.mathlon26.ribble.math.Matrix2D;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class Matrix2DTest {
    @Test
    void testConstructor()
    {
        Matrix2D mat1 = new Matrix2D(2, 1);
        assertEquals(2, mat1.getWidth());
        assertEquals(1, mat1.getHeight());
        assertEquals(0, mat1.getAt(0, 1));

        Matrix2D mat2 = new Matrix2D(2);
        assertEquals(2, mat2.getWidth());
        assertEquals(2, mat2.getHeight());
        assertEquals(0, mat2.getAt(1, 1));

        Matrix2D ident = Matrix2D.identity(2);
        assertEquals(0, ident.getAt(0, 1));
        assertEquals(0, ident.getAt(1, 0));
        assertEquals(1, ident.getAt(0, 0));
        assertEquals(1, ident.getAt(1, 1));

    }

    @Test
    void testSetAt()
    {
        Matrix2D mat = new Matrix2D(3, 3);

        mat.setAt(1, 1, 5);

        assertEquals(5, mat.getAt(1, 1));
    }

    @Test
    void testAddition()
    {
        Matrix2D mat1 = new Matrix2D(2, 3);
        Matrix2D mat2 = new Matrix2D(2, 3);

        mat1.setAt(0, 0, 2);
        mat2.setAt(0, 0, 2);

        mat1.setAt(1, 0, 6);
        mat2.setAt(0, 1, 6);

        mat1.add(mat2);

        assertEquals(4, mat1.getAt(0, 0));
        assertEquals(6, mat1.getAt(1, 0));
        assertEquals(6, mat1.getAt(0,1));

    }

    @Test
    void testSubtraction()
    {
        Matrix2D mat1 = new Matrix2D(2, 3);
        Matrix2D mat2 = new Matrix2D(2, 3);

        mat1.setAt(0, 0, 2);
        mat2.setAt(0, 0, 2);

        mat1.setAt(1, 0, 6);
        mat2.setAt(0, 1, 6);

        mat1.subtract(mat2);

        assertEquals(0, mat1.getAt(0, 0));
        assertEquals(6, mat1.getAt(1, 0));
        assertEquals(-6, mat1.getAt(0,1));

    }

    @Test
    void testMultiplication()
    {
        Matrix2D mat1 = new Matrix2D(3, 2);
        mat1.setAt(0, 0, 1); mat1.setAt(0, 1, 2); mat1.setAt(0, 2, 3);
        mat1.setAt(1, 0, 4); mat1.setAt(1, 1, 5); mat1.setAt(1, 2, 6);

        Matrix2D mat2 = new Matrix2D(2, 3);
        mat2.setAt(0, 0, 7); mat2.setAt(0, 1, 8);
        mat2.setAt(1, 0, 9); mat2.setAt(1, 1, 10);
        mat2.setAt(2, 0, 11); mat2.setAt(2, 1, 12);

        Matrix2D result = Matrix2D.multiply(mat1, mat2);

        // Expected result:
        // [58  64]
        // [139 154]
        assertEquals(58.0, result.getAt(0, 0), "result[0][0]");
        assertEquals(64.0, result.getAt(0, 1), "result[0][1]");
        assertEquals(139.0, result.getAt(1, 0), "result[1][0]");
        assertEquals(154.0, result.getAt(1, 1), "result[1][1]");
    }

    @Test
    void testInvalidMultiplication()
    {
        Matrix2D mat1 = new Matrix2D(3, 2);
        Matrix2D mat2 = new Matrix2D(3, 2);

        assertThrows(IllegalArgumentException.class, () -> {Matrix2D.multiply(mat1, mat2);});
    }

    @Test
    void testScalarMultiplication()
    {
        Matrix2D mat = new Matrix2D(3, 3);
        mat.setAt(2, 0, 2);

        mat.multiply(3);

        assertEquals(6, mat.getAt(2, 0));
    }

    @Test
    void testScalarDivision()
    {
        Matrix2D mat = new Matrix2D(3, 3);
        mat.setAt(2, 0, 6);

        mat.divide(3);

        assertEquals(2, mat.getAt(2, 0));
    }


}
