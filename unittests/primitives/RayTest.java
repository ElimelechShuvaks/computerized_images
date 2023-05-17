package primitives;

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
}