package primitives;

/**
 * A fundamental object in geometry that has a defined direction and size
 * by the end point.
 */
public class Vector extends Point {

    /**
     * A constructor that initializes a vector by Double3 object.
     *
     * @param xyz
     */
    Vector(Double3 xyz) {
        super(xyz);

        if (xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("Invalid vector - (0,0,0)");
    }

    /**
     * A constructor that initializes a vector by 3 points.
     *
     * @param x
     * @param y
     * @param z
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);

        Double3 tmp = new Double3(x, y, z);
        if (tmp.equals(Double3.ZERO))
            throw new IllegalArgumentException("Invalid vector - (0,0,0)");
    }

    /**
     * Vector connection.
     *
     * @param secoude Vector
     * @return new Vector
     */
    public Vector add(Vector v) {
        return new Vector(xyz.add(v.xyz));
    }

    /**
     * Multiply a vector by a number.
     *
     * @param d
     * @return new Vector
     */
    public Vector scale(double d) {
        return new Vector(xyz.scale(d));
    }

    /**
     * vector multiplication - returns a new vector.
     *
     * @param v
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
     * @return
     */
    public double length() {
        return Math.sqrt(dotProduct(this));
    }

    /**
     * A normalization operation that returns a new normalized vector.
     *
     * @return
     */
    public Vector normalize() {
        return scale(1 / length());
    }

    /**
     * scalar product.
     *
     * @param v
     * @return the rezolte number
     */
    public double dotProduct(Vector v) {
        return (xyz.d1 * v.xyz.d1 + xyz.d2 * v.xyz.d2 + xyz.d3 * v.xyz.d3);
    }

    @Override
    public String toString() {
        return "-> " + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

}
