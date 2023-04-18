package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for Triangle class
 */
class TriangleTest {

    /**
     * Test method for {@link Polygon#getNormal(Point)}.
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01 test if normal vector is correct
        Triangle triangle = new Triangle(new Point(0, 0, 0), new Point(0, 5, 0), new Point(5, 0, 0));
        Vector normal = new Vector(0, 0, 1);
        assertTrue(normal.equals(triangle.getNormal(new Point(1, 1, 0))) ||
                normal.equals(triangle.getNormal(new Point(-1, -1, 0))), "bad normal to triangle");
    }
}