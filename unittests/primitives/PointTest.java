package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Point class
 */
class PointTest {

    /**
     * Test method for {@link Point#subtract(Point)}.
     */
    @Test
    void subtract() {
        // ============ Equivalence Partitions Tests ==============
        // TC01 subtract two different points
        Point p1 = new Point(1, 2, 3);
        Point p2 = new Point(4, 5, 6);
        Vector v = new Vector(3, 3, 3);
        assertEquals(v, p2.subtract(p1), "wrong subtract for different points");

        // TC02 subtract from zero point
        p1 = new Point(0, 0, 0);
        p2 = new Point(1, 2, 3);
        v = new Vector(-1, -2, -3);
        assertEquals(v, p1.subtract(p2), "wrong subtract from zero point");

        // =============== Boundary Values Tests ==================
        // TC03 subtracting point from itself
        Point p3 = new Point(1, 2, 3);
        assertThrows(IllegalArgumentException.class, () -> p3.subtract(p3), "can't subtract point from itself");
    }

    /**
     * Test method for {@link Point#add(Vector)}.
     */
    @Test
    void add() {
        // ============ Equivalence Partitions Tests ==============
        // TC01 add two different positive points
        Point p1 = new Point(1, 2, 3);
        Vector v = new Vector(4, 5, 6);
        Point p3 = new Point(5, 7, 9);
        assertEquals(p3, p1.add(v));

        // TC02 add two different points (one positive and one negative)
        p1 = new Point(1, 2, 3);
        v = new Vector(-4, -5, -6);
        p3 = new Point(-3, -3, -3);
        assertEquals(p3, p1.add(v));
    }

    /**
     * Test method for {@link Point#distance(Point)}.
     */
    @Test
    void distance() {
        // ============ Equivalence Partitions Tests ==============
        // TC01 distance between two different points
        Point p0 = new Point(0, 0, 0);
        Point p1 = new Point(1, 0, 0);
        assertEquals(p0.distance(p1), 1, "distance of points is incorrect");

        // =============== Boundary Values Tests ==================
        // TC02 distance from point to itself
        assertEquals(p0.distance(p0), 0, "distance of point to itself is not 0");
    }

    /**
     * Test method for {@link Point#distanceSquared(Point)}.
     */
    @Test
    void distanceSquared() {
        // ============ Equivalence Partitions Tests ==============
        // TC01 distance squared between two different points
        Point p0 = new Point(1,0, 0);
        Point p1 = new Point(2 ,0,  0);
        assertEquals(p0.distanceSquared(p1), 3, "distance of points is incorrect");

        // =============== Boundary Values Tests ==================
        // TC02 distance squared from point to itself
        assertEquals(p0.distanceSquared(p0), 0, "distance of point to itself is not 0");
    }
}