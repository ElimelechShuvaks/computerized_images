package primitives;

import geometries.Geometries;
import geometries.Sphere;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for Ray class.
 */
class RayTest {
    /**
     * Test method for {@link primitives.Ray#getPoint(double)}.
     */
    void testGetPoint() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: t is not zero
        Ray ray = new Ray(new Point(0,0,0), new Vector(1,0,0));
        assertEquals(new Point(1,0,0), ray.getPoint(1));

        // =============== Boundary Values Tests ==================
        //TC02: t is zero
        ray = new Ray(new Point(0,0,0), new Vector(1,0,0));
        assertEquals(new Point(0,0,0), ray.getPoint(0));
    }

    /**
     * Test method for {@link primitives.Ray#findClosestPoint(List)}.
     */
    @Test
    void testFindClosestPoint() {
        Geometries geometries = new Geometries();

        // ============ Equivalence Partitions Tests ==============
        //TC01: Closes point to the ray is in middle of intersection list
        geometries.add(new Sphere(1, new Point(5, 0, 0)));
        geometries.add(new Sphere(1, new Point(3, 0, 0)));
        geometries.add(new Sphere(0.5, new Point(4, 0, 0)));
        Ray ray = new Ray(new Point(0, 0, 0), new Vector(1, 0, 0));
        assertEquals(new Point(2, 0, 0), ray.findClosestPoint(geometries.findIntersections(ray)));

        // =============== Boundary Values Tests ==================
        //TC02: Closes point to the ray is in the end of intersection list
        geometries.add(new Sphere(1, new Point(2, 0, 0)));
        assertEquals(new Point(1, 0, 0), ray.findClosestPoint(geometries.findIntersections(ray)));

        //TC03: Closes point to the ray is in the start of intersection list
        geometries = new Geometries();
        geometries.add(new Sphere(1, new Point(3, 0, 0)));
        geometries.add(new Sphere(1, new Point(4, 0, 0)));
        geometries.add(new Sphere(1, new Point(5, 0, 0)));
        assertEquals(new Point(2, 0, 0), ray.findClosestPoint(geometries.findIntersections(ray)));

        //TC04: Intersection list is empty
        geometries = new Geometries();
        assertNull(ray.findClosestPoint(geometries.findIntersections(ray)));
    }
}