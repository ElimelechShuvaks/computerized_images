package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
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

    /**
     * Test method for {@link geometries.Triangle#findIntersections(primitives.Ray)}.
     */
    @Test
    void findIntersections(){
        // ============ Equivalence Partitions Tests ==============
        // TC01 ray passes through triangle
        Ray ray = new Ray(new Point(3, 3, 2), new Vector(-1, -1, -4));
        Triangle triangle = new Triangle(new Point(1, 0, 0), new Point(1, 5, 0), new Point(6, 0, 0));
        assertEquals(1, triangle.findIntersections(ray).size());
        assertEquals(new Point(2.5, 2.5, 0), triangle.findIntersections(ray).get(0));

        // TC02 ray misses triangle on one side
        ray = new Ray(new Point(3, 3, 2), new Vector(1, 1, -4));
        assertNull(triangle.findIntersections(ray));

        // TC03 ray misses triangle on two side
        ray = new Ray(new Point(3, 3, 2), new Vector(-5, 5.5, -4));
        assertNull(triangle.findIntersections(ray));

        // =============== Boundary Values Tests ==================
        // TC04 ray intersects vertex
        ray = new Ray(new Point(1, 0, 3), new Vector(0, 0, -1));
        assertNull(triangle.findIntersections(ray));

        // TC05 ray intersects edge
        ray = new Ray(new Point(1, 0, 3), new Vector(1, 0, -6));
        assertNull(triangle.findIntersections(ray));

        // TC06 ray intersects edge continuation imaginary line
        ray = new Ray(new Point(0.5, 0, 3), new Vector(0, 0, -1));
        assertNull(triangle.findIntersections(ray));

    }

}