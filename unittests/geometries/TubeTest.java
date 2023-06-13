package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for Plane class
 */
class TubeTest {

    /**
     * Test method for {@link Tube#getNormal(Point)}.
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Tube pl = new Tube(1, new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)));
        assertEquals(new Vector(0, 1, 0), pl.getNormal(new Point(0, 1, 1)), "Bad normal to Tube");

        // =============== Boundary Values Tests ==================
        // TC02:  point that lies on the top of the tube but at the edge
        assertEquals(new Vector(0, 1, 0), pl.getNormal(new Point(0, 1, 0)), "Bad normal to Tube");
    }
}