package primitives;

/**
 * A fundamental object in geometry that has a defined direction and size
 * by the end point.
 */
public class Vector extends Point {

    /**
     * A constructor that initializes a vector by Double3 object.
     *
     * @param xyz the Double3 object that containing the Vector coordinates
     */
    Vector(Double3 xyz) {
        super(xyz);
        if (xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("Invalid vector - (0,0,0)");
    }

    /**
     * A constructor that initializes a vector by 3 points.
     *
     * @param x first coordinate value
     * @param y second coordinate value
     * @param z third coordinate value
     */
    public Vector(double x, double y, double z) {
        super(x, y, z); // this(new Double3(x, y, z);
        if (xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("Invalid vector - (0,0,0)");
    }

    /**
     * add two vectors
     *
     * @param v the another vector
     * @return new vector that is the result adding
     */
    public Vector add(Vector v) {
        return new Vector(xyz.add(v.xyz));
    }

    /**
     * scaling the vector by scalar
     *
     * @param d the scalar
     * @return new vector that is the scaled vector
     */
    public Vector scale(double d) {
        return new Vector(xyz.scale(d));
    }

    /**
     * vector multiplication - returns a new vector.
     *
     * @param v the another vector
     * @return new Vector
     */
    public Vector crossProduct(Vector v) {
        return new Vector(xyz.d2 * v.xyz.d3 - xyz.d3 * v.xyz.d2,
                xyz.d3 * v.xyz.d1 - xyz.d1 * v.xyz.d3,
                xyz.d1 * v.xyz.d2 - xyz.d2 * v.xyz.d1);
    }

    /**
     * Calculation of the squared length of the vector.
     *
     * @return the rezolte number
     */
    public double lengthSquared() {
        return dotProduct(this);
    }

    /**
     * Calculation of the length of the vector.
     *
     * @return the length of the vector
     */
    public double length() {
        return Math.sqrt(dotProduct(this));
    }

    /**
     * A normalization operation that returns a new normalized vector.
     *
     * @return new normalized vector
     */
    public Vector normalize() {
        return scale(1 / length());
    }

    /**
     * scalar product.
     *
     * @param v the another vector
     * @return the rezolte number
     */
    public double dotProduct(Vector v) {
        return (xyz.d1 * v.xyz.d1 + xyz.d2 * v.xyz.d2 + xyz.d3 * v.xyz.d3);
    }

    @Override
    public String toString() {
        return "-> " + xyz;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Vector)
            return super.equals(obj);
        return false;
    }

}
