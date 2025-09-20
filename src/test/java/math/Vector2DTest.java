package math;

import com.github.mathlon26.ribble.math.Vector2D;
import com.github.mathlon26.ribble.math.Vector3D;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Vector2DTest {

    @Test
    void testConstructors() {
        Vector2D v1 = new Vector2D(3, 4);
        assertEquals(3.0, v1.getX());
        assertEquals(4.0, v1.getY());

        Vector2D v2 = new Vector2D(5);
        assertEquals(5.0, v2.getX());
        assertEquals(5.0, v2.getY());

        Vector2D v3 = new Vector2D();
        assertEquals(0.0, v3.getX());
        assertEquals(0.0, v3.getY());
    }

    @Test
    void testSetters() {
        Vector2D v = new Vector2D();
        v.setX(2);
        v.setY(3);
        assertEquals(2.0, v.getX());
        assertEquals(3.0, v.getY());

        v.setValues(7, 8);
        assertEquals(7.0, v.getX());
        assertEquals(8.0, v.getY());

        v.setValues(9);
        assertEquals(9.0, v.getX());
        assertEquals(9.0, v.getY());
    }

    @Test
    void testAddition() {
        Vector2D v1 = new Vector2D(1, 2);
        Vector2D v2 = new Vector2D(3, 4);

        Vector2D result = v1.add(v2);
        assertEquals(new Vector2D(4, 6), result);

        result = v1.add(5);
        assertEquals(new Vector2D(6, 7), result);

        v1.addInPlace(v2);
        assertEquals(new Vector2D(4, 6), v1);

        v1.addInPlace(1, 1);
        assertEquals(new Vector2D(5, 7), v1);

        v1.addInPlace(2);
        assertEquals(new Vector2D(7, 9), v1);
    }

    @Test
    void testSubtraction() {
        Vector2D v1 = new Vector2D(5, 7);
        Vector2D v2 = new Vector2D(2, 3);

        Vector2D result = v1.sub(v2);
        assertEquals(new Vector2D(3, 4), result);

        result = v1.sub(1);
        assertEquals(new Vector2D(4, 6), result);

        v1.subInPlace(v2);
        assertEquals(new Vector2D(3, 4), v1);

        v1.subInPlace(1, 2);
        assertEquals(new Vector2D(2, 2), v1);

        v1.subInPlace(1);
        assertEquals(new Vector2D(1, 1), v1);
    }

    @Test
    void testMultiplicationAndDivision() {
        Vector2D v = new Vector2D(2, 4);

        assertEquals(new Vector2D(4, 8), v.mul(2));

        v.mulInPlace(3);
        assertEquals(new Vector2D(6, 12), v);

        assertEquals(new Vector2D(3, 6), v.div(2));

        v.divInPlace(2);
        assertEquals(new Vector2D(3, 6), v);
    }

    @Test
    void testMagnitudeAndNormalization() {
        Vector2D v = new Vector2D(3, 4);
        assertEquals(5.0, v.magnitude());
        assertEquals(25.0, v.magnitudeSquared());

        Vector2D norm = v.normalized();
        assertEquals(1.0, norm.magnitude(), 1e-9);

        Vector2D zero = new Vector2D(0, 0);
        assertEquals(new Vector2D(0, 0), zero.normalized());

        v.normalizeInPlace();
        assertEquals(1.0, v.magnitude(), 1e-9);
    }

    @Test
    void testDotProduct() {
        Vector2D v1 = new Vector2D(1, 2);
        Vector2D v2 = new Vector2D(3, 4);

        assertEquals(11.0, v1.dot(v2));
    }

    @Test
    void testDistance() {
        Vector2D v1 = new Vector2D(0, 0);
        Vector2D v2 = new Vector2D(3, 4);

        assertEquals(5.0, v1.distance(v2));
        assertEquals(25.0, v1.distanceSquared(v2));
    }

    @Test
    void testToStringAndEquals() {
        Vector2D v1 = new Vector2D(1, 2);
        Vector2D v2 = new Vector2D(1, 2);
        Vector2D v3 = new Vector2D(2, 3);

        assertEquals("Vector2D{x=1.0, y=2.0}", v1.toString());
        assertEquals(v1, v2);
        assertNotEquals(v1, v3);
    }
}
