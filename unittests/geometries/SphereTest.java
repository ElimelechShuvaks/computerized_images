package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

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
}