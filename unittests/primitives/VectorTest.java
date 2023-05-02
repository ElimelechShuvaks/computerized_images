package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests class for Vector class.
 */
class VectorTest {

    /**
     * Test method for {@link Vector#add(Vector)}.
     */
    @Test
    void add() {
        // ============ Equivalence Partitions Tests ==============
        // TC01 - normal test case
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(4, 5, 6);
        Vector v3 = v1.add(v2);
        assertEquals(new Vector(5, 7, 9), v3, "add() wrong result");

        // TC02 - test case for negative number
        Vector v4 = v1.add(new Vector(-2, -3, -4));
        assertEquals(new Vector(-1, -1, -1), v4, "add() wrong result");
    }

    /**
     * Test method for {@link primitives.Vector#subtract(primitives.Vector)}.
     */
    @Test
    void subtract() {
        // ============ Equivalence Partitions Tests ==============
        // TC01 - normal test case
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(2, 3, 4);
        Vector v3 = v1.subtract(v2);
        assertEquals(new Vector(-1, -1, -1), v3, "subtract() wrong result");

        // TC02 - test case for negative number
        Vector v4 = v1.subtract(new Vector(-2, -3, -4));
        assertEquals(new Vector(3, 5, 7), v4, "subtract() wrong result");

        // =============== Boundary Values Tests ==================
        // TC03 - test case for zero vector
        assertThrows(IllegalArgumentException.class, () -> v1.subtract(new Vector(1, 2, 3)), "subtract() for zero vector does not throw an exception");
    }

    /**
     * Test method for {@link Vector#scale(double)}.
     */
    @Test
    void scale() {
        // ============ Equivalence Partitions Tests ==============

        // TC01 - normal test case
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = v1.scale(2.4);
        assertEquals(new Vector(2.4, 4.8, 7.2), v2, "scale() wrong result");

        // TC02 - test case for negative scale
        Vector v3 = v1.scale(-2.4);
        assertEquals(new Vector(-2.4, -4.8, -7.2), v3, "scale() wrong result");

        // =============== Boundary Values Tests ==================
        // TC03 - test case for zero scale
        assertThrows(IllegalArgumentException.class, () -> v1.scale(0),"scale() for zero scale does not throw an exception");
    }

    /**
     * Test method for {@link Vector#crossProduct(Vector)}.
     */
    @Test
    void crossProduct() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v2);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals(v1.length() * v2.length(), vr.length(), 0.00001, "crossProduct() wrong result length");

        // TC02: Test cross-product result orthogonality to its operands
        assertEquals(0, vr.dotProduct(v1), 0.00001, "crossProduct() result is not orthogonal to 1st operand");
        assertEquals(0, vr.dotProduct(v2), 0.00001, "crossProduct() result is not orthogonal to 1st operand");

        // =============== Boundary Values Tests ==================
        // TC03: test zero vector from cross-product of co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v3),
                "crossProduct() for parallel vectors does not throw an exception");
    }

    /**
     * Test method for {@link Vector#lengthSquared()}.
     */
    @Test
    void lengthSquared() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: test that check the squared length
        Vector v1 = new Vector(1, 2, 3);
        double length = v1.lengthSquared();
        assertEquals(14, length, 0.00001, "lengthSquared() wrong result");
    }

    /**
     * Test method for {@link Vector#length()}.
     */
    @Test
    void length() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: test that check the vector length
        Vector v1 = new Vector(1, 2, 3);
        double length = v1.length();
        assertEquals(Math.sqrt(14), length, 0.00001, "length() wrong result");
    }

    /**
     * Test method for {@link Vector#normalize()}.
     */
    @Test
    void normalize() {
        Vector v = new Vector(0, 3, 4);
        Vector n = v.normalize();
        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        assertEquals(1, n.lengthSquared(), 0.00001, "wrong normalized vector length");
        assertThrows(IllegalArgumentException.class, () -> v.crossProduct(n),
                "normalized vector is not in the same direction");
        assertEquals(new Vector(0, 0.6, 0.8), n, "wrong normalized vector");
    }

    /**
     * Test method for {@link Vector#dotProduct(Vector)}.
     */
    @Test
    void dotProduct() {
        // ============ Equivalence Partitions Tests ==============
        // TC01 - normal test case - acute angle
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(2, 3, 4);
        double dot = v1.dotProduct(v2);
        assertEquals(20, dot, 0.00001, "dotProduct() wrong result");

        // TC02 - normal test case - obtuse angle
        v2 = new Vector(-3,-5,-1);
        dot = v1.dotProduct(v2);
        assertEquals(-16, dot, 0.00001, "dotProduct() wrong result");


        // =============== Boundary Values Tests ==================
        // TC03 - test case for orthogonal vectors
        Vector v3 = new Vector(0, 3, -2);
        assertEquals(0, v1.dotProduct(v3), 0.00001, "dotProduct() of orthogonal vectors is not zero");
    }
}