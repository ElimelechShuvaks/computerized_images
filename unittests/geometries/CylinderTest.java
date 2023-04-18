package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for Cylinder class
 */
class CylinderTest {

    /**
     * Test method for {@link Cylinder#getNormal(Point)}.
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // There is a simple single test here
        Cylinder pl = new Cylinder(1, new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)), 2);

        // TC01: Checks if this is in the side
        assertEquals(new Vector(0, 0, 1), pl.getNormal(new Point(0, 1, 0)), "Bad normal to Cylinder");
        // TC02: Checks if this is in the down base
        assertEquals(new Vector(0, 0, 1), pl.getNormal(new Point(0, 1, 2)), "Bad normal to Cylinder");
        // TC03: Checks if this is in the up base
        assertEquals(new Vector(0, 1, 0), pl.getNormal(new Point(0, 1, 1)), "Bad normal to Cylinder");

        // =============== Boundary Values Tests ==================
        // TC04: Checks if this is in the down base center
        assertEquals(new Vector(0, 0, 1), pl.getNormal(new Point(0, 0, 0)), "Bad normal to Cylinder");
        // TC05: Checks if this is in the up base center
        assertEquals(new Vector(0, 0, 1), pl.getNormal(new Point(0, 0, 2)), "Bad normal to Cylinder");
    }
}