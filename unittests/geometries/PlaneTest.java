package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for Plane class
 */
class PlaneTest {

    /**
     * Test method for {@link Plane#Plane(Point, Point, Point)}.
     */
    @Test
    void Constructor() {

        // =============== Boundary Values Tests ==================
        // TC01: two points are equal
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(new Point(0, 0, 1), new Point(0, 0, 1), new Point(0, 0, 2)),
                "constructed Plane with equal points");

        // TC02L: points are all on the same line
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(new Point(0, 0, 1), new Point(0, 0, 3), new Point(0, 0, 2)),
                "constructed plane must have Vectors in different directions");

    }

    /**
     * Test method for {@link Plane#getNormal(Point)}.
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        //TC01 test if normal vector is correct
        Plane p1 = new Plane(new Point(0, 0, 0), new Point(0, 5, 0), new Point(5, 0, 0));
        Vector normal = new Vector(0, 0, 1);
        assertTrue(normal.equals(p1.getNormal(new Point(1, 1, 0))) ||
                normal.equals(p1.getNormal(new Point(-1, -1, 0))), "bad normal to plane");
    }

    /**
     * Test method for {@link Plane#findIntersections(Ray)}.
     */
    @Test
    void testFindIntersections() {
        // ============ Equivalence Partitions Tests ==============
        // TC01 A ray that starts out of the plane, not parallel to the plane, makes a non-right angle with the plane, and cuts the plane
        Plane plane = new Plane(new Point(1, 0, 0), new Vector(0, 1, 0));
        Ray ray = new Ray(new Point(0, -2, 0), new Vector(-3, 6, 0));
        assertEquals(1, plane.findIntersections(ray).size(), "ray intersects plane");
        assertEquals(new Point(-1, 0, 0), plane.findIntersections(ray).get(0), "ray intersects plane");

        //TC02 A ray that starts out of the plane, not parallel to the plane, makes a non-right angle with the plane, and does not cut the plane
        ray = new Ray(new Point(0, -2, 0), new Vector(3, -4, 0));
        assertNull(plane.findIntersections(ray), "ray does not intersect plane");


        // =============== Boundary Values Tests ==================
        // TC03  ray is parallel to plane (0 points)
        ray = new Ray(new Point(0, -2, 0), new Vector(0, 0, 1));
        assertNull(plane.findIntersections(ray), "ray is parallel to plane");

        // TC04  ray is parallel to plane and is included in plane (0 points)
        ray = new Ray(new Point(2, 0, 0), new Vector(1, 0, 0));
        assertNull(plane.findIntersections(ray), "ray is parallel to plane and is included in plane");

        // TC05  ray is orthogonal to plane and starts before plane (1 point)
        ray = new Ray(new Point(0, -2, 0), new Vector(0, 1, 0));
        assertEquals(1, plane.findIntersections(ray).size(), "ray is orthogonal to plane and starts before plane");
        assertEquals(new Point(0, 0, 0), plane.findIntersections(ray).get(0), "ray is orthogonal to plane and starts before plane");

        // TC06  ray is orthogonal to plane and starts in plane (0 points)
        ray = new Ray(new Point(2, 0, 0), new Vector(0, 1, 0));
        assertNull(plane.findIntersections(ray), "ray is orthogonal to plane and starts in plane");

        // TC07  ray is orthogonal to plane and starts after plane (0 points)
        ray = new Ray(new Point(-1, 1, 0), new Vector(0, 1, 0));
        assertNull(plane.findIntersections(ray), "ray is orthogonal to plane and starts after plane");

        // TC08  ray is neither orthogonal nor parallel to plane and starts in plane (0 points)
        ray = new Ray(new Point(2, 0, 0), new Vector(1, 1, 0));
        assertNull(plane.findIntersections(ray), "ray is neither orthogonal nor parallel to plane and starts in plane");

        // TC09  ray is neither orthogonal nor parallel to plane and starts in plane and starts at reference point (0 points)
        ray = new Ray(new Point(1, 0, 0), new Vector(1, 1, 0));
        assertNull(plane.findIntersections(ray), "ray is neither orthogonal nor parallel to plane and starts in plane and starts at reference point");

    }
}