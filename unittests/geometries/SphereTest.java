package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Sphere class
 */
class SphereTest {

    /**
     * Test method for {@link Sphere#getNormal(primitives.Point)}.
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01 get normal of point on sphere
        Sphere s1 = new Sphere (1, new Point(0,0,0));
        Vector normal = new Vector(1,0,0);
        assertEquals(normal, s1.getNormal(new Point(1,0,0)), "Bad normal for sphere");
    }

    /**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Sphere sphere = new Sphere(1d, new Point (1, 0, 0));

        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(
                new Point(-1, 0, 0), new Vector(1, 1, 0))), "Ray's line out of sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point p1 = new Point(0.0651530771650466, 0.355051025721682, 0);
        Point p2 = new Point(1.53484692283495, 0.844948974278318, 0);
        List<Point> result = sphere.findIntersections(new Ray(new Point(-1, 0, 0),
                new Vector(3, 1, 0)));
        assertEquals(2, result.size(), "Wrong number of points");
        assertTrue(List.of(p1, p2).equals(result) || List.of(p2, p1).equals(result), "Ray crosses sphere at two points");

        // TC03: Ray starts inside the sphere (1 point)
        Point intersectionPoint = new Point(0,0,0);
        result = sphere.findIntersections(new Ray(new Point(1.5, 0, 0), new Vector(-1.5, 0, 0)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(intersectionPoint, result.get(0),"Ray starts inside the sphere");

        // TC04: Ray starts after the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(3, 0, 0), new Vector(1, 1, 0))),
                "Ray starts after the sphere");

        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC05: Ray starts at sphere and goes inside (1 point)
        Point p3 = new Point(1,1,0);
        result = sphere.findIntersections(new Ray(new Point(0, 0, 0), new Vector(1, 1, 0)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(p3, result.get(0), "Ray starts at sphere and goes inside");

        // TC06: Ray starts at sphere and goes outside (0 points)
        result = sphere.findIntersections(new Ray(new Point(0, 0, 0), new Vector(-1, -1, 0)));
        assertNull(result, "Wrong number of points");

        // **** Group: Ray's line goes through the center
        // TC07: Ray starts before the sphere (2 points)
        p1 = new Point(0,0,0);
        p2 = new Point(2,0,0);
        result = sphere.findIntersections(new Ray(new Point(3, 0, 0), new Vector(-3, 0, 0)));
        assertEquals(2, result.size(), "Wrong number of points");

        assertTrue(List.of(p1, p2).equals(result) || List.of(p2, p1).equals(result),
                "Ray starts before sphere and goes inside through the center");

        // TC08: Ray starts at sphere and goes inside (1 point)
        Point p = new Point(0,0,0);
        result = sphere.findIntersections(new Ray(new Point(2, 0, 0), new Vector(-3, 0, 0)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(p, result.get(0), "Ray starts at sphere and goes inside, passing through center");

        // TC09: Ray starts inside (1 point)
        result = sphere.findIntersections(new Ray(new Point(1.5, 0, 0), new Vector(-3, 0, 0)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(p, result.get(0), "Ray starts inside sphere, passing through center");

        // TC10: Ray starts at the center (1 point)
        result = sphere.findIntersections(new Ray(new Point(1, 0, 0), new Vector(-3, 0, 0)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(p, result.get(0), "Ray starts at center of the sphere");

        // TC11: Ray starts at sphere and goes outside (0 points)
        result = sphere.findIntersections(new Ray(new Point(2, 0, 0), new Vector(3, 0, 0)));
        assertNull(result, "Wrong number of points");

        // TC12: Ray starts after sphere (0 points)
        result = sphere.findIntersections(new Ray(new Point(3, 0, 0), new Vector(4, 0, 0)));
        assertNull(result, "Wrong number of points");

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC13: Ray starts before the tangent point
        result = sphere.findIntersections(new Ray(new Point(0, 2, 0), new Vector(0, -2, 0)));
        assertNull(result, "Wrong number of points");

        // TC14: Ray starts at the tangent point
        result = sphere.findIntersections(new Ray(new Point(0, 0, 0), new Vector(0, -2, 0)));
        assertNull(result, "Wrong number of points");

        // TC15: Ray starts after the tangent point
        result = sphere.findIntersections(new Ray(new Point(0, 1, 0), new Vector(0, 2, 0)));
        assertNull(result, "Wrong number of points");

        // **** Group: Special cases
        // TC16: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        result = sphere.findIntersections(new Ray(new Point(1, 2, 0), new Vector(0, 0, 1)));
        assertNull(result, "Wrong number of points");

    }
}