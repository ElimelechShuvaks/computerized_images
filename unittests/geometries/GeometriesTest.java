package geometries;

import primitives.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for Geometries class
 */
class GeometriesTest {

    /**
     * Test method for {@link Geometries#findIntersections(Ray)}.
     */
    @Test
    void findIntersections() {
        // ============ Equivalence Partitions Tests ==============
        //TC01 - Ray intersects with a Sphere and plane but not with a Triangle
        Geometries geometries = new Geometries();
        geometries.add(new Sphere(1,new Point(1, 0, 0)));
        geometries.add(new Plane(new Point(1, 0, 0), new Vector(0, 1, 0)));
        geometries.add(new Triangle(new Point(1, 0, 0), new Point(1, 5, 0), new Point(6, 0, 0)));
        assertEquals(3, geometries.findIntersections(new Ray(new Point(0.5, 4, 0.5), new Vector(0, -1, 0))).size(), "Ray intersects with a Sphere and plane but not with a Triangle");

        // =============== Boundary Values Tests ==================
        //TC02 - Ray does not intersect with any geometries
        assertNull(geometries.findIntersections(new Ray(new Point(0, 1, 0), new Vector(0, 1, 0))), "Ray does not intersect with any geometries");

        //TC03 - Ray intersects with only one geometry(plane)
        assertEquals(1, geometries.findIntersections(new Ray(new Point(0.5, 4, 3), new Vector(0, -1, 0))).size(), "Ray intersects with only one geometry(plane)");

        //TC04 - Ray intersects with all 3 geometries(4 points)
        assertEquals(4, geometries.findIntersections(new Ray(new Point(1.5, 1, -0.5), new Vector(-1, -4, 3.5))).size(), "Ray intersects with all 3 geometries(4 points)");

        //TC05 - geometries is empty
        geometries = new Geometries();
        assertNull(geometries.findIntersections(new Ray(new Point(0.5, 4, 0.5), new Vector(0, -1, 0))), "geometries is empty");
    }
}